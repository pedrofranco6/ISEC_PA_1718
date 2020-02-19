package logic.States;

import logic.GameLogic;

public class AwaitRallyTroops extends IStatesAdapter {

    public AwaitRallyTroops(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates rallyTroops(int option) {
        if (!getGame().haveMaxMorale())
            getGame().rallyTroops(option);
        else
            getGame().setLog("You already have max morale.");

        return new AwaitActionSelection(getGame());
    }
}
