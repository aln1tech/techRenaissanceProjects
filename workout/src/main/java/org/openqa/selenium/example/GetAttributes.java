package org.openqa.selenium.example;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetAttributes {

	public WebDriver driver;
	private By bySearchButton = By.name("btnK");
							
	@BeforeClass
	public void setUp() {
		
		String PROXY = "172.25.188.188:8085";

    	org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
    	proxy.setHttpProxy(PROXY)
    	     .setFtpProxy(PROXY)
    	     .setSslProxy(PROXY);
    	DesiredCapabilities cap = new DesiredCapabilities();
    	cap.setCapability(CapabilityType.PROXY, proxy);  	
    	
        driver = new FirefoxDriver(cap);

        // And now use this to visit Google
        driver.get("http://www.google.com");
        
        try {
			Runtime.getRuntime().exec("C:\\Users\\m1031388\\Desktop\\zsclarlogin.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
					
	}

	@Test
	public void getAttribute_ButtonName() {
		WebElement googleSearchBtn = driver.findElement(bySearchButton);
		System.out.println("Name of the button is:- " +googleSearchBtn.getAttribute("name"));
	}

	@Test
	public void getAttribute_Id() {
		WebElement googleSearchBtn = driver.findElement(bySearchButton);
		System.out.println("Id of the button is:- "+ googleSearchBtn.getAttribute("id"));
	}

	@Test
	public void getAttribute_class() {

		WebElement googleSearchBtn = driver.findElement(bySearchButton);
		System.out.println("Class of the button is:- "+ googleSearchBtn.getAttribute("class"));

	}

	@Test
	public void getAttribute_InvalidAttribute() {

		WebElement googleSearchBtn = driver.findElement(bySearchButton);
		//Will return null value as the 'status' attribute doesn't exists
		System.out.println("Invalid Attribute status of the button is:- "+ googleSearchBtn.getAttribute("status"));
	}
	
	@Test
	public void getAttribute_ButtonLabel() {

		WebElement googleSearchBtn = driver.findElement(bySearchButton);
		System.out.println("Label of the button is:- "+ googleSearchBtn.getAttribute("aria-label"));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}