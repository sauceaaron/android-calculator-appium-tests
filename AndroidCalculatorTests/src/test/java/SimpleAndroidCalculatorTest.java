import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class SimpleAndroidCalculatorTest
{
	String TESTOBJECT_API_KEY = System.getenv("TESTOBJECT_API_KEY");
	
	String TESTOBJECT_URL_EU = "https://eu1.appium.testobject.com/wd/hub";
	String TESTOBJECT_URL_US = "https://us1.appium.testobject.com/wd/hub";
//	String TESTOBJECT_URL_US = "https://us1.appium.testobject.com/wd/hub:443"; // does this give an error?
	
	AndroidDriver driver;
	
	String TESTOBJECT_API_URL = "https://app.testobject.com/api/rest/v2/appium";
	
	boolean PASSED = true;
	boolean FAILED = false;
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void setup() throws MalformedURLException
	{
		URL testobjectUrl = new URL(TESTOBJECT_URL_US);
		
		DesiredCapabilities androidCapabilities = new DesiredCapabilities();
		androidCapabilities.setCapability("testobject_api_key", TESTOBJECT_API_KEY);
		androidCapabilities.setCapability("testobject_test_suite", "Android Calculator Tests");
		androidCapabilities.setCapability("testobject_test_name", testName.getMethodName());
		
		androidCapabilities.setCapability("platformName", "Android");
		androidCapabilities.setCapability("platformVersion", "6.0");
		
		driver = new AndroidDriver(testobjectUrl, androidCapabilities);
	}
	
	@Test
	public void add_two_numbers() throws Exception
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("7");
		calculator.pressKey("+");
		calculator.pressKey("8");
		calculator.pressKey("=");
		
		String output = calculator.readScreen();
		
		System.out.println(" ____ OUTPUT ____" + output);
		
		try
		{
			assertEquals("15", output);
			reportTestResult(PASSED);
		}
		catch (AssertionError e)
		{
			reportTestResult(FAILED);
		}
	}
	
	/** This should fail **/
	@Test
	public void divide_two_numbers() throws Exception
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("9");
		calculator.pressKey("/");
		calculator.pressKey("2");
		calculator.pressKey("=");
		
		String output = calculator.readScreen();
		
		System.out.println(" ____ OUTPUT ____" + output);
		
		try
		{
			assertEquals("5", output);
			reportTestResult(PASSED);
		}
		catch (AssertionError e)
		{
			reportTestResult(FAILED);
		}
	}
	
	
	@After
	public void tearDown() throws IOException
	{
		if (driver != null)
		{
			driver.quit();
		}
	}
	
	private void reportTestResult(boolean passed) throws IOException
	{
		String sessionId = driver.getSessionId().toString();
		
		URL endpoint = new URL( TESTOBJECT_API_URL + "/session/" + sessionId + "/test");
		HttpsURLConnection conn = (HttpsURLConnection) endpoint.openConnection();
		
		conn.setRequestMethod("PUT");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		
		String result = "{ \"passed\" :" + passed + "}";
		
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(result);
		writer.flush();
		writer.close();
		
		System.out.println("updated test result for session: " + sessionId + " status: " + conn.getResponseCode());
	}
}
