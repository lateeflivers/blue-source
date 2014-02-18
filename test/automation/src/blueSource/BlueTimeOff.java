package blueSource;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import blueModal.TimeOffDetailsModal;

/**
 * Time off details page
 * @author Lateef Livers
 * Jan 22, 2014
 */
public class BlueTimeOff extends BluePage{


	private List<WebElement> trCollection;				

	private int maxRows;
	private int targetRow;
	
	private TimeOffDetailsModal DetailsModal;

	private static final int VACATION_TYPE_COL = 5;
	private static final int HALF_DAY_COL = 6;
	private static final int DAYS_USED = 0; 
	private static final int DAYS_MAX = 1;
	
	/*
	 * Constants for paths. If the script breaks unexpectedly, 
	 * the path of the web object may need to be updated
	 */
	private static final String FLOATING_HOLIDAYS_WELL = ".//div/span[4]";
	private static final String SICK_DAYS_WELL= ".//div/span[2]";
	private static final String VACATION_DAYS_WELL = ".//div/span[3]";
	private static final String EDIT_COL = ".//td[7]/a/span";
	private static final String DELETE_COL = ".//td[8]/a/span";
	private static final String SaveTimeOffBtn = ".//*[@id='vacation_form']/input[11]";
	private static final String alertMessageXpath ="html/body/div[1]/div[1]/div[1]";
	private static final String BackBtnXpath = ".//*[@id='vacation_form']/a";
	private static final String DetailsModalXpath ="html/body/div[1]/div[1]/div/a";
	
	private static double[] sickDays;
	private static double[] vacationDays;
	private static double[] floatingHolidays;
	
	/**
	 * Constructor for Time Off Page
	 * @author Lateef
	 * Jan 23, 2014
	 * @param driver Webdriver
	 */
	public BlueTimeOff(WebDriver driver){
		super(driver);
		trCollection = driver.findElements(By.className("vacation-row"));
		maxRows = trCollection.size();
		
		sickDays = new double[2];
		setSickDays();
		vacationDays = new double[2];
		setVacationDays();
		floatingHolidays = new double[2];
		setFloatingHolidays();
		

	}

	
	/**
	 * Enters the 'Date Requested' field
	 * @author Lateef
	 * Jan 23, 2014
	 * @param RequestedDate format shoudl be YYYY-MM-DD
	 */
	public void changeDateRequested(String RequestedDate){
		getWebElementBy(By.name("vacation[date_requested]")).clear();
		getWebElementBy(By.name("vacation[date_requested]")).sendKeys(RequestedDate);
	}
	
	/**
	 * Enters the Start date for time off
	 * @author Lateef 
	 * Jan 23, 2014
	 * @param StartDate Start of time off
	 */
	public void changeStartDate(String StartDate){
		getWebElementBy(By.xpath(".//*[@id='start_date-new']")).sendKeys(StartDate);
	}
	
	/**
	 * Enters the End Date for time off
	 * @author Lateef
	 * Jan 23, 2014
	 * @param EndDate End of time off
	 */
	public void changeEndDate(String EndDate){
		getWebElementBy(By.xpath(".//*[@id='end_date-new']")).clear();		
		getWebElementBy(By.xpath(".//*[@id='end_date-new']")).sendKeys(EndDate);
	}
	

	/**
	 * Select the Vacation Type from the drop down.
	 * @author Lateef
	 * Jan 23, 2014
	 * @param VacationType
	 */
	public void setVacationType(String VacationType){
		 new Select(getWebElementBy(By.xpath(".//*[@id='vacation_type-new']"))).selectByValue(VacationType);
		
	}
	
	/**
	 * Enters the Start date, end date and vacation type into the correct field and submits it
	 * @author Lateef
	 * Jan 23, 2014
	 * @param StartDate
	 * @param EndDate
	 * @param VacationType
	 */
	public void enterVacation(String StartDate, String EndDate, String VacationType){
		changeStartDate(StartDate);
		changeEndDate(EndDate);
		setVacationType(VacationType);
		saveTimeOff();
	}
	
	
	/**
	 * Edits the vacation from the specified row
	 * @author Lateef
	 * Jan 23, 2014
	 * @param row Row of time off to be edited. 
	 */
	public void editVacation(int row){
		
		getCurrentTrSize(EDIT_COL);
		
		
		if(0 == maxRows)
			return;
		
		
		for(WebElement trElement : trCollection){
			
			/*When found get ID of row*/
			if(row == targetRow){
				/*Click the edit button for that row*/
				trElement.click();
			  break;
			}
			targetRow++;
		}
		
	}
	
	
	/**
	 * Deletes the vacation from the specified row
	 * @author Lateef 
	 * Jan 23, 2014
	 * @param row Row of time off to be deleted.
	 */
	public void deleteVacation(int row){
		int targetRow = 1;

		getCurrentTrSize(DELETE_COL);
		trCollection = driver.findElements(By.xpath(DELETE_COL));	
		if(0 == maxRows)
			return;
	
		for(WebElement trElement : trCollection){
			
			/*When found get ID of row*/
			if(row == targetRow){
				/*Delete row*/
				trElement.click();
			  break;
			}
			targetRow++;
		}

		
	}
	
	
	/**
	 * Pulls the number of sick days used and total from the well
	 * @author Lateef
	 * Jan 24, 2014
	 */
	public void setSickDays(){
		String[] days = setDaysUsedAndMax(SICK_DAYS_WELL);
		
		sickDays[DAYS_USED] = Double.parseDouble(days[DAYS_USED]);
		sickDays[DAYS_MAX] = Double.parseDouble(days[DAYS_MAX]);
	}
	
	/**
	 * Pulls the number of Vacation days used and total from the well
	 * @author Lateef
	 * Jan 24, 2014
	 */
	public void setVacationDays(){
		String[] days = setDaysUsedAndMax(VACATION_DAYS_WELL);
		vacationDays[DAYS_USED] = Double.parseDouble(days[DAYS_USED]);
		
		vacationDays[DAYS_MAX] = Double.parseDouble(days[DAYS_MAX]);
	}
	
	/**
	 *  Pulls the number of Floating holidays used and total from the well
	 */
	public void setFloatingHolidays(){
		String[] days = setDaysUsedAndMax(FLOATING_HOLIDAYS_WELL);
		
		floatingHolidays[DAYS_USED] = Double.parseDouble(days[DAYS_USED]);
		floatingHolidays[DAYS_MAX] = Double.parseDouble(days[DAYS_MAX]);
	}
	
	/**
	 * Sets the days used for the approbate field
	 * @author Lateef
	 * Jan 24, 2014
	 * @param field target field FLOATING_HOLIDAYS_WELL, SICK_DAY_WELL, or VACATION_DAYS_WELL
	 * @return String array of days used and estimated max
	 */
	private String[] setDaysUsedAndMax(String field){
		String pdoDayField = getWebElementBy(By.xpath(field)).getText();
		String[] pdoDay = pdoDayField.split(" ");
		String[] days = pdoDay[0].split("/");
		
		return new String[]{days[0],days[1]};
		
	}


	/**
	 * Gets a employee's number of used sick days
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of used sick days
	 */
	public double getSickDaysUsed(){
		return getDaysUsed(sickDays);
	}
	
	/**
	 * Gets a employee's number of max sick days
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of max sick days
	 */
	public double getSickDaysMax(){
		return getDaysMax(sickDays);
	}
	
	/**
	 * Gets a employee's number of used vacation days
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of used vacation days
	 */
	public double getVacationDaysUsed(){
		return getDaysUsed(vacationDays);
	}
	
	/**
	 * Gets a employee's number of estimated max vacation days
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of estimated max vacation days
	 */
	public double getVacationDaysMax(){
		return getDaysMax(vacationDays);
	}
	
	/**
	 * Gets a employee's number of used floating holidays
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of used floating holidays
	 */
	public double getFloatingHolidaysUsed(){
		return getDaysUsed(floatingHolidays);
	}
	
	/**
	 * Gets a employee's number of max floating holidays
	 * @author Lateef
	 * Jan 24, 2014
	 * @return number of max floating holidays
	 */
	public double getFloatingHolidaysMax(){
		return getDaysMax(floatingHolidays);
	}
	
	
	/**
	 * Gets the number of total business days from the editable row
	 * @author Lateef
	 * Jan 23, 2014
	 * @return Number of days estimated to be taken
	 */
	public String getTotalBusinessDays(){
		return getWebElementBy(By.xpath(".//*[@id='business_days-new']")).getText();
		
	}


	/**
	 * Returns the number of days used for a given field
	 * @author Lateef
	 * Jan 24, 2014
	 * @param days floatingHolidays, vacationDays, or sickDays
	 * @return number of used days for the indicated field
	 */
	private double getDaysUsed(double[] days){
		return days[DAYS_USED];
	}
	
	/**
	 * Returns the number of days maximum for a given field
	 * @author Lateef
	 * Jan 24, 2014
	 * @param days floatingHolidays, vacationDays, or sickDays
	 * @return number of maximum days for the indicated field
	 */
	private double getDaysMax(double[] days){
		return days[DAYS_MAX];
	}
	
	
	/**
	 * Gets the correct Number of Rows, Resets 'targetRow' to 1
	 * @author Lateef
	 * Jan 24, 2014
	 * @param Target column
	 */
	private void getCurrentTrSize(String Col) {
		trCollection = driver.findElements(By.xpath(Col));
		maxRows = trCollection.size();
		targetRow = 1;
	}


	/**
	 * Toggles the Half Day check box
	 * @author Lateef
	 * Jan 23, 2014
	 */
	public void setHalfDay(){
		getWebElementBy(By.name("vacation[half_day]")).click();
	}
	
	/**
	 * Clicks the "Saves Time Off" button
	 * @author Lateef
	 * Jan 23, 2014
	 */
	public void saveTimeOff(){
		getWebElementBy(By.xpath(SaveTimeOffBtn)).click();
	}
	
	/**
	 * Clicks the back button
	 * @author Lateef
	 * Jan 23, 2014
	 * @return BlueEmployee
	 */
	public BlueEmployee clickBack(){

		getWebElementBy(By.xpath(BackBtnXpath)).click();
		return new BlueEmployee(driver);
	}
	
	

	/**
	 * Checks to see if the update to vacations was successful
	 * @author Lateef
	 * Jan 23, 2014
	 * @return True if successful, False otherwise
	 */
	public Boolean isUpdateSuccessful(){
		if(getAlertMessage().contains("Time off successfully saved.")||getAlertMessage().contains("Time off successfully updated."))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks to see if the delete to vacations was successful
	 * @author Lateef
	 * Jan 23, 2014
	 * @return True if successful, False otherwise
	 */
	public Boolean isDeleteSuccessful(){
		if(getAlertMessage().contains("Time off successfully deleted."))
			return true;
		else
			return false;
	}
	
	
	/**
	 * Returns Alert message, Returns NULL if no message is displayed
	 * @author Lateef
	 * Jan 23, 2014
	 * @return
	 */
	private String getAlertMessage(){
		if(isElementPresentAndDisplayed(By.xpath(alertMessageXpath))==true)
			return getWebElementBy(By.xpath(alertMessageXpath)).getText();
		return null;
	}
	
	/**
	 * Deletes the first entry in the vacation log.
	 * @author Lateef
	 * Jan 23, 2014
	 */
	public void deleteFirstEntry(){
		deleteVacation(1);
	}
	

	/**
	 * Opens the details modal
	 */
	public void openDetails(){
		getWebElementBy(By.xpath(DetailsModalXpath)).click();
		DetailsModal = new TimeOffDetailsModal(driver);
	}
	
	/**
	 * Gets the Number of vacation days accured to date. 
	 * @return number of vacation days
	 */
	public double getAccrualToDate(){
	//	System.out.println(DetailsModal.getAccruedTodate());
		return DetailsModal.getAccruedTodate();
	}
	
}
