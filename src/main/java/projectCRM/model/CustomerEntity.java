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
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "full_name")
	@NotBlank(message = "Inserire il nome del cliente.")
	@Size(min = 2, max = 40)
	private String fullName;
	
	@Column(name = "birth_date")
	@NotNull(message = "Inserire la data di nascita del cliente.")
	private LocalDate birthDate;
	
	@NotBlank(message = "Inserire l'indirizzo del cliente.")
	private String address;
	
	@NotBlank(message = "Inserire il numero di telefono del cliente.")
	private String telephone;
	
	@NotBlank(message = "Inserire l'indirizzo e-mail del cliente.")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private OfferEntity offerId;
}
