package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Sede; // Asegúrate de que esta clase existe y tiene los campos que necesitas

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("sede/ver.xlsx") // Cambia el nombre del componente a "sede/ver.xlsx"
public class SedeXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"sede_lista.xlsx\"");
        List<Sede> sedes = (List<Sede>) model.get("sedes");
        Sheet sheet = workbook.createSheet("Lista de Sedes");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Sedes");

        // Estilos para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);
        headerStyle.setBorderRight(BorderStyle.MEDIUM);
        headerStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Estilos para el cuerpo de la tabla
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);

        // Crear encabezados
        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Departamento");
        header.createCell(2).setCellValue("Distrito");
        header.createCell(3).setCellValue("Dirección");
        header.createCell(4).setCellValue("Disponibilidad");

        // Aplicar estilo a los encabezados
        for (int i = 0; i <= 4; i++) {
            header.getCell(i).setCellStyle(headerStyle);
        }

        // Rellenar datos
        int rowNum = 6;

        for (Sede sede : sedes) {
            Row fila = sheet.createRow(rowNum++);
            cell = fila.createCell(0);
            cell.setCellValue(sede.getId());
            cell.setCellStyle(bodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(sede.getDepartamento());
            cell.setCellStyle(bodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(sede.getDistrito());
            cell.setCellStyle(bodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(sede.getDireccion());
            cell.setCellStyle(bodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(sede.getDisponibilidad()); // Asumiendo que es un booleano o un string
            cell.setCellStyle(bodyStyle);
        }
    }
}
