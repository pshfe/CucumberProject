package EndtoEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowAuthenticationPopup {

	public static void main(String[] args)
	
	{
 
		WebDriverManager.chromedriver().setup();
		WebDriver dr= new ChromeDriver();
		//dr.get("https://the-internet.herokuapp.com/");
		dr.get("http://admin:admin@the-internet.herokuapp.com/");
		dr.findElement(By.linkText("Basic Auth")).click();
	}

}
