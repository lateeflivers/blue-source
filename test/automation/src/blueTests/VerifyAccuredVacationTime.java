package blueTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

public class VerifyAccuredVacationTime {

	private static WebDriver driver;
	@BeforeTest
	public static void initializeTest(){
		driver = new InternetExplorerDriver();
		
	}
}
