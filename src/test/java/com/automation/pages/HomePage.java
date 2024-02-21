package com.automation.pages;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.automation.webdriver.utility.Utility;

public class HomePage extends BasePage{
    Utility utility=new Utility();
    WebDriver driver;



    public HomePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    /**
     * click category link
     * @author ngupta
     * @param category
     * @throws Exception 
     */
    public void clickCategory(String category) throws Exception {
        for(int i=0;i<lnkCategories.size();i++) {
            if(lnkCategories.get(i).getText().contains(category))
                utility.clickElement(lnkCategories.get(i));
        }
    }


    /**
     * click Add to Cart button
     * @author ngupta
     * @throws IOException
     */
    public void clickAddtoCart() throws IOException{
        utility.clickElement(btnAddToCart);
    }


    /**
     * click Home tab
     * @author ngupta
     * @throws IOException
     */
    public void clickHomeTab() throws IOException{
        utility.clickElement(tabHome);
    }



    /**
     * click Cart tab
     * @author ngupta
     * @throws IOException
     */
    public void clickCartTab() throws IOException{
        utility.clickElement(tabCart);
    }


    /**
     * accept alert
     * @author ngupta
     */
    public void acceptAlert() {
        utility.acceptAlert();
    }


    /**
     * click Cart item
     * @author ngupta
     * @param cardName
     * @throws IOException
     */
    public void clickCartItemDelete(String cardName)throws IOException {
        utility.clickElement(driver.findElement(By.xpath("//tbody/tr[td[contains(.,'"+cardName+"')]]/td[4]/a")));
    }


    /**
     * check added item present in cart
     * @author ngupta
     * @param cardName
     * @return boolean
     */
    public boolean checkCartItemPresent(String cardName) {
        return utility.isElementDisplayed(driver.findElement(By.xpath("//tbody/tr[td[contains(.,'"+cardName+"')]]/td[4]/a")));
    }


    /**
     * check added item not present in cart
     * @author ngupta
     * @param cardName
     * @return boolean
     */
    public boolean checkCartItemNotPresent(String cardName) {
        return utility.isElementNotDisplayed(driver.findElement(By.xpath("//tbody/tr[td[contains(.,'"+cardName+"')]]/td[4]/a")));
    }



    /**
     * Enter name for order
     * @author ngupta
     * @param  name
     * @throws IOException
     */
    public void enterNameforOrder(String name) throws IOException{
        utility.enterInput(inputNameField,name);
    }


    /**
     * Enter country for order
     * @author ngupta
     * @param  country
     * @throws IOException
     */
    public void enterCountryforOrder(String country) throws IOException{
        utility.enterInput(inputCountryField,country);
    }


    /**
     * Enter city for order
     * @author ngupta
     * @param city
     * @throws IOException
     */
    public void enterCityforOrder(String city) throws IOException{
        utility.enterInput(inputCityField,city);
    }


    /**
     * Enter credit card information for order
     * @author ngupta
     * @param card
     * @throws IOException
     */
    public void enterCreditCardforOrder(String card) throws IOException{
        utility.enterInput(inputCardField,card);
    }


    /**
     * Enter year for the order
     * @author ngupta
     * @param year
     * @throws IOException
     */
    public void enterYearField(String year) throws IOException{
        utility.enterInput(inputYearField,year);
    }


    /**
     * Enter month for order
     * @author ngupta
     * @param month
     * @throws IOException
     */
    public void enterMonthField(String month) throws IOException{
        utility.enterInput(inputMonthField,month);
    }



    /**
     * click Place order button
     * @author ngupta
     * @throws IOException
     */
    public void clickPlaceOrder() throws IOException{
        utility.clickElement(btnPlaceOrder);
    }


    /**
     * check place order modal box appears
     * @author ngupta
     * @return boolean
     */
    public boolean checkPlaceOrderModalBox() {
        return utility.isElementDisplayed(modalDialogBox);
    }


    /**
     * click purchase button
     * @author ngupta
     * @throws IOException
     */
    public void clickPurchaseButton() throws IOException{
        utility.clickElement(btnPurchase);
    }


    /**
     * check Thank you message displayed
     * @author ngupta
     */
    public void checkThankYouMessageDisplayed() {
        utility.isElementDisplayed(txtThankYouForPurchase);
    }


    /**
     * check placed order text
     * @author ngupta
     * @param amount
     * @return boolean
     */
    public boolean checkPlaceOrderDetails(String amount) {
        return txtPurchasedOrder.getText().contains(amount);
    }


    /**
     * click OK button
     * @author ngupta
     * @throws IOException
     */
    public void clickOkBtn() throws IOException{
        utility.clickElement(btnOk);
    }


    /**
     * click item
     * @author ngupta
     * @param item
     * @throws IOException
     */
    public void clickItem(String item) throws IOException{
        utility.clickElement(driver.findElement(By.xpath("//h4/a[contains(.,'"+item+"')]"))) ;
    }


    /**
     * Get item amount from detail page
     * @author ngupta
     * @return boolean
     * @throws IOException
     */
    public String getItemAmountFromDetailPage() throws IOException{
        return utility.getElementText(driver.findElement(By.xpath("//h3"))).split(" ")[0].replace("$","");
    }




    @FindBy(xpath="//a[contains(.,'Laptops')]")
    private WebElement lnkLaptop;

    @FindBy(xpath="//a[contains(text(),'Add to cart')]")
    private WebElement btnAddToCart;

    @FindBy(xpath="//a[contains(text(),'Home')]")
    private WebElement tabHome;

    @FindBy(xpath="//a[contains(text(),'Cart')]")
    private WebElement tabCart;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement btnPlaceOrder;

    @FindBy(css="input#name")
    private WebElement inputNameField;

    @FindBy(css="input#country")
    private WebElement inputCountryField;

    @FindBy(css="input#city")
    private WebElement inputCityField;

    @FindBy(css="input#card")
    private WebElement inputCardField;

    @FindBy(css="input#year")
    private WebElement inputYearField;

    @FindBy(css="input#month")
    private WebElement inputMonthField;

    @FindBy(xpath="//button[contains(.,'Purchase')]")
    private WebElement btnPurchase;

    @FindBy(xpath="//div[contains(@style,'display: block') and @id='orderModal']")
    private WebElement modalDialogBox;

    @FindBy(xpath="//h2[contains(.,'Thank you for your purchase!')]")
    private WebElement txtThankYouForPurchase;

    @FindBy(xpath="//p[contains(@class,'lead')]")
    private WebElement txtPurchasedOrder;

    @FindBy(xpath="//button[contains(.,'OK')]")
    private WebElement btnOk;

    @FindBy(xpath="//a[contains(.,'CATEGORIES')]")
    private WebElement hdrCategories;

    @FindBy(xpath="//div[a[contains(.,'CATEGORIES')]]/a")
    private List<WebElement> lnkCategories;
}



