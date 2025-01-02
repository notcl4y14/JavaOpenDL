package test;

import opendl.DLShader;
import opendl.DLVec4;

public class ColorShader extends DLShader {
	public int inPosX;
	public int inPosY;
	public double scale;

	public void run () {
		int r = (int) (inPosX * scale);
		int g = (int) (inPosY * scale);
		int b = (int) (inPosY * scale);
		DLColor = new DLVec4(r, g, b, 255);
	}
}
