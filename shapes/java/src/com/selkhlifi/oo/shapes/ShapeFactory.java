package com.selkhlifi.oo.shapes;
import java.util.stream.*;

class ShapeFactory {
	
	static Shape create(final String name, final String str) {
		
		switch(name) {
			case "SQUARE":
				final int sqLength = Integer.parseInt(str);

				return new Square(sqLength);
			case "RECTANGLE":
				final int[] recds = dimensions(str);
				final int recLength = recds[0];
				final int recWidth = recds[1];

				return new Rectangle(recLength, recWidth);
			case "TRIANGLE":
				final int[] trids = dimensions(str);
				final int triBase = trids[0];
				final int triLength = trids[1];

				return new Triangle(triLength, triBase);
			default: 
				throw new IllegalArgumentException(String.format("Not supported shape [name:%s, dimensions:%s]", name, str));
		}
	}

	private static int[] dimensions(String str) {
		return Stream.of(str.split(","))
			.mapToInt(Integer::parseInt)
			.toArray();
	}
}
