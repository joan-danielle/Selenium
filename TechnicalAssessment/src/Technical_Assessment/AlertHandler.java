package Technical_Assessment;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHandler {

    private WebDriver driver;
    private WebDriverWait wait;

    public AlertHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
    }

    public void acceptAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            System.out.println("Alert accepted.");
        } catch (Exception e) {
            System.err.println("No alert was present or unable to accept it: " + e.getMessage());
        }
    }

    public void dismissAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            System.out.println("Alert dismissed.");
        } catch (Exception e) {
            System.err.println("No alert was present or unable to dismiss it: " + e.getMessage());
        }
    }

    public String getAlertText() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (Exception e) {
            System.err.println("No alert was present or unable to get its text: " + e.getMessage());
            return null;
        }
    }

    public void handlePasswordChangeAlert() {
        // Assuming the "OK" button in the "Change your password" alert triggers the alert to be accepted
        acceptAlert();
    }

    // Example usage within your test class:
    public static void main(String[] args) {
        // Initialize your WebDriver here
        // For example:
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // WebDriver driver = new ChromeDriver();
        WebDriver driver = null; // Replace with your actual driver initialization

        try {
            if (driver != null) {
                driver.get("https://www.saucedemo.com/inventory.html"); // Navigate to the page where the alert might appear

                // Simulate the action that triggers the alert (if needed for testing)
                // For example, maybe after a login attempt or some other action

                AlertHandler alertHandler = new AlertHandler(driver);
                alertHandler.handlePasswordChangeAlert();

                // Continue with your test logic after handling the alert
                System.out.println("Continuing with test after handling alert.");

            } else {
                System.err.println("WebDriver not initialized.");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
