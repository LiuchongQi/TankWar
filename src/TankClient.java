import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * @author lenovo
 *
 */
public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	Tank myTank = new Tank(400, 400 , true, Tank.Direction.STOP, this);
	Wall w1=new Wall(200, 200, 20, 200, this);
	Wall w2=new Wall(300, 350, 350, 20, this);
	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	Image offScreenImage = null;
	Blood blood=new Blood();
	
	public void paint(Graphics g) {
		g.drawString("炮弹数目:" + missiles.size(), 10, 50);
		g.drawString("爆炸数目:" + explodes.size(), 10, 70);
		g.drawString("坦克数目:" + tanks.size(), 10, 90);
		g.drawString("Tank hp:" + myTank.getLife(), 10, 110);
		
		if(tanks.size()<=0){
			for(int i=0; i<4; i++) {
				tanks.add(new Tank(50 + 40*(i+1), 50, false, Tank.Direction.D, this));
				tanks.add(new Tank(50 , 50+ 40*(i+1), false, Tank.Direction.D, this));
			}
		}
		
		for(int i=0; i<missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitWall(w1);
			m.hitWall(w2);
			m.hitTank(myTank);
			m.draw(g);
		}
		
		for(int i=0; i<explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.collidesWithTanks(tanks);
			t.draw(g);
			
		}
		
		myTank.draw(g);
		myTank.eat(blood);
		w1.draw(g);
		w2.draw(g);
		blood.draw(g);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.orange);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void lauchFrame() {
		
		for(int i=0; i<4; i++) {
			tanks.add(new Tank(50 + 40*(i+1), 50, false, Tank.Direction.D, this));
			tanks.add(new Tank(50 , 50+ 40*(i+1), false, Tank.Direction.D, this));
		}
		
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("坦克大战");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.orange);
		
		this.addKeyListener(new KeyMonitor());
		
		setVisible(true);
		
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		
	}
}













