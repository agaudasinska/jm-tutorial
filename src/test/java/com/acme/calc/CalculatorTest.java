package com.acme.calc;

import org.junit.Assert;
import org.junit.Test;



public class CalculatorTest {

	private Calculator calculator = new Calculator();

	@Test
	public void additionShouldReturnCorrectResult() {
<<<<<<< HEAD
		
		// given
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		
		// when
		Double result = calculator.add(firstNumber, secondNumber);
		
		// then
		Assert.assertTrue(result == 11);
	

=======
		// given
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		// when
		Double result = calculator.add(firstNumber, secondNumber);
		// then
		Assert.assertFalse(result.isNaN());
		Assert.assertTrue(result == 11);
>>>>>>> upstream/master
	}

	private double firstNumber;
	private double secondNumber;
	private Exception thrown;
	@Test
<<<<<<< HEAD
	public void subtractionShouldReturnCorrectResult() {
	
=======
	public void divisionShouldThrowExceptionWhenDivisorIsZero() {
		givenNumbersWithZero();
		catchException(() -> calculator.divide(firstNumber,secondNumber));
		assertException(DivisorCannotBeZeroException.class);
	}

	private void catchException(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			thrown = e;
		}
	}
>>>>>>> upstream/master

	private void assertException(Class<DivisorCannotBeZeroException> expectedExceptionClass) {
		Assert.assertNotNull(thrown);
		Assert.assertTrue(expectedExceptionClass.equals(thrown.getClass()));
	}
	private void assertThat(Exception e, Class<?> expectedClass) {
		Assert.assertTrue(e.getClass().equals(expectedClass));
	}
	private void thenCorrectExceptionIsThrons(Exception e) {
		Assert.assertTrue(e.getClass().equals(
				DivisorCannotBeZeroException.class));
	}
	// TODO division and multiplication test!
<<<<<<< HEAD
	
	//given
	double firstNumber = 5.0;
	double secondNumber = 6.0;
	
	//when
	Double result = calculator.subtract(secondNumber, firstNumber);
	
	//then
	Assert.assertTrue(result == 1);
	}
	
	
	@Test
	public void multiplyShouldReturnCorrestResult() {
		
		//given
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		
		//when
		Double result = calculator.multiply(firstNumber, secondNumber);
		
		//then
		Assert.assertFalse(result.isNaN());
		Assert.assertTrue(result == 30.0);
=======
	private void givenNumbersWithZero() {
		firstNumber = 5.0;
		secondNumber = 0.0;
>>>>>>> upstream/master
	}

	
	//double firstNumber = 6.0;
	//double secondNumber = 0.0;
	//@Test
	//public void divideShouldThrowExc(){
	//givenNumberWithZero();	
	//Exception exception = null;
	//catchException(() -> calculator.divide(firstNumber, secondNumber));
	//try {
	//	runnable.run();
	//	
	//}
	//calculator.divide(firstNumber, secondNumber);
	//} catch (Exception e) {
	//	exception = e;
	//	Assert.assertTrue(e.getClass().equals(DivisiorCannotBeZeroException.class));
	//}
	
	@Test
	public void divideShouldReturnCorrectResult() {
		double firstNumber = 10.0;
		double secondNumber = 2.0;
		
		Double result = calculator.divide(firstNumber, secondNumber);
		
		Assert.assertTrue(result == 5);
		
	}
	
	
}
