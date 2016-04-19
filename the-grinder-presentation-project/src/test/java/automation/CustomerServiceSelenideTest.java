package automation;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import presentation.Application;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * By default Selenide uses Firefox web browser as it's the most simple and does not use additional installations.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CustomerServiceSelenideTest {

    private static final String INDEX_URL_PAGE = "/";
    private static final String TABLE_LINES = ".table-responsive tbody tr";

    @Test
    public void testCreateCustomer() {
        accessIndexAndGoToCustomersPage();
        String customerId = createCustomer("Tales", "Satiro");
        Assert.assertNotNull(getTrByCustomerId(customerId));
        deleteCustomer(customerId);
        Assert.assertNull(getTrByCustomerId(customerId));
    }

    @Test
    public void testEditCustomer() {
        accessIndexAndGoToCustomersPage();
        String customerId = createCustomer("Pedro", "Cardoso");
        Assert.assertNotNull(getTrByCustomerId(customerId));
        editCustomer(customerId, "Pedro edited", "Cardoso edited");
        Assert.assertNotNull(getTrByCustomerId(customerId));
        deleteCustomer(customerId);
        Assert.assertNull(getTrByCustomerId(customerId));
    }

    @Test
    public void testDeleteCustomer() {
        accessIndexAndGoToCustomersPage();
        String customerId = createCustomer("Carlos", "Serafin");
        Assert.assertNotNull(getTrByCustomerId(customerId));
        deleteCustomer(customerId);
        Assert.assertNull(getTrByCustomerId(customerId));
    }

    @Test
    public void testListCustomers() {
        accessIndexAndGoToCustomersPage();
        String customerId = createCustomer("Tales", "Satiro");
        Assert.assertNotNull(getTrByCustomerId(customerId));
        String customerId2 = createCustomer("Heitor", "Lira");
        Assert.assertNotNull(getTrByCustomerId(customerId2));
        String customerId3 = createCustomer("Ycaro", "Ratis");
        Assert.assertNotNull(getTrByCustomerId(customerId3));
        String customerId4 = createCustomer("Marco", "Rosner");
        Assert.assertNotNull(getTrByCustomerId(customerId4));
        String customerId5 = createCustomer("Sergio", "Santos");
        Assert.assertNotNull(getTrByCustomerId(customerId5));
        String customerId6 = createCustomer("Robson", "Diniz");
        Assert.assertNotNull(getTrByCustomerId(customerId6));
        Assert.assertEquals(6, $$(TABLE_LINES).size());
        deleteCustomer(customerId6);
        Assert.assertEquals(5, $$(TABLE_LINES).size());
        deleteCustomer(customerId5);
        Assert.assertEquals(4, $$(TABLE_LINES).size());
        deleteCustomer(customerId4);
        Assert.assertEquals(3, $$(TABLE_LINES).size());
        deleteCustomer(customerId3);
        Assert.assertEquals(2, $$(TABLE_LINES).size());
        deleteCustomer(customerId2);
        Assert.assertEquals(1, $$(TABLE_LINES).size());
        deleteCustomer(customerId);
        Assert.assertEquals(0, $$(TABLE_LINES).size());

    }

    private String createCustomer(String firstName, String lastName) {
        $("#create-customer-button").click();
        $("#myModal").should(appear);
        $("#first-name-input").setValue(firstName);
        $("#first-name-input").shouldHave(value(firstName));
        $("#last-name-input").setValue(lastName);
        $("#last-name-input").shouldHave(value(lastName));
        $("#save-customer").click();
        $("#myModal").should(disappear);
        SelenideElement createdElement = $$(TABLE_LINES).last().shouldHave(text(firstName)).shouldHave(text(lastName));
        return createdElement.getAttribute("id");
    }

    private void editCustomer(String customerToBeEdited, String newFirstName, String newLastName) {
        SelenideElement elementToBeEdited = getTrByCustomerId(customerToBeEdited);
        if (elementToBeEdited != null) {
            elementToBeEdited.find(".edit-customer").shouldHave(text("Edit")).click();
            $("#myModal").should(appear);
            $("#first-name-input").setValue(newFirstName);
            $("#first-name-input").shouldHave(value(newFirstName));
            $("#last-name-input").setValue(newLastName);
            $("#last-name-input").shouldHave(value(newLastName));
            $("#save-customer").click();
            $("#myModal").should(disappear);
            elementToBeEdited.shouldHave(text(newFirstName)).shouldHave(text(newLastName));
        }
    }

    private SelenideElement getTrByCustomerId(String customerId) {
        for (SelenideElement element : $$(TABLE_LINES)) {
            if (element.getAttribute("id").equals(customerId)) {
                return element;
            }
        }

        return null;
    }

    private void deleteCustomer(String customerId) {
        if (getTrByCustomerId(customerId) != null) {
            int tableSize = $$(TABLE_LINES).size();
            getTrByCustomerId(customerId).find(".delete-customer").shouldHave(text("Delete")).click();
            $$(".table-responsive tbody tr").shouldHaveSize(tableSize - 1);
        }
    }

    private void accessIndexAndGoToCustomersPage() {
        open(INDEX_URL_PAGE);
        $("#hello-message").shouldHave(text("Hello"));
        $("#customers-page").click();
        $("#table-header").shouldHave(text("Customers"));
    }
}
