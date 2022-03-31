package prog2Prak2;

import java.util.Random;

public class XY {
	public static final XY UP = new XY(0,1);
	public static final XY DOWN = new XY(0,-1);
	public static final XY LEFT = new XY(-1,0);
	public static final XY RIGHT = new XY(1,0);
	private final int x;
	private final int y;

	private static Random random = new Random();

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
}
