package logic.States;

import logic.GameLogic;

public class AwaitBeginning extends IStatesAdapter {

    public AwaitBeginning(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates startGame() {
        getGame().begin();
        return new AwaitPickCard(getGame());
    }

}
