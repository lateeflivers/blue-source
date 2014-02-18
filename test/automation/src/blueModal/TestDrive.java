package blueModal;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import blueSource.*;
public interface TestDrive {
	
//	 WebDriver driver;
	// WebElement element;
	 
	/*	static  TestDrive(WebDriver driver){
			this.driver = driver;
		}
	
	 default WebElement getWebElementBy(By locator){

			if(isElementPresent(locator)==true){
				return driver.findElement(locator);
			}
				return null;//searchEmployee(String x);
	 }*/
	/**
	 * Gets the WebElement by the chosen method
	 * @param locator
	 * @return WebElement if true, NULL otherwise
	 */
	/*static public WebElement getWebElementBy(By locator){
		try{
			
	
		if(isElementPresent(locator)==true){
			return driver.findElement(locator);
		}
			return null;
		}catch {
			
		}
		
	}*/
	
	/**
	 * Verify if specified web element is present
	 * @param locator
	 * @return True if successful, False otherwise
	 */
	/*default public boolean isElementPresent(By locator){
		try{
			driver.findElement(locator); 
			return true;
		}catch(NoSuchElementException ne){
			return false;
		}
		
	}*/

}
