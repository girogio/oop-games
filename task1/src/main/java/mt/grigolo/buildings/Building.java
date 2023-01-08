package mt.grigolo.buildings;

import mt.grigolo.utils.LevelSystem;
import mt.grigolo.players.Player;

public abstract class Building {

    LevelSystem levelSystem;

    public Building(){

    }

    public LevelSystem getLevelSystem() {
        return levelSystem;
    }

    public void setLevelSystem(LevelSystem levelSystem) {
        this.levelSystem = levelSystem;
    }

    public abstract void doTick();

    protected abstract void interact(Player player);

}
