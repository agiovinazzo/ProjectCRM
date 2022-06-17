package projectCRM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectCRM.exception.ResourceNotFoundException;
import projectCRM.form.CustomerForm;
import projectCRM.model.CustomerEntity;
import projectCRM.repository.CustomerRepository;
import projectCRM.repository.OfferRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}


	public CustomerEntity saveCustomer(CustomerEntity customerEntity) {

		return customerRepository.save(customerEntity);
	}


	public List<CustomerEntity> getAllCustomers() {

		return customerRepository.findAll();
	}


	public CustomerEntity getCustomerById(Integer id) {

		return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
	}


	public CustomerEntity updateCustomer(CustomerEntity customerEntity, Integer id) {
		CustomerEntity updatedCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));

		updatedCustomer.setFullName(customerEntity.getFullName());
		updatedCustomer.setBirthDate(customerEntity.getBirthDate());
		updatedCustomer.setAddress(customerEntity.getAddress());
		updatedCustomer.setTelephone(customerEntity.getTelephone());
		updatedCustomer.setEmail(customerEntity.getEmail());
		updatedCustomer.setOfferId(customerEntity.getOfferId());

		customerRepository.save(updatedCustomer);

		return updatedCustomer;
	}


	public void deleteCustomer(Integer id) {
		customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
		customerRepository.deleteById(id);		
	}


	public CustomerEntity saveCustomer(CustomerForm customerForm) {

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFullName(customerForm.getFullName());
		customerEntity.setBirthDate(customerForm.getBirthDate());
		customerEntity.setAddress(customerForm.getAddress());
		customerEntity.setTelephone(customerForm.getTelephone());
		customerEntity.setEmail(customerForm.getEmail());
		customerEntity.setOfferId(offerRepository.findById(customerForm.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", customerForm.getOfferId())));

		return customerRepository.save(customerEntity);
	}


	@Transactional
	public CustomerEntity updateCustomer(CustomerForm customerForm, Integer id) {

		try {
			CustomerEntity updatedCustomer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));

			updatedCustomer.setFullName(customerForm.getFullName());
			updatedCustomer.setBirthDate(customerForm.getBirthDate());
			updatedCustomer.setAddress(customerForm.getAddress());
			updatedCustomer.setTelephone(customerForm.getTelephone());
			updatedCustomer.setEmail(customerForm.getEmail());
			updatedCustomer.setOfferId(offerRepository.findById(customerForm.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offerta", "id", customerForm.getOfferId())));

			customerRepository.save(updatedCustomer);


			return updatedCustomer;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
