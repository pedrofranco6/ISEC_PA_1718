package logic.Cards;

import logic.GameLogic;

public class Card1 extends ICardsAdapter{

    public Card1(GameLogic gl) {
        super(gl);
        cardNumber =  1;
        actionPoints = new int[] {3, 2, 1};
    }

    @Override
    public String getEvent(int day)  {
        return "Trebuchet Attack";
    }

    @Override
    public void setEffect(int day) {
        getGame().trebuchetAttack();
    }

}
