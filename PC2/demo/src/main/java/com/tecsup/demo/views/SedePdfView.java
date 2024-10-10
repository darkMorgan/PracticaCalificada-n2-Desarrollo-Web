package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Sede; // Asegúrate de que esta clase existe y tiene los campos que necesitas
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("sede/ver.pdf") // Cambia el nombre del componente a "sede/ver.pdf"
public class SedePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Obtener la lista de sedes del modelo
        List<Sede> sedes = (List<Sede>) model.get("sedes");

        // Comprobar si la lista es nula o vacía
        if (sedes == null || sedes.isEmpty()) {
            // Manejar el caso en que no hay sedes
            document.add(new Phrase("No hay sedes disponibles."));
            return; // Salir del método si no hay datos
        }

        // Crear tabla principal
        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Sedes"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        // Crear tabla de datos
        PdfPTable tabla2 = new PdfPTable(5); // Cambia a 5 columnas
        tabla2.addCell("ID"); // Encabezado ID
        tabla2.addCell("Departamento"); // Encabezado Departamento
        tabla2.addCell("Distrito"); // Encabezado Distrito
        tabla2.addCell("Dirección"); // Encabezado Dirección
        tabla2.addCell("Disponibilidad"); // Encabezado Disponibilidad

        // Agregar los datos a la tabla
        for (Sede sede : sedes) {
            tabla2.addCell(String.valueOf(sede.getId())); // Agrega el ID
            tabla2.addCell(sede.getDepartamento()); // Agrega el Departamento
            tabla2.addCell(sede.getDistrito()); // Agrega el Distrito
            tabla2.addCell(sede.getDireccion()); // Agrega la Dirección
            tabla2.addCell(String.valueOf(sede.getDisponibilidad())); // Asegúrate de que este campo sea booleano
        }

        // Añadir tablas al documento
        document.add(tabla);
        document.add(tabla2);
    }
}
