package presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import presentation.document.CustomerDocument;
import presentation.domain.Customer;
import presentation.repository.CustomerRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDocument> list() {
        return customerRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{customerId:.+}")
    public CustomerDocument findCustomer(@PathVariable final String customerId) {
        return customerRepository.findOne(customerId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{customerId:.+}")
    public void deleteCustomer(@PathVariable final String customerId) {
        CustomerDocument customerToBeDeleted = new CustomerDocument();
        customerToBeDeleted.setId(customerId);
        customerRepository.delete(customerToBeDeleted);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CustomerDocument createCustomer(@RequestBody final Customer customer) {
        CustomerDocument customerDocument = new CustomerDocument(customer.getFirstName(), customer.getLastName());
        return customerRepository.save(customerDocument);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerDocument updateCustomer(@RequestBody final Customer customer) {
        CustomerDocument customerDocument = new CustomerDocument(customer.getId(), customer.getFirstName(), customer.getLastName());
        return customerRepository.save(customerDocument);
    }

}
