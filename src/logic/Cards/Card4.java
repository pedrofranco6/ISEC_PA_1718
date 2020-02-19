package logic.Cards;

import logic.GameLogic;

public class Card4 extends ICardsAdapter {

    public Card4(GameLogic gl) {
        super(gl);
        cardNumber =  4;
        actionPoints = new int[] {2, 2, 3};
        ladderMove[0] = true; ladderMove[1] = true;
        ramMove[1] =  true;
        siegeTowerMove[0] = true; siegeTowerMove[2] = true;
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Death of a Leader";
            case (2):
                return "Gate Fortified";
            case (3):
                return "Flaming Arrows";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().deathOfALeader();
        else if (day == 2)
            getGame().gateFortified();
        else if (day == 3)
            getGame().flamingArrows();
    }
}
