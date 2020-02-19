package ui.graphical;

import logic.ObservableGame;
import logic.States.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GraphicalUI extends JFrame {

    private ObservableGame game;
    private boolean quit = false;
    private boolean wait = false;

    //menu inicial
    JButton start;
    JButton leave;

    //pick card
    JLabel deck;

    //action selection
    JPanel actions;
    JButton archers;
    JButton boilingWater;
    JButton closeCombat;
    JButton coupure;
    JButton rallyTroops;
    JButton tunnelMov;
    JButton supplyRaid;
    JButton sabotage;
    JButton getActionPoints;
    JButton endTurn;

    public GraphicalUI(ObservableGame game) {
        super("9 Card Siege");
        setLayout(null);
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        this.game = game;
    }

    public void startMenu() {

        getContentPane().removeAll();

        JPanel panel = new JPanel(null);
        panel.setSize(120, 80);
        panel.setLocation(440, 225);
        panel.setOpaque(false);

        start = new JButton("Start Game");
        start.setSize(120, 30);
        start.setLocation(0, 0);
        panel.add(start);

        leave = new JButton("Quit");
        leave.setSize(80, 30);
        leave.setLocation(20, 50);
        panel.add(leave);

        add(panel);

        JLabel image = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/card_back_h.png").getImage(), 130)), SwingConstants.CENTER);
        image.setSize(1000, 600);
        image.setLocation(0, 0);
        add(image);

        EventHandler handle = new EventHandler();
        start.addActionListener(handle);
        leave.addActionListener(handle);

        repaint();
        revalidate();
    }

    public void mainMenu() {

        update();
    }

    public void update() {

        getContentPane().removeAll();

        JPanel info = new JPanel(null);
        info.setSize(250, 300);
        info.setLocation(0, 0);

        JLabel infoLabel = new JLabel("Info:");
        infoLabel.setSize(240, 15);
        infoLabel.setLocation(10, 10);
        info.add(infoLabel);

        JLabel dayTurn = new JLabel("Day: " + game.getGame().getGameLogic().getDay() + ", Turn: " + (game.getGame().getGameLogic().getCardIndex()+1));
        dayTurn.setSize(240, 15);
        dayTurn.setLocation(10, 30);
        info.add(dayTurn);

        JLabel teste = new JLabel("Action Points Available: " + game.getGame().getGameLogic().getGameStatus().getActionPoints());
        teste.setSize(240, 15);
        teste.setLocation(10, 45);
        info.add(teste);

        JLabel drmBonus = new JLabel("DRM Bonus: ");
        drmBonus.setSize(240, 15);
        drmBonus.setLocation(10, 75);
        info.add(drmBonus);

        JLabel attackBonus = new JLabel("Attacks: ");
        attackBonus.setSize(240, 15);
        attackBonus.setLocation(30, 90);
        info.add(attackBonus);

        JLabel ladderBonus = new JLabel("Ladder: " +
                (game.getGame().getGameLogic().getGameStatus().getBonusLadderAttacks() +
                        game.getGame().getGameLogic().getGameStatus().getBonusAllAttacks()));
        ladderBonus.setSize(240, 15);
        ladderBonus.setLocation(50, 105);
        info.add(ladderBonus);

        JLabel ramBonus = new JLabel("Ram: " +
                (game.getGame().getGameLogic().getGameStatus().getBonusRamAttacks() +
                        game.getGame().getGameLogic().getGameStatus().getBonusAllAttacks()));
        ramBonus.setSize(240, 15);
        ramBonus.setLocation(50, 120);
        info.add(ramBonus);

        JLabel siegeTowerBonus = new JLabel("Siege Tower: " +
                (game.getGame().getGameLogic().getGameStatus().getBonusSiegeTowerAttacks() +
                        game.getGame().getGameLogic().getGameStatus().getBonusAllAttacks()));
        siegeTowerBonus.setSize(240, 15);
        siegeTowerBonus.setLocation(50, 135);
        info.add(siegeTowerBonus);

        JLabel closeCombatBonus = new JLabel("Close Combat: " +
                (game.getGame().getGameLogic().getGameStatus().getBonusCloseCombatAttacks() +
                        game.getGame().getGameLogic().getGameStatus().getBonusAllAttacks()));
        closeCombatBonus.setSize(240, 15);
        closeCombatBonus.setLocation(50, 150);
        info.add(closeCombatBonus);

        JLabel circleBonus = new JLabel("Circles: " +
                (game.getGame().getGameLogic().getGameStatus().getBonusCircleAttacks() +
                        game.getGame().getGameLogic().getGameStatus().getBonusAllAttacks()));
        circleBonus.setSize(240, 15);
        circleBonus.setLocation(50, 165);
        info.add(circleBonus);

        JLabel actionBonus = new JLabel("Actions: ");
        actionBonus.setSize(240, 15);
        actionBonus.setLocation(30, 180);
        info.add(actionBonus);

        JLabel coupureBonus = new JLabel("Coupure: " + game.getGame().getGameLogic().getGameStatus().getBonusCoupureActions());
        coupureBonus.setSize(240, 15);
        coupureBonus.setLocation(50, 195);
        info.add(coupureBonus);

        JLabel moraleBonus = new JLabel("Morale: " + game.getGame().getGameLogic().getGameStatus().getBonusMoraleActions());
        moraleBonus.setSize(240, 15);
        moraleBonus.setLocation(50, 210);
        info.add(moraleBonus);

        JLabel raidBonus = new JLabel("Raid: " + game.getGame().getGameLogic().getGameStatus().getBonusRaidActions());
        raidBonus.setSize(240, 15);
        raidBonus.setLocation(50, 225);
        info.add(raidBonus);

        JLabel sabotageBonus = new JLabel("Sabotage: " + game.getGame().getGameLogic().getGameStatus().getBonusSabotageActions());
        sabotageBonus.setSize(240, 15);
        sabotageBonus.setLocation(50, 240);
        info.add(sabotageBonus);

        add(info);

        deck = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/card_back_v.png").getImage(), 35)), SwingConstants.CENTER);
        deck.setSize(250, 300);
        deck.setLocation(250, 0);
        add(deck);

        if (game.getState() instanceof AwaitActionSelection) {
            JLabel rectangle = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/red_border.png")
                    .getImage(), 180, 85)), SwingConstants.CENTER);
            rectangle.setSize(180, 85);
            rectangle.setLocation(535, 20 + (game.getGame().getGameLogic().getDay()-1) * 87);
            add(rectangle);

            JLabel card = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/card_"+
                    (game.getGame().getGameLogic().getCard(game.getGame().getGameLogic().getCardIndex()).getCardNumber())
                    +".png").getImage(), 35)), SwingConstants.CENTER);
            card.setSize(250, 300);
            card.setLocation(500, 0);
            add(card);
        }

        JLabel die = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/face_" +
                game.getGame().getGameLogic().getDieFace() + ".png").getImage(), 20)),
                SwingConstants.CENTER);
        die.setSize(250, 300);
        die.setLocation(750, 0);
        add(die);

        if (game.getGame().getGameLogic().showWallStrength()) {
            JLabel wallStr = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            wallStr.setSize(50, 50);
            wallStr.setLocation(29, 313 + ((game.getGame().getGameLogic().getGameStatus().getWallStrength() - 4) * (-1)) * 45);
            add(wallStr);
        }

        if (game.getGame().getGameLogic().showMorale()) {
            JLabel morale = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            morale.setSize(50, 50);
            morale.setLocation(91, 313 + ((game.getGame().getGameLogic().getGameStatus().getMorale()-4)*(-1)) * 45);
            add(morale);
        }

        if (game.getGame().getGameLogic().showSupplies()) {
            JLabel suplies = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            suplies.setSize(50, 50);
            suplies.setLocation(153, 313 + ((game.getGame().getGameLogic().getGameStatus().getSuplies() - 4) * (-1)) * 45);
            add(suplies);
        }

        JLabel tunnelMovement = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                .getImage(), 35)), SwingConstants.CENTER);
        tunnelMovement.setSize(40, 40);
        if (game.getGame().getGameLogic().getGameStatus().getTunnelPos() < 4)
            tunnelMovement.setLocation(27 + game.getGame().getGameLogic().getGameStatus().getTunnelPos() * 31, 545);
        else
            tunnelMovement.setLocation(120 - (game.getGame().getGameLogic().getGameStatus().getTunnelPos()-3) * 31, 545);
        add(tunnelMovement);

        if (game.getGame().getGameLogic().showRaidedSupplies()) {
            JLabel raidedSuplies = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            raidedSuplies.setSize(50, 50);
            raidedSuplies.setLocation(158, 539 - ((game.getGame().getGameLogic().getGameStatus().getRaidSupplies()-1) * 32));
            add(raidedSuplies);
        }

        if (game.getGame().getGameLogic().showSurender()) {
            JLabel surrender = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            surrender.setSize(50, 50);
            surrender.setLocation(91, 494);
            add(surrender);
        }

        JLabel statusCard = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/status_card.png").getImage(), 35)), SwingConstants.CENTER);
        statusCard.setSize(250, 300);
        statusCard.setLocation(0, 300);
        add(statusCard);

        actions = new JPanel(null);
        actions.setSize(500, 300);
        actions.setLocation(250, 300);

        archers = new JButton("Archers Attack");
        archers.setSize(200, 30);
        archers.setLocation(25, 30);
        actions.add(archers);

        boilingWater = new JButton("Boiling Water Attack");
        boilingWater.setSize(200, 30);
        boilingWater.setLocation(25, 70);
        actions.add(boilingWater);

        closeCombat = new JButton("Close Combat Attack");
        closeCombat.setSize(200, 30);
        closeCombat.setLocation(25, 110);
        actions.add(closeCombat);

        coupure = new JButton("Coupure");
        coupure.setSize(200, 30);
        coupure.setLocation(25, 150);
        actions.add(coupure);

        rallyTroops = new JButton("Rally Troops");
        rallyTroops.setSize(200, 30);
        rallyTroops.setLocation(275, 30);
        actions.add(rallyTroops);

        tunnelMov = new JButton("Tunnel Movement");
        tunnelMov.setSize(200, 30);
        tunnelMov.setLocation(275, 70);
        actions.add(tunnelMov);

        supplyRaid = new JButton("Supply Raid");
        supplyRaid.setSize(200, 30);
        supplyRaid.setLocation(275, 110);
        actions.add(supplyRaid);

        sabotage = new JButton("Sabotage");
        sabotage.setSize(200, 30);
        sabotage.setLocation(275, 150);
        actions.add(sabotage);

        getActionPoints = new JButton("Change moral/action points");
        getActionPoints.setSize(240, 30);
        getActionPoints.setLocation(130, 190);
        actions.add(getActionPoints);

        endTurn = new JButton("End Turn");
        endTurn.setSize(100, 30);
        endTurn.setLocation(200, 230);
        actions.add(endTurn);

        MouseEventDemo mouseEventDemo = new MouseEventDemo();
        deck.addMouseListener(mouseEventDemo);

        EventHandler handle = new EventHandler();
        archers.addActionListener(handle);
        boilingWater.addActionListener(handle);
        closeCombat.addActionListener(handle);
        coupure.addActionListener(handle);
        rallyTroops.addActionListener(handle);
        tunnelMov.addActionListener(handle);
        supplyRaid.addActionListener(handle);
        sabotage.addActionListener(handle);
        getActionPoints.addActionListener(handle);
        endTurn.addActionListener(handle);

        if (game.getState() instanceof AwaitPickCard) {
            archers.setEnabled(false);
            boilingWater.setEnabled(false);
            closeCombat.setEnabled(false);
            coupure.setEnabled(false);
            rallyTroops.setEnabled(false);
            tunnelMov.setEnabled(false);
            supplyRaid.setEnabled(false);
            sabotage.setEnabled(false);
            getActionPoints.setEnabled(false);
            endTurn.setEnabled(false);
        } else if (game.getState() instanceof AwaitActionSelection && game.getGame().getGameLogic().showSupRaidSab()){
            supplyRaid.setEnabled(false);
            sabotage.setEnabled(false);
        }

        add(actions);

        JLabel ladder = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                .getImage(), 35)), SwingConstants.CENTER);
        ladder.setSize(50, 50);
        if (game.getGame().getGameLogic().getGameStatus().getLadderPos() == 0)
            ladder.setLocation(818, 310);
        else
            ladder.setLocation(782, 486 + (game.getGame().getGameLogic().getGameStatus().getLadderPos()-4) * 44);
        add(ladder);

        JLabel ram = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                .getImage(), 35)), SwingConstants.CENTER);
        ram.setSize(50, 50);
        if (game.getGame().getGameLogic().getGameStatus().getRamPos() == 0)
            ram.setLocation(848, 310);
        else
            ram.setLocation(848, 486 + (game.getGame().getGameLogic().getGameStatus().getRamPos()-4) * 44);
        add(ram);

        JLabel siegeTower = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                .getImage(), 35)), SwingConstants.CENTER);
        siegeTower.setSize(50, 50);
        if (game.getGame().getGameLogic().getGameStatus().getSiegeTowerPos() == 0)
            siegeTower.setLocation(878, 310);
        else
            siegeTower.setLocation(914, 486 + (game.getGame().getGameLogic().getGameStatus().getSiegeTowerPos()-4) * 44);
        add(siegeTower);

        if (game.getGame().getGameLogic().showTrebuchet()) {
            JLabel trebuchets = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/circle.png")
                    .getImage(), 35)), SwingConstants.CENTER);
            trebuchets.setSize(50, 50);
            trebuchets.setLocation(914 + (game.getGame().getGameLogic().getGameStatus().getTrebuchet()-3) * 66, 539);
            add(trebuchets);
        }

        JLabel enemyCard = new JLabel(new ImageIcon(getScaledImage(new ImageIcon("images/enemy_card.png").getImage(), 35)), SwingConstants.CENTER);
        enemyCard.setSize(250, 300);
        enemyCard.setLocation(750, 300);
        add(enemyCard);

        repaint();
        revalidate();
    }

    private class EventHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            IStates state = game.getState();
            Object object = event.getSource();

            if (state instanceof AwaitBeginning) {
                if (object == start) {
                    game.startGame();
                    mainMenu();
                } else if (object == leave) {
                    dispose();
                }
            } else if (state instanceof AwaitActionSelection) {
                if (object == archers) {
                    game.awaitArchersAttack();

                    String[] lanes = {"Ladder", "Ram", "Siege Tower"};

                    int choice = JOptionPane.showOptionDialog(null, "Choose a lane", "Archers Attack",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane. QUESTION_MESSAGE, null, lanes, null);

                    game.archersAttack(choice+1);
                    if (choice > -1)
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == boilingWater) {
                    game.awaitBoilingWaterAttack();

                    String[] lanes = {"Ladder", "Ram", "Siege Tower"};

                    int choice = JOptionPane.showOptionDialog(null, "Choose a lane", "Boiling Water Attack",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane. QUESTION_MESSAGE, null, lanes, null);

                    game.boilingWaterAttack(choice+1);
                    if (choice > -1)
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == closeCombat) {
                    game.closeCombatAttack();
                    JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == coupure) {
                    game.coupure();
                    JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == rallyTroops) {
                    game.rallyTroops();

                    String[] lanes = {"Boost dice (1 supply)", "No boost"};

                    int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Rally Troops",
                            JOptionPane.YES_NO_OPTION, JOptionPane. QUESTION_MESSAGE, null, lanes, null);

                    game.rallyTroops(choice+1);
                    if (choice > -1)
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == supplyRaid) {
                    game.supplyRaid();
                    JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == tunnelMov) {
                    game.awaitTunnelMovement();

                    String[] lanes = {"Enter the tunnel", "Free Movement", "Fast Movement"};

                    int choice = JOptionPane.showOptionDialog(null, "Choose a movement", "Tunnel Movement",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane. QUESTION_MESSAGE, null, lanes, null);

                    game.tunnelMovement(choice+1);
                    if (choice > -1)
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == supplyRaid) {
                    game.supplyRaid();
                    JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == sabotage) {
                    game.sabotage();
                    JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == getActionPoints) {
                    game.awaitAddActionPoint();

                    String[] lanes = {"Spend Morale", "Spend Supplies"};

                    int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Add Action Points",
                            JOptionPane.YES_NO_OPTION, JOptionPane. QUESTION_MESSAGE, null, lanes, null);

                    game.addActionPoint(choice+1);
                    if (choice > -1)
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                    update();
                } else if (object == endTurn) {
                    game.endTurn();

                    if (game.getGame().getGameLogic().gameOver() || game.getGame().getGameLogic().win()) {
                        JOptionPane.showMessageDialog(null, game.getGame().getGameLogic().getLog());
                        startMenu();
                    } else
                        update();
                }
            }
        }
    }

    private class MouseEventDemo implements MouseListener {

        public void mousePressed(MouseEvent e) { }

        public void mouseReleased(MouseEvent e) { }

        public void mouseEntered(MouseEvent e) { }

        public void mouseExited(MouseEvent e) { }

        public void mouseClicked(MouseEvent e) {
            if (game.getState() instanceof AwaitPickCard) {
                game.pickCard();
                update();
            }
        }
    }

    private Image getScaledImage(Image srcImg, int percentage){
        int width = srcImg.getWidth(null);
        int height = srcImg.getHeight(null);
        BufferedImage resizedImg = new BufferedImage(width*percentage/100, height*percentage/100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width*percentage/100, height*percentage/100, null);
        g2.dispose();

        return resizedImg;
    }

    private Image getScaledImage(Image srcImg, int width, int height){
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }

}
