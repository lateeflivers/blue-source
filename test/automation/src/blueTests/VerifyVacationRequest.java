package blueTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import blueSource.BlueEmployee;
import blueSource.BlueIndex;
import blueSource.BlueLogin;
import blueSource.BlueRequestTimeOff;
import blueSource.BlueTimeOff;

public class VerifyVacationRequest {
	
	private static WebDriver driver;
	public double vacationRates;
	double Years;
	double VacationToDate;
	String[] Months = {"May","June","July","August","September","October","November","December", "Janurary","February","March","April"};
	@BeforeTest
	public static void initializeTest(){
		driver = new InternetExplorerDriver();
		
	}
	
	@Test(description="Add vacation time")
	@Parameters({"url","firstName","lastName","password"})
	public void LoginIntoSite(String url, String firstName, String lastName, String password ){
		BlueLogin loginPage = new BlueLogin(driver);
		
		loginPage.setURL(url);
		loginPage.open();
		loginPage.enterFirstAndLastName(firstName, lastName);
		loginPage.enterPassword(password);
		loginPage.submitForm();
		

	}

	@Test
	@Parameters({"TargetFirst","TargetLast"})
	public void findEmployee(String TargetFirst, String TargetLast){
		BlueIndex indexPage = new BlueIndex(driver);
		indexPage.searchEmployee(TargetFirst, TargetLast);
		indexPage.selectEmployee();
		
	}
	
	@Test(description="Updates start date")
	@Parameters({"Date"})
	public void changeStartDate(String Date){
		BlueEmployee employeePage = new BlueEmployee(driver);
		employeePage.manageGenernalInfo();
		employeePage.EmployeeModal.setStartDate(Date);
		employeePage.EmployeeModal.UpdateEmployee();
		Years = employeePage.getYears();
		employeePage.manageTimeOff();
	
	}
	
	@Test(description="Go to Vacation time off")
	public void checkAccuralRates(){
	
		BlueTimeOff timePage = new BlueTimeOff(driver);
		timePage.openDetails();
		VacationToDate= timePage.getAccrualToDate();
		
	}
	
	@Test(description="Request Time")
	@Parameters({"StartDate","EndDate","VacationType"})
	public void RequestTime(String StartDate, String EndDate, String VacationType){
		BlueRequestTimeOff Request = new BlueRequestTimeOff(driver);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Request.RequestTimeOff();
		Request.EnterStartDate(StartDate);
		Request.EnterEndDate(EndDate);
		Request.SelectVacationType(VacationType);
		Request.EnterMemoText("Automated Test");
		Request.ClickSaveVacation();
		Request.clickLogout();
	}
	
	@Test(description="Go to vacation aprove page")
	@Parameters({"EmployeeFirstName","EmployeeLastName"})
	public void ApproveVacation(String EmployeeFirstName, String EmployeeLastName){
		BlueIndex indexPage = new BlueIndex(driver);
		String fullName = EmployeeFirstName+" "+EmployeeLastName;
		indexPage.getWebElementBy(By.name(fullName)).click();
	}
	
	@Test(description="Opens Manage time off")
	public void openTimeOff(){
		
	}
	
	@AfterTest(description="closes driver")
	public void cleanup(){
		driver.quit();
	}
}
