package presentation.service;

import presentation.document.CustomerDocument;

import java.util.List;

public interface CustomerService {

    CustomerDocument createCustomer(CustomerDocument customerDocument);

    CustomerDocument editCustomer(CustomerDocument customerDocument);

    void removeCustomer(String customerId);

    CustomerDocument findCustomer(String customerId);

    List<CustomerDocument> listCustomers();

    void clearCustomers();
}
