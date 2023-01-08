package mt.grigolo.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    Position positionA;
    Position positionB;

    @Before
    public void setUp()  {
        positionA = new Position(0, 0);
        positionB = new Position(9, 4);
    }

    @Test
    public void getX() {
        assertEquals(0, positionA.getX());
    }

    @Test
    public void getY() {
        assertEquals(0, positionA.getY());
    }

    @Test
    public void shortestPath() {
        assertEquals(10, Position.shortestPath(positionA, positionB).size());
    }

    @Test
    public void testEquals() {
        assertNotEquals(positionA, positionB);
        assertEquals(positionA, new Position(0, 0));
        assertNotEquals(positionA, "");
    }
}