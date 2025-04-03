package pageHelper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.Document;
import javax.swing.text.html.HTML;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseLibrary.BaseClass;

public class GmailUtility extends BaseClass{

public static String subject = "demo";
	@FindBy(xpath="//*[@id='identifierId']")
	private WebElement UserIDTextBox;

	@FindBy(xpath="//span[text()='Next']")
	private WebElement NextButton;

	@FindBy(xpath="//*[@type='password']")
	private WebElement PasswordTextBox;

	@FindBy(xpath="//*[text()='Compose']")
	private WebElement composeBtn;

	@FindBy(xpath="//input[@name='subjectbox']/parent::div/input")
	private WebElement EmailSubjectTextBox;

	@FindBy(xpath="//div[@class='Am Al editable LW-avf tS-tW']")
	private WebElement EmailBodyTextArea;
	
	@FindBy(xpath="//*[@data-tooltip='Formatting options']")
	private WebElement formatingBtn;

	@FindBy(xpath="//*[@aria-label='More formatting options']")
	private WebElement formatingMoreButton;

	@FindBy(xpath="//*[@command='+insertUnorderedList']/parent::td")
	private WebElement BulletedList;

	@FindBy(xpath="//input[@type='file']")
	private WebElement FileUpload;
	
	@FindBy(xpath="//*[@data-tooltip='Save & close']")
	private WebElement saveAndCloseBtn;
	
	@FindBy(xpath="//a[text()='Drafts']/parent::span")
	private WebElement draftBtn;
	
	@FindBy(xpath="(//span[text()='Demo']/parent::span/ancestor::td/preceding-sibling::td[@role='gridcell'])[1]")
	private WebElement draftedMail;
	
	@FindBy(xpath="(//span[text()='Demo']/parent::span/ancestor::td/preceding-sibling::td[@role='gridcell'])[2]")
	private WebElement draftedMail2;
	
	@FindBy(xpath="//div[text()='Recipients']/parent::div")
	private WebElement recipientsField;
	
	@FindBy(xpath="//input[@role='combobox']")
	private WebElement toField;

	@FindBy(xpath="//div[contains(@data-tooltip,'Send')]")
	private WebElement sendBtn;
	
	@FindBy(xpath="//a[text()='Inbox']/parent::span")
	private WebElement inboxBtn;
	
	@FindBy(xpath="//*[@class='bog']/span[text()='demo']/ancestor::td/parent::tr")
	private WebElement receivedMail;
	
	@FindBy(xpath="//*[@class='bog']/span[text()='demo']/ancestor::td/following-sibling::td/span/span")
	private WebElement receivedMailTime;
	
	@FindBy(xpath="//*[@data-tooltip='Search for all messages with label Inbox']/ancestor::div[@class='ha']/h2")
	private WebElement inmailSubject;
	
	@FindBy(xpath="//div[@dir='ltr']/ul")
	private WebElement mailBody;
	
	@FindBy(xpath="//img[@class='aQG aYB']")
	private WebElement imglink;
	
	@FindBy(xpath="//*[@data-tooltip='Download']")
	private WebElement attachmentDownloadBtn;
	
	@FindBy(xpath="//div[@aria-label='Delete']/div")
	private WebElement openMailDeleteBtn;
	
	public GmailUtility() {
		PageFactory.initElements(driver, this);
	}
	JavascriptExecutor js = (JavascriptExecutor)driver;	
	Actions action = new Actions(driver);


	public void gmailLogin()
	{
		try
		{
			System.out.println();
			UserIDTextBox.sendKeys("rajveer4321@gmail.com");
			NextButton.click();
			waitForVisibility(PasswordTextBox,10);
			PasswordTextBox.sendKeys("rajveer@1234");
			NextButton.click();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void ComposeMail()
	{
		try
		{
			waitForVisibility(composeBtn,10);
			composeBtn.click();
			EmailSubjectTextBox.click();
			EmailSubjectTextBox.sendKeys(subject);
			
			EmailBodyTextArea.sendKeys("Line one" +"\r\n"
					+"Line two" +"\r\n"
					+ "Line three");

			EmailBodyTextArea.sendKeys(Keys.CONTROL+"A");
			waitForVisibility(formatingBtn,10);
			formatingBtn.click();
			waitForVisibility(formatingMoreButton,10);
			formatingMoreButton.click();
			BulletedList.click();
			formatingBtn.click();
			String filePath=System.getProperty("user.dir") + "\\testData\\Gmail.jpg";
			FileUpload.sendKeys(filePath);
			waitForVisibility(saveAndCloseBtn,10);
			saveAndCloseBtn.click();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void openDraftMailandSend() {
		try {
			draftBtn.click();
			waitForElementClickable(draftedMail, 20);
			draftedMail.click();
			waitForElementClickable(sendBtn, 20);
			toField.sendKeys("rajveer4321@gmail.com");
			toField.sendKeys(Keys.ENTER);
			sendBtn.click();
		} catch (Exception e) {
			draftBtn.click();
			waitForElementClickable(draftedMail2, 20);
			draftedMail2.click();
			waitForElementClickable(sendBtn, 20);
			toField.sendKeys("rajveer4321@gmail.com");
			toField.sendKeys(Keys.ENTER);
			sendBtn.click();
		}
		
	}
	
	public void moveToInbox() {
		try {
			waitForVisibility(inboxBtn, 10);
			inboxBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public boolean verifyMailReceived() {
		boolean received = false;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
			   LocalDateTime now = LocalDateTime.now();  
			   String time = dtf.format(now);
			   if(receivedMail.isDisplayed() && receivedMailTime.getText().equals(time)) {
				   received = true;
			   }
		} catch (Exception e) {
			return received;
		}
		return received;
	}
	
	public void openMain() {
		try {
			receivedMail.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public boolean verifySubject() {
		boolean subjectMatched= false;
		try {
			if (inmailSubject.isDisplayed() && inmailSubject.getText().equals(subject)) {
				subjectMatched = true;
			}
		} catch (Exception e) {
			return subjectMatched;
		}
		return subjectMatched;
	}
	
	public boolean verifyMailBody() {
		boolean visible = false;
		try {
			if (mailBody.isDisplayed()) {
				visible=true;				
			}
		} catch (Exception e) {
			return visible;
		}
		return visible;
	}
	
	public boolean verifyAttachmentDownloaded() {
		boolean downloaded = false;
		try {
			action.moveToElement(imglink).build().perform();
			if (attachmentDownloadBtn.isDisplayed()) {
				attachmentDownloadBtn.click();
				downloaded=true;
			}
		} catch (Exception e) {
			return downloaded;
		}
		return downloaded;
	}
	
	public void deleteOpenedMail() {
		try {
//			openMailDeleteBtn.click();
			action.moveToElement(openMailDeleteBtn).build().perform();
			js.executeScript("arguments[0].click();", openMailDeleteBtn);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	


}
