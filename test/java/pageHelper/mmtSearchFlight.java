package pageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseLibrary.BaseClass;

public class mmtSearchFlight extends BaseClass {
	@FindBy(xpath = "//*[@class='menu_Flights']//a")
	private WebElement flightsbutton;

	@FindBy(xpath = "//label[@for='fromCity']")
	private WebElement frombutton;

	@FindBy(xpath = "//label[@for='toCity']")
	private WebElement tobutton;

	@FindBy(xpath = "//label[@for='fromCity']/..//div[@role='combobox']/input")
	private WebElement fromInputField;

	@FindBy(xpath = "//label[@for='toCity']/..//div[@role='combobox']/input")
	private WebElement toInputField;

	@FindBy(xpath = "//*[text()='DEL']/ancestor::li")
	private WebElement fromCitySug;

	@FindBy(xpath = "//*[text()='BOM']/ancestor::li")
	private WebElement toCitySug;

	@FindBy(xpath = "//*[@class='makeFlex vrtlCenter ']/a")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[text()='OKAY, GOT IT!']")
	private WebElement popupOkBtn;

	@FindBy(xpath = "//*[text()='Departure']")
	private WebElement sortbyDetartureBtn;

	public mmtSearchFlight() {
		PageFactory.initElements(driver, this);
	}
	JavascriptExecutor js = (JavascriptExecutor)driver;	
	Actions action = new Actions(driver);

	
	public void SearchFlight() {
		try
		{
		js.executeScript("arguments[0].click();", flightsbutton);

		action.moveToElement(frombutton).build().perform();
		action.moveToElement(frombutton).click().perform();
		frombutton.click();
		action.moveToElement(fromInputField).build().perform();	
		action.moveToElement(fromInputField).click().perform();
		fromInputField.click();
		fromInputField.sendKeys("DEL");
		action.moveToElement(fromCitySug).click().perform();
		
		action.moveToElement(tobutton).build().perform();	
		js.executeScript("arguments[0].click();", tobutton);
		action.moveToElement(toInputField).build().perform();
		toInputField.click();
		toInputField.sendKeys("BOM");
		action.moveToElement(toCitySug).build().perform();
		toCitySug.click();
		

		searchBtn.click();

		popupOkBtn.click();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void sortByDetarture() {
		try {
		js.executeScript("arguments[0].scrollIntoView();", sortbyDetartureBtn);
		js.executeScript("arguments[0].click();", sortbyDetartureBtn);
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	}

	public void getPrice() {
		try {
		List<WebElement> priceList = driver
				.findElements(By.xpath("//*[@class='priceSection']//div[@class='textRight flexOne']/p"));
		TreeSet<Integer> Price = new TreeSet<>();
		ArrayList<String> price = new ArrayList<>();

		for (WebElement e : priceList) {
			Price.add(Integer.parseInt(e.getText().replaceAll("[^0-9]", "")));
			price.add(e.getText());
		}
		int temp= (int) (Price.toArray())[1];	
		String secLowestPrice="";
		for (String e : price) {
			if (temp == Integer.parseInt(e.replaceAll("[^0-9]", ""))) {
				secLowestPrice=e.substring(2);
				break;
			}	
		}
		System.out.println("₹ "+secLowestPrice);
		

		String filghtName = driver.findElement(By.xpath(
				"//*[contains(text(),'"+secLowestPrice+"')]/ancestor::div[@class='listingCard']//p[@class='boldFont blackText airlineName']")).getText();
		
		
		System.out.println("This " + filghtName + " has 2nd lowest price of ₹ " +  secLowestPrice);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
