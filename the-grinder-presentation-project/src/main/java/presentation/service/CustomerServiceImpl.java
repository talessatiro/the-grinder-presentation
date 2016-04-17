package presentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import presentation.document.CustomerDocument;
import presentation.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    @Transactional
    public CustomerDocument createCustomer(CustomerDocument customerDocument) {
        return this.customerRepository.save(customerDocument);
    }

    @Override
    @Transactional
    public CustomerDocument editCustomer(CustomerDocument customerDocument) {
        return this.customerRepository.save(customerDocument);
    }

    @Override
    @Transactional
    public void removeCustomer(String customerId) {
        this.customerRepository.delete(customerId);
    }

    @Override
    @Transactional
    public CustomerDocument findCustomer(String customerId) {
        return this.customerRepository.findOne(customerId);
    }

    @Override
    @Transactional
    public List<CustomerDocument> listCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public void clearCustomers() {
        this.customerRepository.deleteAll();
    }
}
