package picture;

import java.awt.image.BufferedImage;

public class Image {
	private BufferedImage img;
	private int value;

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            value of the Number
	 */
	public Image(BufferedImage img, int vaule) {
		this.img = img;
		this.value=vaule;
	}

	/**
	 * set valuer of Number
	 * 
	 * @param value
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public Image getImg(int row, int column) {
		Image image = null;
		return image;
	}
	/**
	 * return value of Number
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	public void setValue(int value){
		this.value=value;
	}



}
