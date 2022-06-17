package projectCRM.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import projectCRM.excel.OfferExcel;
import projectCRM.model.OfferEntity;
import projectCRM.pdf.OfferPDF;
import projectCRM.service.OfferService;

@RestController
@RequestMapping("/offers")
public class OfferController {

	@Autowired
	private OfferService offerService;

	
	@PostMapping
	public ResponseEntity<OfferEntity> saveOffer(@RequestBody OfferEntity offerEntity) {
		return new ResponseEntity<OfferEntity>(offerService.saveOffer(offerEntity), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<OfferEntity> getAllOffers() {
		return offerService.getAllOffers();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OfferEntity> getOfferById(@PathVariable("id") Integer offerId) {
		return new ResponseEntity<OfferEntity>(offerService.getOfferById(offerId), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<OfferEntity> updateOffer(@PathVariable("id") Integer offerId, @RequestBody OfferEntity offerEntity) {
		return new ResponseEntity<OfferEntity>(offerService.updateOffer(offerEntity, offerId), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOffer(@PathVariable("id") Integer offerId) {
		offerService.deleteOffer(offerId);
		return new ResponseEntity<String>("Offerta eliminata con successo.", HttpStatus.OK);		
	}
	
	@GetMapping("/excel")
	public void exportToExcel (HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "offerte_" + currentDateTime + ".xlsx";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<OfferEntity> listOffers = offerService.getAllOffers();

		OfferExcel excelExporter = new OfferExcel(listOffers);
		excelExporter.export(response);
	}


	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "offerte_" + currentDateTime + ".pdf";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<OfferEntity> listOffers = offerService.getAllOffers();

		OfferPDF pdfExporter = new OfferPDF(listOffers);
		pdfExporter.export(response);

	}
}
