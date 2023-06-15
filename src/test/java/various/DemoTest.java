package various;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class DemoTest {

	private static WebDriver driver;

	public static void main(String[] args) throws IOException, AWTException {

		@SuppressWarnings("deprecation")
		FirefoxOptions options = new FirefoxOptions()
				/*
				 * .addPreference("browser.startup.page", 1)
				 * .addPreference("browser.startup.homepage", "https://www.google.co.uk")
				 * .setHeadless(true)
				 */
				.setAcceptInsecureCerts(true)
				.addArguments("--headless")
				.setBinary("C:\\Program Files (x86)\\Mozilla Firefox 42\\firefox.exe");
//		options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox 42\\firefox.exe");
		driver = new FirefoxDriver(options);
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebElement multi = driver.findElement(By.id("multiselect1"));
		File srcFile = multi.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcFile, new File("./Screenshots/multi.png"));
		System.out.println("Element screenshot done");

		File fullpage = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
		FileHandler.copy(fullpage, new File("./Screenshots/fullpage.png"));
		System.out.println("Fullpage Firefox screenshot done");

		driver.findElement(By.id("alert1")).click();

		Robot robot = new Robot();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		BufferedImage bufimage = robot.createScreenCapture(rectangle);
		String filePath = System.getProperty("user.dir") + "//Screenshots//robot.png";
		ImageIO.write(bufimage, "png", new File(filePath));
		System.out.println("Java awt screenshot done of first screen");

		Alert alert1 = driver.switchTo().alert();
		alert1.accept();

		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(0))
				.takeScreenshot(driver);
		String filePathFull = System.getProperty("user.dir") + "//Screenshots//fullpageAnyBrowser.png";
		ImageIO.write(screenshot.getImage(), "png", new File(filePathFull));
		System.out.println("Yandex QATools Ashot fullpage screenshot done");

		driver.quit();

	}

}
