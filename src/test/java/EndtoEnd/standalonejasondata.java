package EndtoEnd;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalonejasondata extends BaseTest {

	
	//String name="IPHONE 13 PRO";
	
	@Test(dataProvider="getdata1",groups={"providingdata"})
	public void Mainordersubmit(HashMap<String,String>hm) throws IOException
	{
       initialize();// beforetest is used so  need to call here.
       LoginPage lp= new LoginPage(dr);
       lp.loginapplication(hm.get("email"),hm.get("password"));
      
  
       Productselection ps= new Productselection(dr);
       String productname= ps.getproductlist(hm.get("Product"));
       System.out.println("Product added to cart is :"+productname);
       ps.gettext();
       ps.getcartclick();
      
      
       MyCart mc= new MyCart(dr);
       mc.productcheck(hm.get("Product"));
       mc.getcheckout();
      
       Payment m =new Payment(dr);
       m.selectcountry("ind");
       m.getcountrylist();
       Thankyoupage tp= m.placeorder();    //we can create object of next page in this page-last method.Instead of creating object here.
       String ordermessage=tp.orderplacedmessage();
       System.out.println("Message printed :"+ordermessage);
       Assert.assertEquals(ordermessage,"THANKYOU FOR THE ORDER.");
       
       
	}
	
 @Test(dataProvider="getdata1",dependsOnMethods= {"Mainordersubmit"})
	public void Myorders(HashMap<String,String>hk) throws IOException
	{
	     initialize();
		LoginPage lp= new LoginPage(dr);
        lp.loginapplication(hk.get("email"),hk.get("password"));
        OrderDetails od= new OrderDetails(dr);
        Assert.assertTrue(od.myorder(hk.get("Product")));
	} 
	
	
	
	@DataProvider
	public Object[][] getdata1() throws IOException
	{
		List<HashMap<String, String>> testdata=readjsondata(System.getProperty("user.dir")+"\\src\\main\\java\\EndtoEnd\\Testdata.json");
		return new Object[][] {{testdata.get(0)},{testdata.get(1)}};
	}

}
