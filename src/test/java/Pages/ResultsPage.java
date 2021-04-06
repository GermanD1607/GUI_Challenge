package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {
    private WebDriver driver;
    private final By minPriceInput = By.id("minValue");
    private final By maxPriceInput = By.id("maxValue");
    private final By filterPriceBtn = By.cssSelector(".facet button");
    private final By priceTag = By.cssSelector(".price-display");
    private final By options = By.id("sort");
    private final By option = By.cssSelector("select option");
    private final By itemTitle = By.cssSelector(".grid-card__title");

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public String getItemTitle(Integer n){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options,0));
        return driver.findElements(itemTitle).get(n).getText();
    }
    public Double getPrice(Integer n){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(priceTag,0));
        return Double.parseDouble(driver.findElements(priceTag).get(n).getText().
                replaceAll("[^a-zA-Z0-9.]", ""));
    }
    public void filter(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(filterPriceBtn,0));
        driver.findElements(minPriceInput).get(0).sendKeys("1200");
        driver.findElements(maxPriceInput).get(0).sendKeys("2300");
        driver.findElements(filterPriceBtn).get(3).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(itemTitle,0));
    }
    public void order(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options,0));
        driver.findElement(options).click();
        driver.findElements(option).get(3).click();
    }
}
