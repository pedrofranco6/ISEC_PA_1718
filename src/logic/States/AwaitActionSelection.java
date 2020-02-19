package logic.States;

import logic.GameLogic;

public class AwaitActionSelection extends IStatesAdapter {

    public AwaitActionSelection(GameLogic gl) {
        super(gl);
    }

    @Override
    public IStates awaitArchersAttack() {

        if (getGame().canArchersAttack()) {
            getGame().setLog("Archers Attack!");
            return new AwaitArchersAttack(getGame());
        } else {
            getGame().setLog("You don't have enought action points to perform this attack.");
            return this;
        }
    }

    @Override
    public IStates awaitBoilingWaterAttack() {

        if (getGame().canBoilingWaterAttack()) {
            getGame().setLog("Boiling water attack!");
            return new AwaitBoilingWaterAttack(getGame());
        } else if (!getGame().haveActionPoints())
            getGame().setLog("You don't have enought action points to perform this attack.");
        else if (getGame().getGameStatus().isUsedBoilingWaterAttack())
            getGame().setLog("You already used boling watter attack in this turn.");
        else
            getGame().setLog("There is no enemy on the chosen circle.");
        return this;
    }

    @Override
    public IStates closeCombatAttack() {
        if (getGame().enemysOnCloseCombatArea()) {
            getGame().closeCombatAttack();
            return this;
        }
        getGame().setLog("There is no enemys on the close combat area.");
        return this;
    }

    @Override
    public IStates coupure() {
        if (getGame().haveActionPoints())
            getGame().coupure();
        else
            getGame().setLog("You don't have enought action points to perform this action.");
        return this;
    }

    @Override
    public IStates rallyTroops() {
        if (getGame().haveActionPoints()) {
            getGame().setLog("Rally Troops!");
            return new AwaitRallyTroops(getGame());
        }
        getGame().setLog("You don't have enought action points to perform this action.");
        return this;
    }

    @Override
    public IStates awaitTunnelMovement() {

        if (getGame().haveActionPoints() ||
                (!getGame().getGameStatus().isUsedFreeTunnelMovement() &&
                        !getGame().getGameStatus().isEnteredTheTunnelInThisTurn())) {
            getGame().setLog("Tunnel movement!");
            return new AwaitTunnelMovement(getGame());
        } else {
            getGame().setLog("Can't move in the tunnel, no actions points or no free movement allowed.");
            return this;
        }
    }

    @Override
    public IStates supplyRaid() {
        getGame().supplyRaid();
        return this;
    }

    @Override
    public IStates sabotage() {
        getGame().sabotage();
        return this;
    }

    @Override
    public IStates awaitAddActionPoint() {

        if (getGame().canTradeActionPoints())
            return new AwaitAddActionPoint(getGame());
        getGame().setLog("You don't have any supply or moral points to trade.");
        return this;
    }

    @Override
    public IStates endTurn() {
        getGame().endTurn();

        if (getGame().gameOver()) {
            getGame().setLog("Game Over! You just Lost");
            return new AwaitBeginning(getGame());
        } else if (getGame().win()) {
            getGame().setLog("Congratulations! You Win!");
            return new AwaitBeginning(getGame());
        } else
            return new AwaitPickCard(getGame());
    }
}