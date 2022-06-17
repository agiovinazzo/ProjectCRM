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

import projectCRM.excel.CustomerExcel;
import projectCRM.form.CustomerForm;
import projectCRM.model.CustomerEntity;
import projectCRM.pdf.CustomerPDF;
import projectCRM.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerForm customerForm) {


		return new ResponseEntity<CustomerEntity>(customerService.saveCustomer(customerForm), HttpStatus.CREATED);
	}

	@GetMapping
	public List<CustomerEntity> getAllCustomers() {
		return customerService.getAllCustomers();
	}


	@GetMapping("{id}")
	public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable("id") Integer customerId) {
		return new ResponseEntity<CustomerEntity>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerForm customerForm, @PathVariable("id") Integer customerId) {
		return new ResponseEntity<CustomerEntity>(customerService.updateCustomer(customerForm, customerId), HttpStatus.OK);
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer customerId) {
		customerService.deleteCustomer(customerId);

		return new ResponseEntity<String>("Cliente eliminato con successo.", HttpStatus.OK);		
	}


	@GetMapping("/excel")
	public void exportToExcel (HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "clienti_" + currentDateTime + ".xlsx";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<CustomerEntity> listCustomers = customerService.getAllCustomers();

		CustomerExcel excelExporter = new CustomerExcel(listCustomers);
		excelExporter.export(response);
	}


	@GetMapping("/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";


		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String fileName = "clienti_" + currentDateTime + ".pdf";

		String headerValue = "attachement; filename=" + fileName;

		response.setHeader(headerKey, headerValue);

		List<CustomerEntity> listCustomers = customerService.getAllCustomers();

		CustomerPDF pdfExporter = new CustomerPDF(listCustomers);
		pdfExporter.export(response);

	}
}