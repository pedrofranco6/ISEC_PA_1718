package logic.States;

import logic.GameLogic;

public class AwaitAddActionPoint extends  IStatesAdapter{

    public AwaitAddActionPoint(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates addActionPoint(int number) {

        getGame().addActionPoint(number);
        return new AwaitActionSelection(getGame());
    }
}
