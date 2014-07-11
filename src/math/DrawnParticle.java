package math;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Dimension;

public class DrawnParticle {

	private Color hue;
	private int width = 5;
	private int height = 5;
	private Gravity grav = new Gravity();
	private Vector3 position;
	private boolean bounced = false;
	private int lifecount = 0;
	
	public Vector3 velocity;
	
	protected JFrame frame;
	protected Graphics2D g2d;
	
	public DrawnParticle(double x, double y, JFrame _frame, Graphics2D _g2d) {
		this.hue = Color.BLUE;
		frame = _frame;
		g2d = _g2d;
		this.velocity = new Vector3();
		this.position = new Vector3(x, y, 0);
	}
	public void setVelocity(Vector3 vel) { this.velocity.setAsSelf(vel); }
	
	public void draw() {
		g2d.setColor(hue);
		g2d.fillRect((int)this.position.x, (int)this.position.y, this.width, this.height);
	}
	
	public void update() {
		lifecount++;
		this.velocity.addTo(this.grav);
		this.position.addTo(this.velocity);
	}
	
	// Inspired by https://github.com/hunterloftis/playfuljs-demos/tree/gh-pages/particles2
	// But different method
	public void bounce() {
		Dimension screen = frame.getContentPane().getSize();
		if (this.position.y > screen.height && !bounced && lifecount > 100) {
			bounced = true;
			this.velocity.y = -this.velocity.y * 0.3;
		}
	}
}
