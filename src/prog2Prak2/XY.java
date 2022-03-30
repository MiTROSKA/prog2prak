package prog2Prak2;

import java.util.Random;

public class XY {
	private final int x;
	private final int y;

	private static Random random = new Random();

	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return this.x; }

	public int getY() { return this.y; }

	public XY move() {
		int a,b;
		do {
		 a = x + random.nextInt(3)-1;
		 b = y + random.nextInt(3)-1;
		} while (a == b);

		return new XY(a, b);
	}
}
