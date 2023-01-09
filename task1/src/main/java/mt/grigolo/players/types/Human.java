package mt.grigolo.players.types;

import mt.grigolo.buildings.Building;
import mt.grigolo.buildings.types.ResourceGenerator;
import mt.grigolo.buildings.types.TroopGenerator;
import mt.grigolo.exceptions.ArmyAwayException;
import mt.grigolo.exceptions.ArmyFullException;
import mt.grigolo.exceptions.InsufficientResourceException;
import mt.grigolo.players.Player;
import mt.grigolo.resources.types.Elixir;
import mt.grigolo.resources.types.Gem;
import mt.grigolo.resources.types.Gold;
import mt.grigolo.troops.types.Archer;
import mt.grigolo.troops.types.Barbarian;
import mt.grigolo.troops.types.Goblin;

import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Player {


    public Human(int x, int y, int id) {
        super(x, y, id);
    }

    @Override
    public void playerInput() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println(getVillage());

            System.out.println("1. Build");
            System.out.println("2. Upgrade");
            System.out.println("3. Train");
            System.out.println("4. Attack");
            System.out.println("5. Surrender");
            System.out.println("6. Pass");

            do {
                System.out.print("> ");
                choice = scanner.nextInt();


                System.out.print("\033[H\033[2J");

                switch (choice) {
                    case 1 -> {
                        int building, type = 0;
                        boolean purchaseSuccesful = false;

                        do { // until purchase successful


                            do { // until valid building type

                                System.out.println("1. Resource Generator");
                                System.out.println("2. Troop Generator");
                                System.out.println("3. Back");
                                System.out.print("> ");
                                building = scanner.nextInt();

                            } while (building < 1 || building > 3);

                            if (building == 3) {
                                break;
                            }

                            switch (building) {

                                // if resource generator
                                case 1 -> {

                                    do { // until valid sub building type
                                        System.out.print("\033[H\033[2J");
                                        ResourceGenerator goldGen = new ResourceGenerator(new Gold(), new Elixir());
                                        ResourceGenerator gemGen = new ResourceGenerator(new Gem(), new Gold());
                                        ResourceGenerator elixirGen = new ResourceGenerator(new Elixir(), new Gem());

                                        System.out.println("1. " + goldGen.getBuildCostString() + " – " + goldGen);
                                        System.out.println("2. " + gemGen.getBuildCostString() + " – " + gemGen);
                                        System.out.println("3. " + elixirGen.getBuildCostString() + " – " + elixirGen);

                                        System.out.println("4. Back");
                                        System.out.print("> ");
                                        type = scanner.nextInt();

                                    } while (type < 1 || type > 4);
                                }

                                // if troop generator1
                                case 2 -> {
                                    do { //until valid sub building type
                                        System.out.print("\033[H\033[2J");
                                        TroopGenerator placeHolderGen = new TroopGenerator(new Archer(), getVillage().getArmy(), getVillage().getGemStorage());
                                        System.out.println("1. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                                        placeHolderGen = new TroopGenerator(new Goblin(), getVillage().getArmy(), getVillage().getGoldStorage());
                                        System.out.println("2. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                                        placeHolderGen = new TroopGenerator(new Barbarian(), getVillage().getArmy(), getVillage().getElixirStorage());
                                        System.out.println("3. " + placeHolderGen.getBuildCostString() + " – " + placeHolderGen);

                                        System.out.println("4. Back");
                                        System.out.print("> ");
                                        type = scanner.nextInt();
                                    } while (type < 1 || type > 4);
                                }
                            }

                            try {
                                purchaseSuccesful = build(building, type);
                            } catch (InsufficientResourceException e) {
                                System.out.println("\n\n" + e.getMessage() + "\n\n");
                            }

                        } while (!purchaseSuccesful);
                    }

                    case 2 -> {
                        int buildingToLevelUp;
                        do {
                            System.out.println("Choose building to upgrade\n ");
                            for (int i = 0; i < getVillage().getBuildings().size(); i++) {
                                System.out.println(i + 1 + ". Cost: " + getVillage().getBuildings().get(i).getLevelUpString() + " – "
                                        + getVillage().getBuildings().get(i) + "\n");
                            }
                            System.out.println(getVillage().getBuildings().size() + 1 + ". Back");
                            System.out.print("> ");
                            buildingToLevelUp = scanner.nextInt();
                        } while (buildingToLevelUp > getVillage().getBuildings().size() + 1 || buildingToLevelUp < 1);

                        if (buildingToLevelUp != getVillage().getBuildings().size() + 1) {
                            try {
                                getVillage().levelUpBuilding(buildingToLevelUp - 1);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("\n\n\n\n\n\n");
                        }
                    }

                    case 3 -> {
                        System.out.println("Choose troop to train\n ");

                        for (int i = 0; i < getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).count(); i++) {
                            System.out.println(i + 1 + ". Cost: " + getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).toArray()[i]);
                        }

                        System.out.println(getVillage().getBuildings().stream().filter(building -> building instanceof TroopGenerator).count() + 1 + ". Back");

                        System.out.print("> ");

                        ArrayList<Integer> troopGenerators = new ArrayList<>();

                        for (Building building : getVillage().getBuildings()) {
                            if (building instanceof TroopGenerator) {
                                troopGenerators.add(getVillage().getBuildings().indexOf(building));
                            }
                        }

                        int troopToTrain = scanner.nextInt();

                        try {
                            getVillage().getBuildings().get(troopGenerators.get(troopToTrain - 1)).interact();
                        } catch (InsufficientResourceException | ArmyFullException | ArmyAwayException e) {
                            System.out.println("\n\n" + e.getMessage() + "\n\n");
                        }
                    }

                }
            } while (choice < 1 || choice > 6);
        } while (choice != 6);
    }
}