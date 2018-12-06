package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import sun.audio.*;



public class MyCanvas extends Canvas implements KeyListener, MouseListener{ 
	
	
	
	Game link = new Game(10,350,60,60,"filespng/hockeyplayer.png");
	//Game goalie = new Game(300,350,60,60,"filespng/goalie.png");
	Goalie goalie = new Goalie(300,350,60,60,"filespng/goalie.png");
	LinkedList badguys = new LinkedList();
	Timer timer = new Timer();
	Background back = new Background(0,0,1000,800,"filespng/NewNet.jpg");
	LinkedList pucks = new LinkedList();
	
	int timerSeconds = 32;
	int numberofbadguys = 10;
	int kills = 0;
	
	public MyCanvas() {
		this.setSize(1000,800);
		this.addKeyListener(this);
		this.addMouseListener(this);
		playIt("filespng/hockey.wav");
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				timerSeconds = timerSeconds - 1;
				repaint();
				System.out.println(timerSeconds);
			} 
		}, 1000, 1000);
		
		Random rand = new Random();
		int winwidth = this.getWidth() - 800;
		int winheight = this.getHeight() - 450;
		for(int i = 0; i < 10; i++) {
			
			Badguy bg = new Badguy(rand.nextInt(winwidth) + 450, rand.nextInt(winheight) + 150,50,50,"filespng/redring.png");
			Rectangle r = new Rectangle(rand.nextInt(winwidth),rand.nextInt(winheight),50,50);
			//if (r.contains(link.getxCoord(), link.getyCoord())) {
			//	System.out.println("badguy ontop of Auston");
			//	continue;
			//}
            badguys.add(bg);
		}
		//System.out.println(badguys.size());
		
		for(int k = 0; k < 20; k++);
		
		
		
	}
	
	public void playIt(String filename) {
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void paint(Graphics g) {
		Font font = new Font("Bangers", Font.BOLD,30);
		Font endgamefont = new Font("Impact", Font.BOLD,80);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawImage(back.getImg(), back.getxCoord(), back.getyCoord(), 1000, 800, this);
		g.drawString(String.valueOf(timerSeconds), 500, 50);
		g.drawImage(goalie.getImg(), goalie.getxCoord(), goalie.getyCoord(), goalie.getWidth(), goalie.getHeight(), this);
		g.drawImage(link.getImg(), link.getxCoord(), link.getyCoord(), link.getWidth(), link.getHeight(), this);
		

		//g.drawRect(158, 125, 674, 575);
		//g.drawRect(450,200, 250,300);
		
		if(timerSeconds>=0 && kills == 10) {
			g.setFont(endgamefont);
			g.drawString("Shooter Wins!!!!", 200, 300);
			timer.cancel();
			repaint();
		}else if(timerSeconds <= 0 && kills < 10) {
			g.setFont(endgamefont);
			g.drawString("Goalie Wins!!!!", 200, 300);
			timer.cancel();
			repaint();
			
		}
		
		for(int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			System.out.println(i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
			Rectangle bgr =  new Rectangle(bg.getxCoord(), bg.getyCoord(),bg.getWidth(),bg.getHeight());
			
			
			for(int j = 0; j < pucks.size(); j++) {
				Projectile k = (Projectile) pucks.get(j);
				if (k.getxCoord() > this.getWidth()) { pucks.remove(k); }
				k.setxCoord(k.getxCoord() + .5);
				g.drawImage(k.getImg(), (int) k.getxCoord(), (int) k.getyCoord(), k.getWidth(),k.getHeight(), this);
				
			
			Rectangle kr = new Rectangle( (int) k.getxCoord(), (int) k.getyCoord(),k.getWidth(),k.getHeight());
			if (kr.intersects(bgr)) {
				badguys.remove(i);
				pucks.remove(j);
				kills++;
				playIt("filespng/baseball_hit.wav");
				repaint();
			
			}
			Rectangle gor = new Rectangle (goalie.getxCoord(), goalie.getyCoord(), goalie.getWidth(), goalie.getWidth());
			Rectangle kr2 = new Rectangle( (int) k.getxCoord(), (int) k.getyCoord(),k.getWidth(),k.getHeight());
			if (gor.intersects(kr2)) {
				pucks.remove(j);
				playIt("filespng/bloop_x.wav");
			
				
			}
			
			
			
			repaint();
		}
	}
}
	
	public void keyTyped(KeyEvent e) {
		}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() ==32) {
			Projectile puck = new Projectile(link.getxCoord(), link.getyCoord(), 30, 30,"filespng/goodhockeypuck.png");
			pucks.add(puck);
			repaint();
		}
		
		
		link.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		repaint();
		
		goalie.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	
		System.out.println(e);
		link.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight());
		
		goalie.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		
		for(int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
			Rectangle gr = new Rectangle (link.getxCoord(), link.getyCoord(), link.getWidth(),link.getHeight());
			
			if (r.intersects(gr)) {
				System.out.println("badguy hit by auston");
				badguys.remove(i);
				pucks.remove(i);
			} 
		
				
			
		}
		
		
		repaint();
		// TODO Auto-generated method stub
	}
		
		public void mousePressed(MouseEvent e) {
		       System.out.println(e.getX());
		    }
		
		public void mouseReleased(MouseEvent e) {
			System.out.println(e.getX());
		    }
		public void mouseEntered(MouseEvent e) {
			System.out.println(e.getX());
		    }
		
		public void mouseExited(MouseEvent e) {
			System.out.println(e.getX());
		    }
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getX());
		    }
		
	}
	


