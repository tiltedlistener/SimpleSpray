package math;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class DrawnParticle {

	private Color hue;
	private int width = 10;
	private int height = 10;
	private Gravity grav = new Gravity();
	private Vector3 position;
	
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
		this.velocity.addTo(this.grav);
		this.position.addTo(this.velocity);
	}
}
