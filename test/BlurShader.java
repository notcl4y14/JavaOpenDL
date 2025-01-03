package test;

import opendl.DLShader;
import opendl.DLTexture;
import opendl.DLVec4;

public class BlurShader extends DLShader {
	public double inRadius;
	public double inRes;
	
	public int inPosX;
	public int inPosY;
	public DLTexture inTexture;

	public void run () {
		double blur = inRadius / inRes;

		int x1 = this.inPosX - (int)blur;
		int y1 = this.inPosY - (int)blur;
		int x2 = this.inPosX + (int)blur;
		int y2 = this.inPosY + (int)blur;
		int area = 0;

		x1 = x1 < 0 ? 0 : x1 >= inTexture.width ? inTexture.width : x1;
		y1 = y1 < 0 ? 0 : y1 >= inTexture.width ? inTexture.width : y1;
		x2 = x2 < 0 ? 0 : x2 >= inTexture.width ? inTexture.width : x2;
		y2 = y2 < 0 ? 0 : y2 >= inTexture.width ? inTexture.width : y2;

		area = (x2 - x1) * (y2 - y1);

		DLVec4 color = new DLVec4(0, 0, 0, 0);

		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				DLVec4 newColor = inTexture.get(x, y);
				color = color.add(newColor);
			}
		}

		color = color.div(area);
		DLColor = color;
	}
}
