package com.selkhlifi.oo.shapes;

import java.io.*;

class Rectangle implements Shape {
	private final int w;
	private final int l;

	Rectangle(final int w, final int l) {
		this.w = w;
		this.l = l;
	}
	
	@Override
	public void area(final Writer out) throws IOException {
		out.write(Integer.toString(w*l));
	}
}
