import com.google.gson.Gson;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public abstract class AndroidTestBase
{
	@Rule
	public TestName testName = new TestName();
	
	protected AndroidDriver driver;
	protected boolean status;
	
	protected static final String TESTOBJECT_URL_EU = "https://eu1.appium.testobject.com/wd/hub";
	protected static final String TESTOBJECT_URL_US = "https://us1.appium.testobject.com/wd/hub";
	
	protected static final String TESTOBJECT_API_URL = "https://app.testobject.com/api/rest/v2/appium";
	
	protected static final boolean PASSED = true;
	protected static final boolean FAILED = false;
	
	@Before
	public void setUp() throws MalformedURLException
	{
		URL remoteUrl = new URL(TESTOBJECT_URL_US);
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "6");
//		desiredCapabilities.setCapability("deviceName", "Samsung Galaxy S6");
		
		desiredCapabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));
		desiredCapabilities.setCapability("testobject_suite_name", this.getClass().getSimpleName());
		desiredCapabilities.setCapability("testobject_test_name", testName.getMethodName());
		
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		
		System.out.println("capabilities: " + driver.getCapabilities());
	}
	
	@After
	public void tearDown() throws IOException
	{
		if (driver != null)
		{
			reportTestResult(status);
			driver.quit();
		}
	}
	
	protected void reportTestResult(boolean status) throws IOException
	{
		String sessionId = driver.getSessionId().toString();
		URL endpoint = new URL( TESTOBJECT_API_URL + "/session/" + sessionId + "/test");
		
		String result = "{ \"passed\" :" + status + "}";
		
		HttpsURLConnection conn = (HttpsURLConnection) endpoint.openConnection();
		conn.setRequestMethod("PUT");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(result);
		writer.flush();
		writer.close();
		
		int responseCode = conn.getResponseCode();
		
		System.out.println("updating test result: " + result + " for session: " + sessionId + " got response: " + responseCode);
	}
}
