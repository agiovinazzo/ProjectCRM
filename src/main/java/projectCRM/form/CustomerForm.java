package projectCRM.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CustomerForm {
	
	private Integer customerId;
	private String fullName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private String address;
	private String telephone;
	private String email;
	private Integer offerId;

}
