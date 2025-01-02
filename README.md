# JavaOpenDL

This is a prototype of my idea for a graphics library made in Java. It's *kind of similar* to OpenGL but it works quite differently.

### Shaders
First of all, both have shaders and OpenGL has two types of them: Vertex Shader and Fragment Shader. OpenDL has only one shader for applying colors to textures (similar to fragment shaders). However, shaders for paths are also planned.

### Paths
Paths are objects that generate new textures that you can modify later with shaders or drawing it onto another texture. Each path can be different and reused. For example, you can make a SpritePath, which draws sprites onto the new textures.

### Textures
Just basic image objects. Can be modified by Shaders, generated manually or by Paths and drawn onto another texture.

### "OpenDL"?
**OpenDL** stands for **Open Drawing Library**. Because, you know, you draw textures and etc.

## Features
- Textures
- Paths
- Shaders

## Planned Features
- Path Shaders
- A different language for shaders (like GLSL)
- Custom Shader Appliers (probably)
- Performance with GPU (if possible?)

## Example "Sketch" Code
```java
package main;

import opendl.DLTexture;
import opendl.DLPath;
import opendl.DLShader;

public class Main {
	public static void main(String[] args) {
		DLTexture canvas = new DLTexture(800, 600);

		DLShader fillShader = new DLShader();
		fillShader.setSource(Shaders.fillShader);
		fillShader.compile();

		DLVec4 colorBlack = new DLVec4( new DLVec3(0), 255 );

		fillShader.setAttrib("color", colorBlack);
		fillShader.apply(canvas); // Or `canvas.apply(fillShader);` maybe

		DLPath spritePath = new DLPath();
		spritePath.setSource(Paths.spritePath);
		spritePath.compile();

		spritePath.setAttrib("sprite", Sprites.sprite);
		DLTexture spriteTexture = spritePath.run(); // Maybe not `.run();` but something else

		int drawX1 = (800 / 2) - (spriteTexture.width / 2);
		int drawY1 = (600 / 2) - (spriteTexture.height / 2);
		int drawX2 = (800 / 2) + (spriteTexture.width / 2);
		int drawY2 = (600 / 2) + (spriteTexture.height / 2);

		canvas.drawTexture(spriteTexture, new DLVec4(drawX1, drawY1, drawX2, drawY2));
	}
}
```