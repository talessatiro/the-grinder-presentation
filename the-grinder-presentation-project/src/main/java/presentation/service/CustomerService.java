package presentation.service;

import presentation.document.CustomerDocument;

import java.util.List;

public interface CustomerService {

    CustomerDocument createCustomer(CustomerDocument customerDocument);

    CustomerDocument editCustomer(CustomerDocument customerDocument);

    void removeCustomer(String customerId);

    void removeCustomers(List<CustomerDocument> customerDocuments);

    CustomerDocument findCustomer(String customerId);

    List<CustomerDocument> listCustomers();

    List<CustomerDocument> listCustomersByTag(String tag);
}
