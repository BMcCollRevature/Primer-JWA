package fiveTestCases;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class TestCase001 {
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
				
		driver.findElement(By.xpath("//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]")).click();
		Thread.sleep(3000);
		//click on log out
		driver.findElement(By.className("icon-user")).click();
		driver.findElement(By.id("logout_link")).click();
						
				
		softassert.assertAll();
				
		//close browser
		driver.close();
		driver.quit();

	}
}
