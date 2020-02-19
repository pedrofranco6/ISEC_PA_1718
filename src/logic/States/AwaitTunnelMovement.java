package logic.States;

import logic.GameLogic;

public class AwaitTunnelMovement extends IStatesAdapter {

    public AwaitTunnelMovement(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates tunnelMovement(int number) {
        if (getGame().validTunnelMovementOption(number)) {
            getGame().tunnelMovement(number);
            getGame().setLog("You just moved in the tunnel.");
            return new AwaitActionSelection(getGame());
        } else {
            getGame().setLog("Invalid tunnel movement.");
            return new AwaitActionSelection(getGame());
        }
    }
}
