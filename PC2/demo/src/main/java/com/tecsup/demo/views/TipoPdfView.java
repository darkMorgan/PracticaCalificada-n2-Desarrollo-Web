package com.tecsup.demo.views;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Tipo; // Asegúrate de que esta clase existe y tiene los campos que necesitas
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("tipo/ver.pdf")
public class TipoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Obtener la lista de tipos del modelo
        List<Tipo> tipos = (List<Tipo>) model.get("tipos");

        // Comprobar si la lista es nula o vacía
        if (tipos == null || tipos.isEmpty()) {
            // Manejar el caso en que no hay tipos
            document.add(new Phrase("No hay tipos disponibles."));
            return; // Salir del método si no hay datos
        }

        // Crear tabla principal
        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Tipos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        // Crear tabla de datos
        PdfPTable tabla2 = new PdfPTable(10); // Cambia a 10 columnas
        tabla2.addCell("ID");
        tabla2.addCell("Nombre");
        tabla2.addCell("Marca");
        tabla2.addCell("Modelo");
        tabla2.addCell("Año");
        tabla2.addCell("Precio");
        tabla2.addCell("Combustible");
        tabla2.addCell("Kilometraje");
        tabla2.addCell("Color");
        tabla2.addCell("Garantía");

        // Agregar los datos a la tabla
        for (Tipo tipo : tipos) {
            tabla2.addCell(String.valueOf(tipo.getId())); // Agrega el ID
            tabla2.addCell(tipo.getNombre());
            tabla2.addCell(tipo.getMarca());
            tabla2.addCell(tipo.getModelo());
            tabla2.addCell(String.valueOf(tipo.getAnio()));
            tabla2.addCell(String.valueOf(tipo.getPrecio()));
            tabla2.addCell(tipo.getCombustible());
            tabla2.addCell(String.valueOf(tipo.getKilometraje()));
            tabla2.addCell(tipo.getColor());
            tabla2.addCell(tipo.getGarantia());
        }

        // Añadir tablas al documento
        document.add(tabla);
        document.add(tabla2);
    }
}
