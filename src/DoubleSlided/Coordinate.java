package DoubleSlided;

public class Coordinate {
	int x;
	int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate c = (Coordinate) obj;
			return this.x == c.getX() && this.y == c.getY();
		}
		return false;
	}
}
