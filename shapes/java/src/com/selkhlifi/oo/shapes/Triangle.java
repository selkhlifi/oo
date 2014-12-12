package com.selkhlifi.oo.shapes;

import java.io.*;

class Triangle implements Shape {

	private final int b;
	private final int l;

	Triangle(final int b, final int l) {
		this.b = b;
		this.l = l;
	}
	
	@Override
	public void area(final Writer out) throws IOException {
		out.write(Integer.toString(b*l / 2));
	}
}
