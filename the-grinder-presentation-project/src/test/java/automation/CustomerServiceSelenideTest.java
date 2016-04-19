package automation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import presentation.Application;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * By default Selenide uses Firefox web browser as it's the most simple and does not use additional installations.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CustomerServiceSelenideTest {

    @Test
    public void openIndexAndCreateACustomer(){
        open("/");
        $("h1").shouldHave(text("Hello"));
    }

    @Test
    public void createCustomer(){
        open("/");
        $("h1").shouldHave(text("Hello"));
        $("#customers-page").click();
        $("h1").shouldHave(text("Customers"));
        $("#create-customer-button").click();
        $("#first-name-input").setValue("Heitor");
        $("#last-name-input").setValue("Lira");
        $(".btn-primary").click();
    }
}
