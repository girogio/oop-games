package mt.grigolo.utils;

import java.util.ArrayList;

public class Position {

    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static ArrayList<Position> shortestPath(Position a, Position b) {
        ArrayList<Position> path = new ArrayList<>();
        int x = a.getX();
        int y = a.getY();
        int x2 = b.getX();
        int y2 = b.getY();
        int dx = Math.abs(x - x2);
        int dy = Math.abs(y - y2);
        int sx = x < x2 ? 1 : -1;
        int sy = y < y2 ? 1 : -1;
        int err = dx - dy;
        while (true) {
            path.add(new Position(x, y));
            if (x == x2 && y == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position pos) {
            return pos.getX() == this.getX() && pos.getY() == this.getY();
        }
        return false;
    }
}
