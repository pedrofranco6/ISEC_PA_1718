package logic;

import java.io.Serializable;

public class Status implements Serializable {

    private int actionPoints;
    private int wallStrength;
    private int morale;
    private int suplies;
    private int tunnelPos;
    private int raidSupplies;
    private int trebuchet;
    private int ladderPos;
    private int ramPos;
    private int siegeTowerPos;
    private boolean enteredTheTunnelInThisTurn;
    private boolean usedBoilingWaterAttack;
    private boolean usedFreeTunnelMovement;
    private int bonusLadderAttacks;
    private int bonusRamAttacks;
    private int bonusSiegeTowerAttacks;
    private int bonusCircleAttacks;
    private int bonusAllAttacks;
    private int bonusCloseCombatAttacks;
    private int bonusSabotageActions;
    private int bonusMoraleActions;
    private int bonusCoupureActions;
    private int bonusRaidActions;

    public Status() {
        this.actionPoints = 100;
        this.wallStrength = 4;
        this.morale = 4;
        this.suplies = 4;
        this.tunnelPos = 0;
        this.raidSupplies = 1;
        this.trebuchet = 3;
        this.ladderPos = 4;
        this.ramPos = 4;
        this.siegeTowerPos = 4;
        this.enteredTheTunnelInThisTurn = false;
        this.usedBoilingWaterAttack = false;
        this.usedFreeTunnelMovement = false;
        this.bonusLadderAttacks = 0;
        this.bonusRamAttacks = 0;
        this.bonusSiegeTowerAttacks = 0;
        this.bonusCircleAttacks = 0;
        this.bonusAllAttacks = 0;
        this.bonusCloseCombatAttacks = 0;
        this.bonusCoupureActions = 0;
        this.bonusMoraleActions = 0;
        this.bonusRaidActions = 0;
        this.bonusSabotageActions = 0;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getWallStrength() {
        return wallStrength;
    }

    public void setWallStrength(int wallStrength) {
        this.wallStrength = wallStrength;
    }

    public int getMorale() {
        return morale;
    }

    public void setMorale(int morale) {
        this.morale = morale;
    }

    public int getSuplies() {
        return suplies;
    }

    public void setSuplies(int suplies) {
        this.suplies = suplies;
    }

    public int getTunnelPos() {
        return tunnelPos;
    }

    public void setTunnelPos(int tunnelPos) {
        this.tunnelPos = tunnelPos;
    }

    public int getRaidSupplies() {
        return raidSupplies;
    }

    public void setRaidSupplies(int raidSupplies) {
        this.raidSupplies = raidSupplies;
    }

    public int getTrebuchet() {
        return trebuchet;
    }

    public void setTrebuchet(int trebuchet) {
        this.trebuchet = trebuchet;
    }

    public int getLadderPos() {
        return ladderPos;
    }

    public void setLadderPos(int ladderPos) {
        this.ladderPos = ladderPos;
    }

    public int getRamPos() {
        return ramPos;
    }

    public void setRamPos(int ramPos) {
        this.ramPos = ramPos;
    }

    public int getSiegeTowerPos() {
        return siegeTowerPos;
    }

    public void setSiegeTowerPos(int siegeTowerPos) {
        this.siegeTowerPos = siegeTowerPos;
    }

    public boolean isEnteredTheTunnelInThisTurn() {
        return enteredTheTunnelInThisTurn;
    }

    public void setEnteredTheTunnelInThisTurn(boolean enteredTheTunnelInThisTurn) {
        this.enteredTheTunnelInThisTurn = enteredTheTunnelInThisTurn;
    }

    public boolean isUsedBoilingWaterAttack() {
        return usedBoilingWaterAttack;
    }

    public void setUsedBoilingWaterAttack(boolean usedBoilingWaterAttack) {
        this.usedBoilingWaterAttack = usedBoilingWaterAttack;
    }

    public boolean isUsedFreeTunnelMovement() {
        return usedFreeTunnelMovement;
    }

    public void setUsedFreeTunnelMovement(boolean usedFreeTunnelMovement) {
        this.usedFreeTunnelMovement = usedFreeTunnelMovement;
    }

    public int getBonusLadderAttacks() {
        return bonusLadderAttacks;
    }

    public void setBonusLadderAttacks(int bonusLadderAttacks) {
        this.bonusLadderAttacks = bonusLadderAttacks;
    }

    public int getBonusRamAttacks() {
        return bonusRamAttacks;
    }

    public void setBonusRamAttacks(int bonusRamAttacks) {
        this.bonusRamAttacks = bonusRamAttacks;
    }

    public int getBonusSiegeTowerAttacks() {
        return bonusSiegeTowerAttacks;
    }

    public void setBonusSiegeTowerAttacks(int bonusSiegeTowerAttacks) {
        this.bonusSiegeTowerAttacks = bonusSiegeTowerAttacks;
    }

    public int getBonusCircleAttacks() {
        return bonusCircleAttacks;
    }

    public void setBonusCircleAttacks(int bonusCircleAttacks) {
        this.bonusCircleAttacks = bonusCircleAttacks;
    }

    public int getBonusAllAttacks() {
        return bonusAllAttacks;
    }

    public void setBonusAllAttacks(int bonusAllAttacks) {
        this.bonusAllAttacks = bonusAllAttacks;
    }

    public int getBonusCloseCombatAttacks() {
        return bonusCloseCombatAttacks;
    }

    public void setBonusCloseCombatAttacks(int bonusCloseCombatAttacks) {
        this.bonusCloseCombatAttacks = bonusCloseCombatAttacks;
    }

    public int getBonusSabotageActions() {
        return bonusSabotageActions;
    }

    public void setBonusSabotageActions(int bonusSabotageActions) {
        this.bonusSabotageActions = bonusSabotageActions;
    }

    public int getBonusMoraleActions() {
        return bonusMoraleActions;
    }

    public void setBonusMoraleActions(int bonusMoraleActions) {
        this.bonusMoraleActions = bonusMoraleActions;
    }

    public int getBonusCoupureActions() {
        return bonusCoupureActions;
    }

    public void setBonusCoupureActions(int bonusCoupureActions) {
        this.bonusCoupureActions = bonusCoupureActions;
    }

    public int getBonusRaidActions() {
        return bonusRaidActions;
    }

    public void setBonusRaidActions(int bonusRaidActions) {
        this.bonusRaidActions = bonusRaidActions;
    }

    @Override
    public String toString() {
        return "Status of Game:\n" +
                "Wall Strength = " + wallStrength +
                "\tMorale = " + morale +
                "\tSuplies = " + suplies +
                "\tRaid Supplies = " + raidSupplies +
                "\tTrebuchet = " + trebuchet +
                "\tLadderPos = " + ladderPos +
                "\tRamPos = " + ramPos +
                "\tSiegeTowerPos = " + siegeTowerPos;
    }

}
