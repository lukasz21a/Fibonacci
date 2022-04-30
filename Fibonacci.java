package com.mytest.junit.samples;

import java.math.BigInteger;

// all the print statements in this class were used for debugging purposes.
public class Fibonacci {
	
	/**
	 * A static Method to calculate the sum of the first 
	 * <code>n</code> Fibonacci numbers.
	 * 
	 * @param n  First <code>n</code> Fibonacci numbers to sum up.
	 * @return The sum (BigInteger).
	 */
	public static BigInteger sumOfFibo(int n) {
		BigInteger[] nums = new BigInteger[3];
		BigInteger sum = BigInteger.valueOf(0);
		
		for(int i = 0; i <= n; i++) {
			if(i <= 1) {
				nums[i] = BigInteger.valueOf(i);
				sum = BigInteger.valueOf(i);
			}
			else {
				nums[2] = nums[0].add(nums[1]);
				nums[0] = nums[1];
				nums[1] = nums[2];
				sum = sum.add(nums[2]);
			}
		}
		
		return sum;
	}
	
	/**
	 * A static method to calculate the last digit of the sum
	 * of Fibonacci numbers in a given interval <code>[f, t]</code>.
	 * <p>
	 * This method simply subtracts two large sums (BigInteger)
	 * of <code>t</code> first numbers and <code>f - 1</code> 
	 * first numbers of the Fibonacci sequence. It serves only
	 * as a comparison to {@link lastDigitOfSum}, as it is very 
	 * time and space consuming.
	 * 
	 * @param f  The beginning of the interval (inclusive).
	 * @param t  The end of the interval (inclusive).
	 * @return The last digit of the sum (byte).
	 */
	public static byte lastDigitOfBigInt(int f, int t) {
		BigInteger ten = BigInteger.valueOf(10);
		byte result;
		
		System.out.print("expected:");
		if(f == 0) {
			result = sumOfFibo(t).subtract(sumOfFibo(f)).mod(ten).byteValue();
			System.out.printf("%d\n", result);
			return result;
		}
			
		result = sumOfFibo(t).subtract(sumOfFibo(f - 1)).mod(ten).byteValue();
		System.out.printf("%d\n", result);
		return result;
	}
	
	/**
	 * A static method to calculate the last digit of the sum
	 * of Fibonacci numbers in a given interval <code>[f, t]</code>.
	 * <p>
	 * This method is a much more efficient version of 
	 * {@link lastDigitOfBigInt}. It uses the principle that in the 
	 * Fibonacci sequence, the last digits are repeated every 60 
	 * items. The same applies to the sum of the first <code>n</code>
	 * numbers. It generates two arrays to store the 60 last digits, one
	 * for the Fibonacci sequence and one for the sums.
	 * 
	 * @param f  The beginning of the interval (inclusive).
	 * @param t  The end of the interval (inclusive).
	 * @return The last digit of the sum (byte).
	 */
	public static byte lastDigitOfSum(long f, long t) {
		byte[] lastDigit = new byte[60];
		byte[] sumLastDigit = new byte[60];
		byte resFrom = (byte) (f % 60);
		byte resTo = (byte) (t % 60);
		byte result;
		
		for(int i = 0; i < 60; i++) {
			if(i <= 1) {
				lastDigit[i] = (byte) i;
				sumLastDigit[i] = (byte) (i + 10);
			}
			else {
				lastDigit[i] = (byte) ((lastDigit[i - 1] + lastDigit[i - 2]) % 10);
				sumLastDigit[i] = (byte) ((sumLastDigit[i - 1] + lastDigit[i]) % 100);
				if(sumLastDigit[i] < 10)
					sumLastDigit[i] = (byte) (sumLastDigit[i] + 10);
			}
		}
		
		System.out.print("result:");
		if(resFrom == 0) {
			result = (byte) ((sumLastDigit[resTo] - sumLastDigit[resFrom] % 10) % 10);
			System.out.printf("%d\n", result);
			System.out.println("----------");
			return result;
		}
		
		result = (byte) ((sumLastDigit[resTo] - sumLastDigit[resFrom - 1] % 10) % 10);
		System.out.printf("%d\n", result);
		System.out.println("----------");
		return result;
	}
}