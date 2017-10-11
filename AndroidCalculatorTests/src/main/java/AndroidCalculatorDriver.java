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
	By four = By.id("net.ludeke.calculator:id/digit4");
	By five = By.id("net.ludeke.calculator:id/digit5");
	By six = By.id("net.ludeke.calculator:id/digit6");
	By seven = By.id("net.ludeke.calculator:id/digit7");
	By eight = By.id("net.ludeke.calculator:id/digit8");
	By nine = By.id("net.ludeke.calculator:id/digit9");
	By zero = By.id("net.ludeke.calculator:id/digit0");
	
	By point = By.id("net.ludeke.calculator:id/dot");
	
	By add = By.id("net.ludeke.calculator:id/plus");
	By subtract = By.id("net.ludeke.calculator:id/minus");
	By multiply = By.id("net.ludeke.calculator:id/mul");
	By divide = By.id("net.ludeke.calculator:id/div");
	By equals = By.id("net.ludeke.calculator:id/equal");
	
	By delete = By.id("net.ludeke.calculator:id/del");
	
	By screen = By.xpath("//android.widget.EditText[1]");
	
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
		put(".", point);
		put("+", add);
		put("-", subtract);
		put("*", multiply);
		put("/", divide);
		put("=", equals);
		put("clear", delete);
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
		return (MobileElement) driver.findElement(screen);
	}
	
	public String readScreen()
	{
		return getScreen().getText();
	}
}
