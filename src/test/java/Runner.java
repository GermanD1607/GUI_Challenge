import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Runner extends Hooks{
    private final String url="https://reverb.com";
    private final By cookies = By.cssSelector(".intl-settings-nag__close");
    private Properties props = new Properties();
    public Runner() {
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var2) {
            System.out.println("Hay un error leyendo el archivo de properties");
        }
    }

    public void CerrarCookies(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cookies,0));
        driver.findElement(cookies).click();
    }

    @Test
    public void signUpTest(){
        driver.navigate().to(url+"/signup");
        SignUpPage signUp = new SignUpPage(driver);
        CerrarCookies();
        HomePage home = signUp.Register(props.getProperty("first_name"), props.getProperty("last_name"),
                props.getProperty("user_password"),props.getProperty("user_email"));
        assertTrue(home.GetUserIcon().isDisplayed());
        home.LogOut();
    }
    @DataProvider(name ="mails")
    public Object[][] testData(){
        return new Object[][]{
                {"invalid@mail"},
                {props.getProperty("login_mail")}
        };
    }
    @Test(dataProvider = "mails")
    public void loginTest(String email){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        LogInPage login = home.OpenLogin();
        home = login.Log(email,props.getProperty("user_password"));
        if(email.contains("invalid")){
            assertTrue(home.GetLoginButton().isDisplayed());
        }else {
            assertTrue(home.GetUserIcon().isDisplayed());
            home.LogOut();
        }
    }
    @Test
    public void searchTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.Search(props.getProperty("search_data"));
        CerrarCookies();
        assertTrue(results.GetItem(0).contains(props.getProperty("search_data")));
    }
    @Test
    public void categoriesTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        home.SelectCategory();
        CategoriesPage categories = home.SelectElement();
        assertTrue(categories.getTitle().equals(props.getProperty("browse_data")));
    }
    @Test
    public void filterPriceTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.Search(props.getProperty("filter_data"));
        CerrarCookies();
        results.Filter();
        assertTrue(results.GetPrice(0)>1200 && results.GetPrice(0)<2300);
    }
    @Test
    public void orderPriceTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.Search(props.getProperty("order_data"));
        CerrarCookies();
        results.Order();
        assertTrue(results.GetPrice(0)>results.GetPrice(1));
    }
}
