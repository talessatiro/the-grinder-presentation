package integration;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@After
	public void after(){
		customerService.clearCustomers();
	}

	@Test
	@Rollback
	public void testCreateCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(customerDocument);

		Assert.assertNotNull(customerDocument.getId());
	}

	@Test
	@Rollback
	public void testEditCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(customerDocument);

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
		customerDocument = customerService.createCustomer(customerDocument);

		Assert.assertNotNull(customerDocument.getId());

		customerService.removeCustomer(customerDocument.getId());

		Assert.assertNull(customerService.findCustomer(customerDocument.getId()));

	}

	@Test
	@Rollback
	public void testFindCustomers() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		CustomerDocument customerDocument2 = new CustomerDocument("Heitor", "Lira");
		customerDocument = customerService.createCustomer(customerDocument);
		customerDocument2 = customerService.createCustomer(customerDocument2);

		Assert.assertNotNull(customerDocument.getId());
		Assert.assertNotNull(customerDocument2.getId());

		Assert.assertEquals(2, customerService.listCustomers().size());

	}

	@Test
	@Rollback
	public void testFindCustomer() {
		CustomerDocument customerDocument = new CustomerDocument("Tales", "Satiro");
		customerDocument = customerService.createCustomer(customerDocument);

		Assert.assertNotNull(customerDocument.getId());

		CustomerDocument customerDocumentFound = customerService.findCustomer(customerDocument.getId());
		Assert.assertEquals("Tales", customerDocumentFound.getFirstName());
		Assert.assertEquals("Satiro", customerDocumentFound.getLastName());
	}

}
