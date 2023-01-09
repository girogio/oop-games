package mt.grigolo.buildings.types;

import mt.grigolo.buildings.Building;
import mt.grigolo.players.Player;
import mt.grigolo.resources.Resource;

public class ResourceGenerator extends Building {

    private final Resource resourceGenerated;

    private int resGenPerTick = 10, resGenIncrease;

    private static final double resGenIncFactor = 1.2;


    public ResourceGenerator(Resource resourceGenerated, Resource resourceConsumed) {
        super(resourceConsumed, 10, 30, 50, 50);
        this.resourceGenerated = resourceGenerated;
    }

    @Override
    protected void levelUpLogic() {
        resGenPerTick += resGenIncrease;
        resGenIncrease *= resGenIncFactor;
    }

    @Override
    public void doTick() {
        resourceGenerated.increment(resGenPerTick);
    }

    @Override
    public void interact() {
        // no interaction
    }

    @Override
    public String toString() {
        return "Lvl. " + getLevel() + "/" + getMaxLevel() +
                resourceGenerated.getClass().getSimpleName() + " Generator" +
                " (" + resGenPerTick + " " + resourceGenerated.getClass().getSimpleName() + "/turn)";
    }

}