import java.awt.*;

public class Wall {
	public Wall(int x, int y, int w, int h, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}

	int x, y, w, h;
	TankClient tc;

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
}
