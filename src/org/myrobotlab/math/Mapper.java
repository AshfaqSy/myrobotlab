package org.myrobotlab.math;

public final class Mapper {

	float minX;
	float maxX;
	float minY;
	float maxY;

	public Mapper(float minX, float maxX, float minY, float maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	final public float calc(float s) {
		return minY + ((s - minX) * (maxY - minY)) / (maxX - minX);
	}
	
	final public int calcInt(float s) {
		return java.lang.Math.round(minY + ((s - minX) * (maxY - minY)) / (maxX - minX));
	}
	/*
	final public int calc(Float s) {
		return java.lang.Math.round(minY + ((s - minX) * (maxY - minY)) / (maxX - minX));
	}
	*/
}
