package mt.grigolo.players.types;

import mt.grigolo.buildings.Building;
import mt.grigolo.buildings.resources.ResourceGenerator;
import mt.grigolo.buildings.troops.TroopGenerator;
import mt.grigolo.buildings.troops.types.ArcherGenerator;
import mt.grigolo.buildings.troops.types.BarbarianGenerator;
import mt.grigolo.buildings.troops.types.GoblinGenerator;
import mt.grigolo.exceptions.*;
import mt.grigolo.game.Map;
import mt.grigolo.players.Player;
import mt.grigolo.resources.types.Elixir;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.resources.types.Gold;
import mt.grigolo.utils.Input;

import java.util.ArrayList;

public class Human extends Player {

    private Map map;

    public Human(int x, int y, int id) {
        super(x, y, id);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public void playerInput() {

        int choice;

        do {

            Input.clearScreen();

            System.out.println("Round " + map.round + ", player " + getId() + "'s turn.\n\n");

            if(!isAlive()){
                System.out.println("""
                                                        _____  _____
                                                        <     `/     |
                                                         >          (
                                                        |   _     _  |
                                                        |  |_) | |_) |
                                                        |  | \\ | |   |
                                                        |            |
                                         ______.______%_|            |__________  _____
                                       _/                                       \\|     |
                                      |                    You Died.
                                      |_____.-._________              ____/|___________|
                                                        |            |
                                                        |            |
                                                        |            |
                                                        |            |
                                                        |   _        <
                                                        |__/         |
                                                         / `--.      |
                                                       %|            |%
                                                   |/.%%|          -< @%%%
                                                   `\\%`@|     v      |@@%@%%    - mfj
                                                 .%%%@@@|%    |    % @@@%%@%%%%
                                            _.%%%%%%@@@@@@%%_/%\\_%@@%%@@@@@@@%%%%%%
                        """);
                Input.pause();
                choice = 6; //force pass
            }else {

                System.out.println(map + "\n\n");

                System.out.println("P" + getId() + "'s " + getVillage());

                System.out.println("1. Build");
                System.out.println("2. Upgrade");
                System.out.println("3. Train");
                System.out.println("4. Attack");
                System.out.println("5. Surrender");
                System.out.println("6. Pass");
                System.out.print("> ");

                choice = Input.getInt(1, 6);
            }

            if (choice == 1) {

                int building, type;

                Input.clearScreen();
                System.out.println("1. Resource Generator");
                System.out.println("2. Troop Generator");
                System.out.println("3. Back");
                System.out.print("> ");

                building = Input.getInt(1, 3);

                Input.clearScreen();

                if (building == 1) {

                    ResourceGenerator goldGen = new ResourceGenerator(new Gold(), new Elixir());
                    ResourceGenerator gemGen = new ResourceGenerator(new Gem(), new Gold());
                    ResourceGenerator elixirGen = new ResourceGenerator(new Elixir(), new Gem());

                    System.out.println("1. " + goldGen.getBuildCostString() + " – " + goldGen);
                    System.out.println("2. " + gemGen.getBuildCostString() + " – " + gemGen);
                    System.out.println("3. " + elixirGen.getBuildCostString() + " – " + elixirGen);

                } else if (building == 2) {

                    TroopGenerator placeHolderGen = new ArcherGenerator(getVillage());
                    System.out.println("1. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                    placeHolderGen = new GoblinGenerator(getVillage());
                    System.out.println("2. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                    placeHolderGen = new BarbarianGenerator(getVillage());
                    System.out.println("3. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                } else if (building == 3) {
                    playerInput();
                }

                System.out.println("4. Back");
                System.out.print("> ");

                type = Input.getInt(1, 4);

                if (type == 4) {
                    playerInput();
                } else {
                    try {
                        build(building, type);
                    } catch (InsufficientResourceException e) {
                        System.out.println("\n\n" + e.getMessage() + "\n\n");
                        Input.pause();
                    }
                }

            } else if (choice == 2) {

                int buildingToLevelUp;
                Input.clearScreen();
                System.out.println("Choose building to upgrade\n ");
                System.out.println("1. " + getVillage().getLevelUpString() + " – " + getVillage());

                for (int i = 0; i < getVillage().getBuildings().size(); i++) {
                    System.out.println(i + 2 + ". Cost: " + getVillage().getBuildings().get(i).getLevelUpString() + " – "
                            + getVillage().getBuildings().get(i) + "\n");
                }

                System.out.println(getVillage().getBuildings().size() + 2 + ". Back");
                System.out.print("> ");

                buildingToLevelUp = Input.getInt(1, getVillage().getBuildings().size() + 2);

                if (buildingToLevelUp == getVillage().getBuildings().size() + 2) {
                    playerInput();
                } else {
                    try {
                        if (buildingToLevelUp == 1) {
                            getVillage().levelUp();
                        } else {
                            getVillage().getBuildings().get(buildingToLevelUp - 2).levelUp();
                        }
                    } catch (InsufficientResourceException | MaxLevelException e) {
                        System.out.println("\n\n" + e.getMessage() + "\n\n");
                        Input.pause();
                    }
                }

            } else if (choice == 3) {
                Input.clearScreen();
                for (int i = 0; i < getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).count(); i++) {
                    TroopGenerator b = (TroopGenerator) getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).toArray()[i];
                    System.out.println((i + 1) + ". " + b.getInteractCostString() + " – " + b.generatedTroop());
                }

                System.out.println(getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).count() + 1 + ". Back");

                System.out.println("\nChoose troop index to train.\n ");
                System.out.print("> ");

                ArrayList<Integer> troopGenerators = new ArrayList<>();

                for (Building building : getVillage().getBuildings()) {
                    if (building instanceof TroopGenerator) {
                        troopGenerators.add(getVillage().getBuildings().indexOf(building));
                    }
                }

                int troopToTrain = Input.getInt(1, troopGenerators.size() + 1);

                if (troopToTrain == troopGenerators.size() + 1) {
                    playerInput();
                    return;
                } else {
                    try {
                        getVillage().getBuildings().get(troopGenerators.get(troopToTrain - 1)).interact();
                    } catch (InsufficientResourceException | ArmyFullException | ArmyAwayException e) {
                        System.out.println("\n\n" + e.getMessage() + "\n\n");
                        Input.pause();
                    }
                }

            } else if (choice == 4) {

                Input.clearScreen();
                System.out.println("Choose a village to attack\n ");

                ArrayList<Integer> villages = new ArrayList<>();

                for (int i = 0; i < Map.players.size(); i++) {
                    if (!this.equals(Map.players.get(i))) {
                        Player p = Map.players.get(i);
                        System.out.println("Player " + p.getId() + "'s Village:\n");
                        System.out.println(Map.players.get(i).getVillage());
                        System.out.println("\n");
                        villages.add(i);
                    }
                }

                for (Integer x : villages)
                    System.out.println(x + ". Player " + Map.players.get(x).getId() + "'s Village");

                System.out.println(Map.players.size() + ". Back");
                System.out.print("> ");


                int villageToAttack = Input.getInt(1, Map.players.size());

                if (villageToAttack == Map.players.size()) {
                    playerInput();
                    return;
                }


                try {
                    getVillage().getArmy().initiateAttack(Map.players.get(villages.get(villageToAttack - 1)).getVillage());
                } catch (ArmyEmptyException | ArmyBusyException e) {
                    System.out.println("\n\n" + e.getMessage() + "\n\n");
                    Input.pause();
                }


            } else if (choice == 5) {
                getVillage().setHealth(0);
                return;
            }

        } while (choice != 6);
    }

}