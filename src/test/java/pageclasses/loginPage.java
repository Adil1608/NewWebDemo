package pageclasses;

import utils.Constants;
import utils.baseClass;

public class loginPage extends baseClass {

    /**
     * LOCATORS
     **/
    private String EMAIL_FIELD = "id=>identifierId";
    private String EMAIL_FIELD_NEXT_BTN = "id=>identifierNext";
    private String PASSWORD_FIELD = "xpath=>//*[@id=\"password\"]//input";
    private String PASSWORD_FIELD_NEXT_BTN = "id=>passwordNext";


    public void navigateToURL() throws InterruptedException {
        driver.get(Constants.BASE_URL);
        Thread.sleep(2000);
    }

    public void enterUsername() {
        sendData(EMAIL_FIELD, "adizir@testingsaiyan.co.in");
    }

    public void clickOnNextAfterEnteringUsername() {
        clickOnElement(EMAIL_FIELD_NEXT_BTN);
    }

    public void enterpassword() {
        sendData(PASSWORD_FIELD, "99379937");
    }

    public void clickOnNextAfterEnteringpassword() {
        clickOnElement(PASSWORD_FIELD_NEXT_BTN);
    }

}


