import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */

public class LibraryTest {

	private WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
//		FirefoxOptions options = new FirefoxOptions();
//		options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox 42\\firefox.exe");
//		options.setPlatformName("Windows 7");
//		options.setBrowserVersion("latest");
//		driver = new FirefoxDriver(options);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
    
	@Test
    public void testSomeLibraryMethod() {
//        Library classUnderTest = new Library();
//		("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
//        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
//        assertEquals(driver.findElement(By.cssSelector("div[id='content'] h1")), "Register Account");
        assertEquals(driver.findElement(By.cssSelector("div[id='content'] h1")).getText(), "Register Account");
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
