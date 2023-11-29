package mainObjects;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates o) {
            return o.x == x && o.y == y;
        }
        return super.equals(obj);
    }
}
