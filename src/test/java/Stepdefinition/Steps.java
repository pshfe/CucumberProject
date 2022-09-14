package Stepdefinition;

import java.io.IOException;

import org.junit.Assert;

import EndtoEnd.BaseTest;
import EndtoEnd.LoginPage;
import EndtoEnd.MyCart;
import EndtoEnd.OrderDetails;
import EndtoEnd.Payment;
import EndtoEnd.Productselection;
import EndtoEnd.Thankyoupage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps extends BaseTest
{
	
public LoginPage lp;
public Productselection ps;
public  MyCart mc;
public Payment m ;
public Thankyoupage tp;
public OrderDetails od;
@Given("I landed on Ecommerce Website")	
public void I_landed_on_Ecommerce_Website() throws IOException
{
	initialize();
}


@Given("^Login with valid emailid (.+) and password (.+)$")
public void Login_with_valid_emailid_and_password (String email,String password) throws IOException
{
	
    lp= new LoginPage(dr);
    lp.loginapplication(email,password);
}

@When("^Add Products (.+) into the cart$")
public void When_Add_Products_into_the_cart(String Product)
{
	ps= new Productselection(dr);
    String productname= ps.getproductlist(Product);
    System.out.println("Product added to cart is :"+productname);
    ps.gettext();
    ps.getcartclick();
}

@And("^Checkout (.+) and Submit the order$")
public void And_Checkout_and_Submit_the_order(String Product)
{
	 mc= new MyCart(dr);
     mc.productcheck(Product);
     mc.getcheckout();
     m =new Payment(dr);
     m.selectcountry("ind");
     m.getcountrylist();
     tp= m.placeorder();
}

@Then("{string} is displayed on Confirmation Page")
public void check_confirmation_message(String string)
{
    String ordermessage=tp.orderplacedmessage();
    System.out.println("Message printed :"+ordermessage);
    Assert.assertEquals(ordermessage,string);
    dr.quit();
}

@When("^check for the (.+) in cart$")
public void check_for_the_Product_in_cart(String Product)
{
	 od= new OrderDetails(dr);
	 od.myorder(Product);
}

@Then("^verify the assertions for proper (.+) in cart$")
public void verify_the_assertions_for_proper_product_in_cart(String Product)
{
	Assert.assertTrue(od.myorder(Product));
	dr.quit();
}
}
