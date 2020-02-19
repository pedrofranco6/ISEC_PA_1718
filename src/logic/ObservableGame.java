package logic;

import logic.States.IStates;

import java.util.Observable;

public class ObservableGame extends Observable {

    Game game;

    public ObservableGame() {
        this.game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public IStates getState()
    {
        return game.getState();
    }

    public void startGame() {
        game.startGame();
    }

    public void pickCard() {
        game.pickCard();
    }

    public void awaitArchersAttack() {
        game.awaitArchersAttack();
    }

    public void archersAttack(int track) {
        game.archersAttack(track);
    }

    public void awaitBoilingWaterAttack() {
        game.awaitBoilingWaterAttack();
    }

    public void boilingWaterAttack(int track) {
        game.boilingWaterAttack(track);
    }

    public void closeCombatAttack() {
        game.closeCombatAttack();
    }

    public void coupure() {
        game.coupure();
    }

    public void rallyTroops() {
        game.rallyTroops();
    }

    public void rallyTroops(int option) {
        game.rallyTroops(option);
    }

    public void awaitTunnelMovement() {
        game.awaitTunnelMovement();
    }

    public void tunnelMovement(int number) {
        game.tunnelMovement(number);
    }

    public void supplyRaid() {
        game.supplyRaid();
    }

    public void sabotage() {
        game.sabotage();
    }

    public void awaitAddActionPoint() {
        game.awaitAddActionPoint();
    }

    public void addActionPoint(int number) {
        game.addActionPoint(number);
    }

    public void endTurn() {
        game.endTurn();
    }

}
