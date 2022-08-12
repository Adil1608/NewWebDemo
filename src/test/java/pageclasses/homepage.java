package pageclasses;

import utils.baseClass;

public class homepage extends baseClass {

    /** LOCATORS **/
    private String COMPOSE_BTN = "xpath=>//div[@role='button' and text()='Compose']";
    private String TO_FIELD = "css=>.afx div input";
    private String SUBJECT_FIELD = "xpath=>//*[@name='subjectbox']";
    private String BODY_FIELD = "xpath=>//*[@aria-label='Message Body' and @class='Am Al editable LW-avf tS-tW']";
    private String SEND_BTN = "xpath=>//*[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']";
    private String MESSAGE_SENT_TEXT = "xpath=>//span[text()='Message sent']";
    private String PROFILE_USERNAME_TEXT = "xpath=>//div[@class='Wdz6e ZnExKf']";
    private String PROFILE_USERNAME_ICON= "xpath=>//a[contains(@aria-label,'Google Account:')]";

    public void clickComposeBtn(){
        waitForElement(COMPOSE_BTN,3);
        clickOnElement(COMPOSE_BTN);
    }

    public void enterToField(String data){
        sendData(TO_FIELD,data);
    }

    public void enterSubjectField(String data){
        sendData(SUBJECT_FIELD,data);
    }

    public void enterBodyField(String data){
        sendData(BODY_FIELD,data);
    }

    public void clickSendBtn(){
        clickOnElement(SEND_BTN);
    }

    public Boolean verifyEmailSentMessage(){
        sleep(2000);
        String actualtext = getElement(MESSAGE_SENT_TEXT).getText();
        return actualtext.contains("Message sent");
    }

    public void clickOnProfileIcon(){
        waitForElement(PROFILE_USERNAME_ICON,3);
        clickOnElement(PROFILE_USERNAME_ICON);
    }

    public Boolean verifyProfileUsername(String username){
        driver.switchTo().frame("account");
        String actualtext = getElement(PROFILE_USERNAME_TEXT).getText();
        driver.switchTo().defaultContent();
        return actualtext.equalsIgnoreCase(username);
    }

}
