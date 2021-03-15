import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class SearchPage{
    ////li/div[@class="site-header__nav__link__icon"]
    private final By searchBar = By.cssSelector(".site-search__controls__input");
    private By btnSearch = By.cssSelector(".site-search__controls__submit");
    private By icon = By.xpath("//li/div[@class='site-header__nav__link__icon']");
    private WebDriver driver;

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public ResultsPage search(String words){
        WebElement bar = driver.findElement(searchBar);
        bar.sendKeys(words);


        driver.findElement(btnSearch).click();
        return new ResultsPage(driver);
    }
    public WebElement IconUser(){
        return driver.findElement(icon);
    }
}
