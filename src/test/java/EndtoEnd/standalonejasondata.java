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
	public void Mainordersubmit(String data) throws IOException
	{
		String[] a=data.split(",");
       initialize();// beforetest is used so  need to call here.
       LoginPage lp= new LoginPage(dr);
      // lp.loginapplication(hm.get("email"),hm.get("password"));
       lp.loginapplication(a[0],a[1]);

  
       Productselection ps= new Productselection(dr);
      // String productname= ps.getproductlist(hm.get("Product"));
       String productname= ps.getproductlist(a[2]);

       System.out.println("Product added to cart is :"+productname);
       ps.gettext();
       ps.getcartclick();
      
      
       MyCart mc= new MyCart(dr);
    //   mc.productcheck(hm.get("Product"));
       mc.productcheck(a[2]);
       mc.getcheckout();
      
       Payment m =new Payment(dr);
       m.selectcountry("ind");
       m.getcountrylist();
       Thankyoupage tp= m.placeorder();    //we can create object of next page in this page-last method.Instead of creating object here.
       String ordermessage=tp.orderplacedmessage();
       System.out.println("Message printed :"+ordermessage);
       Assert.assertEquals(ordermessage,"THANKYOU FOR THE ORDER.");
       
       
	}
	
 /*@Test(dataProvider="getdata1",dependsOnMethods= {"Mainordersubmit"})
	public void Myorders(HashMap<String,String>hk) throws IOException
	{
	     initialize();
		LoginPage lp= new LoginPage(dr);
        lp.loginapplication(hk.get("email"),hk.get("password"));
        OrderDetails od= new OrderDetails(dr);
        Assert.assertTrue(od.myorder(hk.get("Product")));
	} */
	
 @Test(dataProvider="getdata1",dependsOnMethods= {"Mainordersubmit"})
	public void Myorders(String b) throws IOException
	{
	    String[] a=b.split(",");
	    initialize();
		LoginPage lp= new LoginPage(dr);
        lp.loginapplication(a[0],a[1]);
        OrderDetails od= new OrderDetails(dr);
        Assert.assertTrue(od.myorder(a[2]));
	} 
	
	
	@DataProvider
	public String[] getdata1() throws IOException
	{
		List<HashMap<String, String>> testdata=readjsondata(System.getProperty("user.dir")+"\\src\\main\\java\\EndtoEnd\\Testdata.json");
		System.out.println("No of Users :"+testdata.size());
		String[] str= new String[testdata.size()] ;
		
		for (int i=0;i<testdata.size();i++)
		{
			HashMap<String, String> a=testdata.get(i);
			String email =a.get("email");
			String password= a.get("password");
			String product=a.get("Product");
			str[i]=email+","+password+","+product;
			
		}
		return str;
		

		
		//return new Object[][] {{testdata.get(0)},{testdata.get(1)}};
		
	}

}
