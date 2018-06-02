package com.jerald.udemy.microservice.currencyconversionservice;

public class LimitsConfiguration {

	private int maximum;
	private int minimum;
	
	
	protected LimitsConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitsConfiguration(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	
	public int getMaximum() {
		return maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	
	
}
