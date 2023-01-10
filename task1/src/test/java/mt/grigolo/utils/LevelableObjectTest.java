package mt.grigolo.utils;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.resources.Resource;
import mt.grigolo.resources.types.Gold;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelableObjectTest {

    LevelableObject levelableObject = new LevelableObject(10, 10, 10, new Gold(1000, 1000)) {
        @Override
        protected void levelUpLogic() {

        }
    };

    @Test
    public void levelUp() throws MaxLevelException, InsufficientResourceException {
        for (int i = 0; i < 2; i++) {
            levelableObject.levelUp();
        }
        assertEquals(3, levelableObject.getLevel());
        assertEquals(32, levelableObject.getLevelUpCost());
    }

    @Test(expected = MaxLevelException.class)
    public void levelUpMaxLevel() throws MaxLevelException, InsufficientResourceException {
        for (int i = 0; i < 10; i++) {
            levelableObject.levelUp();
        }
        levelableObject.levelUp();
    }

    @Test(expected = InsufficientResourceException.class)
    public void levelUpInsufficientResource() throws MaxLevelException, InsufficientResourceException {
        levelableObject.setLevelUpResource(new Gold(0, 0));
        levelableObject.levelUp();
    }
}
