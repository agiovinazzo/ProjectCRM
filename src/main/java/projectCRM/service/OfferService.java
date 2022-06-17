package projectCRM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectCRM.exception.ResourceNotFoundException;
import projectCRM.model.OfferEntity;
import projectCRM.repository.OfferRepository;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;





	public OfferEntity saveOffer(OfferEntity offerEntity) {

		return offerRepository.save(offerEntity);
	}



	public List<OfferEntity> getAllOffers() {

		return offerRepository.findAll();
	}



	public OfferEntity getOfferById(Integer id) {

		return offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", id));
	}



	public OfferEntity updateOffer(OfferEntity offerEntity, Integer id) {
		OfferEntity updatedOffer = offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", id));

		updatedOffer.setDescOffer(offerEntity.getDescOffer());
		updatedOffer.setStart(offerEntity.getStart());
		updatedOffer.setEnd(offerEntity.getEnd());

		offerRepository.save(updatedOffer);

		return updatedOffer;
	}



	public void deleteOffer(Integer id) {
		offerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", id));
		offerRepository.deleteById(id);
	}
}
