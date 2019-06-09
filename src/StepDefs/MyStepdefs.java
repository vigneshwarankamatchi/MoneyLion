package StepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class MyStepdefs {

    WebDriver driver;
    public MyStepdefs(){
        String Geckodriverpath = "C:\\selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", Geckodriverpath);
         driver = new FirefoxDriver();
    }

    @Given("^I am a new customer And access to the MoneyLion website$")
    public void iAmANewCustomerAndAccessToTheMoneyLionWebsite() {
        driver.get("https://www.moneylion.com");
    }

    @When("^I hover on “Membership” and click on “Plus” at the top of the webpage$")
    public void iHoverOnMembershipAndClickOnPlusAtTheTopOfTheWebpage() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div/nav/div[1]/div/div[1]/div[1]/h3/span"))).perform();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/nav/div[1]/div/div[1]/div[1]/div/a[2]")).click();
    }

    @Then("^I should able to go to the MoneyLion Plus membership page$")
    public void iShouldAbleToGoToTheMoneyLionPlusMembershipPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        String expectedPagetitile = "Borrow. Save. Invest. | MoneyLion Plus";
        String actualpagetitle = driver.getTitle();
        assertEquals(expectedPagetitile, actualpagetitle);

    }


    @And("^I should be able to see (.*) testimony is displayed$")
    public void iShouldBeAbleToSeeRobertsTestimonyIsDisplayed(String testiname1) throws InterruptedException {

        String testiname = testiname1;
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div/div[1]/div"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div[2]/div[4]/div/div[1]/ul/li"));
        String Name = "";
        WebDriverWait wait = new WebDriverWait(driver, 15);

        int i = 1;
        for (WebElement elements : allElements) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div/div[" + i + "]/div/div/div[2]/div[2]")));
            WebElement Testimonis = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div/div[" + i + "]/div/div/div[2]/div[2]"));
            Thread.sleep(1000);
            Name = Testimonis.getText();
            System.out.println("******************************");
            System.out.println(Name);
            System.out.println("******************************");
            if (Name.contentEquals(testiname))
                break;
            else {
                i++;
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[1]/div[3]")));
                WebElement nextbrn = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div[1]/div[3]"));
                Thread.sleep(1000);
                nextbrn.click();
            }
        }
    }


    @Then("^Close the Browser$")
    public void closeTheBrowser() {
        driver.quit();
    }

    @And("^I click on the Rewards at the bottom of the page")
    public void clickRewards()
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[15]/div/div[1]/div[2]/div[3]/a[2]/span")));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[15]/div/div[1]/div[2]/div[3]/a[2]/span")).click();
    }

    @And("^I scroll to view the “Earn rewards by” section$")
    public void iScrollToViewTheEarnRewardsBySection() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Earn rewards by')]")));
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Earn rewards by')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Then("^I should able to see all four ways to earn rewards$")
    public void iShouldAbleToSeeAllFourWaysToEarnRewards() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
    }
}