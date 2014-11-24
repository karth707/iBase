package com.iBase.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SettingsPassedTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://ec2-54-187-126-153.us-west-2.compute.amazonaws.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSettingsPassed() throws Exception {
	  driver.get(baseUrl + "/iBase/login");
	  driver.findElement(By.name("userId")).clear();
	  driver.findElement(By.name("userId")).sendKeys("jake@asu.edu");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("password1");
  	  driver.findElement(By.name("submit")).click();
  	  assertEquals("iBase - Image Database", driver.getTitle());
  	  driver.get(baseUrl + "/iBase/settings");
  	  driver.findElement(By.name("profileImageFile")).sendKeys("/Users/KartheekGanesh/Sources/iBaseTestImages/sparky.jpg");
  	  driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  	  assertEquals("Settings", driver.findElement(By.linkText("Settings")).getText());
  	  assertEquals("Upload", driver.findElement(By.linkText("Upload")).getText());
  	  assertEquals("Home", driver.findElement(By.linkText("Home")).getText());
  	  assertEquals("Logout", driver.findElement(By.linkText("Logout")).getText());
  	  assertEquals("jake@asu.edu", driver.findElement(By.cssSelector("em")).getText());
  	  assertEquals("Update your profile picture!", driver.findElement(By.cssSelector("h3")).getText());
  	  assertEquals("Profile picture successfully updated!", driver.findElement(By.cssSelector("p")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
