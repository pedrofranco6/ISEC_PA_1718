package logic.States;

import logic.GameLogic;

public class AwaitArchersAttack extends IStatesAdapter {

    public AwaitArchersAttack(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates archersAttack(int track) {

        getGame().archersAttack(track);
        getGame().setLog("Archers attack on track " + track);

        return new AwaitActionSelection(getGame());
    }

}
