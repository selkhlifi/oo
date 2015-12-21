package com.selkhlifi.oo.shapes;

import java.io.*;

public class Shapes {
	
	private final Writer out;
	
	public Shapes(final Writer out) {
		this.out = out;		
	}

	public void area(final String name, final String dims) throws IOException {
		ShapeFactory.create(name, dims).area(out);
	}
}
