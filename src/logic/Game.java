package logic;

import logic.States.AwaitBeginning;
import logic.States.IStates;

import java.io.Serializable;

public class Game implements Serializable {

    private IStates state;
    private GameLogic gameLogic;

    public Game() {
        this.gameLogic = new GameLogic();
        this.state = new AwaitBeginning(this.gameLogic);
    }

    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void startGame() {
        setState(getState().startGame());
    }

    public void pickCard() {
        setState(getState().pickCard());
    }

    public void awaitArchersAttack() {
        setState(getState().awaitArchersAttack());
    }

    public void archersAttack(int track) {
        setState(getState().archersAttack(track));
    }

    public void awaitBoilingWaterAttack() {
        setState(getState().awaitBoilingWaterAttack());
    }

    public void boilingWaterAttack(int track) {
        setState(getState().boilingWaterAttack(track));
    }

    public void closeCombatAttack() {
        setState(getState().closeCombatAttack());
    }

    public void coupure() {
        setState(getState().coupure());
    }

    public void rallyTroops() {
        setState(getState().rallyTroops());
    }

    public void rallyTroops(int option) {
        setState(getState().rallyTroops(option));
    }

    public void awaitTunnelMovement() {
        setState(getState().awaitTunnelMovement());
    }

    public void tunnelMovement(int number) {
        setState(getState().tunnelMovement(number));
    }

    public void supplyRaid() {
        setState(getState().supplyRaid());
    }

    public void sabotage() {
        setState(getState().sabotage());
    }

    public void awaitAddActionPoint() {
        setState(getState().awaitAddActionPoint());
    }

    public void addActionPoint(int number) {
        setState(getState().addActionPoint(number));
    }

    public void endTurn() {
        setState(getState().endTurn());
    }

}
