package mt.grigolo.clash_of_clubs.buildings.resources;

import mt.grigolo.clash_of_clubs.buildings.Building;
import mt.grigolo.clash_of_clubs.resources.Resource;
import mt.grigolo.clash_of_clubs.utils.Globals;

import static mt.grigolo.clash_of_clubs.utils.Globals.resGenDefaultGenPerTick;

public class ResourceGenerator extends Building {

    private final Resource resourceGenerated;

    private int resGenPerTick = resGenDefaultGenPerTick, resGenIncrease = Globals.resGenDefaultGenIncrease;

    public ResourceGenerator(Resource resourceGenerated, Resource resourceConsumed) {
        super(resourceConsumed, Globals.resGenMaxLevel, Globals.resGenLevelUpCost, Globals.resGenCostIncrease, Globals.resGenBuildCost);
        this.resourceGenerated = resourceGenerated;
    }

    @Override
    protected void levelUpLogic() {
        resGenPerTick += resGenIncrease;
        resGenIncrease *= Globals.resGenIncFactor;
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
        return "Lvl. " + getLevel() + "/" + getMaxLevel() + " " +
                resourceGenerated.getClass().getSimpleName() + " Generator" +
                " (" + resGenPerTick + " " + resourceGenerated.getClass().getSimpleName() + (resourceGenerated.getClass().getSimpleName().equals("Gem") ? "s" : "") + "/turn)";
    }

}