package mt.grigolo.buildings.types;

import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.buildings.troops.types.ArcherGenerator;
import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.players.Village;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TroopGeneratorTest {

    Village village;
    TroopGenerator troopGenerator;

    @Before
    public void setUp() {
        village = new Village(4, 12);
        troopGenerator = new ArcherGenerator(village);
    }

    @Test
    public void levelUpLogic() throws MaxLevelException, InsufficientResourceException {
        assertEquals(0, troopGenerator.getDiscount(), 0.001);
        troopGenerator.levelUp();
        assertEquals(0.03, troopGenerator.getDiscount(), 0.001);
    }

    @Test
    public void interact() throws InsufficientResourceException, ArmyFullException, ArmyAwayException {
        troopGenerator.doTick();
        assertEquals(0, village.getArmy().size());
        troopGenerator.interact();
        troopGenerator.interact();
        assertEquals(2, village.getArmy().size());
    }


    @Test(expected = InsufficientResourceException.class)
    public void interactInsufficientResource() throws InsufficientResourceException, ArmyFullException, ArmyAwayException {
        village.getGemStorage().setAmount(0);
        troopGenerator.interact();
    }
}