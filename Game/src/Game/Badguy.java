package Game;

import java.awt.Image;
import java.awt.Toolkit;

public class Badguy {
	
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img; 
	
	public Badguy() {
		setxCoord(10);
		setyCoord(10);
		setWidth(15);
		setHeight(15);
		setImg("filespng/download-2.png.jpg"); 
	}
	
	public Badguy(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	private void setHeight(int h) {
		// TODO Auto-generated method stub
		this.height = h;
	}

	private void setWidth(int w) {
		// TODO Auto-generated method stub
		this.width = w;
	}

	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
		
	}
	
	public Image getImg() {
		return img;
	}
	
	public int getxCoord() {
		return this.xCoord;
	}
	
	public int getyCoord() {
		return this.yCoord;
	}
	
	public void setxCoord(int x) {
		this.xCoord = x;
	}
	
	public void setyCoord(int y) {
		this.yCoord = y;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	
}
