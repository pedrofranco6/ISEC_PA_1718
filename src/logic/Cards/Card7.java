package logic.Cards;

import logic.GameLogic;

public class Card7 extends ICardsAdapter {

    public Card7(GameLogic gl) {
        super(gl);
        cardNumber =  7;
        actionPoints = new int[] {2, 2, 3};
        ladderMove[2] = true;
        ramMove[0] = true; ramMove[2] = true;
        siegeTowerMove[1] = true; siegeTowerMove[2] = true;
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Determined Enenmy";
            case (2):
                return "Iron Shields";
            case (3):
                return "Faith";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().determinedEnenmy();
        else if (day == 2)
            getGame().ironShields();
        else if (day == 3)
            getGame().faith();
    }
}