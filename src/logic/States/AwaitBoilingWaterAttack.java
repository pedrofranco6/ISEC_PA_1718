package logic.States;

import logic.GameLogic;

public class AwaitBoilingWaterAttack extends IStatesAdapter{

    public AwaitBoilingWaterAttack(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates boilingWaterAttack(int track) {

        if (getGame().validTrackForBolingWaterAttack(track)) {
            getGame().boilingWaterAttack(track);
            getGame().setLog("Boiling water attack on track " + track);
            return new AwaitActionSelection(getGame());
        }else{
            getGame().setLog("There is no enemy on the chosen track.");
            return this;
        }
    }
}
