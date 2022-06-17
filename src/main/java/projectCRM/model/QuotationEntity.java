package projectCRM.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "quotation")
@Data
public class QuotationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_id")
	private Integer quotationId;


	@NotBlank(message = "Inserire il codice del preventivo.")
	private String code;

	@Column(name = "quotation_date")
	@NotNull(message = "Inserire la data del preventivo.")
	private LocalDate quotationDate;


	@NotNull(message = "Inserire il prezzo riportato sul preventivo.")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "offer_id")
	private OfferEntity offerId;


	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customerId;

}
