package mt.grigolo.utils;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.resources.Resource;
import mt.grigolo.resources.types.GemStorage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LevelSystemTest {

    LevelSystem levelSystem;
    Resource resourceConsumed;

    @Before
    public void setUp() {
        resourceConsumed = new GemStorage(20000, 100000);
        levelSystem = new LevelSystem(resourceConsumed, 5, 100, 100) {
            @Override
            public void levelUpLogic() {
                // do nothing
            }
        };
    }

    @Test
    public void levelUp() {
        try {

            levelSystem.levelUp();
            assertEquals(2, levelSystem.getLevel());
            assertEquals(19900, resourceConsumed.getAmount());

            levelSystem.levelUp();
            assertEquals(3, levelSystem.getLevel());
            assertEquals(19700, resourceConsumed.getAmount());

            levelSystem.levelUp();
            assertEquals(4, levelSystem.getLevel());
            assertEquals(19380, resourceConsumed.getAmount());

            assertEquals(464, levelSystem.getLevelUpCost());
        } catch (InsufficientResourceException | MaxLevelException e) {
            fail();
        }

    }

    @Test
    public void levelUpMaxLevel() {
        try {
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
            levelSystem.levelUp();
        } catch (InsufficientResourceException e) {
            fail();
        } catch (MaxLevelException e) {
            assertEquals(6, levelSystem.getLevel());
            assertEquals(18280, resourceConsumed.getAmount());
        }
    }

    @Test
    public void testInsufficientResource() {
        resourceConsumed.setAmount(0);

        try {
            levelSystem.levelUp();
            fail();
        } catch (InsufficientResourceException e) {
            assertEquals(1, levelSystem.getLevel());
            assertEquals(0, resourceConsumed.getAmount());
        } catch (MaxLevelException e) {
            fail();
        }

    }
}