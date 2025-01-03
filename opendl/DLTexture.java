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

	public DLTexture applyShader (DLShader shader, boolean doubleBuffered) {
		DLTexture texture = doubleBuffered ? new DLTexture(width, height) : this;

		int index = 0;

		while (index < this.area) {
			DLVec4 color = this.get(index);
			DLVec2 position = new DLVec2(index % width, (int)index / width);

			shader.DLColor = color;
			shader.DLPosition = position;
			
			shader.run();

			texture.set(index, shader.DLColor);
			index++;
		}

		return texture;
	}

	public DLTexture applyShader (DLShader shader) {
		return applyShader(shader, true);
	}

	public DLTexture applyShader (DLShader shader, boolean doubleBuffered, boolean directDraw) {
		DLTexture texture = this.applyShader(shader, doubleBuffered);
		if (directDraw)
			this.data = texture.data;
		return texture;
	}

	public DLTexture clip (DLVec4 rect) {
		int x1 = (int) rect.v1;
		int y1 = (int) rect.v2;
		int x2 = (int) rect.v3;
		int y2 = (int) rect.v4;

		DLTexture texture = new DLTexture(x2 - x1, y2 - y1);

		int x = x1;
		int y = y1;

		while (x < x2) {
			while (y < y2) {
				texture.set( x - x1, y - y1, this.get(x, y) );
				y++;
			}

			x++;
			y = y1;
		}

		return texture;
	}

	public void drawTexture (DLTexture texture, int x, int y) {
		for (int i = 0; i < texture.area; i++) {
			int drawX = (i % texture.width) + x;
			int drawY = (i / texture.width) + y;

			this.set(drawX, drawY, texture.get(i));
		}
	}

	public void drawTexture (DLTexture texture, DLVec4 rect) {
		int x1 = (int)rect.v1;
		int y1 = (int)rect.v2;
		int x2 = (int)rect.v3;
		int y2 = (int)rect.v4;

		int width = x2 - x1;
		int height = y2 - y1;
		int area = width * height;

		double widthFraction = texture.width / width;
		double heightFraction = texture.height / height;

		for (int i = 0; i < area; i++) {
			int x = (i % width);
			int y = (i / width);
			int pixelX = (int) (x * widthFraction);
			int pixelY = (int) (y * heightFraction);

			this.set(x + x1, y + y1, texture.get(pixelX, pixelY));
		}
	}

	public void addTexture (DLTexture texture, int x, int y) {
		for (int i = 0; i < texture.area; i++) {
			int drawX = (i % texture.width) + x;
			int drawY = (i / texture.width) + y;

			DLVec4 color1 = this.get(drawX, drawY);
			DLVec4 color2 =	texture.get(i); 

			DLVec4 color = color1.add(color2);

			color.v1 = color.v1 > 255 ? 255 : color.v1;
			color.v2 = color.v2 > 255 ? 255 : color.v2;
			color.v3 = color.v3 > 255 ? 255 : color.v3;
			color.v4 = color.v4 > 255 ? 255 : color.v4;

			this.set(drawX, drawY, color);
		}
	}
}
