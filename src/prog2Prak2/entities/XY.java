package prog2Prak2.entities;

import java.util.Random;

public class XY {
	public static final XY UP = new XY(0, -1);
	public static final XY DOWN = new XY(0, 1);
	public static final XY LEFT = new XY(-1,0);
	public static final XY RIGHT = new XY(1,0);
	private final int x;
	private final int y;

	private static final Random random = new Random();

	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return this.x; }
	public int getY() { return this.y; }

	public XY move(XY WASD) {
		int a,b;
		a = x + WASD.getX();
		b = y + WASD.getY();

		return new XY(a,b);
	}

	public XY randomMove() {
		int a,b;
		do {
		 a = x + random.nextInt(3)-1;
		 b = y + random.nextInt(3)-1;
		} while (a == x && b == y);

		return new XY(a, b);
	}
	
	public XY fakeDiffCalc(XY pos1) {
		int a,b,c,d,x,y;
		a = pos1.getX();
		b = pos1.getY();
		c = this.getX();
		d = this.getY();
		
		x = a - c;
		y = b - d;
		if(x < 0) { x = x*(-1); }
		if(y < 0) { y = y*(-1); }
		
		return new XY(x,y);
	}
		
	
	public XY realDiffCalc(XY pos1) {
		int a,b,c,d,x,y;
		a = pos1.getX();
		b = pos1.getY();
		c = this.getX();
		d = this.getY();
		
		x = a - c;
		y = b - d;
		
		return new XY(x,y); 
	}
	
	public XY abnormalize() {
		int a,b;
		
		if(this.x != 0) {
			a = this.x/Math.abs(x);
		}else {
			a = this.x;
		}
		
		if(this.y != 0) {
			b = this.y/Math.abs(y);
		}else {
			b = this.y;
		}
		return new XY(a,b);
	}
	
	public XY negate() {
		return new XY(this.x*(-1), this.y*(-1));
	}

}
