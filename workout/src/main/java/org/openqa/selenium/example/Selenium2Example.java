package org.openqa.selenium.example;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example  {
	static WebDriver driver;
	static WebDriverWait wait;
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
    	
    	String PROXY = "172.25.188.188:8085";

    	org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
    	proxy.setHttpProxy(PROXY)
    	     .setFtpProxy(PROXY)
    	     .setSslProxy(PROXY);
    	DesiredCapabilities cap = new DesiredCapabilities();
    	cap.setCapability(CapabilityType.PROXY, proxy);  	
    	
        driver = new FirefoxDriver(cap);

        driver.manage().window().maximize(); driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        wait = new WebDriverWait(driver, 5);
        
        // And now use this to visit Google
        driver.get("http://www.tnstc.in/TNSTCOnline/");
        
        try {
			Runtime.getRuntime().exec("C:\\Users\\m1031388\\Desktop\\zsclarlogin.exe");
		} catch (IOException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Before Element Reading Thread Sleep");
        
        try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
        
		System.out.println("Going to Read Element - Thread Sleep Completed.");
		
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement userNameInputText = driver.findElement(By.name("txtUserLoginID"));
        WebElement passwordInputText = driver.findElement(By.name("txtPassword"));

        userNameInputText.clear();
        // Enter something to search for
        userNameInputText.sendKeys("ero_subu");
        
        passwordInputText.clear();
        passwordInputText.sendKeys("subujava");
        
        
        driver.findElement(By.linkText("Sign In")).click();   

/*        // Now submit the form. WebDriver will find the form for us from the element
        userNameInputText.submit();*/

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Current URL :"+driver.getCurrentUrl());
        
        By matchStartPlaceTagText = By.id("matchStartPlace");
        By matchEndPlaceTagText = By.id("matchEndPlace");
        
        //Selecting From 
		wait.until(ExpectedConditions.presenceOfElementLocated(matchStartPlaceTagText));
		WebElement matchStartPlaceTextBoxElement = driver.findElement(matchStartPlaceTagText);
		matchStartPlaceTextBoxElement.sendKeys("CHENNAI");
		selectOptionWithText("CHENNAI CMBT",1);
		
		System.out.println("From Selection Completd.");
		
		//Selecting To
		wait.until(ExpectedConditions.presenceOfElementLocated(matchEndPlaceTagText));
		WebElement matchEndPlaceTextBoxElement = driver.findElement(matchEndPlaceTagText);
		matchEndPlaceTextBoxElement.sendKeys("erod");
		selectOptionWithText("ERODE",2);
		
		System.out.println("To Selection Completd.");
		
		//Selecting Journy Date 
		WebElement txtdeptDateRtripTextBoxElement = driver.findElement(By.id("deptDateRtripimgExact"));
		new Actions(driver).moveToElement(txtdeptDateRtripTextBoxElement).perform();
		//txtdeptDateRtripTextBoxElement.click();
		txtdeptDateRtripTextBoxElement.sendKeys("07/01/2016");
		//selectJQueryDate("7");
		
		
		
        //Close the browser
        //driver.quit();
    }
    
public static void selectJQueryDate(String date) {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modifcal")));
		WebElement table = driver.findElement(By.className("calendar")); 
		System.out.println("JQuery Calendar Dates");
		
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
				for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));
			
			for (WebElement cell : cells) {
				if (cell.getText().equals(date)) {
					driver.findElement(By.linkText(date)).click();
				}
			}
		}
	}
	
	public static void selectOptionWithText(String textToSelect,int tagNum) {
		try {
			WebElement autoOptions = driver.findElement(By.id("ui-id-"+tagNum));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			
			System.out.println("Before For Loop");
			
			for(WebElement option : optionsToSelect){
				
				System.out.println("Options :"+option.getText());
				
		        if(option.getText().equals(textToSelect)) {
		        	System.out.println("Trying to select: "+textToSelect);
		            option.click();
		            break;
		        }
		    }
			
		} catch (NoSuchElementException e) {
			System.out.println("selectOptionWithText - "+textToSelect +" - "+ e.getStackTrace());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("selectOptionWithText - "+textToSelect +" - "+ e.getStackTrace());
			e.printStackTrace();
		}
	}
	
public void selectOptionWithIndex(int indexToSelect) {
		
		try {
			WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
		        if(indexToSelect<=optionsToSelect.size()) {
		        	System.out.println("Trying to select based on index: "+indexToSelect);
		           optionsToSelect.get(indexToSelect).click();
		        }
		} 		
		catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

}	