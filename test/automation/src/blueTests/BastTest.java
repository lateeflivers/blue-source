package blueTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BastTest {
	protected static WebDriver driver;
	
	@BeforeTest
	public static void initializeTest(){
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/**
	 * Closes driver after test is complete
	 */
	@AfterTest(description="closes driver")
	public void cleanup(){
		driver.quit();
	}
}
