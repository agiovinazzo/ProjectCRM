package projectCRM.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "offers")
@Data
public class OfferEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private Integer offerId;
	
	@Column(name = "desc_offer")
	@NotBlank(message = "Inserire il nome dell'offerta.")
	@Size(min = 2, max = 15)
	private String descOffer;
	
	@NotNull(message = "Inserire la data di inizio dell'offerta")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "starting")
	private LocalDate start;
	
	@NotNull(message = "Inserire la data di fine dell'offerta")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "ending")
	private LocalDate end;
}
