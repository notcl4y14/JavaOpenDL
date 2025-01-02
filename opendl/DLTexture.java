package opendl;

public class DLTexture {
	public int width;
	public int height;
	public int area;
	public DLVec4[] data;

	public DLTexture (int width, int height) {
		this.width = width;
		this.height = height;
		this.area = this.width * this.height;
		this.data = new DLVec4[this.area];
	}

	public DLVec4 get (int index) {
		if (index < 0)
			return this.data[0];
		
		if (index >= this.area)
			return this.data[this.area - 1];
		
		return this.data[index];
	}

	public DLVec4 get (int x, int y) {
		if (x < 0)
			x = 0;
		if (x >= this.width)
			x = this.width;
		
		if (y < 0)
			y = 0;
		if (y >= this.height)
			y = this.height;
		
		return this.get(y * this.width + x);
	}

	public void set (int index, DLVec4 color) {
		this.data[index] = color;
	}

	public void set (int x, int y, DLVec4 color) {
		this.set(y * this.width + x, color);
	}

	public void drawTexture (DLTexture texture, int x, int y) {
		for (int i = 0; i < texture.area; i++) {
			int drawX = (i % texture.width) + x;
			int drawY = (i / texture.width) + y;

			this.set(drawX, drawY, texture.get(i));
		}
	}
}
