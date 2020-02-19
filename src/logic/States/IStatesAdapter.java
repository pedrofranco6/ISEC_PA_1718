package logic.States;

import logic.GameLogic;

import java.io.Serializable;

public class IStatesAdapter implements IStates, Serializable {

    private GameLogic game;

    public IStatesAdapter(GameLogic g) {
        this.game = g;
    }

    public GameLogic getGame() {
        return game;
    }

    @Override
    public IStates startGame() {
        return this;
    }

    @Override
    public IStates pickCard() {
        return this;
    }

    @Override
    public IStates awaitArchersAttack() {
        return this;
    }

    @Override
    public IStates archersAttack(int track) {
        return this;
    }

    @Override
    public IStates awaitBoilingWaterAttack() {
        return this;
    }

    @Override
    public IStates boilingWaterAttack(int track) {
        return this;
    }

    @Override
    public IStates awaitTunnelMovement() {
        return this;
    }

    @Override
    public IStates closeCombatAttack() {
        return this;
    }

    @Override
    public IStates coupure() {
        return this;
    }

    @Override
    public IStates rallyTroops() {
        return this;
    }

    @Override
    public IStates rallyTroops(int option) {
        return this;
    }

    @Override
    public IStates tunnelMovement(int number) {
        return this;
    }

    @Override
    public IStates supplyRaid() {
        return this;
    }

    @Override
    public IStates sabotage() {
        return this;
    }

    @Override
    public IStates awaitAddActionPoint() {
        return this;
    }

    @Override
    public IStates addActionPoint(int num) {
        return this;
    }

    @Override
    public IStates endTurn() {
        return this;
    }

}