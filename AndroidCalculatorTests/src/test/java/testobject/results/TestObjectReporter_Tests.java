package testobject.results;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestObjectReporter_Tests
{
	@Test
	public void should_create_passed_json_result()
	{
		String json = TestObjectReporter.createJsonResult(TestStatus.PASSED);
		
		System.out.println("json: \n" + json);
	}
	
	@Test
	public void should_create_failed_json_result()
	{
		String json = TestObjectReporter.createJsonResult(TestStatus.FAILED);
		
		System.out.println("json: \n" + json);
	}
	
	@Test
	public void should_create_endpoint_url_with_session_id() throws MalformedURLException
	{
		String session_id = "bogus_session_id";
		
		URL url = TestObjectReporter.createEndpointURL(session_id);
		
		System.out.println("url: " +  url);
	}
	
	
	public AndroidDriver createAndroidDriver(String testName) throws MalformedURLException
	{
		String TESTOBJECT_URL_US = "https://us1.appium.testobject.com/wd/hub";
		URL remoteUrl = new URL(TESTOBJECT_URL_US);
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "6");
		
		desiredCapabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));
		desiredCapabilities.setCapability("testobject_suite_name", this.getClass().getSimpleName());
		desiredCapabilities.setCapability("testobject_test_name", testName);
		
		AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		
		return driver;
	}
}
