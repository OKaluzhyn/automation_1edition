package edu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class AuthPage {
	
	FirefoxDriver driver;
	By login = By.className("login-link");
	By userName = By.id("_username");
	By password = By.id("_password");
	By loginButton = By.name("login");
	By errorMessage = By.className("errorText");
	
	
	public AuthPage(FirefoxDriver driver){
		this.driver = driver;
	}
	
	

	public void clickLogin(){
		driver.findElement(login).click();
	}
	
	//Set user name in textbox
		public void setUserName(String strUserName){
			driver.findElement(userName).sendKeys(strUserName);
		}
		
		//Set password in password textbox
		public void setPassword(String strPassword){
			 driver.findElement(password).sendKeys(strPassword);
		}
		
		//Click on login button
		public void clickLoginButton(){
				driver.findElement(loginButton).click();
		}
		public void loginToAuthPage(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName(strUserName);
			//Fill password
			this.setPassword(strPasword);
			//Click Login button
			this.clickLoginButton();				
		}
				
			public boolean isErrorMessagePresent() {
								try{

					driver.findElement(errorMessage);

				return true;

				} catch(ElementNotVisibleException ex){

				return false;

				}

				}

				 
			
			    
			    }
			
		
	
	
