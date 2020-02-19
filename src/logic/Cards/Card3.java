package logic.Cards;

import logic.GameLogic;

public class Card3 extends ICardsAdapter {

    //option da textui, actionSelection
    int option;

    public Card3(GameLogic gl) {
        super(gl);
        cardNumber =  3;
        actionPoints = new int[] {2, 2, 2};
        ladderMove[0] = true; ladderMove[2] = true;
        ramMove[2] = true;
    }

    @Override
    public String getEvent(int day) {
        switch (day) {
            case (1):
                return "Supplies Spoiled";
            case (2):
                return "Bad Weather";
            case (3):
                return "Boiling Oil";
            default:
                return "";
        }
    }

    @Override
    public void setEffect(int day) {
        if (day == 1)
            getGame().suppliesSpoiled();
        else if (day == 2)
            getGame().badWeather(option);
        else if (day == 3)
            getGame().boilingOil();
    }
}
