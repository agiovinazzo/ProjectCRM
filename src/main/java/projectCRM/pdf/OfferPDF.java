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

import projectCRM.model.OfferEntity;

public class OfferPDF {

private List<OfferEntity> listOffer;
	
	
	public OfferPDF(List<OfferEntity> listOffer) {
		super();
		this.listOffer = listOffer;
	}
	
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.YELLOW);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("ID Offerta", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Nome Offerta", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Data Di Inizio", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Data Di Fine", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table) {
		for(OfferEntity o : listOffer) {
			table.addCell(String.valueOf(o.getOfferId()));
			table.addCell(o.getDescOffer());
			table.addCell(o.getStart().toString());
			table.addCell(o.getEnd().toString());
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLACK);
		font.setSize(18);
		
		Paragraph title = new Paragraph("LE NOSTRE OFFERTE", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1.7f, 2.5f, 2.2f, 2.8f});
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		document.close();	
	}
}
