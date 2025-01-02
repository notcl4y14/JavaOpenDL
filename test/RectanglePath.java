package test;

import opendl.DLPath;
import opendl.DLVec4;

public class RectanglePath extends DLPath {
	public int x;
	public int y;
	public int width;
	public int height;
	public DLVec4 color;

	public void run () {
		DLTexture = new opendl.DLTexture(width, height);

		for (int i = 0; i < DLTexture.area; i++) {
			DLTexture.set(i, color);
		}
	}
}