package mt.grigolo.buildings.types;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.players.Village;
import mt.grigolo.troops.types.Archer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TroopGeneratorTest {

    Village village;
    TroopGenerator troopGenerator;

    @Before
    public void setUp() {
        village = new Village(0, 0);
        troopGenerator = new TroopGenerator(new Archer(), village.getArmy(), village.getGemStorage(), 10);
    }

    @Test
    public void levelUpLogic() throws MaxLevelException, InsufficientResourceException {
        assertEquals(0, troopGenerator.getDiscount(), 0.001);
        troopGenerator.levelUp();
        assertEquals(0.03, troopGenerator.getDiscount(), 0.001);
    }

    @Test
    public void interact() throws InsufficientResourceException {
        troopGenerator.doTick();
        assertEquals(0, village.getArmy().size());
        troopGenerator.interact();
        troopGenerator.interact();
        troopGenerator.interact();
        troopGenerator.interact();
        troopGenerator.interact();
        troopGenerator.interact();
        assertEquals(2, village.getArmy().size());
    }


    @Test (expected = InsufficientResourceException.class)
    public void interactInsufficientResource() throws InsufficientResourceException {
        village.getGemStorage().setAmount(0);
        troopGenerator.interact();
    }
}