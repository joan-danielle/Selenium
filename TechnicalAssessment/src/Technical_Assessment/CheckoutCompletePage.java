package Technical_Assessment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 class CheckoutCompletePage {

	    private WebDriver driver;
	    private WebDriverWait wait;

	    @FindBy(className = "complete-header")
	    WebElement completeHeader;

	    @FindBy(className = "complete-text")
	    WebElement completeText;

	    public CheckoutCompletePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    /**
	     * Verifies that the order completion header message is displayed and contains the expected text.
	     *
	     * @param expectedHeader The expected header text.
	     * @return True if the header is displayed and contains the expected text, false otherwise.
	     */
	    public boolean verifyOrderCompletionHeader(String expectedHeader) {
	        try {
	            WebElement headerElement = wait.until(ExpectedConditions.visibilityOf(completeHeader));
	            return headerElement.getText().trim().equalsIgnoreCase(expectedHeader.trim());
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    /**
	     * Gets the order completion sub-text message.
	     *
	     * @return The order completion sub-text.
	     */
	    public String getOrderCompletionSubText() {
	        try {
	            WebElement textElement = wait.until(ExpectedConditions.visibilityOf(completeText));
	            return textElement.getText().trim();
	        } catch (Exception e) {
	            return "";
	        }
	    }

	    /**
	     * Verifies that the order is successful by checking the header and optionally the sub-text.
	     *
	     * @param expectedHeader The expected success header text (e.g., "THANK YOU FOR YOUR ORDER").
	     * @param expectedSubText (Optional) The expected sub-text message. If null or empty, it will not be checked.
	     * @return True if the order is considered successful based on the verification, false otherwise.
	     */
	    public boolean verifyOrderSuccessful(String expectedHeader, String expectedSubText) {
	        boolean headerMatches = verifyOrderCompletionHeader(expectedHeader);
	        if (expectedSubText != null && !expectedSubText.isEmpty()) {
	            return headerMatches && getOrderCompletionSubText().contains(expectedSubText);
	        }
	        return headerMatches;
	    }

	    /**
	     * Verifies that the order is successful by only checking the header.
	     *
	     * @param expectedHeader The expected success header text (e.g., "THANK YOU FOR YOUR ORDER").
	     * @return True if the header is displayed and matches the expected text.
	     */
	    public boolean verifyOrderSuccessful(String expectedHeader) {
	        return verifyOrderSuccessful(expectedHeader, null);
	    }
	}
