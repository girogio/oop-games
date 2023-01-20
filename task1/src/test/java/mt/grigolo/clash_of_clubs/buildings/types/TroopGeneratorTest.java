package mt.grigolo.clash_of_clubs.buildings.types;

import mt.grigolo.clash_of_clubs.buildings.troops.TroopGenerator;
import mt.grigolo.clash_of_clubs.buildings.troops.types.ArcherGenerator;
import mt.grigolo.clash_of_clubs.exceptions.ArmyAwayException;
import mt.grigolo.clash_of_clubs.exceptions.ArmyFullException;
import mt.grigolo.clash_of_clubs.exceptions.InsufficientResourceException;
import mt.grigolo.clash_of_clubs.exceptions.MaxLevelException;
import mt.grigolo.clash_of_clubs.players.Village;
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
        village.getGoldStorage().setAmount(0);
        troopGenerator.interact();
    }
}