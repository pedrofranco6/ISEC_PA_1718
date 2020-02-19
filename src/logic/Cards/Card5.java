package logic.Cards;

import logic.GameLogic;

public class Card5 extends ICardsAdapter {

    public Card5(GameLogic gl) {
        super(gl);
        cardNumber =  5;
        actionPoints = new int[] {3, 2, 2};
        ladderMove[1] = true; ladderMove[2] = true;
        ramMove[0] = true; ramMove[1] = true;
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Volley of Arrows";
            case (2):
                return "Collapsed!";
            case (3):
                return "Repaired Trebuchet";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().volleyOfArrows();
        else if (day == 2)
            getGame().collapsed();
        else if (day == 3)
            getGame().repairedTrebuchet();
    }

}
