package projectCRM.excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import projectCRM.model.QuotationEntity;

public class QuotationExcel {

	private XSSFWorkbook  workbook;
	private XSSFSheet sheet;

	private List<QuotationEntity> listQuotation;




	public QuotationExcel(List<QuotationEntity> listQuotation) {
		super();
		this.listQuotation = listQuotation;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Preventivi");
	}





	private void writeHeaderRow() {
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("ID Preventivo");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Codice");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Data Preventivo");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Offerta");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("Cliente");
		cell.setCellStyle(style);
	}

	private void writeDataRows() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for(QuotationEntity q : listQuotation) {
			Row row = sheet.createRow(rowCount++);

			Cell cell = row.createCell(0);
			cell.setCellValue(q.getQuotationId().toString());
			sheet.autoSizeColumn(0);
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(q.getCode());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(q.getQuotationDate().toString());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(q.getOfferId().getDescOffer().toString());
			sheet.autoSizeColumn(3);
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(q.getCustomerId().getFullName().toString());
			sheet.autoSizeColumn(4);
			cell.setCellStyle(style);
		}

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDataRows();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}