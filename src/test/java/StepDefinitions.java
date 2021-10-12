import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    public WebDriver driver;
    WebDriverWait wait;

    @Given("^User visits e-commerce website$")
    public void user_visits_a_visual_discovery_engine_site() throws Exception {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed"); //uncomment if you want to run in headless mode
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com");
    }

    @When("^User enters valid email \"([^\"]*)\" and valid password \"([^\"]*)\" credentials$")
    public void user_enters_valid_credentials(String email, String password) throws Exception {
        wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login")));// wait until getting the login button
        WebElement btnLogin = driver.findElement(By.className("login"));
        btnLogin.click();
        Thread.sleep(1000);
        WebElement txtEmail = driver.findElement(By.id("email"));
        txtEmail.sendKeys(email);
        WebElement txtPassword = driver.findElement(By.id("passwd"));
        txtPassword.sendKeys(password);
        Thread.sleep(1000);
        WebElement btnSubmitLogin = driver.findElement(By.id("SubmitLogin"));
        btnSubmitLogin.click();
    }

    @Then("^User can logged in successfully$")
    public void user_can_logged_in_successfully() throws Exception {
        wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Samia Nira')]")));
        WebElement lblUserName = driver.findElement(By.xpath("//span[contains(text(),'Samia Nira')]"));
        Assert.assertEquals(lblUserName.getText(), "Samia Nira");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}




