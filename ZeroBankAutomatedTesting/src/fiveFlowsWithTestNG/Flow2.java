package fiveFlowsWithTestNG;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Flow2 {

	public WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		// system path for browser web driver
		System.setProperty("webdriver.chrome.driver","C:\\SeleniumBrowserDrivers\\chromedriver.exe");
				
		//initialize web driver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);				
		driver.get("http://zero.webappsecurity.com/");
		
		String actualTitle =driver.getTitle();
		String expectedTitle = "Zero - Personal Banking - Loans - Credit Cards";
	
		assertEquals(actualTitle, expectedTitle, "Title did not match!!! \n");
	}
	
	@Test
	public void logInTest() {
		driver.findElement(By.id("signin_button")).click();
		
		assertEquals(driver.getTitle(), "Zero - Log in","Login page title did not match!! \n" );
		
		driver.findElement(By.name("user_login")).sendKeys("username");
		driver.findElement(By.name("user_password")).sendKeys("password");
		
		WebElement checkbox = driver.findElement(By.id("user_remember_me"));
		
			boolean isDisplayed =checkbox.isDisplayed();
			if(isDisplayed) {
				System.out.println("Checkbox is displayed");
				boolean isEnabled = checkbox.isEnabled();
				if(isEnabled) {
					System.out.println("Checkbox is enabled");
					boolean isChecked = checkbox.isSelected();
					if(!isChecked) {
						checkbox.click();
						System.out.println("Checked the checkbox");
					}else {
						System.out.println("Checkbox is already checked");
					}
				}else {
					System.out.println("Checkbox is not enabled");
				}
			}else {
				System.out.println("Checkbox is not displayed");
			}
			
		//click on sign in button
		driver.findElement(By.name("submit")).click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();	
			
	}
	
	@Test
	public void purchaseForeignCurrency() throws InterruptedException {

		//click on pay bills page
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement payBills = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pay Bills")));
		payBills.click();
		
		//go to purchase foreign currency page
		WebElement purchaseCurrency = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Purchase Foreign Currency")));
		purchaseCurrency.click();
		
		
		//Select box method
		//select currency
		Select currency = new Select(driver.findElement(By.id("pc_currency")));
		currency.selectByVisibleText("China (yuan)");
		currency.selectByValue("THB");
		currency.selectByIndex(8);
		
		
		//radio button
		driver.findElement(By.id("pc_inDollars_false")).click();
		driver.findElement(By.id("pc_calculate_costs")).click();
		
		//handling alerts
		Alert amountAlert = driver.switchTo().alert();
		
		//get text
		String alertText = amountAlert.getText();
		System.out.println("Alert text is ----  " + alertText);
		
		//click on OK button
		amountAlert.accept();
		
		//adding amount and submitting
		driver.findElement(By.id("pc_amount")).sendKeys("100");
		driver.findElement(By.id("pc_calculate_costs")).click();
		driver.findElement(By.id("purchase_cash")).click();

	}
	
	@AfterTest
	public void cleanUp() {
		//click on log out
		driver.findElement(By.className("icon-user")).click();
		driver.findElement(By.id("logout_link")).click();
		
		driver.close();
		driver.quit();
		
	}
}
