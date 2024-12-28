package api.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AgiliboTest extends Base{
	@Test
	public void name() {
		driver.get("https://app.devagilibo.com/");
		driver.findElement(By.xpath("(//a[normalize-space()='Login / Sign up'])[1]")).click();
		
		
	}

}
