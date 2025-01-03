package test;

import opendl.DLShader;
import opendl.DLVec4;

public class ColorShader extends DLShader {
	public double scale;

	public void run () {
		int r = (int) (DLPosition.v1 * scale);
		int g = (int) (DLPosition.v2 * scale);
		int b = (int) (DLPosition.v2 * scale);
		DLColor = new DLVec4(r, g, b, DLColor.v4);
	}
}
