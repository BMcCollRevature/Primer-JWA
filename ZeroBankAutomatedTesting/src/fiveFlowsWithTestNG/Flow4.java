package fiveFlowsWithTestNG;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

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

public class Flow4 {
	
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();	
	}
	
	@Test
	public void accountActivity(){
		
		//going to account activity page
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement accountActivity = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account Activity")));
		accountActivity.click();
		
		//choosing which account to view
		Select Account = new Select(driver.findElement(By.name("accountId")));
		Account.selectByVisibleText("Checking");
		Account.selectByVisibleText("Brokerage");
		
		//find transactions based on type of transaction
		driver.findElement(By.linkText("Find Transactions")).click();
		Select typetoSearch = new Select(driver.findElement(By.name("type")));
		typetoSearch.selectByVisibleText("Deposit");
		
		driver.findElement(By.xpath("//button[contains(text(),'Find')]")).click();

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
