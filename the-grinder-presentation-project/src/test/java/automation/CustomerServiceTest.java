package automation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import presentation.Application;
import presentation.document.CustomerDocument;
import presentation.service.CustomerService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CustomerServiceTest {

	private static final String TEST_TAG = "TEST_DATA";

	@Autowired
	private CustomerService customerService;

	@After
	public void after(){
		List<CustomerDocument> customersToBeDeleted = customerService.listCustomersByTag(TEST_TAG);
		customerService.removeCustomers(customersToBeDeleted);
	}

	@Test
	@Rollback
	public void testCreateCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(applyTestTag(customerDocument));

		Assert.assertNotNull(customerDocument.getId());
	}

	@Test
	@Rollback
	public void testEditCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(applyTestTag(customerDocument));

		String customerId = customerDocument.getId();
		Assert.assertNotNull(customerId);

		String editedName = "Soares";
		customerDocument.setLastName(editedName);
		customerDocument = customerService.editCustomer(customerDocument);

		Assert.assertEquals(customerDocument.getId(), customerId);
		Assert.assertEquals(customerDocument.getLastName(), editedName);
	}

	@Test
	@Rollback
	public void testRemoveCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(applyTestTag(customerDocument));

		Assert.assertNotNull(customerDocument.getId());

		customerService.removeCustomer(customerDocument.getId());

		Assert.assertNull(customerService.findCustomer(customerDocument.getId()));

	}

	@Test
	@Rollback
	public void testListCustomers() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		CustomerDocument customerDocument2 = new CustomerDocument("Heitor", "Lira");
		customerDocument = customerService.createCustomer(applyTestTag(customerDocument));
		customerDocument2 = customerService.createCustomer(applyTestTag(customerDocument2));

		Assert.assertNotNull(customerDocument.getId());
		Assert.assertNotNull(customerDocument2.getId());

		Assert.assertEquals(2, customerService.listCustomersByTag(TEST_TAG).size());

	}

	@Test
	@Rollback
	public void testFindCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(applyTestTag(customerDocument));

		Assert.assertNotNull(customerDocument.getId());

		CustomerDocument customerDocumentFound = customerService.findCustomer(customerDocument.getId());
		Assert.assertEquals("Tales", customerDocumentFound.getFirstName());
		Assert.assertEquals("Satiro", customerDocumentFound.getLastName());
	}

	private CustomerDocument applyTestTag(CustomerDocument customerDocument){
		customerDocument.setTag(TEST_TAG);

		return customerDocument;
	}

}
