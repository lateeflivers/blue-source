package blueTests;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import blueModal.BlueManageEmployeeModal;
import blueSource.BlueIndex;
import blueSource.BlueLogin;

/* As an Admin, I want to be able to add new users to BlueSource so that their info can be tracked */
public class AddUser extends BastTest {
	
	private static final String BlueSourceIconXpath = "html/body/header/div/div/a/img";
	
	@Test(description="Add vacation time")
	@Parameters({"url","firstName","lastName","password"})
	public void LoginIntoSite(String url, String firstName, String lastName, String password ){
		BlueLogin loginPage = new BlueLogin(driver);
		
		loginPage.setURL(url);
		loginPage.open();
		loginPage.enterFirstAndLastName(firstName, lastName);
		loginPage.enterPassword(password);
		loginPage.submitForm();
	//	assertTrue(loginPage.isElementPresentAndDisplayed(By.xpath(BlueSourceIconXpath)));

	}
	
	@Test(description="Open new employee modal")
	public void ClickAddNewEmployee(){
		BlueIndex indexPage = new BlueIndex(driver);
		//indexPage.addEmployee();
		driver.findElement(By.xpath(".//*[@id='ng-app']/div[2]/div/div/button")).click();
	}
	
	@Test(description="Add and enter employee tables")
	@Parameters({"empfirstName","emplastName","StartDate","Manager","Dept","Level","Role","Location"})
	public void AddNewUser(String empfirstName, String emplastName, String StartDate, String Manager, String Dept, String Level, String Role, String Location){
		//BlueIndex indexPage = new BlueIndex(driver);
		//indexPage.addEmployee();
		//driver.findElement(By.xpath(".//*[@id='ng-app']/div[2]/div/div[2]/button")).click();
		BlueManageEmployeeModal AddEmployee = new BlueManageEmployeeModal(driver);
		AddEmployee.setUserName(empfirstName, emplastName);
		AddEmployee.setFirstName(empfirstName);
		AddEmployee.setLastName(emplastName);
		AddEmployee.setStartDate(StartDate);
		AddEmployee.setEmail(empfirstName, emplastName);
		AddEmployee.selectDepartment(Dept);
		AddEmployee.selectRole(Role);
		AddEmployee.selectLevel(Level);
		AddEmployee.selectManager(Manager);
		AddEmployee.selectLocation(Location);
		AddEmployee.UpdateEmployee();
		
		
	}
	

}
