package year2021;

public class Tup {
    public int x;
    public int y;

    public Tup(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public boolean equals(Object o) {
        Tup other = (Tup) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return y << 128 + x;
    }
}
