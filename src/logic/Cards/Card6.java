package logic.Cards;

import logic.GameLogic;

public class Card6 extends ICardsAdapter {

    public Card6(GameLogic gl) {
        super(gl);
        cardNumber =  6;
        actionPoints = new int[] {3, 3, 3};
        //move slowest enemy
        ladderMove[1] = true;
        ramMove[2] = true;
        siegeTowerMove[2] = true;
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Cover of Darkness";
            case (2):
                return "Enemy Fatigue";
            case (3):
                return "Rally!";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().coverOfDarkness();
        else if (day == 2)
            getGame().enemyFatigue();
        else if (day == 3)
            getGame().rally();
    }
}