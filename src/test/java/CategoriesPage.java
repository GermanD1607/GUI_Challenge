import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CategoriesPage{
    private WebDriver driver;

    private By title = By.cssSelector("h1");
    private final By menu = By.cssSelector(".cms-facets");

    public CategoriesPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(menu,0));
        return driver.findElement(title).getText();
    }
}
