import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleAdditionTest extends AndroidTestBase
{
	@Test
	public void sampleTest()
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("1");
		calculator.pressKey("+");
		calculator.pressKey("2");
		calculator.pressKey("=");
		
		String result = calculator.readScreen();
		
		assertEquals("3", result);
	}
}
	