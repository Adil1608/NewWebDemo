package stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageclasses.homepage;

public class homepagesteps extends homepage {

    @When("I click on compose button on homepage")
    public void i_click_on_compose_button_on_homepage() {
        clickComposeBtn();
    }

    @When("I enter To address as {string} in compose window")
    public void i_enter_to_address_as_in_compose_window(String string) {
        enterToField(string);
    }

    @When("I enter subject line as {string} in compose window")
    public void i_enter_subject_line_as_in_compose_window(String string) {
        enterSubjectField(string);
    }

    @When("I enter body as {string} in compose window")
    public void i_enter_body_as_in_compose_window(String string) {
        enterBodyField(string);
    }

    @When("I click on send button in compose window")
    public void i_click_on_send_button_in_compose_window() {
        clickSendBtn();
    }

    @Then("I verify email sent message")
    public void i_verify_email_sent_message() {
        Assert.assertTrue(verifyEmailSentMessage());
    }

    @Then("I verify received email in Inbox")
    public void i_verify_received_email_in_inbox() {
        sleep(2000);
        WebElement subjectText = null;
        try {
            subjectText = driver.findElement(By.xpath("(//*[@class='zA zE']//span[text()='Automation QA test for Incubyte'])[1]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(subjectText);
    }

    @Then("I should see user should be successfully Logged In")
    public void i_should_see_user_should_be_successfully_logged_in() {
        clickOnProfileIcon();
        sleep(2000);
        Assert.assertTrue(verifyProfileUsername("adizir@testingsaiyan.co.in"));
    }
}

