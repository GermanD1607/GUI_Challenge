import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage{
    private WebDriver driver;
    private final By minPrice = By.id("minValue");
    private final By maxPrice = By.id("maxValue");
    private final By btn = By.cssSelector(".facet button");
    private final By options = By.id("sort");
    private final By option = By.cssSelector("select option");
    private final By item = By.cssSelector(".grid-card__title");

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }
    public String getTitle(){
        String[] busqueda = driver.getCurrentUrl().split("=");
        System.out.println(busqueda);
        return busqueda[busqueda.length-1];
    }
    public String getItem(Integer n){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options,0));
        System.out.println("text: "+driver.findElements(item).get(0).getText());
        return driver.findElements(item).get(n).getText();
    }
    public void filter(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btn,0));
        driver.findElements(minPrice).get(0).sendKeys("1200");
        driver.findElements(maxPrice).get(0).sendKeys("2300");
        driver.findElements(btn).get(1).click();
    }
    public void order(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options,0));
        driver.findElement(options).click();
        driver.findElements(option).get(3).click();
    }
    public Integer getPrice(Integer n){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options,1));
        return Integer.parseInt(driver.findElements(item).get(n).getText());
    }
}
