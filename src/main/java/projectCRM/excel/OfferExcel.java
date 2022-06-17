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

import projectCRM.model.OfferEntity;

public class OfferExcel {

	private XSSFWorkbook  workbook;
	private XSSFSheet sheet;

	private List<OfferEntity> listOffers;




	public OfferExcel(List<OfferEntity> listOffers) {
		super();
		this.listOffers = listOffers;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Offerte");
	}





	private void writeHeaderRow() {
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("ID Offerta");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Nome Offerta");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Data Di Inizio");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Data Di Fine");
		cell.setCellStyle(style);
	}

	private void writeDataRows() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for(OfferEntity o : listOffers) {
			Row row = sheet.createRow(rowCount++);

			Cell cell = row.createCell(0);
			cell.setCellValue(o.getOfferId().toString());
			sheet.autoSizeColumn(0);
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(o.getDescOffer());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(o.getStart().toString());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(o.getEnd().toString());
			sheet.autoSizeColumn(3);
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