package presentation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import presentation.document.CustomerDocument;

import java.util.List;

public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {

    CustomerDocument findByFirstName(String firstName);
    List<CustomerDocument> findByLastName(String lastName);

}
