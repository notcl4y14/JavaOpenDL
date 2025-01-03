package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import opendl.DLTexture;
import opendl.DLVec4;

public class Main {
	public static void main (String[] args) {
		// Creating a new texture
		DLTexture texture = new DLTexture(256, 256);

		// Filling the entire texture with the black color
		for (int i = 0; i < texture.area; i++) {
			texture.set(i, new DLVec4(0, 0, 0, 255));
		}

		// Creating and initializing Color Shader
		ColorShader colorShader = new ColorShader();
		colorShader.scale = 1;

		// Applying the shader to the texture
		texture = texture.applyShader(colorShader);

		// Creating and initializing Rectangle Path
		RectanglePath rectPath = new RectanglePath();
		rectPath.x = 10;
		rectPath.y = 10;
		rectPath.width = 25;
		rectPath.height = 25;
		rectPath.color = new DLVec4(0, 0, 0, 255);

		// Running the Path and getting a new texture from it
		rectPath.run();
		DLTexture rectTexture = rectPath.DLTexture;

		// Changing Color Shader settings for re-applying it
		colorShader.scale = 256/25;
		
		// Applying Color Shader to Rectangle Texture
		rectTexture = rectTexture.applyShader(colorShader);

		// Drawing Rectangle Texture to the first Texture
		texture.drawTexture(rectTexture, rectPath.x, rectPath.y);

		// Clipping a part of Texture and drawing it onto that Texture
		DLTexture partTexture = texture.clip(new DLVec4(0, 0, 45, 45));
		texture.drawTexture(partTexture, 256 - 45, 256 - 45);

		// Creating and initializing Blur Shader
		BlurShader blurShader = new BlurShader();
		blurShader.radius = 1;
		blurShader.resolution = 1;
		blurShader.texture = texture;

		// Creating a new Texture for drawing on (double-buffering)
		DLTexture newTexture = new DLTexture(256, 256);
		
		// Applying Blur Shader to Texture
		newTexture = newTexture.applyShader(blurShader);

		// Applying the new Texture to that Texture
		texture.drawTexture(newTexture, 0, 0);

		// Drawing the new Texture to that Texture with a different position size
		texture.drawTexture(newTexture, new DLVec4(40, 40, 80, 80));

		// Saving the result in a new file
		saveTexture(texture, "output.png");
	}

	public static void printTexture (DLTexture texture) {
		for (int i = 0; i < texture.area; i++) {
			DLVec4 color = texture.get(i);
			String colorString = color.v1 + "," + color.v2 + "," + color.v3 + "," + color.v4;
			System.out.println(i + "\t" + color + "\t" + colorString);
		}
	}

	public static void saveTexture (DLTexture texture, String filename) {
		BufferedImage image = new BufferedImage(texture.width, texture.height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = image.getGraphics();
		
		for (int i = 0; i < texture.area; i++) {
			DLVec4 color = texture.get(i);
			int x = i % texture.width;
			int y = i / texture.width;

			graphics.setColor(new Color((int)color.v1, (int)color.v2, (int)color.v3, (int)color.v4));
			graphics.fillRect(x, y, 1, 1);
		}

		File file = new File(filename);
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
