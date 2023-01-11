package mt.grigolo.clash_of_clubs.utils;

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
    public void testDistance() {
        assertEquals(10, Position.distance(positionA, positionB));
    }

    @Test
    public void testEquals() {
        assertNotEquals(positionA, positionB);
        assertEquals(positionA, new Position(0, 0));
        assertNotEquals(positionA, "");
    }
}