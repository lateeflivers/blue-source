package blueModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BlueManageEmployeeModal {
	private static final String blueSourceUserNameXPATH = ".//*[@id='employee_username']";
	private static final String FirstNameXpath = ".//*[@id='employee_first_name']";
	private static final String LastNameXpath = ".//*[@id='employee_last_name']";
	private static final String SelectEmpLevelXpath = ".//*[@id='employee_level']";
	private static final String SelectRoleXpath = ".//*[@id='employee_role']";
	private static final String SelectManagerXpath = ".//*[@id='employee_manager_id']";
	private static final String SelectStatusDropdownXpath = ".//*[@id='employee_status']";
	private static final String SelectLocationXpath = ".//*[@id='employee_location']";
	private static final String StartDateXpath = ".//*[@id='employee_start_date']";
	private static final String CellPhoneStartDateXpath =".//*[@id='employee_cell_phone']";
	private static final String OfficePhoneXpath = ".//*[@id='employee_office_phone']";
	private static final String EmailXpath = ".//*[@id='employee_email']";
	private static final String IMNameXpath ="";
	private static final String SelectIMClientXpath = "";
	private static final String SelectDeptXpath = ".//*[@id='employee_department']";
	private static final String CloseBtnName = "button";
	private static final String UpdateEmployeeName = "commit";
	
	private static WebDriver driver; 
	private static WebElement element;
	
	public BlueManageEmployeeModal(WebDriver driver){
		this.driver = driver;
	}
	
	public void setUserName(String firstName, String lastName){
		driver.findElement(By.xpath(blueSourceUserNameXPATH)).sendKeys(firstName+"."+lastName);
	}
	
	public void setFirstName(String firstName){
		driver.findElement(By.xpath(FirstNameXpath)).sendKeys(firstName);
	}
	
	public void setLastName(String lastName){
		 driver.findElement(By.xpath(LastNameXpath)).sendKeys(lastName);
	}
	
	public void selectLevel(String level){

		SelectBox(level, By.xpath(SelectEmpLevelXpath));
	}
	
	public void selectRole(String role){
		SelectBox(role,By.xpath(SelectRoleXpath));
	}
	
	public void selectManager(String Manager){
		SelectBox(Manager, By.xpath(SelectManagerXpath));
	}
	
	public void selectLocation(String Location){
		SelectBox(Location, By.xpath(SelectLocationXpath));
	}
	
	public void selectIMClient(String IMClient){
		SelectBox(IMClient, By.xpath(SelectIMClientXpath));
	}
	
	public void selectDepartment(String Dept){
		SelectBox(Dept, By.xpath(SelectDeptXpath));
	}
	
	public void setEmail(String firstName, String lastName){
		String email =firstName+"."+lastName+"@orasi.com";
		setPath(email,By.xpath(EmailXpath));
	//	driver.findElement(By.xpath(EmailXpath)).sendKeys(firstName+"."+lastName+"@orasi.com");
	}
	
	public void setStartDate(String StartDate){
		setPath(StartDate,By.xpath(StartDateXpath));
	}
	
	public void UpdateEmployee(){
		driver.findElement(By.name(UpdateEmployeeName)).click();
	}
	
	private void setPath(String text, By path){
		driver.findElement(path).sendKeys(text);
	}
	
	private void SelectBox(String option, By xpath){
		element =driver.findElement(xpath);
		new Select(element).selectByVisibleText(option);

	}
	
}
