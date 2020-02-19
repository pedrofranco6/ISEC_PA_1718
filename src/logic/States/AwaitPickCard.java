package logic.States;

import logic.Cards.*;
import logic.GameLogic;

public class AwaitPickCard extends IStatesAdapter {

    public AwaitPickCard (GameLogic gl){
        super(gl);
    }

    @Override
    public IStates pickCard() {

        getGame().newTurn();

        return new AwaitActionSelection(getGame());
    }

}
