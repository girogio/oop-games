package mt.grigolo.clash_of_clubs.buildings.types;

import mt.grigolo.clash_of_clubs.buildings.resources.ResourceGenerator;
import mt.grigolo.clash_of_clubs.exceptions.InsufficientResourceException;
import mt.grigolo.clash_of_clubs.exceptions.MaxLevelException;
import mt.grigolo.clash_of_clubs.players.Village;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceGeneratorTest {

    Village v;
    ResourceGenerator resourceGenerator;

    @Before
    public void setUp() {
        v = new Village(0, 0);
        resourceGenerator = new ResourceGenerator(v.getGemStorage(), v.getGemStorage());
    }

    @Test
    public void levelUpLogic() throws MaxLevelException, InsufficientResourceException {

        resourceGenerator.levelUp();
        assertEquals(2, resourceGenerator.getLevel());
    }

    @Test
    public void testTick() {
        resourceGenerator.interact();
        resourceGenerator.doTick();
    }
}