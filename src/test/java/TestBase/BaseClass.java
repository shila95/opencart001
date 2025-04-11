package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" }) // Add groups for grouping test
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		
		// location of properties file and Loading properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\Config.properties");
		p = new Properties();
		p.load(file);
		
		// log4j
		logger = LogManager.getLogger(this.getClass());
		
		
		//use while run selenium grid
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("Windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("Mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching os");
				return;
			}
			
			//browser
			switch(br)
			{
			case "Edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "Chrome": capabilities.setBrowserName("Chrome"); break;
			default: System.out.println("No Matching Browser");
			return;
			}
			
			/*
			capabilities.setPlatform(Platform.LINUX);
			capabilities.setBrowserName("MicrosoftEdge");
			*/
			driver=new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"),capabilities);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br) {
			case "Edge": driver = new EdgeDriver(); break;
			case "Chrome": driver = new ChromeDriver(); break;
			default: System.out.println("Invalid Browser...");
				return;
			}
		}
		
		/*
		// testNG.xml
		switch (br) {
		case "Edge": driver = new EdgeDriver(); break;
		case "Chrome": driver = new ChromeDriver(); break;
		default: System.out.println("Invalid Browser...");
			return;
		}
		*/
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// driver.get("https://tutorialsninja.com/demo/");

		// using Properties file for getting URL
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
	}

		@AfterClass(groups= {"Sanity","Regression","Master"})  //Add groups for grouping test
	public void teaeDown() 
	{
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}

	public String randomNumber() {
		String generatedString = RandomStringUtils.randomNumeric(7);
		return generatedString;
	}

	public String randomAlphaNumber() {
		String alpha = RandomStringUtils.randomAlphabetic(6);
		String number = RandomStringUtils.randomNumeric(7);
		return (alpha + "$" + number);
	}

	public String captureScreen(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourcefile = ts.getScreenshotAs(OutputType.FILE);

		String targetfilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + " " + timestamp + ".png";
		File targetfile = new File(targetfilepath);

		sourcefile.renameTo(targetfile);
		return targetfilepath;

	}

}
