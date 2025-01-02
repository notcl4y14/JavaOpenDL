package opendl;

import java.util.ArrayList;

public class DL {
	public static ArrayList<DLTexture> textures;
	public static ArrayList<DLPath> paths;
	public static ArrayList<DLShader> shaders;

	public static DLTexture texture;
	public static DLPath path;
	public static DLShader shader;

	private static long createID () {
		// return (long) (Math.random() * 2 * Long.MAX_VALUE - Long.MAX_VALUE);
		return System.currentTimeMillis();
	}

	public static void init () {
		textures = new ArrayList<>();
		paths = new ArrayList<>();
		shaders = new ArrayList<>();
		
		texture = null;
		path = null;
		shader = null;
	}

	public static void bindTexture (long textureID) {
		texture = textures.get((int)textureID);
	}

	public static void bindPath (long pathID) {
		path = paths.get((int)pathID);
	}

	public static void bindShader (long shaderID) {
		shader = shaders.get((int)shaderID);
	}

	public static long createTexture (int width, int height) {
		DLTexture texture = new DLTexture(width, height);
		long id = createID();
		textures.add((int)id, texture);
		return id;
	}
}