package com.mytest.juint.test;

import org.junit.Test;

import static org.junit.Assert.*;

import com.mytest.junit.samples.Fibonacci;

public class MyTest {
	
	//Comparing two methods working on different principle,
	//but returning the same result.
	@Test
	public void compareResults() {
		
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < i; j++) {
				System.out.printf("i:%d, j:%d\n", i, j);
				byte expected = Fibonacci.lastDigitOfBigInt(j, i);
				byte result = Fibonacci.lastDigitOfSum(j, i);
				assertEquals(expected, result);
			}
		}
	}
	
	
	@Test(timeout = 800)
	public void testSumOfFibo() {
	Fibonacci.sumOfFibo(123456);
	}
	
	//The "naive method" takes too much time to run, even though, 
	//the input numbers are not so big.
	@Test(timeout = 500)
	public void testBigInt() {
	Fibonacci.lastDigitOfBigInt(124234, 99973235);
	}
	
	//The more efficient solution can process much larger 
	//numbers much faster.
	@Test(timeout = 1)
	public void testLastDigit() {
	Fibonacci.lastDigitOfSum(1244435334534532L, 999723487238479852L);
	}
}
