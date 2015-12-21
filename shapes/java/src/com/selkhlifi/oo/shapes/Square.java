package com.selkhlifi.oo.shapes;

import java.io.Writer;
import java.io.IOException;

class Square implements Shape {

	private final int l;

	Square(final int l) {
		this.l = l;		
	}

	@Override
	public void area(final Writer out) throws IOException {
		out.write(Integer.toString(l*l));
	}
}
