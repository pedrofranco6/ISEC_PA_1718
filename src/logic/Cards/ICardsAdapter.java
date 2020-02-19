package logic.Cards;

import logic.GameLogic;

import java.io.Serializable;

public class ICardsAdapter implements ICards, Serializable {

    private GameLogic game;
    protected int cardNumber;
    protected int[] actionPoints = new int[] {1, 1, 1};
    protected boolean[] ladderMove = new boolean[] {false, false, false};
    protected boolean[] ramMove = new boolean[] {false, false, false};
    protected boolean[] siegeTowerMove = new boolean[] {false, false, false};

    public ICardsAdapter(GameLogic g) {
        this.game = g;
    }

    public GameLogic getGame() {
        return game;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getActionPoints(int day) {
        return actionPoints[day];
    }

    public boolean getLadderMove(int day) {
        return ladderMove[day];
    }

    public boolean getRamMove(int day) {
        return ramMove[day];
    }

    public boolean getSiegeTowerMove(int day) {
        return siegeTowerMove[day];
    }

    @Override
    public String getEvent(int day) {
        return "";
    }

    @Override
    public void setEffect(int day) {

    }

    public void setMovement(int day) {
        getGame().enemyMovement(ladderMove[day-1], ramMove[day-1], siegeTowerMove[day-1]);
    }

}

