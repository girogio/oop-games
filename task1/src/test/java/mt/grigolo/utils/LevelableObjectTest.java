package mt.grigolo.utils;

import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.exceptions.MaxLevelException;
import mt.grigolo.resources.Resource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelableObjectTest {
    LevelableObject levelableObject = new LevelableObject(10, 10, 10, new Resource(1000, 1000) {
        @Override
        public String getName(boolean plural) {
            return "Test Currency";
        }
    }) {
        @Override
        public void levelUpLogic() {
            // do nothing
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
        levelableObject.setLevelUpResource(new Resource(0, 0) {
            @Override
            public String getName(boolean plural) {
                return "Test Currency";
            }
        });
        levelableObject.levelUp();
    }
}
