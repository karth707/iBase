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

public class UploadPassedTest {
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
  public void testUploadPassed() throws Exception {
	  driver.get(baseUrl + "/iBase/login");
	  driver.findElement(By.name("userId")).clear();
	  driver.findElement(By.name("userId")).sendKeys("jake@asu.edu");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("password1");
  	  driver.findElement(By.name("submit")).click();
  	  assertEquals("iBase - Image Database", driver.getTitle());
  	  driver.get(baseUrl + "/iBase/upload");
  	  driver.findElement(By.name("imageFile")).sendKeys("/Users/KartheekGanesh/Sources/iBaseTestImages/sparky.jpg");
  	  driver.findElement(By.id("name")).sendKeys("ASU Logo");
  	  assertEquals("Home", driver.findElement(By.linkText("Home")).getText());
  	  assertEquals("iBase - Image Database", driver.getTitle());
  	  assertEquals("Upload", driver.findElement(By.linkText("Upload")).getText());
  	  assertEquals("Settings", driver.findElement(By.xpath("//li[3]")).getText());
  	  assertEquals("Logout", driver.findElement(By.linkText("Logout")).getText());
  	  assertEquals("jake@asu.edu", driver.findElement(By.cssSelector("em")).getText());
  	  assertEquals("Share your photo with the world!", driver.findElement(By.cssSelector("h3")).getText());
  	  assertEquals("", driver.findElement(By.cssSelector("input[type=\"submit\"]")).getText());
  	  assertEquals("Title:", driver.findElement(By.id("imageFile")).getText());
  	  driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
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