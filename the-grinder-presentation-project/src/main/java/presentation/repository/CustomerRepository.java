package presentation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import presentation.document.CustomerDocument;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {

    CustomerDocument findByFirstName(String firstName);
    List<CustomerDocument> findByLastName(String lastName);
    List<CustomerDocument> findByTag(String tag);

}
