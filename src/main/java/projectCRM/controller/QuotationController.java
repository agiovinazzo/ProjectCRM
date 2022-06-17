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

import projectCRM.excel.QuotationExcel;
import projectCRM.form.QuotationForm;
import projectCRM.model.QuotationEntity;
import projectCRM.pdf.QuotationPDF;
import projectCRM.service.QuotationService;

@RestController
@RequestMapping("/quotations")
public class QuotationController {
	
	
	@Autowired
	private QuotationService quotationService;

	

	@PostMapping
	public ResponseEntity<QuotationEntity> saveQuotation(@RequestBody QuotationForm quotationForm) {
		
		
		return new ResponseEntity<QuotationEntity>(quotationService.saveQuotation(quotationForm), HttpStatus.CREATED);
	}

	@GetMapping
	public List<QuotationEntity> getAllQuotations() {
		return quotationService.getAllQuotations();
	}


	@GetMapping("{id}")
	public ResponseEntity<QuotationEntity> getQuotationById(@PathVariable("id") Integer quotationId) {
		return new ResponseEntity<QuotationEntity>(quotationService.getQuotationById(quotationId), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<QuotationEntity> updateQuotation(@RequestBody QuotationForm quotationForm, @PathVariable("id") Integer quotationId) {
		return new ResponseEntity<QuotationEntity>(quotationService.updateQuotation(quotationForm, quotationId), HttpStatus.OK);
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteQuotation(@PathVariable("id") Integer quotationId) {
		quotationService.deleteQuotation(quotationId);
		
		return new ResponseEntity<String>("Preventivo eliminato con successo.", HttpStatus.OK);		
	}
	
	@GetMapping("/excel")
	public void exportToExcel (HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "preventivi_" + currentDateTime + ".xlsx";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<QuotationEntity> listQuotations = quotationService.getAllQuotations();

		QuotationExcel excelExporter = new QuotationExcel(listQuotations);
		excelExporter.export(response);
	}


	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "preventivi_" + currentDateTime + ".pdf";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<QuotationEntity> listQuotations = quotationService.getAllQuotations();

		QuotationPDF pdfExporter = new QuotationPDF(listQuotations);
		pdfExporter.export(response);
	}
}