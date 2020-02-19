package logic.States;

import java.io.Serializable;

public interface IStates extends Serializable {
    IStates startGame();
    IStates pickCard();
    IStates awaitArchersAttack();
    IStates archersAttack(int track);
    IStates awaitBoilingWaterAttack();
    IStates boilingWaterAttack(int track);
    IStates closeCombatAttack();
    IStates coupure();
    IStates rallyTroops();
    IStates rallyTroops(int option);
    IStates awaitTunnelMovement();
    IStates tunnelMovement(int number);
    IStates supplyRaid();
    IStates sabotage();
    IStates awaitAddActionPoint();
    IStates addActionPoint(int num);
    IStates endTurn();

}
