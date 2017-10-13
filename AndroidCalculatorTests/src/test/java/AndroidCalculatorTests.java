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
		
		try
		{
			assertEquals("3", output);
			status = PASSED;
		}
		catch (AssertionError e)
		{
			status = FAILED;
		}
	}
	
	/** this is expected to fail **/
	@Test
	public void subtract_two_numbers() throws Exception
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("7");
		calculator.pressKey("-");
		calculator.pressKey("8");
		calculator.pressKey("=");
		
		String output = calculator.readScreen().trim();
		
		System.out.println("output:" + output);
		
		try
		{
			assertEquals("-1", output);
			status = PASSED;
		}
		catch (AssertionError e)
		{
			status = FAILED;
		}
	}
	
	
	@Test
	public void multiply_two_numbers() throws Exception
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("7");
		calculator.pressKey("*");
		calculator.pressKey("8");
		calculator.pressKey("=");
		
		String output = calculator.readScreen().trim();
		
		System.out.println("output:" + output);
		
		try
		{
			assertEquals("56", output);
			status = PASSED;
		}
		catch (AssertionError e)
		{
			status = FAILED;
		}
	}
	
	@Test
	public void divide_two_numbers() throws Exception
	{
		AndroidCalculatorDriver calculator = new AndroidCalculatorDriver(driver);
		
		calculator.pressKey("8");
		calculator.pressKey("/");
		calculator.pressKey("3");
		calculator.pressKey("=");
		
		String output = calculator.readScreen().trim();
		
		System.out.println("output:" + output);
		
		try
		{
			assertEquals("3", output);
			status = PASSED;
		}
		catch (AssertionError e)
		{
			status = FAILED;
		}
	}
}
