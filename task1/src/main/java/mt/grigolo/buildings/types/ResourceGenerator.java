package mt.grigolo.buildings.types;

import mt.grigolo.buildings.Building;
import mt.grigolo.utils.LevelSystem;
import mt.grigolo.players.Player;
import mt.grigolo.resources.Resource;

public class ResourceGenerator extends Building {

    private final Resource resourceGenerated;

    private int resGenPerTick = 10, resGenIncrease;

    private static final double resGenIncFactor = 1.2;


    public ResourceGenerator(Resource resourceGenerated, Resource resourceConsumed) {
        LevelSystem levelSystem = new LevelSystem(resourceConsumed, 10, 50, 50) {

            @Override
            public void levelUpLogic() {
                resGenPerTick += resGenIncrease;
                resGenIncrease *= resGenIncFactor;
            }

        };
        super.setLevelSystem(levelSystem);
        this.resourceGenerated = resourceGenerated;
    }


    @Override
    public void doTick() {
        resourceGenerated.increment(resGenPerTick);
    }

    @Override
    public void interact(Player player) {
        // no interaction
    }

}