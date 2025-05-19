package Technical_Assessment;


import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class FinalInterview_LiveAssessment {
	
	private static CheckoutCompletePage checkoutCompletePage;

	public static void main(String[] args) throws InterruptedException {
	
		//Setting up the chrome driver executable
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		 // Create ChromeOptions object
        ChromeOptions options = new ChromeOptions();

        // Create a HashMap for Chrome preferences
        Map<String, Object> prefs = new HashMap<>();

        // Create a nested HashMap for the 'profile' section
        Map<String, Object> profilePrefs = new HashMap<>();

        // Set the 'password_manager_leak_detection' preference to false
        profilePrefs.put("password_manager_leak_detection", false);

        // Add the 'profile' preferences to the main 'prefs' map
        prefs.put("profile", profilePrefs);

        // Set the experimental options for Chrome
        options.setExperimentalOption("prefs", prefs);

        // Initialize ChromeDriver with the configured options
        WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		//Navigating to the Swag Labs website
		driver.get("https://www.saucedemo.com/");
		System.out.println("Navigating to htps://www.saucedemo.com/" );
		checkoutCompletePage = new CheckoutCompletePage(driver);
		
		//Sending standard_user in username field
		WebElement username = driver.findElement(By.id("user-name"));
		username.sendKeys("standard_user");
		System.out.println("Inputing: "+ username );
		
		//Sending secret_sauce in password field
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");
		System.out.println("Inputing: "+ password );
		
		//Clicking the Login Button
		driver.findElement(By.id("login-button")).click();
		System.out.println("Login button is successfully clicked");	
		
		//Select one specific product (Sauce Labs Fleece Jacket)
		WebElement SauceLabsFleeceJacket = driver.findElement(By.linkText("Sauce Labs Fleece Jacket"));
		SauceLabsFleeceJacket.isDisplayed();
		SauceLabsFleeceJacket.click();
		System.out.println("Sauce Labs Fleece Jacket is displayed");
		
		//Selecting OK in that google password alert
		try {
            // Try to switch to the alert
            Alert alert = driver.switchTo().alert();

            // If an alert is present, get its text
            String alertText = alert.getText();
            System.out.println("Alert is present with text: " + alertText);

            // Accept the alert (click "OK")
            alert.accept();
            System.out.println("Alert accepted.");

        } catch (NoAlertPresentException e) {
            // If no alert is present, this exception will be caught
            System.out.println("Alert is not displayed.");
        }	
		
		//and add it to the shopping cart.
		WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
		addToCartButton.click();
		System.out.println("Sauce Labs Fleece Jacket is successfully add to the cart.");
		
		//Navigating back to homepage
		WebElement backToProductButton =  driver.findElement(By.id("back-to-products"));
		backToProductButton.click();
		System.out.println("Navigating to the homepage to select one more item");
		
		//Selecting OK in that google password alert
		try {
            // Try to switch to the alert
            Alert alert = driver.switchTo().alert();

            // If an alert is present, get its text
            String alertText = alert.getText();
            System.out.println("Alert is present with text: " + alertText);

            // Accept the alert (click "OK")
            alert.accept();
            System.out.println("Alert accepted.");

        } catch (NoAlertPresentException e) {
            // If no alert is present, this exception will be caught
            System.out.println("Alert is not displayed.");
        }; 
		
		//Select any one random product and add it to the shopping cart. Selection should be dynamic
        try {

            // Locate all the "Add to cart" buttons
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

            // Get the number of available "Add to cart" buttons (products)
            int numberOfProducts = addToCartButtons.size();

            if (numberOfProducts > 0) {
                // Create a Random object
                Random random = new Random();

                // Generate a random index within the bounds of the product list
                int randomIndex = random.nextInt(numberOfProducts);

                // Get the randomly selected "Add to cart" button
                WebElement randomAddToCartButton = addToCartButtons.get(randomIndex);

                // Find the name of the product associated with the selected button
                randomAddToCartButton.findElement(By.xpath("//button[text()='Add to cart']"));

                // Click the randomly selected "Add to cart" button
                randomAddToCartButton.click();

                System.out.println("Successfully added another product to the shopping cart.");

            } else {
                System.out.println("No products found with 'Add to cart' button.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
		
		
		//Navigate to the shopping cart
            boolean testPassed = true;
            String errorMessage = null;

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement shoppingCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-test='shopping-cart-link']")));
                shoppingCartLink.click();

                wait.until(ExpectedConditions.titleContains("Your Cart")); 
                if (!driver.getCurrentUrl().contains("/cart")) {
                    testPassed = false;
                    errorMessage = "Navigation to cart page failed. Current URL: " + driver.getCurrentUrl();
                } else {
                    System.out.println("Shopping cart link is clickable and navigation was successful.");
                }

            } catch (org.openqa.selenium.TimeoutException h) {
                testPassed = false;
                errorMessage = "Shopping cart link was not clickable within the timeout: " + e.getMessage();
                System.err.println(errorMessage);
            } catch (org.openqa.selenium.NoSuchElementException j) {
                testPassed = false;
                errorMessage = "Shopping cart link element not found: " + e.getMessage();
                System.err.println(errorMessage);
            } finally {
                if (driver != null) {
                    driver.quit();
                }

                if (testPassed) {
                    System.out.println("Verification passed.");
                } else {
                    System.err.println("Verification failed: " + errorMessage);
                }
            }
        }
            
         try {
                WebElement topElement = driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a"));

                // Scroll the element into view using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topElement);

                System.out.println("Scrolled to the top element.");

            } catch (Exception g) {
                System.out.println("Top element not found: " + g.getMessage());
                // Fallback to direct scrolling if the element isn't found
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            }

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement shoppingCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='shopping_cart_container']/a")));
		shoppingCart.click();
		System.out.println("Shopping cart is successfully click");
		
		//Verifying the 2 items is displayed in the shopping cart
		//Locate the element displaying the shopping cart item count (the badge).
        WebElement cartBadge = driver.findElement(By.cssSelector("span[data-test='shopping-cart-badge']"));

        //Get the text content of the badge.
        String actualItemCountText = cartBadge.getText();

        //Convert the text to an integer.
        int actualItemCount = Integer.parseInt(actualItemCountText);

        //Define the expected item count.
        int expectedItemCount = 2;
        
        if (actualItemCount == expectedItemCount) {
            System.out.println("Verification passed: Expected and actual item counts match (" + actualItemCount + ").");
        } else {
            System.err.println("Verification failed: Expected " + expectedItemCount + " items, but found " + actualItemCount + ".");
        }
		
        //Clicking the check out button
		WebElement checkOutButton = driver.findElement(By.id("checkout"));
		checkOutButton.click();
		System.out.println("Check out button is successfully clicked");
		
		//Fill out the shipping information form.
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys("Test First Name");
		System.out.println("First name: " + firstName);
		
		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys("Test Last Name");
		System.out.println("Last name: " + lastName );
		
		WebElement zipCode= driver.findElement(By.id("postal-code"));
		zipCode.sendKeys("1000");
		System.out.println("ZipCode " + zipCode);
		
		//Clicking the continue button
		driver.findElement(By.id("continue")).click();
		System.out.println("Continue button is successfully clicked");
		//Clicking the finish button
		driver.findElement(By.id("finish")).click();
		System.out.println("Finish button is successfully clicked");
		
		//verify that the order is successful.
		assertTrue(checkoutCompletePage.verifyOrderSuccessful("THANK YOU FOR YOUR ORDER!"), "Order was not successful based on the header.");
        assertTrue(checkoutCompletePage.getOrderCompletionSubText().contains("Your order has been dispatched"), "Order sub-text verification failed.");
		
		
		driver.close();
		
    	}
	}
