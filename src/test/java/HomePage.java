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
    private By btnResult = By.cssSelector(".category-flyout__column__section a");

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public LogInPage OpenLogin(){
        driver.findElement(btnLogin).click();
        return new LogInPage(driver);
    }
    public void SelectCategory(){
        driver.findElements(btnCategory).get(4).click();
    }
    public CategoriesPage SelectElement(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnResult,1));
        driver.findElements(btnResult).get(0).click();
        return new CategoriesPage(driver);
    }
    public ResultsPage Search(String words){
        WebElement bar = driver.findElement(searchBar);
        bar.sendKeys(words);
        driver.findElement(btnSearch).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".facet-container"),1));
        return new ResultsPage(driver);
    }
    public WebElement GetUserIcon(){
        return driver.findElement(icon);
    }
    public WebElement GetLoginButton(){
        return driver.findElement(btnLogin);
    }
    public void LogOut(){

    }
}