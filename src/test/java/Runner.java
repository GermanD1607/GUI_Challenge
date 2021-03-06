import Pages.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.AssertJUnit.assertTrue;

public class Runner extends Hooks {
    private final Logger log = Logger.getLogger(Runner.class);
    private final String url="https://reverb.com";
    private Properties props = new Properties();
    public Runner() {
        BasicConfigurator.configure();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var) {
            log.error("No fue posible leer el archivo");
        }
    }

    @Test
    public void signUpTest(){
        driver.navigate().to(url+"/signup");
        SignUpPage signUp = new SignUpPage(driver);
        closeCookies();
        HomePage home = signUp.register(System.getenv("first_name"), System.getenv("last_name"),
                System.getenv("user_password"), System.getenv("user_email"));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/signUpTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(home.getUserIcon().isDisplayed());

        home.logOut();
    }
    @Test
    public void validLoginTest() {
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        LogInPage login = home.openLogin();
        home = login.log(System.getenv("login_mail"), System.getenv("user_password"));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/validLoginTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(home.getUserIcon().isDisplayed());

        home.logOut();
    }
    @Test
    public void invalidLoginTest() {
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        LogInPage login = home.openLogin();
        String email = "invalid@mail";
        home = login.log(email, System.getenv("user_password"));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/invalidLoginTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(home.getLoginButton().isDisplayed());
    }
    @Test
    public void searchTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.search(props.getProperty("search_data"));
        closeCookies();
        log.info(results.getItemTitle(0));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/searchTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(results.getItemTitle(0).contains(props.getProperty("search_data")));
    }
    @Test
    public void categoriesTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        home.selectCategory();
        CategoriesPage categories = home.selectElement();
        log.info(categories.getTitle());
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/categoriesTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(categories.getTitle().equals(props.getProperty("browse_data")));
    }
    @Test
    public void filterPriceTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.search(props.getProperty("filter_data"));
        closeCookies();
        results.filter();
        log.info(results.getPrice(0));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/filterPriceTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(driver.getCurrentUrl().contains("price_min=1200&price_max=2300"));
    }
    @Test
    public void orderPriceTest(){
        driver.navigate().to(url);
        HomePage home = new HomePage(driver);
        ResultsPage results = home.search(props.getProperty("order_data"));
        closeCookies();
        results.order();
        log.info(results.getPrice(4));
        log.info(results.getPrice(5));
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:/Users/gvanegas/GUI_Challenge/screenshots/orderPriceTest.png"));
        } catch (IOException e) {
            log.error(e);
        }
        assertTrue(results.getPrice(4)>results.getPrice(5));
    }
}
