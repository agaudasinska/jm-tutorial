package com.acme.calc;

import org.junit.Assert;
import org.junit.Test;



public class CalculatorTest {

	private Calculator calculator = new Calculator();

	@Test
	public void additionShouldReturnCorrectResult() {
		
		// given
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		
		// when
		Double result = calculator.add(firstNumber, secondNumber);
		
		// then
		Assert.assertTrue(result == 11);
	

	}

	@Test
	public void subtractionShouldReturnCorrectResult() {
	

	// TODO division and multiplication test!
	
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
	}

	
	@Test
	public void divideShouldReturnCorrstResult() {
		double firstNumber = 10.0;
		double secondNumber = 2.0;
		
		Double result = calculator.divide(firstNumber, secondNumber);
		
		Assert.assertTrue(result == 5);
		
	}
	
	
}
