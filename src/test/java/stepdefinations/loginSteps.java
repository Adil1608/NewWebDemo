package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageclasses.loginPage;

public class loginSteps extends loginPage {

    @Given("I navigates to the gmail URL")
    public void i_navigates_to_the_gmail_url() throws InterruptedException {
        navigateToURL();
    }

    @When("I enter username as {string}")
    public void i_enter_username_as(String string) throws InterruptedException {
        enterUsername();
    }

    @When("I tap on Next after entering username")
    public void i_tap_on_next_after_entering_username() {
        clickOnNextAfterEnteringUsername();
    }

    @When("I enter password as {string}")
    public void i_enter_password_as(String string) {
        enterpassword();
    }

    @When("I tap on Next after entering password")
    public void i_tap_on_next_after_entering_password() {
        clickOnNextAfterEnteringpassword();
        sleep(2000);
    }

}
