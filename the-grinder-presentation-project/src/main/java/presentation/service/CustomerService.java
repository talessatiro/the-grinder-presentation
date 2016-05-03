package presentation.service;

import org.springframework.cache.annotation.Cacheable;
import presentation.document.CustomerDocument;

import java.util.List;

public interface CustomerService {

    CustomerDocument createCustomer(CustomerDocument customerDocument);

    CustomerDocument editCustomer(CustomerDocument customerDocument);

    void removeCustomer(String customerId);

    void removeCustomers(List<CustomerDocument> customerDocuments);

    CustomerDocument findCustomer(String customerId);

    @Cacheable("customers")
    List<CustomerDocument> listCustomers();

    List<CustomerDocument> listCustomersByTag(String tag);
}
