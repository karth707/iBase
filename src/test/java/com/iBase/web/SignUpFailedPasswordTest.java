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

public class SignUpFailedPasswordTest {
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
  public void testSignUpFailedOnInvalidPassword() throws Exception {
    driver.get(baseUrl + "/iBase/signup");
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Jake");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Krammer");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("jake123@asu.edu");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass1");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("password must be atleast 6 characters!", driver.findElement(By.cssSelector("p")).getText());
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