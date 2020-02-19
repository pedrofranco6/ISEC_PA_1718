package ui.text;

import logic.ObservableGame;
import logic.States.*;

import java.util.Scanner;

public class TextUI {
    private ObservableGame game;
    private boolean quit = false;
    Scanner sc;

    public TextUI(ObservableGame game) {
        sc = new Scanner(System.in);
        this.game = game;
    }

    public void uiAwaitBeginning() {
        int option;

        while (true) {

            do {
                System.out.println();
                System.out.println("0 - Quit");
                System.out.println();
                System.out.println("1 - Start");
                System.out.println();
                System.out.print("> ");

                option = sc.nextInt();

            } while (option < 0 || option > 1);

            switch (option) {

                case 0:
                    quit = true;
                    return;

                case 1:
                    game.startGame();
                    return;

                default:
                    return;
            }
        }
    }

    public void uiAwaitPickCard() {
        int option;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Draw new card");
            System.out.println();
            System.out.print("> ");

            option = sc.nextInt();

        } while (option < 0 || option > 1);

        switch (option) {

            case 0:
                quit = true;
                return;

            case 1:
                game.pickCard();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            default:
                return;
        }
    }

    public void uiAwaitActionSelection() {
        int option;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("0 - Quit");
            System.out.println();
            System.out.println("1 - Archers Attack");
            System.out.println("2 - Boiling Water Attack");
            System.out.println("3 - Close Combat Attack");
            System.out.println("4 - Coupure");
            System.out.println("5 - Rally Troops");
            System.out.println("6 - Tunnel Movement");
            System.out.println("7 - Supply Raid");
            System.out.println("8 - Sabotage");
            System.out.println("9 - Change moral/action points");
            System.out.println();
            System.out.println("10 - End turn");
            System.out.println();
            System.out.print("> ");

            option = sc.nextInt();

        } while (option < 0 || option > 10);

        switch (option) {

            case 0:
                quit = true;
                return;

            case 1:
                game.awaitArchersAttack();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 2:
                game.awaitBoilingWaterAttack();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 3:
                game.closeCombatAttack();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 4:
                game.coupure();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 5:
                game.rallyTroops();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 6:
                game.awaitTunnelMovement();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 7:
                game.supplyRaid();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 8:
                game.sabotage();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 9:
                game.awaitAddActionPoint();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            case 10:
                game.endTurn();
                System.out.println(game.getGame().getGameLogic().getLog());
                return;

            default:
                return;
        }
    }

    public void uiAwaitArchersAttack() {
        int track;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("Choose a track to attack:");
            System.out.println();
            System.out.print("> ");

            track = sc.nextInt();

            if (track < 1 || track > 3)
                System.out.println("Invalid track, choose again.");
        }while(track < 1 || track > 3);

        //attack on track
        game.archersAttack(track);
        System.out.println(game.getGame().getGameLogic().getLog());
    }

    public void uiAwaitBoilingWaterAttack() {
        int track;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("Choose a circle to attack:");
            System.out.println();
            System.out.print("> ");

            track = sc.nextInt();

            if (track < 1 || track > 3)
                System.out.println("Invalid circle, choose again.");
        }while(track < 1 || track > 3);

        //attack on track
        game.boilingWaterAttack(track);
        System.out.println(game.getGame().getGameLogic().getLog());
    }

    public void uiAwaitForTunnelMov() {
        int number;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("0 - Leave tunnel movement");
            System.out.println();
            System.out.println("1 - Move into the tunnel");
            System.out.println();
            System.out.println("2 - Free Movement");
            System.out.println();
            System.out.println("3 - Fast Movement");
            System.out.println();
            System.out.print("> ");

            number = sc.nextInt();

        } while (number < 0 || number > 3);

        game.tunnelMovement(number);
        System.out.println(game.getGame().getGameLogic().getLog());
    }

    public void uiAwaitAddActionPoint() {
        int number;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("1 - Spend 1 Morale point");
            System.out.println();
            System.out.println("2 - Spend 1 Supply point");
            System.out.println();
            System.out.print("> ");

            number = sc.nextInt();
        } while (number < 1 || number > 2);

        game.addActionPoint(number);
        System.out.println(game.getGame().getGameLogic().getLog());
    }

    public void uiAwaitRallyTroops() {
        int option;

        do {
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("1 - Boost your die roll by spending 1 supply point");
            System.out.println();
            System.out.println("2 - Just try to boost morale");
            System.out.println();
            System.out.print("> ");

            option = sc.nextInt();
        } while (option < 1 || option > 2);

        game.rallyTroops(option);
        System.out.println(game.getGame().getGameLogic().getLog());
    }

    public void run() {

        while (!quit) {

            IStates state = game.getState();

            if (state instanceof AwaitBeginning)
                uiAwaitBeginning();
            else if (state instanceof AwaitPickCard)
                uiAwaitPickCard();
            else if (state instanceof AwaitActionSelection)
                uiAwaitActionSelection();
            else if (state instanceof AwaitArchersAttack)
                uiAwaitArchersAttack();
            else if (state instanceof AwaitBoilingWaterAttack)
                uiAwaitBoilingWaterAttack();
            else if (state instanceof AwaitTunnelMovement)
                uiAwaitForTunnelMov();
            else if (state instanceof AwaitAddActionPoint)
                uiAwaitAddActionPoint();
            else if (state instanceof AwaitRallyTroops)
                uiAwaitRallyTroops();
        }
    }
}
