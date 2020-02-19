package logic;

import logic.Cards.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class GameLogic implements Serializable {

    private Die die;
    private ArrayList<ICardsAdapter> cards;
    private int day;
    private int cardIndex;
    private Status gameStatus;
    private int dieFace = 1;

    String log = "";

    public GameLogic() {

        day = 3;
        cardIndex = 0;
        die = new Die();
        gameStatus = new Status();
    }

    public void begin() {

        //reset stats
        day = 1;
        getGameStatus().setActionPoints(0);
        getGameStatus().setWallStrength(4);
        getGameStatus().setMorale(4);
        getGameStatus().setSuplies(4);
        getGameStatus().setTunnelPos(0);
        getGameStatus().setRaidSupplies(0);
        getGameStatus().setTrebuchet(3);
        getGameStatus().setLadderPos(4);
        getGameStatus().setRamPos(4);
        getGameStatus().setSiegeTowerPos(4);

        shuffleCards();
    }

    public void shuffleCards() {
        cards = new ArrayList<>();
        cards.add(new Card1(this));
        cards.add(new Card2(this));
        cards.add(new Card3(this));
        cards.add(new Card4(this));
        cards.add(new Card5(this));
        cards.add(new Card6(this));
        cards.add(new Card7(this));

        Collections.shuffle(cards);
    }

    public ICards getCard(int number) {
        return cards.get(number--);
    }

    private Die getDie() {
        return die;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public Status getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Status gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void addLog(String logs) { this.log += logs;}

    public ArrayList<ICardsAdapter> getCards() {
        return cards;
    }

    public int getDieFace() {
        return dieFace;
    }

    //METODOS PARA OS ESTADOS
    public boolean haveActionPoints() {
        return getGameStatus().getActionPoints() > 0;
    }

    public boolean haveSupplyPoints() {
        return getGameStatus().getSuplies() > 0;
    }

    //archerAttack
    public boolean canArchersAttack() {
        return  getGameStatus().getActionPoints() > 0;
    }

    public void archersAttack(int track) {
        if (track != 0) {
            dieFace = getDie().roll();
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);

            if (track == 1 && dieFace > 2 && getGameStatus().getLadderPos() < 4)
                getGameStatus().setLadderPos(getGameStatus().getLadderPos() + 1);
            else if (track == 2 && dieFace > 3 && getGameStatus().getLadderPos() < 4)
                getGameStatus().setRamPos(getGameStatus().getRamPos() + 1);
            else if (track == 3 && dieFace > 4 && getGameStatus().getLadderPos() < 4)
                getGameStatus().setSiegeTowerPos(getGameStatus().getSiegeTowerPos() + 1);
        }
    }

    //boilingWaterAttack
    public boolean canBoilingWaterAttack() {
        return getGameStatus().getActionPoints() > 0 && !getGameStatus().isUsedBoilingWaterAttack() &&
                (getGameStatus().getLadderPos() == 1 || getGameStatus().getRamPos() == 1 ||
                        getGameStatus().getSiegeTowerPos() == 1);
    }

    public boolean validTrackForBolingWaterAttack(int track) {
        return (track == 1 && getGameStatus().getLadderPos() == 1) ||
                (track == 2 && getGameStatus().getRamPos() == 1) ||
                (track == 3 && getGameStatus().getSiegeTowerPos() == 1);
    }

    public void boilingWaterAttack(int track) {

        getGameStatus().setUsedBoilingWaterAttack(true);

        if (track == 1)
            getGameStatus().setLadderPos(getGameStatus().getLadderPos() + 2);
        else if (track == 2)
            getGameStatus().setRamPos(getGameStatus().getRamPos() + 2);
        else if (track == 3)
            getGameStatus().setSiegeTowerPos(getGameStatus().getSiegeTowerPos() + 2);
    }

    //tunnelMovement
    private boolean isInTheTunnel() {
        return (getGameStatus().getTunnelPos() > 0 && getGameStatus().getTunnelPos() < 3) ||
                (getGameStatus().getTunnelPos() > 3 && getGameStatus().getTunnelPos() < 6);
    }

    private boolean justEnteredTheTunnel() {
        return getGameStatus().isEnteredTheTunnelInThisTurn();
    }

    private boolean usedFreeTunnelMovement() {
        return getGameStatus().isUsedFreeTunnelMovement();
    }

    public boolean validTunnelMovementOption(int number) {
        return  ((number == 1 && !isInTheTunnel()) ||
                (number == 2 && !justEnteredTheTunnel() && !usedFreeTunnelMovement() && isInTheTunnel()) ||
                number == 3 && isInTheTunnel());
    }

    public void tunnelMovement(int number) {
        if (number == 1){
            getGameStatus().setEnteredTheTunnelInThisTurn(true);
            if (getGameStatus().getTunnelPos() == 0)
                getGameStatus().setTunnelPos(1);
            else
                getGameStatus().setTunnelPos(4);
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
        } else if (number == 2) {
            getGameStatus().setTunnelPos(getGameStatus().getTunnelPos() + 1);
            if (getGameStatus().getTunnelPos() == 6) {
                getGameStatus().setSuplies(getGameStatus().getSuplies() + getGameStatus().getRaidSupplies());
                if (getGameStatus().getSuplies() > 4)
                    getGameStatus().setSuplies(4);
                getGameStatus().setTunnelPos(0);
            }
            getGameStatus().setUsedFreeTunnelMovement(true);
        }else {
            if (getGameStatus().getTunnelPos() > 0 && getGameStatus().getTunnelPos() < 3)
                getGameStatus().setTunnelPos(3);
            else if (getGameStatus().getTunnelPos() > 3 && getGameStatus().getTunnelPos() < 6) {
                getGameStatus().setSuplies(getGameStatus().getSuplies() + getGameStatus().getRaidSupplies());
                if (getGameStatus().getSuplies() > 4)
                    getGameStatus().setSuplies(4);
                getGameStatus().setTunnelPos(0);
            }
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
        }
        if (getGameStatus().getTunnelPos() == 0 && getGameStatus().getRaidSupplies() > 0) {
            getGameStatus().setSuplies(getGameStatus().getSuplies() + getGameStatus().getRaidSupplies());
            if (getGameStatus().getSuplies() > 4)
                getGameStatus().setSuplies(4);
            getGameStatus().setRaidSupplies(0);
        }
    }

    //closeCombatAttack
    public boolean enemysOnCloseCombatArea() {
        return (getGameStatus().getLadderPos() == 0 || getGameStatus().getRamPos() == 0 ||
                getGameStatus().getSiegeTowerPos() == 0);
    }

    public void closeCombatAttack() {
        getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
        dieFace = getDie().roll();
        if (dieFace + getGameStatus().getBonusCloseCombatAttacks()
                + getGameStatus().getBonusAllAttacks() > 4){
            if (getGameStatus().getLadderPos() == 0)
                getGameStatus().setLadderPos(1);
            if (getGameStatus().getRamPos() == 0)
                getGameStatus().setRamPos(1);
            if (getGameStatus().getSiegeTowerPos() == 0)
                getGameStatus().setSiegeTowerPos(1);
            setLog("Sucess! You just pushed the enemies out of the close combat area.");
        } else if (dieFace + getGameStatus().getBonusCloseCombatAttacks()
                + getGameStatus().getBonusAllAttacks() == 1) {
            getGameStatus().setMorale(getGameStatus().getMorale() - 1);
            if (getGameStatus().getMorale() < 0)
                getGameStatus().setMorale(0);
            setLog("You rolled 1 and lost a soldier because of that.");
        } else {
            setLog("Sadly you couldn't push the enemies out.");
        }
    }

    //wallReinforcement
    public void coupure() {
        if (getGameStatus().getWallStrength() < 4) {
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
            dieFace = getDie().roll();
            if (dieFace + getGameStatus().getBonusCoupureActions() > 5) {
                setLog("Wall strength increased successfully!");
                getGameStatus().setWallStrength(getGameStatus().getWallStrength() + 1);
                if (getGameStatus().getWallStrength() > 4)
                    getGameStatus().setWallStrength(4);
            } else
                setLog("Unfortunately you didn't repair the wall.");
        } else
            setLog("Your wall strength is already at 4.");
    }

    //rallyTroops
    public boolean haveMaxMorale() {
        return getGameStatus().getMorale() == 4;
    }

    public void rallyTroops(int option) {
        if (option != 0) {
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
            dieFace = getDie().roll();

            if (option == 2 && dieFace > 4) {
                getGameStatus().setMorale(getGameStatus().getMorale() + 1);
                setLog("Congrats! You just boosted your morale.");
            } else if (option == 1) {
                getGameStatus().setSuplies(getGameStatus().getSuplies() - 1);
                if (dieFace > 3) {
                    getGameStatus().setMorale(getGameStatus().getMorale() + 1);
                    setLog("Congrats! You just boosted your morale.");
                } else
                    setLog("Unfortunately you couldn't boost your morale.");
            } else
                setLog("Unfortunately you couldn't boost your morale.");
        }
    }

    //supplyRaid
    public void supplyRaid() {
        if (getGameStatus().getTunnelPos() == 3 && getGameStatus().getRaidSupplies() < 2
                && getGameStatus().getActionPoints() > 0) {
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
            dieFace = getDie().roll();
            if (dieFace + getGameStatus().getBonusRaidActions() == 2) {
                setLog("You weren't capable of stealing supplies.");
                return;
            } else if (dieFace + getGameStatus().getBonusRaidActions() == 1) {
                getGameStatus().setMorale(getGameStatus().getMorale() - 1);
                setLog("You rolled 1 on the supply raid and lost a soldier because of that.");
                return;
            } else if (dieFace + getGameStatus().getBonusRaidActions() >= 6) {
                getGameStatus().setRaidSupplies(2);
                setLog("Wow, you rolled 6 and got 2 supplies in 1 strike!");
                return;
            } else {
                getGameStatus().setRaidSupplies(getGameStatus().getRaidSupplies() + 1);
                setLog("Nice, you raid 1 supply sucessfully.");
                return;
            }
        }
        if (getGameStatus().getActionPoints() < 1)
            setLog("You don't have enough action points to perform this action.");
        if (getGameStatus().getTunnelPos() != 3)
            setLog("You don't have soldiers on the enemy back line.");
        if (getGameStatus().getRaidSupplies() == 2)
            setLog("You already have 2 raided supplies to return to the castle.");
    }


    //sabotage
    public void sabotage() {
        if (getGameStatus().getTunnelPos() == 3 && getGameStatus().getActionPoints() > 0 &&
                getGameStatus().getTrebuchet() > 0) {
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() - 1);
            dieFace = getDie().roll();
            if (dieFace + getGameStatus().getBonusSabotageActions() == 1) {
                getGameStatus().setMorale(getGameStatus().getMorale() - 1);
                setLog("You rolled 1 on the sabotage and lost a soldier because of that.");
                return;
            } else if (dieFace + getGameStatus().getBonusSabotageActions() == 5  ||
                    dieFace + getGameStatus().getBonusSabotageActions() >= 6) {
                getGameStatus().setTrebuchet(getGameStatus().getTrebuchet() - 1);
                setLog("Great, you sabotaged a trebuchet.");
                return;
            } else {
                setLog("Hmm, you must have forgotten the wrench in the castle, so you just failed the sabotage.");
                return;
            }
        }
        if (getGameStatus().getActionPoints() < 1)
            setLog("You don't have enough action points to perform this action.");
        if (getGameStatus().getTrebuchet() < 1)
            setLog("There are no more trebuchets to sabotage.");
        if (getGameStatus().getTunnelPos() != 3)
            setLog("You don't have soldiers on the enemy back line.");
    }

    //tradeActionPoints
    public boolean canTradeActionPoints() {
        return (getGameStatus().getMorale() > 0 || getGameStatus().getSuplies() > 0);
    }

    public void addActionPoint(int number) {
        if (number == 1 && getGameStatus().getMorale() > 0) {
            getGameStatus().setMorale(getGameStatus().getMorale() - 1);
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() + 1);
        }
        else if (number == 2 && getGameStatus().getSuplies() > 0) {
            getGameStatus().setSuplies(getGameStatus().getSuplies() - 1);
            getGameStatus().setActionPoints(getGameStatus().getActionPoints() + 1);
        }
        setLog("Action point was added.");
    }

    public boolean showWallStrength() {
        return getGameStatus().getWallStrength() > 0 && getGameStatus().getWallStrength() < 5;
    }

    public boolean showMorale() {
        return getGameStatus().getMorale() > 0 && getGameStatus().getMorale() < 5;
    }

    public boolean showSupplies() {
        return getGameStatus().getSuplies() > 0 && getGameStatus().getSuplies() < 5;
    }

    public boolean showSurender() {
        return getGameStatus().getWallStrength() == 0 ||
                getGameStatus().getMorale() == 0 ||
                getGameStatus().getSuplies() == 0;
    }

    public boolean showRaidedSupplies() {
        return getGameStatus().getRaidSupplies() == 1 || getGameStatus().getRaidSupplies() == 2;
    }

    public boolean showTrebuchet() {
        return getGameStatus().getTrebuchet() > 0 && getGameStatus().getTrebuchet() < 4;
    }

    public boolean showSupRaidSab() {
        return getGameStatus().getTunnelPos() != 3;
    }

    //endTurn
    public void endTurn() {

        /*setLog("End of Turn:\n" +
                "Day " + day + ", Turn " + (cardIndex+1) + ", Event: " + cards.get(cardIndex).getEvent(day) + "\n" +
                "Your status: Wall strenght-> " + getGameStatus().getWallStrength() + ", Morale-> " +
                getGameStatus().getMorale() + ", Supplies-> " + getGameStatus().getSuplies() + "\n" +
                "Enemy status: Trebuchets-> " + getGameStatus().getTrebuchet() + ", Ladder-> " + getGameStatus().getLadderPos() +
                ", Ram-> " + getGameStatus().getRamPos() + ", Siege Tower-> " + getGameStatus().getSiegeTowerPos());
*/
        //reset stats
        getGameStatus().setActionPoints(0);
        getGameStatus().setUsedBoilingWaterAttack(false);
        getGameStatus().setEnteredTheTunnelInThisTurn(false);
        getGameStatus().setUsedFreeTunnelMovement(false);

        //reset all bonuses
        getGameStatus().setBonusLadderAttacks(0);
        getGameStatus().setBonusRamAttacks(0);
        getGameStatus().setBonusSiegeTowerAttacks(0);
        getGameStatus().setBonusAllAttacks(0);
        getGameStatus().setBonusCloseCombatAttacks(0);
        getGameStatus().setBonusCircleAttacks(0);
        getGameStatus().setBonusCoupureActions(0);
        getGameStatus().setBonusMoraleActions(0);
        getGameStatus().setBonusSabotageActions(0);
        getGameStatus().setBonusRaidActions(0);

        //5.0 Enemy Line Check
        if (getGameStatus().getTunnelPos() == 3 && getDie().roll() == 1){
            //soldier captured
            getGameStatus().setTunnelPos(0);
            getGameStatus().setMorale(getGameStatus().getMorale() - 1);
            if (getGameStatus().getMorale() < 0)
                getGameStatus().setMorale(0);
        }

        //End of Day Phase (11.0)
        if (cardIndex == 6) {
            day++;
            cardIndex = 0;
            getGameStatus().setSuplies(getGameStatus().getSuplies() - 1);
            if ((getGameStatus().getTunnelPos() > 0 && getGameStatus().getTunnelPos() < 3) ||
                    (getGameStatus().getTunnelPos() > 3 && getGameStatus().getTunnelPos() < 6)) {
                getGameStatus().setSuplies(getGameStatus().getSuplies() + getGameStatus().getRaidSupplies());
                if (getGameStatus().getSuplies() > 4)
                    getGameStatus().setSuplies(4);
                getGameStatus().setTunnelPos(0);
            } else if (getGameStatus().getTunnelPos() == 3) {
                getGameStatus().setMorale(getGameStatus().getMorale() - 1);
                if (getGameStatus().getMorale() < 0)
                    getGameStatus().setMorale(0);
                getGameStatus().setTunnelPos(0);
            }
            shuffleCards();
        } else {
            cardIndex++;
        }
    }

    //Victory or Loss Check Phase (10.0)
    public boolean gameOver() {
        int numberOfEnemysOnCloseCombat = 0;

        if (getGameStatus().getLadderPos() == 0)
            numberOfEnemysOnCloseCombat++;
        if (getGameStatus().getRamPos() == 0)
            numberOfEnemysOnCloseCombat++;
        if (getGameStatus().getSiegeTowerPos() == 0)
            numberOfEnemysOnCloseCombat++;
        return numberOfEnemysOnCloseCombat > 1 ||
                getGameStatus().getWallStrength() == 0 ||
                getGameStatus().getMorale() == 0 ||
                getGameStatus().getSuplies() == 0;
    }

    public boolean win() {
        return day == 4;
    }

    public void newTurn() {

        //Card Play Phase (6.0)
        getGameStatus().setActionPoints(cards.get(cardIndex).getActionPoints(day-1));
        //Event Phase (7.0)
        cards.get(cardIndex).setEffect(day);
        //Enemy Movement Phase (8.0)
        cards.get(cardIndex).setMovement(day);

        setLog("\nStart of Turn:\n" +
                        "Day " + day + ", Turn " + (cardIndex+1) + ", Event: " + cards.get(cardIndex).getEvent(day) +
                        "\nAction Points Available-> " + cards.get(cardIndex).getActionPoints(day-1) +
                        "\nYour status: Wall strenght-> " + getGameStatus().getWallStrength() + ", Morale-> " +
                        getGameStatus().getMorale() + ", Supplies-> " + getGameStatus().getSuplies() +
                        "\nEnemy status: Trebuchets-> " + getGameStatus().getTrebuchet() + ", Ladder-> " + getGameStatus().getLadderPos() +
                        ", Ram-> " + getGameStatus().getRamPos() + ", Siege Tower-> " + getGameStatus().getSiegeTowerPos()
                /*+ "\nActive bonuses: " */);
    }

    //all cards - enemy movement
    public void enemyMovement(boolean ladder, boolean ram, boolean siegeTower) {
        if (ladder && getGameStatus().getLadderPos() > 0)
            getGameStatus().setLadderPos(getGameStatus().getLadderPos() - 1);
        if (ram && getGameStatus().getRamPos() > 0)
            getGameStatus().setRamPos(getGameStatus().getRamPos() - 1);
        if (siegeTower && getGameStatus().getSiegeTowerPos() > 0)
            getGameStatus().setSiegeTowerPos(getGameStatus().getSiegeTowerPos() - 1);
    }

    //card1
    public void trebuchetAttack() {
        dieFace = getDie().roll();
        if (getGameStatus().getTrebuchet() == 3)
            getGameStatus().setWallStrength(getGameStatus().getWallStrength() - 2);
        else if (getGameStatus().getTrebuchet() == 2)
            getGameStatus().setWallStrength(getGameStatus().getWallStrength() - 2);
        else if (getGameStatus().getTrebuchet() == 2) {
            dieFace = getDie().roll();
            if (dieFace > 3)
                getGameStatus().setWallStrength(getGameStatus().getWallStrength() - 1);
        }
    }

    //card2
    public void illness() {
        getGameStatus().setMorale(getGameStatus().getMorale() - 1);
        getGameStatus().setSuplies(getGameStatus().getSuplies() - 1);
    }

    public void guardsDistracted() {
        getGameStatus().setBonusSabotageActions(getGameStatus().getBonusSabotageActions() + 1);
        getGameStatus().setBonusMoraleActions(getGameStatus().getBonusMoraleActions() + 1);
        addLog("Morale and Sabotage Actions");
    }

    //card3
    public void suppliesSpoiled() {
        getGameStatus().setSuplies(getGameStatus().getSuplies() - 1);
    }

    public boolean badWeather(int option) {
        //only raid and sabotage are allow in this turn
        return  (option != 7 && option != 8);
    }

    public void boilingOil() {
        getGameStatus().setBonusCircleAttacks(getGameStatus().getBonusCircleAttacks() + 2);
        addLog("Circle Attacks");
    }

    //card4
    public void deathOfALeader() {
        getGameStatus().setMorale(getGameStatus().getMorale() - 1);
    }

    public void gateFortified() {
        getGameStatus().setBonusRamAttacks(getGameStatus().getBonusRamAttacks() + 1);
        addLog("Ram Attacks");
    }

    public void flamingArrows() {
        getGameStatus().setBonusSiegeTowerAttacks(getGameStatus().getBonusSiegeTowerAttacks() + 1);
        addLog("Siege Tower Attacks");
    }

    //card5
    public void volleyOfArrows() {
        getGameStatus().setBonusAllAttacks(getGameStatus().getBonusAllAttacks() + 1);
        addLog("All Attacks");
    }

    public void collapsed() {
        //siege tower removed from the game if on starting space
        if (getGameStatus().getSiegeTowerPos() == 4)
            getGameStatus().setSiegeTowerPos(5);
    }

    public void repairedTrebuchet() {
        if (getGameStatus().getTrebuchet() < 3)
            getGameStatus().setTrebuchet(getGameStatus().getTrebuchet() + 1);
        getGameStatus().setBonusCoupureActions(getGameStatus().getBonusCoupureActions() + 1);
        addLog("Coupure Actions");
    }

    //card6
    public void coverOfDarkness() {
        getGameStatus().setBonusRaidActions(getGameStatus().getBonusRaidActions() + 1);
        getGameStatus().setBonusSabotageActions(getGameStatus().getBonusSabotageActions() + 1);
        addLog("Raid and Sabotage Actions");
    }

    public void enemyFatigue() {
        getGameStatus().setBonusCoupureActions(getGameStatus().getBonusCoupureActions() + 1);
        getGameStatus().setBonusRaidActions(getGameStatus().getBonusRaidActions() + 1);
        getGameStatus().setBonusSabotageActions(getGameStatus().getBonusSabotageActions() + 1);
        addLog("Coupure, Raid and Sabotage Actions");
    }

    public void rally() {
        getGameStatus().setBonusCircleAttacks(getGameStatus().getBonusCircleAttacks() + 1);
        getGameStatus().setBonusCloseCombatAttacks(getGameStatus().getBonusCloseCombatAttacks() + 1);
        addLog("Circle and Close Combat Attacks");
    }

    //card7
    public void determinedEnenmy() {
        getGameStatus().setBonusRamAttacks(getGameStatus().getBonusRamAttacks() - 1);
        addLog("Ram Attack debuf (-1)");
    }

    public void ironShields() {
        getGameStatus().setBonusSiegeTowerAttacks(getGameStatus().getBonusSiegeTowerAttacks() - 1);
        addLog("Siege Tower Attack debuf (-1)");
    }

    public void faith() {
        getGameStatus().setBonusRamAttacks(getGameStatus().getBonusRamAttacks() + 1);
        getGameStatus().setBonusLadderAttacks(getGameStatus().getBonusLadderAttacks() + 1);
        getGameStatus().setBonusSiegeTowerAttacks(getGameStatus().getBonusSiegeTowerAttacks() + 1);
        getGameStatus().setBonusMoraleActions(getGameStatus().getBonusMoraleActions() + 1);
        addLog("Ladder, Ram and Siege Tower Attacks and Morale Actions");
    }

}
