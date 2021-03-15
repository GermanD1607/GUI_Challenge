import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    private final By userName = By.id("user_session_login");
    private final By userPass = By.id("user_session_password");
    private final By menu = By.cssSelector(".site-header__nav__link.site-header__nav__link--login");
    private By btnLogin = By.cssSelector(".session-form .button.button--orange.width-100");

    private WebDriver driver;

    public LogInPage(WebDriver driver){
        this.driver = driver;
    }

    public void Open(){
        driver.findElement(menu).click();
    }
    public void Log(String name, String pass){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(userName,0));
        driver.findElement(userName).sendKeys(name);
        driver.findElement(userPass).sendKeys(pass);
        driver.findElement(btnLogin).click();
    }
}
