package com.selkhlifi.oo.shapes;

import java.io.IOException;
import java.io.StringWriter;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ShapesTest {
    
   private StringWriter out;
	private Shapes shapes;

	@Before
	public void initWriter() {
	out = new StringWriter();
	shapes = new Shapes(out);
	}
	@Test
	public void square() throws IOException {
	shapes.area("SQUARE", "30");
	assertEquals("900", out.toString());
	}
	@Test
	public void rectangle() throws IOException {
	shapes.area("RECTANGLE", "50,20");
	assertEquals("1000", out.toString());
	}
	@Test
	public void triangle() throws IOException {
	shapes.area("TRIANGLE", "50,20");
	assertEquals("500", out.toString());
	}
}
