import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Blood {
	int x,y,w,h;
	int step=0;
	private boolean live=true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	private int [][]pos={
			{100,200},{300,100},{500,150,},{50,550},{160,600},{650,400},{500,300}
	};
	public Blood(){
		x=pos[0][0];
		y=pos[0][1];
		w=h=20;
	}
	public void draw(Graphics g){
		if(!live)return;
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		move();
	}
	private void move(){
		step++;
		if(step==pos.length){
			step=0;
		}
		x=pos[step][0];
		y=pos[step][1];
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}

	
}
