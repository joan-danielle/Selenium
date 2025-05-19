import java.time.Duration;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


		public class PracticeTestLoginApp {

		    private WebDriver driver;
		    private HerokuLoginPage herokuloginpage;
		    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/login";

		    @BeforeEach
		    public void setUp() {
		    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		        driver = new ChromeDriver();
		        driver.manage().window().maximize(); // Maximize browser for consistent view
		        driver.get(LOGIN_PAGE_URL);
		        herokuloginpage = new HerokuLoginPage(driver);
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username']")));
		    }

		    @Test
		    public void testSuccessfulLogin() {
		    	try {
		            Thread.sleep(1000); // ONLY FOR DEBUGGING
		        } catch (InterruptedException e) {
		            Thread.currentThread().interrupt();
		        }
		    	herokuloginpage.enterUsername("tomsmith");
		    	herokuloginpage.enterPassword("SuperSecretPassword!");
		    	herokuloginpage.clickLogin();
		        // Assuming successful login navigates to a page with a specific URL or element
		        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
				
				//Getting the success message
				String loginmessage = driver.findElement(By.id("flash")).getText();
				System.out.println(loginmessage);
		    }

		    @Test
		    public void testInvalidCredentials() {
		    	herokuloginpage.enterUsername("invalidUser");
		    	herokuloginpage.enterPassword("invalidPassword");
		    	herokuloginpage.clickLogin();
		    	Assert.assertTrue("Error message should be displayed.", herokuloginpage.isErrorMessageDisplayed());;
		        Assert.assertEquals(herokuloginpage.getErrorMessageText(), "Your username is invalid!", "Incorrect error message."); 
		    }

		    @AfterEach
		    public void tearDown() {
		        if (driver != null) {
		            driver.quit();
		        }
		    }
		}
