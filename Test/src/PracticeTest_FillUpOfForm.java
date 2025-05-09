import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;


public class PracticeTest_FillUpOfForm {

	public static void main(String[] args) {
		//Set the path to the chrome driver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Joan\\eclipse-workspace\\Test\\chromedriver.exe");
				
		//Initializing the Webdriver
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
 
		//navigate to the URL
		driver.get("https://formy-project.herokuapp.com/form");
		driver.manage().window().maximize();
		
		//Inputting details in the form
		//FirstName
		WebElement firstname = driver.findElement(By.id("first-name"));
		firstname.sendKeys("Test");
		
		//LastName
		WebElement lastname = driver.findElement(By.id("last-name"));
		lastname.sendKeys("Name");
		
		//Job Title
		WebElement jobtitle = driver.findElement(By.id("job-title"));
		jobtitle.sendKeys("QA Automation Engineer");
		
		//Highest Level of education
		WebElement radioButton = driver.findElement(By.id("radio-button-2"));
		radioButton.click();
		
		//Sex
		WebElement sex = driver.findElement(By.id("checkbox-2"));
		sex.click();
		
		//Yrs of Expience
		//Scrolling down
		Select yrsOfExperience = new Select(driver.findElement(By.id("select-menu")));
		//Scrolling down
		js. executeScript("arguments[0]. scrollIntoView();", yrsOfExperience);
		//Select the value in the dropdown
		driver.findElement(By.id("select-menu")).click();
		yrsOfExperience.selectByValue("3");
		
		//Date
		WebElement datePickerInput = driver.findElement(By.id("datepicker"));
		datePickerInput.click();
        
        //Get Today's Date
        LocalDate today = LocalDate.now();
        int dayOfMonth = today.getDayOfMonth();
        
        // Construct the XPath to find the element representing today's date
        String todayXPath = "//td[not(contains(@class, 'old')) and not(contains(@class, 'new')) and text()='" + dayOfMonth + "']";

        // Find and click the element for today's date
        WebElement todayDateElement = driver.findElement(By.xpath(todayXPath));
        todayDateElement.click();
  
        //Clicking Submit button
        WebElement submit = driver.findElement(By.linkText("Submit"));
        submit.click();
  
        //Close the Windows once test is completed
      	driver.quit();

	}

}
