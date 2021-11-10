package fiveTestCases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class TestCase005 {
	public static void main(String[] args) throws InterruptedException {
		// system path for browser web driver
		System.setProperty("webdriver.chrome.driver","C:\\SeleniumBrowserDrivers\\chromedriver.exe");
						
		//initialize web driver
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);				
		driver.get("http://zero.webappsecurity.com/");
				
		assertEquals(driver.getTitle(), "Zero - Personal Banking - Loans - Credit Cards", "Title did not match!!! \n");
	
				
		//click on sign in button
		driver.findElement(By.id("signin_button")).click();
		
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(driver.getTitle(), "Zero - Log in","Login page title did not match!! \n" );
				
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
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
				
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement accountActivity = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Online Statements")));
		accountActivity.click();
			
		//choosing which account to transfer to and from adding amount
		Select Account = new Select(driver.findElement(By.name("accountId")));
		Account.selectByVisibleText("Checking");
		driver.findElement(By.xpath("//a[contains(text(),'2011')]")).click();

		Account.selectByVisibleText("Brokerage");
		driver.findElement(By.xpath("//a[contains(text(),'2009')]")).click();
	
				
		softassert.assertAll();
				
		//close browser
		driver.close();
		driver.quit();
	}
}
