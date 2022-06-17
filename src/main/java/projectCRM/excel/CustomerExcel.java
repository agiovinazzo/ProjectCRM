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

import projectCRM.model.CustomerEntity;

public class CustomerExcel {

	private XSSFWorkbook  workbook;
	private XSSFSheet sheet;

	private List<CustomerEntity> listCustomers;




	public CustomerExcel(List<CustomerEntity> listCustomers) {
		super();
		this.listCustomers = listCustomers;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Clienti");
	}





	private void writeHeaderRow() {
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("ID Cliente");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Nome E Cognome");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Data Di Nascita");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Indirizzo");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("Telefono");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("E-mail");
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue("Offerta");
		cell.setCellStyle(style);
	}

	private void writeDataRows() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for(CustomerEntity c : listCustomers) {
			Row row = sheet.createRow(rowCount++);

			Cell cell = row.createCell(0);
			cell.setCellValue(c.getCustomerId());
			sheet.autoSizeColumn(0);
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(c.getFullName());
			sheet.autoSizeColumn(1);
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(c.getBirthDate().toString());
			sheet.autoSizeColumn(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(c.getAddress());
			sheet.autoSizeColumn(3);
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(c.getTelephone());
			sheet.autoSizeColumn(4);
			cell.setCellStyle(style);
			
			cell = row.createCell(5);
			cell.setCellValue(c.getEmail());
			sheet.autoSizeColumn(5);
			cell.setCellStyle(style);
			
			cell = row.createCell(6);
			cell.setCellValue(String.valueOf(c.getOfferId()));
			sheet.autoSizeColumn(6);
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