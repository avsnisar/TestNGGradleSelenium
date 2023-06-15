package various;

import java.net.URI;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BiDiTest {

//	FirefoxOptions fo = new FirefoxOptions().setBinary("");
//	private WebDriver driver = new FirefoxDriver(fo);
	private WebDriver driver;

	@Test
	public void basicAuthentication() throws InterruptedException {

		// works in ChromeDriver and EdgeDriver. FirefoxDriver can't be cast to HasAuthentication type
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("the-internet.herokuapp.com");
		HasAuthentication l = (HasAuthentication) driver;
		l.register(uriPredicate, UsernameAndPassword.of("admin", "admin"));
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		Thread.sleep(1000);
		driver.quit();
	}
}
