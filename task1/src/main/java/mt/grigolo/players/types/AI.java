package mt.grigolo.players.types;

import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.exceptions.*;
import mt.grigolo.game.Map;
import mt.grigolo.players.Player;
import mt.grigolo.utils.Input;

import java.util.List;

public class AI extends Player {

    public AI(int x, int y, int id) {
        super(x, y, id);
    }

    public void playerInput() {

        int numberOfActions = Input.randomInt(1, 3);

        for (int i = 0; i < numberOfActions; i++) {

            int choice = Input.randomInt(1, 4);

            if (choice == 1) {

                int building, type;

                building = Input.randomInt(1, 2);
                type = Input.randomInt(1, 3);

                try {
                    build(building, type);
                } catch (InsufficientResourceException ignored) {

                }

            } else if (choice == 2 && getVillage().getBuildings().size() > 0) {

                int buildingToLevelUp = Input.randomInt(0, getVillage().getBuildings().size() - 1);

                try {
                    if (buildingToLevelUp == 1) {
                        getVillage().levelUp();
                    } else {
                        getVillage().getBuildings().get(buildingToLevelUp).levelUp();
                    }
                } catch (InsufficientResourceException | MaxLevelException ignored) {

                }

            } else if (choice == 3 && getVillage().getBuildings().stream().anyMatch(building -> building instanceof TroopGenerator)) {

                List<Integer> troopGenerators = getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).
                        map(building -> getVillage().getBuildings().indexOf(building)).toList();

                int troopToTrain = Input.randomInt(0, troopGenerators.size() - 1);

                try {
                    getVillage().getBuildings().get(troopToTrain).interact();
                } catch (InsufficientResourceException | ArmyFullException | ArmyAwayException ignored) {

                }

            } else if (choice == 4 && Map.players.size() > 1) {

                List<Integer> villages = Map.players.stream().filter(player -> !this.equals(player)).map(Map.players::indexOf).toList();

                int villageToAttack = Input.randomInt(0, Map.players.size() - 2);

                try {
                    getVillage().getArmy().initiateAttack(Map.players.get(villages.get(villageToAttack)).getVillage());
                } catch (ArmyEmptyException | ArmyBusyException ignored) {

                }

            }
        }

    }

}
