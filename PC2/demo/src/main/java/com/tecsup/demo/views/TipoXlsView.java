package com.tecsup.demo.views;

import java.util.List;
import java.util.Map;

import com.tecsup.demo.modelo.entidades.Tipo;

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

@Component("tipo/ver.xlsx")
public class TipoXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"tipo_view.xlsx\"");
        List<Tipo> cursos = (List<Tipo>) model.get("tipos");
        Sheet sheet = workbook.createSheet("Lista de tipos");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de tipos");

        row = sheet.createRow(1);
        cell = row.createCell(0);

        // Estilos para el encabezado
        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Estilos para el cuerpo de la tabla
        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        // Crear encabezados
        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("Nombre");
        header.createCell(1).setCellValue("Marca");
        header.createCell(2).setCellValue("Modelo");
        header.createCell(3).setCellValue("Año");
        header.createCell(4).setCellValue("Precio");
        header.createCell(5).setCellValue("Combustible");
        header.createCell(6).setCellValue("Kilometraje");
        header.createCell(7).setCellValue("Color");
        header.createCell(8).setCellValue("Garantía");

        // Aplicar estilo a los encabezados
        for (int i = 0; i <= 8; i++) {
            header.getCell(i).setCellStyle(theaderStyle);
        }

        // Rellenar datos
        int rownum = 6;

        for (Tipo tipo : cursos) {
            Row fila = sheet.createRow(rownum++);
            cell = fila.createCell(0);
            cell.setCellValue(tipo.getNombre());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(tipo.getMarca());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(tipo.getModelo());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            cell.setCellValue(tipo.getAnio());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(tipo.getPrecio());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(5);
            cell.setCellValue(tipo.getCombustible());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(6);
            cell.setCellValue(tipo.getKilometraje());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(7);
            cell.setCellValue(tipo.getColor());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(8);
            cell.setCellValue(tipo.getGarantia());
            cell.setCellStyle(tbodyStyle);
        }
    }
}
