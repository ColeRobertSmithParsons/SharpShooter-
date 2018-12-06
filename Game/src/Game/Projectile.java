package Game;

import java.awt.Image;
import java.awt.Toolkit;
	
public class Projectile {
		
		private double xCoord;
		private double yCoord;
		private int width;
		private int height;
		private Image img; 
		
		public Projectile(int x, int y, int w, int h, String imgpath) {
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
		
		public double getxCoord() {
			return this.xCoord;
		}
		
		public double getyCoord() {
			return this.yCoord;
		}
		
		public void setxCoord(double d) {
			this.xCoord = d;
		}
		
		public void setyCoord(int y) {
			this.yCoord = y;
		}

		public int getWidth() {
			return this.width;
		}

		public int getHeight() {
			// TODO Auto-generated method stub
			return this.height;
		}
		
}

