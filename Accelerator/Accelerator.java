package com.Accelerator;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Core.Framework;

public class Accelerator {

private WebDriver driver;
	
	public Accelerator(WebDriver driver){
		this.driver = driver;
	}
	
	// method name: JClickElement (JavaScript click)
	// purpose: use this method where normal webdriver failed to click due to some issue
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public  boolean JClickElement(By locator) throws Exception {
        try {            

            WebElement element = Framework.driver.findElement(locator);
            Framework.wait.until(ExpectedConditions.elementToBeClickable(element));
            JavascriptExecutor executor = (JavascriptExecutor)Framework.driver;
            executor.executeScript("arguments[0].scrollIntoView({behavior: \"instant\", block: \"center\", inline: \"nerest\"})", element);
            executor.executeScript("arguments[0].click();", element);

            Framework.logger.WriteLog("Clicked on Element : " + locator, true, false);            
            return  true;
        }catch (Exception e) {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	// method name: ClickElement
	// purpose: use this method to click any web element in web browser
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public  boolean ClickElement(By locator) throws Exception {
        try {
            WebElement element = Framework.driver.findElement(locator);
            Framework.wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Framework.logger.WriteLog("Clicked on Element : " + locator, true, false);            
            return  true;
        }catch (Exception e) {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	// method name: EnterText
	// purpose: use this method when to enter any data into web browser
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public  boolean EnterText(String strValue, By locator) throws Exception {
        try {
        	Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            WebElement element = Framework.driver.findElement(locator);
            element.clear();
            element.sendKeys(strValue);
            Framework.logger.WriteLog(   "Entered value "  +  strValue + "  in Text box : " +   locator ,  true , false);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
        }
    }
	
	// method name: EnterTextUsingJS
	// purpose: use this method when webdriver failed to enter data into web browser
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public  boolean EnterTextUsingJS(String strValue, By locator) throws Exception {
		try {
			Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			WebElement element = Framework.driver.findElement(locator);
			element.clear();
			JavascriptExecutor executor = (JavascriptExecutor)Framework.driver;
			executor.executeScript("arguments[0].scrollIntoView({behavior: \"instant\", block: \"center\", inline: \"nerest\"})", element);
			executor.executeScript("arguments[0].setAttribute('value', '"+strValue+"')", element);
			Framework.logger.WriteLog(   "Entered value "  +  strValue + "  in Text box : " +   locator ,  true , false);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Framework.logger.WriteLog(   "Unable to Find Element : " +   locator , false,true );
            return false;
		}
	}
	
	
	// method name: VerifyControlExist
	// purpose: use this method to verify whether element exist
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public  boolean VerifyControlExist(By locator) throws Exception {
    	boolean isControlExists = false;
        try {
        	
        	Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            WebElement element = Framework.driver.findElement(locator);
	            if (   element.isDisplayed()  )
	            {
	            	Framework.logger.WriteLog(   "Verified that control exists. Control locator : " +   locator ,  true , false);
		            isControlExists = true;
	            }else
	            {
	            	Framework.logger.WriteLog( "The control not exist. Control locator : " +   locator ,  false , false);
	            
	            }            
	        }catch (Exception e) {
	        	Framework.logger.WriteLog( "The control not exist. Control locator : " +   locator ,  false , false);
	            
	        }
        return isControlExists;
    }
	
	// method name: VerifyControlExist
	// purpose: use this method to highlight any web element in web browser, mostly use while giving any demo to client
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By
	// Return type: boolean
	
	public boolean highlightElement(By locator) throws Exception{
		
		try {
			Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			WebElement element = Framework.driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor)Framework.driver;
			executor.executeScript("arguments[0].setAttribute('style','border: solid 2px red;');", element);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.executeScript("arguments[0].setAttribute('style','border: solid 2px white;');", element);
			Framework.logger.WriteLog(   "Highlighted Element " +   locator ,  true , false);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Framework.logger.WriteLog( " Unable to Find Element : " +   locator ,  false , true);
			return false;
		}
	}
	
	// method name: verifyPDFContent
	// purpose: use this method to verify the content of pdf file
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: pdf file path & string value to validate
	// Return type: boolean
	
	public boolean verifyPDFContent(String pdfPath, String textToValidate) throws Exception, IOException{
		
		File file = new File(pdfPath);
		PDDocument document = PDDocument.load(file);
		
		PDFTextStripper pdfTextStripper = new PDFTextStripper();
		String text = pdfTextStripper.getText(document);
		
		if(text.contains(textToValidate)){
			Framework.logger.WriteLog(   textToValidate + " = is present in pdf " ,  true , false);
			document.close();
			return true;
		}else{
			Framework.logger.WriteLog(   textToValidate + " = is not present in pdf " ,  false , false);
			document.close();
			return false;
		}
		
	}
	
	
	// method name: isCheckBoxSelect
	// purpose: use this method to select or deselect the check box
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: org.openqa.selenium.By & boolean value
	// Return type: boolean
	
	public boolean isCheckBoxSelect(boolean isCheckBoxSelected, By locator) throws Exception{
		Framework.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		WebElement element = Framework.driver.findElement(locator);
		
		if(isCheckBoxSelected == true){
			if(!element.isSelected()){
				ClickElement(locator);
			}
		}else if(isCheckBoxSelected == false){
			if(!element.isSelected()){
				
			}else{
				ClickElement(locator);
			}
		}
		Framework.logger.WriteLog( " Checkbox found " + locator ,  true , true);
		return true;
	}
	
	// method name: waitTillElementDisappear
	// purpose: use this method to wait web driver until specific text is not disappear (Max wait time 10mins)
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Input Required: text value
	
	public void waitTillElementDisappear(String elementText){
		
		try {
			String bodyText = null;
			WebElement element = driver.findElement(By.tagName("body"));
			bodyText = element.getText();
			int count = 0;
			do {
				Thread.sleep(2000);
				count = count+2;
				if(bodyText.replaceAll(" ", "").toLowerCase().contains(elementText.toLowerCase())){
					System.out.println("Wait for "+elementText+" to be disappear!");
					if(count >= 300){
						Framework.logger.WriteLog( " Application performance is too slow! " ,  true , true);
						Assert.assertFalse(true);
					}
				}
			} while (element.getSize().getHeight() > 0);
		} catch (Exception e) {
			
		}
	}
	
	// method name: CaptureScreenShot
	// purpose: use this method to capture screen shot of web application page
	// created: Mar 08, 2019
	// Author: Sourabh Mondal
	// Return Type: string - file path with screen shot file
	
	public String CaptureScreenShot() {
		
		DateFormat format = new SimpleDateFormat("dd_MMM_YY");
		Date dateobj = new Date();
		String strDirName = format.format(dateobj);
		File file = new File(System.getProperty("user.dir")+"\\Log\\"+ strDirName);
		if( ! file.exists()){
			file.mkdir();
		}
		format = new SimpleDateFormat("HH_mm_ss");
		dateobj = new Date();
		String strFileName = format.format(dateobj)+".png";
		String strCompleteFileName = System.getProperty("user.dir")+"\\Logs\\"+ strDirName +"\\"+ strFileName;
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
		FileUtils.copyFile(scrFile, new File(strCompleteFileName));
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			strCompleteFileName = "";
		}
		return strCompleteFileName;
	}
}
