import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage{

    private final By firstName = By.id("user_first_name");
    private final By lastName = By.id("user_last_name");
    private final By email = By.id("user_email");
    private final By password = By.id("user_password");
    private By btnSignUp = By.cssSelector(".session-form .button.button--orange.width-100");
    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage Register(String fn,String ln,String pass,String mail){

        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        //driver.findElement(email).sendKeys(mail);
        //driver.findElement(password).sendKeys(pass);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnSignUp,0));
        driver.findElements(btnSignUp).get(0).click();
        return new HomePage(driver);
    }
}
