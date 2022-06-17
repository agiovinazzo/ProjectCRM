package projectCRM.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QuotationForm {

	
	private Integer quotationId;
	private String code;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate quotationDate;
	private Double price;
	private Integer offerId;
	private Integer customerId;
}
