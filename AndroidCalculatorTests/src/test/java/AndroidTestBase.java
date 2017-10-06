import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AndroidTestBase
{
	protected AndroidDriver driver;
	
	@Before
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("privateDevicesOnly", "true");
		desiredCapabilities.setCapability("testobject_test_name", "calculator test recorded from appium inspector");
		desiredCapabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY");
		URL remoteUrl = new URL("https://us1.appium.testobject.com/wd/hub");
		
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
