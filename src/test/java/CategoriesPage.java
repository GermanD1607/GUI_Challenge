import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CategoriesPage{
    private WebDriver driver;

    private By title = By.cssSelector("h1");

    private By btnCategory = By.cssSelector(".category-flyout-header__link");
    private By btnResult = By.cssSelector(".category-flyout__column__section a");

    public CategoriesPage(WebDriver driver){
        this.driver = driver;
    }

    public void select(){
        driver.findElements(btnCategory).get(4).click();
    }

    public void search(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnResult,1));
        driver.findElements(btnResult).get(0).click();
    }

    public String getTitle(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(title,0));
        return driver.findElement(title).getText();
    }
}
