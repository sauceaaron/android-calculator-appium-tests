import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.HashMap;

public class AndroidCalculatorDriver
{
	AndroidDriver driver;
	
	By one = By.id("net.ludeke.calculator:id/digit1");
	By two = By.id("net.ludeke.calculator:id/digit2");
	By three = By.id("net.ludeke.calculator:id/digit3");
	
	By plus = By.id("net.ludeke.calculator:id/plus");
	By equals = By.id("net.ludeke.calculator:id/equals");
	
	HashMap<String, By> keys = new HashMap<String, By>() {{
		put("1", one);
		put("2", two);
		put("3", three);
		put("4", four);
		put("5", five);
		put("6", six);
		put("7", seven);
		put("8", eight);
		put("9", nine);
		put("0", zero);
		put("+", plus);
		put("-", minus);
		put("*", times);
		put("/", divide);
		put("=", equals);
		put("clear", clear);
	}};
	
	public AndroidCalculatorDriver(AndroidDriver driver)
	{
		this.driver = driver;
	}
	
	public MobileElement getKey(String key)
	{
		return (MobileElement) driver.findElement(keys.get(key));
	}
	
	public void pressKey(String key)
	{
		getKey(key).click();
	}
	
	public MobileElement getScreen()
	{
		return (MobileElement) driver.findElementByXPath("//adroid.widget.EditText[1]");
	}
	
	public String readScreen()
	{
		return getScreen().getText();
	}
}
