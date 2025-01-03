package test;

import opendl.DLShader;
import opendl.DLTexture;
import opendl.DLVec4;

public class BlurShader extends DLShader {
	public double radius;
	public double resolution;
	
	public DLTexture texture;

	public void run () {
		double blur = radius / resolution;
		
		int x1 = (int)DLPosition.v1 - (int)blur;
		int y1 = (int)DLPosition.v2 - (int)blur;
		int x2 = (int)DLPosition.v1 + (int)blur;
		int y2 = (int)DLPosition.v2 + (int)blur;
		int area = 0;

		x1 = x1 < 0 ? 0 : x1 >= texture.width ? texture.width : x1;
		y1 = y1 < 0 ? 0 : y1 >= texture.width ? texture.width : y1;
		x2 = x2 < 0 ? 0 : x2 >= texture.width ? texture.width : x2;
		y2 = y2 < 0 ? 0 : y2 >= texture.width ? texture.width : y2;

		area = (x2 - x1) * (y2 - y1);

		DLVec4 color = new DLVec4(0, 0, 0, 0);

		for (int x = x1; x < x2; x++) {
			for (int y = y1; y < y2; y++) {
				DLVec4 newColor = texture.get(x, y);
				color = color.add(newColor);
			}
		}

		color = color.div(area);
		DLColor = color;
	}
}
