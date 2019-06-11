package StepDefs;

import com.cucumber.listener.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.annotations.AfterClass;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class MyStepdefs {

    WebDriver driver;

    public MyStepdefs() {
        String Geckodriverpath = "C:\\selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", Geckodriverpath);
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig("src/extent-config.xml");
    }

    @Given("^I am a new customer And access to the MoneyLion website$")
    public void CustomerMoneyLionWebsite() {
        driver.get("https://www.moneylion.com");
    }

    @When("^I hover on “Membership” and click on “Plus” at the top of the webpage$")
    public void Hoverandclickmembershipplus() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div/nav/div[1]/div/div[1]/div[1]/h3/span"))).perform();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/nav/div[1]/div/div[1]/div[1]/div/a[2]")).click();
    }

    @Then("^I should able to go to the MoneyLion Plus membership page$")
    public void customerviewMoneyLionPlusMembershipPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        String expectedPagetitile = "Borrow. Save. Invest. | MoneyLion Plus";
        String actualPagetitle = driver.getTitle();
        assertEquals(expectedPagetitile, actualPagetitle);
    }

    @And("^I should be able to see (.*) testimony is displayed$")
    public void Toverifycustomertestimonial(String testiNameParam) throws InterruptedException {

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
            if (Name.contentEquals(testiNameParam))
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
    public void clickRewards() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[15]/div/div[1]/div[2]/div[3]/a[2]/span")));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[15]/div/div[1]/div[2]/div[3]/a[2]/span")).click();
    }

    @And("^I scroll to view the “Earn rewards by” section$")
    public void NAvigatetoTheEarnRewardsBySection() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Earn rewards by')]")));
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Earn rewards by')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Then("^I should able to see all four ways to earn rewards$")
    public void verifyEarnRewardsBySection(List<List<String>> datatable) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <= 499; j++) {
                WebElement element1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div[1]/h1[2]"));
                String textone = element1.getText();
                int x = textone.length();
                Thread.sleep(500);
                String texttwo = element1.getText();
                int y = texttwo.length();
                if (y < x) {
                    System.out.println(textone);
                    break;
                }
            }
            for (int j = 0; j <= 999; j++) {
                Thread.sleep(100);
                WebElement element1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div[1]/h1[2]"));
                String textone = element1.getText();
                int x = textone.length();
                if (x == 0) {
                    break;
                }
            }
        }
    }

    @When("^I hover on “Products” and click on “Investing” at the top of the webpage$")
    public void iHoverOnProductsAndClickOnInvestingAtTheTopOfTheWebpage() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/nav/div[1]/div/div[1]/div[2]/h3/span"))).perform();
        driver.findElement(By.xpath("/html/body/div[1]/div/nav/div[1]/div/div[1]/div[2]/div/a[2]/span")).click();
        WebElement slider = driver.findElement(By.xpath("//div[@class='rc-slider']"));
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[7]/div[1]/div[1]/div[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        act.dragAndDropBy(slider, 231, 10).perform();
    }

    @And("^I scroll to view the projection slider$")
    public void iScrollToViewTheProjectionSlider() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[7]/div[1]/div[1]/div[1]/span"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

    @And("^I select (-?\\d+) as my deposit amount$")
    public void iSelectInitialAmountAsMyDepositAmount(int depositAmount) {

        WebElement slider_locator = driver.findElement(By.xpath("//div[@class='rc-slider']/div[4]"));
        WebElement year_slider_locator = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][3]/*[local-name()='foreignObject' and contains(@class,'active')]/*[local-name()='span']"));
        WebElement depositted = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][2]/*[local-name()='g'][3]/*[local-name()='g'][2]/*[local-name()='foreignObject']/*[local-name()='div']/*[local-name()='div'][2]"));
        WebElement projected = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][2]/*[local-name()='g'][3]/*[local-name()='g'][1]/*[local-name()='foreignObject']/*[local-name()='div']/*[local-name()='div'][2]"));
        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.dragAndDropBy(slider_locator, -200, 0).build();
        dragAndDrop.perform();

        for (int i = 0; i <= 500; i++) {
            Actions builder1 = new Actions(driver);
            Action dragAndDrop1 = builder1.dragAndDropBy(slider_locator, i, 0).build();
            dragAndDrop1.perform();
            WebElement sliderval = driver.findElement(By.xpath("//div[@class=\"rc-slider\"]/div[4]"));
            String value = sliderval.getAttribute("aria-valuenow");
            int ariavalue = Integer.parseInt(value);
            if (ariavalue == depositAmount) {
                break;
            }
        }
    }

    @And("^I change the year to (-?\\d+)$")
    public void iChangeTheYearToYear() {
        WebElement circle1 = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][2]/*[local-name()='g'][3]/*[local-name()='circle'][1]"));

        Actions builder2 = new Actions(driver);
        Action moveandplace = builder2.dragAndDropBy(circle1, -500, 0).build();
        moveandplace.perform();

        String givenvalue1 = "Year 21";
        for (int i = 0; i <= 500; i++) {
            Actions builder1 = new Actions(driver);
            Action dragAndDrop1 = builder1.dragAndDropBy(circle1, i, 0).build();
            dragAndDrop1.perform();


            WebElement stext = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][3]/*[local-name()='foreignObject' and contains(@class,'active')]/*[local-name()='span']"));
            String value = stext.getText();
            System.out.println(value);

            System.out.println(givenvalue1);
            if (value.equals(givenvalue1)) {
                break;
            }
        }
    }

    @Then("^I should able to see the (.*) and (.*) are displayed$")
    public void iShouldAbleToSeeTheProjectedValueAndDepositedAmountAreDisplayed(int deposited_amount, int projected_amount) {

        WebElement depositted = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][2]/*[local-name()='g'][3]/*[local-name()='g'][2]/*[local-name()='foreignObject']/*[local-name()='div']/*[local-name()='div'][2]"));
        String deposited_amount = depositted.getText();
        WebElement projected = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g'][2]/*[local-name()='g'][3]/*[local-name()='g'][1]/*[local-name()='foreignObject']/*[local-name()='div']/*[local-name()='div'][2]"));
        String projected_amount = depositted.getText();
        System.out.println(deposited_amount);
        System.out.println(projected_amount);
    }
}