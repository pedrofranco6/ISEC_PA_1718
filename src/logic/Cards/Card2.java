package logic.Cards;

import logic.GameLogic;

public class Card2 extends ICardsAdapter {

    public Card2(GameLogic gl) {
        super(gl);
        cardNumber =  2;
        actionPoints = new int[] {2, 2, 1};
        siegeTowerMove[0] = true;
        //move slowest enemy
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Illness";
            case (2):
                return "Guards Distracted";
            case (3):
                return "Trebuchet Attack";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().illness();
        else if (day == 2)
            getGame().guardsDistracted();
        else if (day == 3)
            getGame().trebuchetAttack();
    }
}
