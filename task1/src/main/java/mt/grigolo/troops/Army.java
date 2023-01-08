package mt.grigolo.troops;

import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.game.Village;
import mt.grigolo.utils.Position;

import java.util.ArrayList;

public class Army {

    private final ArrayList<Troop> troops = new ArrayList<>();

    public int maxTroops;
    private boolean stationed;

    public void addTroop(Troop troop) throws ArmyFullException {
        if (troops.size() < maxTroops) {
            troops.add(troop);
        } else
            throw new ArmyFullException();
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    private final Village sourceVillage;
    private Village targetVillage;

    Position currentPos;

    public Army(Village sourceVillage) {
        this.sourceVillage = sourceVillage;
        currentPos = sourceVillage.pos;
        maxTroops = 2;
    }

    public void emptyInventory() {
        if(currentPos.equals(sourceVillage.pos)) {
            for (Troop troop : troops) {
                troop.getInventory().transferTo(sourceVillage.getGemStorage());
                troop.getInventory().transferTo(sourceVillage.getGoldStorage());
                troop.getInventory().transferTo(sourceVillage.getElixirStorage());
            }
            stationed = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\t Capacity: " + this.troops.size() + "/" + this.maxTroops + "\n");
        for (Troop troop : troops) {
            s.append("\t ").append(troop.toString()).append("\n");
        }
        return s.toString();
    }


}
