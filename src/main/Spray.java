package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;

import math.*;

public class Spray extends JFrame implements Runnable{

	private static final long serialVersionUID = 0;
	
	public static void main(String[] args) {
		new Spray();
	}
	
	private int screenWidth = 640;
	private int screenHeight = 480;
	
	private int sprayCounter = 0;
	private int sprayLimit = 1000;
	private int sprayDelay = 2;
	private int sprayDelayCount = 0;
	
	BufferedImage backBuffer;
	Graphics2D g2d;
	Thread gameloop;
	Random rand = new Random();
	
	private LinkedList<DrawnParticle> _sprites;
	public LinkedList<DrawnParticle> sprites() { return _sprites; }
	
	public Spray() {
		super("Spray!!");
		setSize(screenWidth, screenHeight);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backBuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		_sprites = new LinkedList<DrawnParticle>();
		
		gameloop = new Thread(this);
		gameloop.start();
	}

	public void run() {
		Thread t = Thread.currentThread();
		while (t == gameloop) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {}
			addToSpray();
			sprayUpdate();
			repaint();
		}
		
	}
	
	public void paint(Graphics g) {
		// Clear Screen
		g2d.clearRect(0, 0, this.screenWidth, this.screenHeight); 
		
		// Paint background
		g2d.setColor(Color.black);
		g2d.fill(new Rectangle(0,0, screenWidth -1, screenHeight -1));
		
		// Draw Sprites
		drawSpray();
		
		// Draw Backbuffer
		g.drawImage(backBuffer, 0, 0, this);
	}
	
	public void sprayUpdate() { 
		for (int n=0;n<_sprites.size();n++) {
			DrawnParticle spr = (DrawnParticle)_sprites.get(n);
			spr.update();
		}
	}
	
	public void drawSpray() {
		for (int n=0;n<_sprites.size();n++) {
			DrawnParticle spr = (DrawnParticle)_sprites.get(n);
			spr.draw();
		}
	}
	
	public void addToSpray() {
		if (sprayCounter < sprayLimit) {
			sprayDelayCount++;
			if (sprayDelayCount >= sprayDelay) {
				DrawnParticle p = new DrawnParticle(screenWidth/2, screenHeight, this, g2d);
				
				double initialXSpeed = rand.nextDouble();
				double initialYSpeed = rand.nextDouble();
				
				int sign = rand.nextInt();
				if (sign < 0)
					initialXSpeed *= -1;
				
				p.setVelocity(new Vector3(3*initialXSpeed, -8*initialYSpeed, 0));
				sprites().add(p);
				sprayCounter++;
				sprayDelayCount = 0;
			}
		}
	}

}
