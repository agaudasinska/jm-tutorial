package com.acme.craft.fixme.solid.liskov.substitution;

public class Square extends Rectangle {

	
	public void setWidth(int width) {
		this.width = width;
		this.height = width;
	}

	public void setHeight(int height) {
		this.width = height;
		this.height = height;
	}
}
