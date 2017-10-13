import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AndroidCalculatorTests extends AndroidTestBase
{
	@Test
	public void add_two_numbers() throws IOException
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("1");
		calculator.pressKey("+");
		calculator.pressKey("2");
		calculator.pressKey("=");
		
		String output = calculator.readScreen().trim();
		System.out.println("output: " + output);
		
		assertEquals("3", output);
	}
}
	