

import java.awt.Color;
import java.awt.image.BufferedImage;


public class TransposePicture implements Picture {

	private final Picture originalPicture;
	
	public TransposePicture(Picture originalPicture) {
		super();
		this.originalPicture = originalPicture;
	}
	public int getHeight() {
		return originalPicture.getWidth();
	}
	public int getWidth() {
		return originalPicture.getHeight();
	}
	public Color get(int x, int y) {
		return originalPicture.get(y, x);
	}
	public void set(int x, int y, Color color) {
		originalPicture.set(y, x, color);
	}
	public BufferedImage getImage() {
		throw new UnsupportedOperationException();
	}

}
