package projectCRM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectCRM.exception.ResourceNotFoundException;
import projectCRM.form.QuotationForm;
import projectCRM.model.QuotationEntity;
import projectCRM.repository.CustomerRepository;
import projectCRM.repository.OfferRepository;
import projectCRM.repository.QuotationRepository;

@Service
public class QuotationService {

	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;



	public QuotationEntity saveQuotation(QuotationEntity quotationEntity) {

		return quotationRepository.save(quotationEntity);
	}


	public List<QuotationEntity> getAllQuotations() {

		return quotationRepository.findAll();
	}


	public QuotationEntity getQuotationById(Integer id) {

		return quotationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Preventivo", "id", id));
	}


	public QuotationEntity updateQuotation(QuotationEntity quotationEntity, Integer id) {
		QuotationEntity updatedQuotation = quotationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Preventivo", "id", id));

		updatedQuotation.setCode(quotationEntity.getCode());
		updatedQuotation.setQuotationDate(quotationEntity.getQuotationDate());
		updatedQuotation.setPrice(quotationEntity.getPrice());
		updatedQuotation.setOfferId(quotationEntity.getOfferId());
		updatedQuotation.setCustomerId(quotationEntity.getCustomerId());

		quotationRepository.save(updatedQuotation);

		return updatedQuotation;
	}


	public void deleteQuotation(Integer id) {
		quotationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Preventivo", "id", id));
		quotationRepository.deleteById(id);		
	}


	public QuotationEntity saveQuotation(QuotationForm quotationForm) {

		QuotationEntity quotationEntity = new QuotationEntity();
		quotationEntity.setCode(quotationForm.getCode());
		quotationEntity.setQuotationDate(quotationForm.getQuotationDate());
		quotationEntity.setPrice(quotationForm.getPrice());
		quotationEntity.setOfferId(offerRepository.findById(quotationForm.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", quotationForm.getOfferId())));
		quotationEntity.setCustomerId(customerRepository.findById(quotationForm.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", quotationForm.getCustomerId())));

		return quotationRepository.save(quotationEntity);
	}


	@Transactional
	public QuotationEntity updateQuotation(QuotationForm quotationForm, Integer id) {

		try {
			QuotationEntity updatedQuotation = quotationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Preventivo", "id", id));

			updatedQuotation.setCode(quotationForm.getCode());
			updatedQuotation.setQuotationDate(quotationForm.getQuotationDate());
			updatedQuotation.setPrice(quotationForm.getPrice());
			updatedQuotation.setOfferId(offerRepository.findById(quotationForm.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", quotationForm.getOfferId())));
			updatedQuotation.setCustomerId(customerRepository.findById(quotationForm.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", quotationForm.getCustomerId())));
			
			quotationRepository.save(updatedQuotation);


			return updatedQuotation;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
