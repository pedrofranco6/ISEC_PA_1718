import logic.Game;

import logic.ObservableGame;
import ui.text.TextUI;
import ui.graphical.GraphicalUI;

public class Main {

    public static void main(String[] args) {

        //TextUI textUI = new TextUI(new ObservableGame());
        //textUI.run();

        GraphicalUI graphicalUI = new GraphicalUI(new ObservableGame());
        graphicalUI.startMenu();
        //graphicalUI.mainMenu();
    }
}
