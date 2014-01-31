package blueTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import blueSource.BlueLogin;
import blueSource.BlueRequestTimeOff;

public class VerifyVacationRequest {
	
	private static WebDriver driver;
	@Test(description="Add vacation time")
	@Parameters({"url","firstName","lastName","password"})
	public void Login(String url, String firstName, String lastName, String password, String StartDate, String EndDate, String VacationType ){
		BlueLogin loginPage = new BlueLogin(driver);
		
		loginPage.setURL(url);
		loginPage.open();
		loginPage.enterFirstAndLastName(firstName, lastName);
		loginPage.enterPassword(password);
		loginPage.submitForm();
		

	}

	@Test(description="Add vacation time")
	@Parameters({"StartDate","EndDate","VacationType"})
	public void RequestTime(String StartDate, String EndDate, String VacationType){
		BlueRequestTimeOff Request = new BlueRequestTimeOff(driver);
		Request.RequestTimeOff();
		Request.EnterStartDate(StartDate);
		Request.EnterEndDate(EndDate);
		Request.SelectVacationType(VacationType);
		Request.EnterMemoText("Automated Test");
		
	}
}
