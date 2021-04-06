package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final By searchBar = By.cssSelector(".site-search__controls__input");
    private By btnSearch = By.cssSelector(".site-search__controls__submit");
    private By btnLogin = By.cssSelector(".site-header__nav__link.site-header__nav__link--login");
    private By icon = By.xpath("//li/div[@class='site-header__nav__link__icon']");
    private By btnCategory = By.cssSelector(".category-flyout-header__link");
    private By btnOut = By.xpath("//li/a/div[@class='size-80 opacity-70']");
    private By result = By.xpath("//div[@class='category-flyout__column__section']/h3/a");

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public LogInPage openLogin(){
        driver.findElement(btnLogin).click();
        return new LogInPage(driver);
    }
    public void selectCategory(){
        driver.findElements(btnCategory).get(4).click();
    }
    public CategoriesPage selectElement(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(result,0));
        driver.findElement(result).click();
        return new CategoriesPage(driver);
    }
    public ResultsPage search(String words){
        WebElement bar = driver.findElement(searchBar);
        bar.sendKeys(words);
        driver.findElement(btnSearch).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".facet-container"),1));
        return new ResultsPage(driver);
    }
    public WebElement getUserIcon(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(icon,0));
        return driver.findElement(icon);
    }
    public WebElement getLoginButton(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnLogin,0));
        return driver.findElement(btnLogin);
    }
    public void logOut(){
        driver.findElement(icon).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(btnOut));
        driver.findElement(btnOut).click();
    }
}
