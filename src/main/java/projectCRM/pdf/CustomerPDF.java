package projectCRM.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import projectCRM.model.CustomerEntity;

public class CustomerPDF {

	private List<CustomerEntity> listCustomer;


	public CustomerPDF(List<CustomerEntity> listCustomer) {
		super();
		this.listCustomer = listCustomer;
	}


	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.YELLOW);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("ID Cliente", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nome E Cognome", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Data Di Nascita", font));
		table.addCell(cell);

		//		cell.setPhrase(new Phrase("Indirizzo", font));
		//		table.addCell(cell);

		cell.setPhrase(new Phrase("Telefono", font));
		table.addCell(cell);

//		cell.setPhrase(new Phrase("E-mail", font));
//		table.addCell(cell);

		cell.setPhrase(new Phrase("Offerta", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for(CustomerEntity c : listCustomer) {
			table.addCell(String.valueOf(c.getCustomerId()));
			table.addCell(c.getFullName());
			table.addCell(c.getBirthDate().toString());
			//table.addCell(c.getAddress());
			table.addCell(c.getTelephone());
			//table.addCell(c.getEmail());
			table.addCell(String.valueOf(c.getOfferId().getDescOffer()));

		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());


		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLACK);
		font.setSize(18);

		Paragraph title = new Paragraph("I NOSTRI CLIENTI", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {2.0f, 2.2f, 2.8f, 3.0f, 3.0f});

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);
		document.close();	
	}
}
