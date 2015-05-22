package edu.pages;

import org.junit.Test;
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
	
	By forgotPass = By.xpath(".//*[@id='js_hide_popup_login']");
	//form
	By email = By.className("js_forgot_password_email");
	By submitButton = By.className("btn");
	By successMessage = By.className("js_forgot_password_content_2" );
	By errorMes2 = By.className("errorText");
	 
	
	
	
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
		
		
		// Customer 1
		public void loginAsCustomer1(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Cust.23.02@i.ua");
			//Fill password
			this.setPassword("test11");
			//Click Login button
			this.clickLoginButton();				
		
				}
		
		
		
		//Customer 2
		public void loginAsCustomer2(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Cust.23.02@i.ua");
			//Fill password
			this.setPassword("5e2eee");
			//Click Login button
			this.clickLoginButton();		
		
		}
		
		
		public void loginAsWriter1(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Write.23.02@i.ua");
			//Fill password
			this.setPassword("test11");
			//Click Login button
			this.clickLoginButton();	
		}
		
		public void loginAsWriter2(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Cust.23.02@i.ua");
			//Fill password
			this.setPassword("5e2eee");
			//Click Login button
			this.clickLoginButton();		
		}
		
		public void loginAsWriter3(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Cust.23.02@i.ua");
			//Fill password
			this.setPassword("5e2eee");
			//Click Login button
			this.clickLoginButton();		
		}
		
		
		public void loginAsDeactivatedUser(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("svetwrite@i.ua");
			//Fill password
			this.setPassword("a505e6");
			//Click Login button
			this.clickLoginButton();		
		}
		
		public void loginWithWrongPass(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("Cust.23.02@i.ua");
			//Fill password
			this.setPassword("0000");
			//Click Login button
			this.clickLoginButton();		
		}
		
		public void loginWithWrongUserName(String strUserName,String strPasword){
			//open pop-up
			this.clickLogin(); 
			//Fill user name
			this.setUserName("xxx@i.ua");
			//Fill password
			this.setPassword("5e2eee");
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
//Forgot Password methods
			
			
			public void clickFPlink(){
				driver.findElement(forgotPass).click();
				}
			public void clickSubmit(){
				driver.findElement(submitButton).click();
			}
			public void assertError(){
				driver.findElement(errorMes2).getText().contains("This is an obligatory field.");
			}
			public void setEmail(String strEmail){
				driver.findElement(email).sendKeys("user@email");
			}
			public void assertSuccMessage(){
				driver.findElement(successMessage).getText().contains("We have just sent temporary password to your email.Use these details to login.");
			}
			
			
			
			public void forgotPass (String strEmail){
				this.clickLogin();
				this.clickFPlink();
				this.setEmail(null);
				this.assertSuccMessage();
			}
				
			public void forgotPassNoEmail(){
				this.clickLogin();
				this.clickFPlink();
				this.clickSubmit();
				this.assertError();
			}

}

					
		
	
	
