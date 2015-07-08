package br.com.caelum.agiletickets.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleTest {
	
	@Test
	public void buscaFunciona() throws InterruptedException{
		FirefoxDriver driver = new FirefoxDriver();
		
		driver.get("http://www.google.com.br");
		
		WebElement txt = driver.findElement(By.name("q"));
		
		txt.sendKeys("3 libertadores");
		txt.submit();
		
//		Thread.sleep(7000);
		
		Assert.assertTrue(driver.getPageSource().contains("São Paulo Futebol Clube"));
		
//		Assert.assertFalse(driver.getPageSource().contains("Corinthians"));
		
		driver.close();
	}

}
