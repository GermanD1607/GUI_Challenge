import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

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
        signUp.Register(props.getProperty("first_name"), props.getProperty("last_name"),
                props.getProperty("user_password"),props.getProperty("user_email"));
    }
    @DataProvider(name ="mails")
    public Object[][] endpoints(){
        return new Object[][]{
                {"invalid@mail"},
                {props.getProperty("login_mail")}
        };
    }
    @Test(dataProvider = "mails")
    public void loginTest(String email){
        driver.navigate().to(url);
        LogInPage login = new LogInPage(driver);
        login.Open();
        login.Log(email,props.getProperty("user_password"));
        SearchPage home = new SearchPage(driver);
        assertTrue(home.IconUser().isDisplayed());
    }
    @Test
    public void searchTest(){
        driver.navigate().to(url);
        SearchPage search = new SearchPage(driver);

        ResultsPage results = search.search(props.getProperty("search_data"));
        CerrarCookies();
        assertTrue(results.getItem(0).contains(props.getProperty("search_data")));
    }
    @Test
    public void categoriesTest(){
        driver.navigate().to(url);
        CategoriesPage categories = new CategoriesPage(driver);
        categories.select();
        categories.search();
        System.out.println("Titulo: "+categories.getTitle());
        assertTrue(categories.getTitle().equals(props.getProperty("browse_data")));
    }
    @Test
    public void filterPriceTest(){
        driver.navigate().to(url);
        SearchPage search = new SearchPage(driver);
        ResultsPage results = search.search(props.getProperty("filter_data"));
        results.filter();
        assertTrue(results.getPrice(0)>1200 && results.getPrice(0)<2300);
    }
    @Test
    public void orderPriceTest(){
        driver.navigate().to(url);
        SearchPage search = new SearchPage(driver);
        ResultsPage results = search.search(props.getProperty("order_data"));
        CerrarCookies();
        results.order();
        assertTrue(results.getPrice(0)>results.getPrice(1));
    }
}
