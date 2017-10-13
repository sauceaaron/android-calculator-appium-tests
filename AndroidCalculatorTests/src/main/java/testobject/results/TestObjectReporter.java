package testobject.results;

import com.google.gson.Gson;
import io.appium.java_client.android.AndroidDriver;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class TestObjectReporter
{
	public static final String TESTOBJECT_API_URL = "https://app.testobject.com/api/rest/v2/appium";
	
	
	public static String createJsonResult(boolean status)
	{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("passed", status);
		
		Gson gson = new Gson();
		String json = gson.toJson(result);
		
		return json;
	}
	
	
	public static URL createEndpointURL(String sessionId) throws MalformedURLException
	{
		URL url = new URL( TESTOBJECT_API_URL + "/session/" + sessionId + "/test");
		
		return url;
	}
	
	protected static void reportTestResult(String sessionId, boolean status) throws IOException
	{
		String result = createJsonResult(status);
		URL url = new URL( TESTOBJECT_API_URL + "/session/" + sessionId + "/test");
		
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
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
