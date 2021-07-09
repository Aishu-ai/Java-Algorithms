

import java.awt.Color;
import java.awt.image.BufferedImage;


public class PictureAs2DArray implements Picture {

	private final Color[][] pixels;
	private final int width;
	private final int height;

	public PictureAs2DArray(BufferedImage image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.pixels = new Color[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x][y] = new Color(image.getRGB(x, y));
			}
		}
	}

	public PictureAs2DArray(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new Color[width][height];
	}

	
	public int getHeight() {
		return height;
	}

	
	public int getWidth() {
		return width;
	}

	
	public Color get(int x, int y) {
		return pixels[x][y];
	}

	
	public void set(int x, int y, Color color) {
		pixels[x][y] = color;
	}


	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, get(x,y).getRGB());
			}
		}
		return image;
	}

}
