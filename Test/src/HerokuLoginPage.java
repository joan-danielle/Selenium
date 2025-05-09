import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

class HerokuLoginPage {
	
	   private WebDriver driver;
	   private WebDriverWait wait;
	   
	   	// Using @FindBy annotations for concise element location
	    @FindBy(id = "username")
	    WebElement usernameInput;

	    @FindBy(id = "password")
	    WebElement passwordInput;
	    
	    @FindBy(xpath = "//*[@id='login']/button")
	   WebElement login;
	    
	    // Assuming there might be an error message element
	    @FindBy(id = "flash")
	    WebElement errorMessage;
	    
	    // Constructor to initialize the WebDriver and PageFactory
	    public HerokuLoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	        PageFactory.initElements(driver, this); // Initialize elements using @FindBy
	    }
	    
	    // Method to wait for and enter the username
	    public void enterUsername(String username) {
	        waitVisibilityOf(usernameInput).sendKeys(username);
	    }
	    
	    // Method to wait for and enter the password
	    public void enterPassword(String password) {
	        waitVisibilityOf(passwordInput).sendKeys(password);
	    }
	    
	    // Method to wait for and click the login button
	    public void clickLogin() {
	        waitElementToBeClickable(login).click();
	    }
	    
	    // Method to perform the entire login action
	    public void login(String username, String password) {
	        enterUsername(username);
	        enterPassword(password);
	        clickLogin();
	        
	    }

	    // Method to check if the error message is displayed
	    public boolean isErrorMessageDisplayed() {
	        try {
	            return waitVisibilityOf(errorMessage).isDisplayed();
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            return false; // Error message element not found
	        }
	    }

	    // Method to get the text of the error message
	    public String getErrorMessageText() {
	        return waitVisibilityOf(errorMessage).getText();
	    }

		// Reusable explicit wait methods
	    private WebElement waitVisibilityOf(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    private WebElement waitElementToBeClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
}