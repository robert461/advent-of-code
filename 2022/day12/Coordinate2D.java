package day12;

import java.util.Objects;

public class Coordinate2D implements Comparable<Coordinate2D> {
    public int x = 0;
    public int y = 0;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate2D() {
        x = 0;
        y = 0;
    }

    @Override
	public int hashCode() {
		return Objects.hash(y,x);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;

		if (getClass() != obj.getClass())
			return false;
        
		Coordinate2D other = (Coordinate2D) obj;

		if (x != other.x) return false;
		if (y != other.y) return false;

		return true;
	}

    @Override
    public int compareTo(Coordinate2D o) {
        if (this.equals(o)) return 0;
		else if (o.y > this.y) return -1;
		else if (o.y < this.y) return 1;
		else return (o.x > this.x ? -1 : 1);
    }
}
