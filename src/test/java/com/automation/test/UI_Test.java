package com.automation.test;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.core.ExtentTestNGListener;
import com.automation.pages.HomePage;
import com.automation.webdriver.core.BaseTest;
 
@Listeners(ExtentTestNGListener.class)
public class UI_Test extends BaseTest {
    
    
    @Test
    public void addToCartAndPlaceOrder() throws Exception{
        HomePage homePage=new HomePage(getDriver());
        homePage.clickCategory("Laptops");
        homePage.clickItem("Sony vaio i5");
        String price=homePage.getItemAmountFromDetailPage();
        homePage.clickAddtoCart();
        homePage.acceptAlert();
        homePage.clickHomeTab();
        homePage.clickCategory("Laptops");
        homePage.clickItem("Dell i7 8gb");
        homePage.clickAddtoCart();
        homePage.acceptAlert();
        homePage.clickCartTab();
        assertTrue(homePage.checkCartItemPresent("Dell i7 8gb"), "Dell i7 8gb is not displayed in the cart");
        homePage.clickCartItemDelete("Dell i7 8gb");
        assertTrue(homePage.checkCartItemNotPresent("Dell i7 8gb"), "Not able to delete Dell i7 8gb from the cart");
        assertTrue(homePage.checkCartItemPresent("Sony vaio i5"), "Sony vaio i5 is not added in the cart ");
        homePage.clickPlaceOrder();
        assertTrue(homePage.checkPlaceOrderModalBox(), "Place Order modal box is not displayed");
        homePage.enterNameforOrder("testOrder");
        homePage.enterCountryforOrder("India");
        homePage.enterCityforOrder("Noida");
        homePage.enterCreditCardforOrder("1234567812345678");
        homePage.enterYearField("2020");
        homePage.enterMonthField("November");
        homePage.clickPurchaseButton();
        boolean result=homePage.checkPlaceOrderDetails(price);
        homePage.clickOkBtn();
        assertTrue(result, "Promo Banner details are not present");
    }
    
}

