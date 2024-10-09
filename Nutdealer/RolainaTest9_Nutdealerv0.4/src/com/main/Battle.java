/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzen
 */
public class Battle {

    ImageIcon redhart = new ImageIcon(NutDealer.class.getResource("test.png"));
    private File0 file0;
    //private Ruins ruinlocation = new Ruins();
    //private NutDealer nutdealer = new NutDealer(file0);
    private Player player;
    private Item bag;
    private Enemy enemy;
    private String title = "Encounter";
    private Object[] actions = {"Fight", "Act", "Item", "Mercy"};
    String[] fight = {"Hit", "Intentional Miss"},
            mercy = {"Spare", "Flee"},
            turn = {"Fight", "Act", "Item", "Mercy"},
            move = {"Up", "Down", "Left", "Right", "Stay"},
            key = {"..."};
    Object decision;

    public Battle(Player you, Item bage) {
        player = you;
        bag = bage;
    }

    public void setEnemy(Enemy newEnemy) {
        enemy = newEnemy;
    }

    public void encounter(int id) {
        boolean won = false;
        boolean killed = false;
        int EXP = 0;
        int GOLD = 0;
        int opt;
        int turns = 1, state = 0, acts = 0; //state = for enc counter, acts = for quote counter
        switch (id) {
            case 0 -> {
                JOptionPane.showOptionDialog(null, "But nobody came." + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
            }
            case 1 -> {
                Enemy froggit = new Enemy();
                froggit.changeName("Froggit");
                froggit.setAT(4);
                froggit.setDF(4);
                froggit.setEnemyInfo("Life is difficult for this enemy.");
                froggit.setMHP(30);
                int enmydmg = (int) (froggit.getAT() + player.HPmod() - (player.getDF() / 5));
                int plyrdmg = (int) (((player.damage - froggit.DF) + (int) (Math.random() * 3)) * 2.2);
                String[] neutral = {"Froggit doesn't seem to know why it's here.", "Froggit hops to and fro.", "The battlefield is filled with the smell of mustard seed.", "You are intimidated by Froggit's raw strength. Only kidding."};
                Object[] enc = {"Froggit hopped close!", neutral[(int) (Math.random() * 4)], "Froggit seems reluctant to fight you."};
                String[] act = {"Check", "Compliment", "Threaten"};
                String[] quote = {"Ribbit, ribbit.", "Croak, croak.", "Hop, hop.", "Meow."};
                Object[] quotes = {quote[(int) (Math.random() * 4)], "(Blushes deeply.) Ribbit..", "Shiver, shiver."};
                boolean comp_or_threat = false;

                OUTER:
                while (true) {
                    String[] froggiti = {froggit.getHealth()};
                    System.out.println(froggit.HP);
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) {
                        case 0 -> {
                            decision = JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, froggiti, froggiti[0]);
                            if (decision == null) {
                                continue;
                            }
                            opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                            switch (opt) {
                                case 0 -> {
                                    JOptionPane.showOptionDialog(null, "The froggit is hit.\n-" + plyrdmg + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    froggit.removeHP(plyrdmg);
                                }
                                case 1 ->
                                    JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                            switch (opt) {
                                case 0 ->
                                    JOptionPane.showOptionDialog(null, froggit.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was flattered anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    comp_or_threat = true;
                                    acts = 1;
                                    state = 2;
                                }
                                case 2 -> {
                                    JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was scared anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    comp_or_threat = true;
                                    acts = 2;
                                    state = 2;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 2 -> {
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> {
                                    if (comp_or_threat) {
                                        won = true;
                                        GOLD += 2;
                                        break OUTER;
                                    }
                                }
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    break OUTER;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (froggit.HP <= 0) {
                        JOptionPane.showOptionDialog(null, "Froggit died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 2;
                        won = true;
                        break;
                    }
                    int ea = (int) (Math.random() * 3);
                    switch (ea) {
                        case 0 -> {
                            for (int i = 0; i < 3; i++) {
                                opt = JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nFive small flies enter, then move towards you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt == 4) {
                                    player.removeHP(enmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nA small frog appears, and jumps from your left to your right.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            if (opt == 0 || opt == 2) {
                                player.removeHP(enmydmg);
                                if (player.HP <= 0) {
                                    break OUTER;
                                }
                            }
                        }
                        case 2 -> {
                            JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nFive small flies enter, but moves away from you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                        }
                    }
                    if (!comp_or_threat) {
                        state = 1;
                    }
                    turns++;
                }
            }
            case 2 -> {
                Enemy whimsun = new Enemy();
                whimsun.changeName("Whimsun");
                whimsun.setAT(4);
                whimsun.setDF(0);
                whimsun.setEnemyInfo("This monster is too sensitive to fight...");
                whimsun.setMHP(10);
                int enmydmg = (int) (whimsun.getAT() + player.HPmod() - (player.getDF() / 5));
                int plyrdmg = (int) (((player.damage - whimsun.DF) + (int) (Math.random() * 3)) * 2.2);
                String[] neutral = {"Whimsun continues to mutter apologies.", "Whimsun avoids eye contact.", "Whimsun is fluttering.", "It's starting to smell like lavender and mothballs."};
                Object[] enc = {"Whimsun approached meekly!", neutral[(int) (Math.random() * 4)], "Whimsun is hyperventilating."};
                String[] act = {"Check", "Console", "Terrorize"};
                String[] quote = {"I'm sorry...", "*sniff, sniff*", "I have no choice..", "Forgive me..."};
                Object[] quotes = {quote[(int) (Math.random() * 4)], "I can't handle this..."};
                boolean con_or_terr = false;

                OUTER:
                while (true) {
                    String[] info = {whimsun.getHealth()};
                    System.out.println(whimsun.HP);
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) {
                        case 0 -> {
                            decision = JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, info, info[0]);
                            if (decision == null) {
                                continue;
                            }
                            opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                            switch (opt) {
                                case 0 -> {
                                    JOptionPane.showOptionDialog(null, "The whimsun is hit.\n-" + plyrdmg + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    whimsun.removeHP(plyrdmg);
                                }
                                case 1 ->
                                    JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                            switch (opt) {
                                case 0 ->
                                    JOptionPane.showOptionDialog(null, whimsun.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "Halfway through your first word, Whimsun bursts into tears and runs away." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    if (con_or_terr) {
                                        won = true;
                                        GOLD += 2;
                                    }
                                    break OUTER;
                                }
                                case 2 -> {
                                    JOptionPane.showOptionDialog(null, "You raise your arms and wiggle your fingers. Whimsun freaks out!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    con_or_terr = true;
                                    acts = 1;
                                    state = 2;
                                    won = true;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 2 -> {
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> {
                                    if (con_or_terr) {
                                        GOLD += 2;
                                    }
                                    won = true;
                                    break OUTER;
                                }
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    break OUTER;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (whimsun.HP <= 0) {
                        JOptionPane.showOptionDialog(null, "Whimsun died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 2;
                        GOLD += 2;
                        won = true;
                        break;
                    }
                    int ea = (int) (Math.random() * 2);
                    switch (ea) {
                        case 0 -> {
                            for (int i = 0; i < 3; i++) {
                                opt = JOptionPane.showOptionDialog(null, whimsun.name + "'s turn. \nA ring of moths circles you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt != 4) {
                                    player.removeHP(enmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, whimsun.name + "'s turn. \nTwo vertical lines of moths on either side of you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            if (opt == 3 || opt == 2) {
                                player.removeHP(enmydmg);
                                if (player.HP <= 0) {
                                    break OUTER;
                                }
                            }
                        }
                    }
                    if (!con_or_terr) {
                        state = 1;
                    }
                    turns++;
                }
            }
            case 3 -> {
                Enemy froggit = new Enemy(); //Enemy1 Details
                froggit.changeName("Froggit");
                froggit.setAT(4);
                froggit.setDF(4);
                froggit.setEnemyInfo("Life is difficult for this enemy.");
                froggit.setMHP(30);
                Enemy whimsun = new Enemy(); //Enemy2 Details
                whimsun.changeName("Whimsun");
                whimsun.setAT(4);
                whimsun.setDF(0);
                whimsun.setEnemyInfo("This monster is too sensitive to fight...");
                whimsun.setMHP(10);
                int fenmydmg = (int) (froggit.getAT() + player.HPmod() - (player.getDF() / 5)); //Enemy1 dmg
                int wenmydmg = (int) (whimsun.getAT() + player.HPmod() - (player.getDF() / 5)); //Enemy2 dmg
                int plyrdmgf = (int) (((player.damage - froggit.DF) + (int) (Math.random() * 3)) * 2.2); //Damage to enemy1
                int plyrdmgw = (int) (((player.damage - whimsun.DF) + (int) (Math.random() * 3)) * 2.2); //Damage to enemy2
                int acts2 = 0;
                //Enemy 1 quotes
                String[] neutral = {"Froggit doesn't seem to know why it's here.", "Froggit hops to and fro.", "The battlefield is filled with the smell of mustard seed.", "You are intimidated by Froggit's raw strength. Only kidding."};
                String[] act = {"Check", "Compliment", "Threaten"};
                String[] quote = {"Ribbit, ribbit.", "Croak, croak.", "Hop, hop.", "Meow."};
                Object[] quotes = {quote[(int) (Math.random() * 4)], "(Blushes deeply.) Ribbit..", "Shiver, shiver."};
                //Enemy 2 quotes
                String[] wneutral = {"Whimsun continues to mutter apologies.", "Whimsun avoids eye contact.", "Whimsun is fluttering.", "It's starting to smell like lavender and mothballs."};
                String[] wact = {"Check", "Console", "Terrorize"};
                String[] wquote = {"I'm sorry...", "*sniff, sniff*", "I have no choice..", "Forgive me..."};
                Object[] wquotes = {wquote[(int) (Math.random() * 4)], "I can't handle this..."};
                //Battle Message
                Object[] enc = {"Froggit and Whimsun drew near!", neutral[(int) (Math.random() * 4)], wneutral[(int) (Math.random() * 4)], "Froggit seems reluctant to fight you.", "Whimsun is hyperventilating."};

                boolean spare1 = false, out1 = false; //froggit
                boolean spare2 = false, out2 = false; //whimsun
                String[] info = {froggit.getHealth(), whimsun.getHealth(), null};

                OUTER:
                while (true) {
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) { //Your Turn
                        case 0 -> { //Fight
                            decision = (String) JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, info, info[0]);
                            if (decision == null) {
                                continue;
                            } else if (decision.equals(froggit.getHealth())) { //Enemy 1
                                opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                                switch (opt) {
                                    case 0 -> { //Hit
                                        JOptionPane.showOptionDialog(null, "The froggit is hit.\n-" + plyrdmgf + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        froggit.removeHP(plyrdmgf);
                                        info[0] = froggit.getHealth();
                                    }
                                    case 1 -> //Intentional Miss
                                        JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    default -> {
                                        continue;
                                    }
                                }
                            } else if (decision.equals(whimsun.getHealth())) { //Enemy 2
                                opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                                switch (opt) {
                                    case 0 -> { //Hit
                                        JOptionPane.showOptionDialog(null, "The whimsun is hit.\n-" + plyrdmgw + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        whimsun.removeHP(plyrdmgw);
                                        info[1] = whimsun.getHealth();
                                    }
                                    case 1 -> //Intentional Miss
                                        JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    default -> {
                                        continue;
                                    }
                                }
                            }
                        }
                        case 1 -> { //Act
                            decision = (String) JOptionPane.showInputDialog(null, "You chose to ACT." + player.getHealth(), title, -1, redhart, info, info[0]);
                            if (decision == null) {
                                continue;
                            } else if (decision.equals(froggit.getHealth())) {//Enemy 1
                                opt = JOptionPane.showOptionDialog(null, "You chose to ACT." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                                switch (opt) {
                                    case 0 -> //Check
                                        JOptionPane.showOptionDialog(null, froggit.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    case 1 -> {
                                        JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was flattered anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        spare1 = true;
                                        acts = 1;
                                    }
                                    case 2 -> {
                                        JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was scared anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        spare1 = true;
                                        acts = 2;
                                    }
                                    default -> {
                                        continue;
                                    }
                                }
                            } else if (decision.equals(whimsun.getHealth())) { //Enemy 2
                                opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, wact, wact[0]);
                                switch (opt) {
                                    case 0 -> //Check
                                        JOptionPane.showOptionDialog(null, whimsun.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    case 1 -> {
                                        JOptionPane.showOptionDialog(null, "Halfway through your first word, Whimsun bursts into tears and runs away." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        if (spare2) {
                                            GOLD += 2;
                                            out2 = true;
                                        } else if (out1) {
                                            GOLD += 2;
                                            won = true;
                                            break OUTER;
                                        } else {
                                            out2 = true;
                                        }
                                        enemyout(info, whimsun.getHealth(), 2);
                                    }
                                    case 2 -> {
                                        JOptionPane.showOptionDialog(null, "You raise your arms and wiggle your fingers. Whimsun freaks out!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        spare2 = true;
                                        acts2 = 1;
                                    }
                                    default -> {
                                        continue;
                                    }
                                }
                            }
                        }
                        case 2 -> { //Item
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> { //Mercy
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> { //Spare
                                    if (spare1 && !out1) { //If Enemy1 does not want to fight
                                        enemyout(info, froggit.getHealth(), 2);
                                        GOLD += 2;
                                        out1 = true;
                                    } else if (spare2 && !out2) { //If Enemy2 does not want to fight
                                        enemyout(info, whimsun.getHealth(), 2);
                                        GOLD += 2;
                                        out2 = true;
                                    } else if (spare1 && spare2) { //If both does not want to fight
                                        GOLD += 4;
                                        out1 = true;
                                        out2 = true;
                                    }
                                }
                                case 1 -> { //Flee
                                    JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    break OUTER;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (froggit.HP <= 0 && !out1) { //Enemy 1 dies, second condition to only trigger once
                        JOptionPane.showOptionDialog(null, "Froggit died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 2;
                        enemyout(info, froggit.getHealth(), 2);
                        out1 = true;
                    }
                    if (whimsun.HP <= 0 && !out2) { //Enemy 2 dies, second condition to only trigger once
                        JOptionPane.showOptionDialog(null, "Whimsun died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 2;
                        GOLD += 2;
                        enemyout(info, whimsun.getHealth(), info.length);
                        out2 = true;
                    }
                    if ((froggit.HP <= 0 && whimsun.HP <= 0) || (out1 && out2)) { //Win condition, checks before enemy turn
                        won = true;
                        break;
                    }

                    //Enemy's Turn
                    if (!out2 && !out1) { //Double attack
                        System.out.println(out2);
                        int prev = 4;
                        for (int i = 0; i < 3; i++) {
                            opt = JOptionPane.showOptionDialog(null, froggit.name + " and " + whimsun.name + "'s turn. \nFive small flies enter, then move towards you, while you are surrounded by moths.\nFroggit: " + quotes[acts] + "\nWhimsun: " + wquotes[acts2] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            if (opt == prev) {
                                player.removeHP((fenmydmg + wenmydmg) / 2);
                                if (player.HP <= 0) {
                                    break OUTER;
                                }
                                prev = opt;
                            }
                        }
                    } else if (!out2 && out1) { //Single Enemy1 attack
                        int ea = (int) (Math.random() * 3);
                        switch (ea) {
                            case 0 -> {
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, whimsun.name + "'s turn. \nA ring of moths circles you.\n" + wquotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt != 4) {
                                        player.removeHP(wenmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                            case 1 -> {
                                opt = JOptionPane.showOptionDialog(null, whimsun.name + "'s turn. \nTwo vertical lines of moths on either side of you.\n" + wquotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt == 3 || opt == 2) {
                                    player.removeHP(wenmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                            }
                        }
                    } else { //Single Enemy2 attack
                        int ea = (int) (Math.random() * 3);
                        switch (ea) {
                            case 0 -> {
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nFive small flies enter, then move towards you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 4) {
                                        player.removeHP(fenmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                            case 1 -> {
                                opt = JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nA small frog appears, and jumps from your left to your right.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt == 0 || opt == 2) {
                                    player.removeHP(fenmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                            }
                            case 2 -> {
                                JOptionPane.showOptionDialog(null, froggit.name + "'s turn. \nFive small flies enter, but moves away from you.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            }
                        }
                    }
                    //Enemy state, for quote configuration
                    if (spare1 && !out1) {
                        state = 3;
                    } else if (spare2 && !out2) {
                        state = 4;
                    } else {
                        if (!out1 && !out2) {
                            state = (int) ((Math.random() * 2) + 1);
                        } else if (!out1 && out2) {
                            state = 1;
                        } else if (out1 && !out2) {
                            state = 2;
                        }
                    }
                    turns++;
                }
            } //multiple
            case 4 -> {
                Enemy moldsmal = new Enemy();
                moldsmal.changeName("Moldsmal");
                moldsmal.setAT(4);
                moldsmal.setDF(0);
                moldsmal.setEnemyInfo("Stereotypical: Curvaceously attractive, but no brains...");
                moldsmal.setMHP(50);
                int enmydmg = (int) (moldsmal.getAT() + player.HPmod() - (player.getDF() / 5)); //dmg of enemy
                int plyrdmg = (int) (((player.damage - moldsmal.DF) + (int) (Math.random() * 3)) * 2.2); //dmg of player
                String[] neutral = {"Moldsmal waits pensively.", "Moldsmal burbles quietly.", "Moldsmal is ruminating.", "The aroma of lime gelatin wafts through."};
                String[] act = {"Check", "Imitate", "Flirt"}; //Act
                String[] quote = {"Squorch...", "Burble burb...", "*Slime sounds*", "*Sexy wiggle*"}; //Neutral quote
                boolean acted = false;

                OUTER:
                while (true) {
                    Object[] enc = {"Moldsmal blocked the way!", neutral[(int) (Math.random() * 4)], "Moldsmal has started to spoil."}; //[2]Low HP
                    Object[] quotes = {quote[(int) (Math.random() * 4)]}; //battle quotes
                    String[] minfo = {moldsmal.getHealth()}; //monster info
                    System.out.println(moldsmal.HP);
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) {
                        case 0 -> {
                            decision = JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, minfo, minfo[0]);
                            if (decision == null) {
                                continue;
                            }
                            opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                            switch (opt) {
                                case 0 -> {
                                    JOptionPane.showOptionDialog(null, "The moldsmal is hit.\n-" + plyrdmg + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    moldsmal.removeHP(plyrdmg);
                                }
                                case 1 ->
                                    JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                            switch (opt) {
                                case 0 ->
                                    JOptionPane.showOptionDialog(null, moldsmal.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "You lie immobile with Moldsmal. You feel like you understand the world a little better." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    acted = true;
                                }
                                case 2 -> {
                                    JOptionPane.showOptionDialog(null, "You wiggle your hips. Moldsmal wiggles back. What a meaningful conversation!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    acted = true;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 2 -> {
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> {
                                    if (acted) {
                                        GOLD += 1;
                                    }
                                    won = true;
                                    break OUTER;
                                }
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    break OUTER;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (moldsmal.HP <= 0) {
                        JOptionPane.showOptionDialog(null, moldsmal.name + " died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 3;
                        won = true;
                        break;
                    }
                    int ea = (int) (Math.random() * 2);
                    switch (ea) {
                        case 0 -> {
                            String[] danger = {"Pellet coming from top.", "Pellet is in your bottom.", "Pellet coming from your left.", "Pellet coming from your right."};
                            for (int i = 0; i < 5; i++) {
                                int dangerdirection = (int) (Math.random() * 4);
                                opt = JOptionPane.showOptionDialog(null, moldsmal.name + "'s turn. \nSeveral pellets fall from the top of the box, which explodes into circles of smaller pellets after falling a short distance.\n" + danger[dangerdirection] + "\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt == 4 || opt == dangerdirection) {
                                    player.removeHP(enmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            int prev = 0;
                            for (int i = 0; i < 3; i++) {
                                opt = JOptionPane.showOptionDialog(null, moldsmal.name + "'s turn. \nSeveral small pellets fall in a zigzag pattern from the top of the box to the bottom.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                if (opt == 0 || opt == 4) {
                                    player.removeHP(enmydmg);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                } else if (opt >= 1 && opt <= 3) {
                                    if (opt == prev) {
                                        player.removeHP(enmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                    prev = opt;
                                }
                            }
                        }
                    }
                    if (moldsmal.HP <= (moldsmal.maxHP / 4)) {
                        state = 2;
                    } else {
                        state = 1;
                    }
                    turns++;
                }
            } //single
            case 5 -> {
                Enemy moldsmal1 = new Enemy();
                moldsmal1.changeName("Moldsmal");
                moldsmal1.setAT(4);
                moldsmal1.setDF(0);
                moldsmal1.setEnemyInfo("Stereotypical: Curvaceously attractive, but no brains...");
                moldsmal1.setMHP(50);
                Enemy moldsmal2 = new Enemy();
                moldsmal2.changeName("Moldsmal");
                moldsmal2.setAT(4);
                moldsmal2.setDF(0);
                moldsmal2.setEnemyInfo("Stereotypical: Curvaceously attractive, but no brains...");
                moldsmal2.setMHP(50);
                Enemy moldsmal3 = new Enemy();
                moldsmal3.changeName("Moldsmal");
                moldsmal3.setAT(4);
                moldsmal3.setDF(0);
                moldsmal3.setEnemyInfo("Stereotypical: Curvaceously attractive, but no brains...");
                moldsmal3.setMHP(50);
                int enmydmg1 = (int) (moldsmal1.getAT() + player.HPmod() - (player.getDF() / 5)); //Enemy1 dmg - All the same AT so all is referenced here
                int plyrdmg1 = (int) (((player.damage - moldsmal1.DF) + (int) (Math.random() * 3)) * 2.2); //Damage to enemy1 - All the same DF so all is referenced here
                int acts2 = 0;
                //Enemy 1 quotes - All the same type, all is referenced here
                String[] neutral = {"Moldsmal waits pensively.", "Moldsmal burbles quietly.", "Moldsmal is ruminating.", "The aroma of lime gelatin wafts through."};
                String[] act = {"Check", "Imitate", "Flirt"}; //Act
                String[] quote = {"Squorch...", "Burble burb...", "*Slime sounds*", "*Sexy wiggle*"};
                String[] info = {moldsmal1.getHealth(), moldsmal2.getHealth(), moldsmal3.getHealth(), null};
                boolean spare1 = false, out1 = false; //enemy1
                boolean spare2 = false, out2 = false; //enemy2
                boolean spare3 = false, out3 = false; //enemy3

                OUTER:
                while (true) {

                    Object[] quotes = {quote[(int) (Math.random() * 4)]};
                    //Battle Message
                    Object[] enc = {"You tripped into a line of Moldsmals.", neutral[(int) (Math.random() * 4)], "Moldsmal has started to spoil."};
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) { //Your Turn
                        case 0 -> { //Fight
                            decision = (String) JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, info, info[0]);
                            if (decision == null) {
                                continue;
                            } else {
                                opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                                switch (opt) {
                                    case 0 -> { //Hit
                                        int index = 0;
                                        JOptionPane.showOptionDialog(null, "The moldsmal is hit.\n-" + plyrdmg1 + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        if (decision.equals(moldsmal1.getHealth())) {
                                            index = indexOf(info, moldsmal1.getHealth(), info.length);
                                            moldsmal1.removeHP(plyrdmg1);
                                            info[index] = moldsmal1.getHealth();
                                        } else if (decision.equals(moldsmal2.getHealth())) {
                                            index = indexOf(info, moldsmal2.getHealth(), info.length);
                                            moldsmal2.removeHP(plyrdmg1);
                                            info[index] = moldsmal2.getHealth();
                                        } else if (decision.equals(moldsmal3.getHealth())) {
                                            index = indexOf(info, moldsmal3.getHealth(), info.length);
                                            moldsmal3.removeHP(plyrdmg1);
                                            info[index] = moldsmal3.getHealth();
                                        }
                                    }
                                    case 1 -> //Intentional Miss
                                        JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    default -> {
                                        continue;
                                    }
                                }
                            }
                        }
                        case 1 -> { //Act
                            decision = (String) JOptionPane.showInputDialog(null, "You chose to ACT." + player.getHealth(), title, -1, redhart, info, info[0]);
                            if (decision == null) {
                                continue;
                            } else { //Enemy 1
                                opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                                switch (opt) {
                                    case 0 -> {
                                        if (decision.equals(moldsmal1.getHealth())) {
                                            JOptionPane.showOptionDialog(null, moldsmal1.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        } else if (decision.equals(moldsmal2.getHealth())) {
                                            JOptionPane.showOptionDialog(null, moldsmal2.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        } else if (decision.equals(moldsmal3.getHealth())) {
                                            JOptionPane.showOptionDialog(null, moldsmal3.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        }
                                    }
                                    case 1 -> {
                                        JOptionPane.showOptionDialog(null, "You lie immobile with Moldsmal. You feel like you understand the world a little better." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        if (decision.equals(moldsmal1.getHealth())) {
                                            spare1 = true;
                                        } else if (decision.equals(moldsmal2.getHealth())) {
                                            spare2 = true;
                                        } else if (decision.equals(moldsmal3.getHealth())) {
                                            spare3 = true;
                                        }
                                    }
                                    case 2 -> {
                                        JOptionPane.showOptionDialog(null, "You wiggle your hips. Moldsmal wiggles back. What a meaningful conversation!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        if (decision.equals(moldsmal1.getHealth())) {
                                            spare1 = true;
                                        } else if (decision.equals(moldsmal2.getHealth())) {
                                            spare2 = true;
                                        } else if (decision.equals(moldsmal3.getHealth())) {
                                            spare3 = true;
                                        }
                                    }
                                    default -> {
                                        continue;
                                    }
                                }
                            }
                        }
                        case 2 -> { //Item
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> { //Mercy
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> { //Spare
                                    if (spare1 && !out1) {
                                        GOLD += 1;
                                    }
                                    if (spare2 && !out2) {
                                        GOLD += 1;
                                    }
                                    if (spare3 && !out3) {
                                        GOLD += 1;
                                    }
                                    won = true;
                                    break OUTER;
                                }
                                case 1 -> { //Flee
                                    JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    break OUTER;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (moldsmal1.HP <= 0 && !out1) { //Enemy 1 dies, second condition to only trigger once
                        JOptionPane.showOptionDialog(null, "Moldsmal died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 3;
                        enemyout(info, moldsmal1.getHealth(), info.length);
                        out1 = true;
                    }
                    if (moldsmal2.HP <= 0 && !out2) { //Enemy 2 dies, second condition to only trigger once
                        JOptionPane.showOptionDialog(null, "Moldsmal died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 3;
                        enemyout(info, moldsmal2.getHealth(), info.length);
                        out2 = true;
                    }
                    if (moldsmal3.HP <= 0 && !out3) { //Enemy 3 dies, second condition to only trigger once
                        JOptionPane.showOptionDialog(null, "Moldsmal died!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        player.addkill();
                        EXP += 3;
                        GOLD += 3;
                        enemyout(info, moldsmal3.getHealth(), info.length);
                        out3 = true;
                    }
                    if ((moldsmal1.HP <= 0 && moldsmal2.HP <= 0 && moldsmal3.HP <= 0) || (out1 && out2 && out3)) { //Win condition, checks before enemy turn
                        won = true;
                        break;
                    }

                    //Enemy's Turn
                    if (!out1 && !out2 && !out3) { //Triple
                        String[] danger = {"Pellet coming from top.", "Pellet is in your bottom.", "Pellet coming from your left.", "Pellet coming from your right."};
                        for (int i = 0; i < 7; i++) {
                            int dangerdirection = (int) (Math.random() * 4);
                            int dangerdirection2 = (int) (Math.random() * 4);
                            int dangerdirection3 = (int) (Math.random() * 4);
                            opt = JOptionPane.showOptionDialog(null, moldsmal1.name + "s' turn. \nSeveral pellets fall from the top of the box, which explodes into circles of smaller pellets after falling a short distance.\n" + danger[dangerdirection] + "\n" + danger[dangerdirection2] + "\n" + danger[dangerdirection3] + "\nMoldsmal: " + quotes[acts] + "\nMoldsmal: " + quotes[acts] + "\nMoldsmal: " + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            if (opt == 4 || opt == dangerdirection || opt == dangerdirection2 || opt == dangerdirection3) {
                                player.removeHP(enmydmg1);
                                if (player.HP <= 0) {
                                    break OUTER;
                                }
                            }
                        }
                    } else if ((out1 && !out2 && !out3) || (!out1 && out2 && !out3) || (!out1 && !out2 && out3)) { //Double attack
                        int prev = 0, opp = (prev + 2) % 5;
                        for (int i = 0; i < 3; i++) {
                            opt = JOptionPane.showOptionDialog(null, moldsmal1.name + "'s turn. \nSeveral small pellets fall in a zigzag pattern from the top of the box to the bottom.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                            if (opt == 0 || opt == 4) {
                                player.removeHP(enmydmg1);
                                if (player.HP <= 0) {
                                    break OUTER;
                                }
                            } else if (opt >= 1 && opt <= 3) {
                                if (opt == prev || opt == opp) {
                                    player.removeHP(enmydmg1);
                                    if (player.HP <= 0) {
                                        break OUTER;
                                    }
                                }
                                prev = opt;
                            }
                        }
                    } else {
                        int ea = (int) (Math.random() * 2);
                        switch (ea) {
                            case 0 -> {
                                String[] danger = {"Pellet coming from top.", "Pellet is in your bottom.", "Pellet coming from your left.", "Pellet coming from your right."};
                                for (int i = 0; i < 5; i++) {
                                    int dangerdirection = (int) (Math.random() * 4);
                                    opt = JOptionPane.showOptionDialog(null, moldsmal1.name + "'s turn. \nSeveral pellets fall from the top of the box, which explodes into circles of smaller pellets after falling a short distance.\n" + danger[dangerdirection] + "\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 4 || opt == dangerdirection) {
                                        player.removeHP(enmydmg1);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                            case 1 -> {
                                int prev = 0;
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, moldsmal1.name + "'s turn. \nSeveral small pellets fall in a zigzag pattern from the top of the box to the bottom.\n" + quotes[acts] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 0 || opt == 4) {
                                        player.removeHP(enmydmg1);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    } else if (opt >= 1 && opt <= 3) {
                                        if (opt == prev) {
                                            player.removeHP(enmydmg1);
                                            if (player.HP <= 0) {
                                                break OUTER;
                                            }
                                        }
                                        prev = opt;
                                    }
                                }
                            }
                        }
                    }
                    //Enemy state, for quote configuration
                    if ((moldsmal1.HP <= (moldsmal1.maxHP / 4) && !out1) || (moldsmal2.HP <= (moldsmal2.maxHP / 4) && !out2) || (moldsmal2.HP <= (moldsmal2.maxHP / 4) && !out3)) {
                        state = 2;
                    } else {
                        state = 1;
                    }
                    turns++;
                }
            } //multiple same enemy
            case 18 -> {
                Enemy toriel = new Enemy();
                toriel.changeName("Toriel");
                toriel.setAT(6);
                if (player.ruin_geno) {
                    toriel.setDF(-9999);
                } else {
                    toriel.setDF(1);
                }
                toriel.setEnemyInfo("Knows best for you.");
                toriel.setMHP(440);
                int enmydmg = (int) (toriel.getAT() + player.HPmod() - (player.getDF() / 5));
                int plyrdmg = (int) (((player.damage - toriel.DF) + (int) (Math.random() * 3)) * 2.2);
                String[] neutral = {"Toriel looks through you.", "Toriel prepares a magical attack.", "Toriel takes a deep breath.", "Toriel is acting aloof."};
                String[] act = {"Check", "Talk"};
                String[] quote = {".....",
                    "..... .....",
                    "..... ..... .....",
                    "...?",
                    "What are you doing?",
                    "Attack or run away!",
                    "What are you proving this way?",
                    "Fight me or leave!",
                    "Stop it.",
                    "Stop looking at me that way.",
                    "Go away!",
                    "...",
                    "... ... ",
                    "I know you want to go home, but...",
                    "But please... go upstairs now. ",
                    "I promise I will take good care of you here.",
                    "I know we do not have much, but... ",
                    "We can have a good life here. ",
                    "Why are you making this so difficult?",
                    "Please, go upstairs. ",
                    "..... ",
                    "Ha ha... ",
                    "Pathetic, is it not? I cannot save even a single child. ",
                    "... ",
                    "No, I understand.\n"
                    + "You would just be unhappy trapped down here.\n"
                    + "The RUINS are very small once you get used to them.\n"
                    + "It would not be right for you to grow up in a place like this.\n"
                    + "My expectations... My loneliness... My fear...\n"
                    + "For you, my child... I will put them aside."};
                String[] talk = {"You couldn't think of any conversation topics.",
                    "You tried to think of something to say again, but....",
                    "Ironically, talking does not seem to be the solution to this situation."
                };
                String[] defeat = {"Urgh...\n",
                    "You are stronger than I thought...\n",
                    "Listen to me, small one...\n",
                    "If you go beyond this door,\n",
                    "Keep walking as far as you can.\n",
                    "Eventually you will reach an exit.\n",
                    "... ....\n",
                    "ASGORE... Do not let ASGORE take your soul.\n",
                    "His plan cannot be allowed to succeed.\n",
                    "......\n",
                    "Be good, won't you?\n",
                    "M y c h i l d ."};
                String[] genodefeat = {"Y... you... really hate me that much?\n",
                    "Now I see who I was protecting by keeping you here.\n",
                    "Not you...\n",
                    "But them!\n",
                    "Ha... ha..."};
                int spared = 0;
                int ttalk = 0;
                OUTER:
                while (true) {
                    Object[] enc = {"Toriel blocks the way!", neutral[(int) (Math.random() * 4)], "..."}; //[2]Low HP
                    String[] minfo = {toriel.getHealth()}; //monster info
                    opt = JOptionPane.showOptionDialog(null, enc[state] + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
                    switch (opt) {
                        case 0 -> {
                            decision = JOptionPane.showInputDialog(null, "You chose to FIGHT." + player.getHealth(), title, -1, redhart, minfo, minfo[0]);
                            if (decision == null) {
                                continue;
                            }
                            opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                            switch (opt) {
                                case 0 -> {
                                    JOptionPane.showOptionDialog(null, "Toriel is hit.\n-" + plyrdmg + " HP!" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    toriel.removeHP(plyrdmg);
                                }
                                case 1 ->
                                    JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 1 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                            switch (opt) {
                                case 0 ->
                                    JOptionPane.showOptionDialog(null, toriel.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, talk[ttalk] + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                    ttalk++;
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        case 2 -> {
                            JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            continue;
                        }
                        case 3 -> {
                            opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                            switch (opt) {
                                case 0 -> {
                                    if (spared == 24) {
                                        String[] tspare = {"If you truly wish to leave the RUINS...\n",
                                            "I will not stop you.\n",
                                            "However, when you leave...\n",
                                            "Please do not come back.\n",
                                            "I hope you understand.\n",
                                            "Goodbye, my child."};
                                        for (String tspares : tspare) {
                                            JOptionPane.showOptionDialog(null, tspares + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                        }
                                        break OUTER;
                                    } else {
                                        spared++;
                                    }
                                }
                                case 1 -> {
                                    JOptionPane.showOptionDialog(null, "You decide not to flee." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                                }
                                default -> {
                                    continue;
                                }
                            }
                        }
                        default -> {
                            continue;
                        }
                    }
                    if (toriel.HP <= 0) {
                        if (player.ruin_geno) {
                            for (String defeats : genodefeat) {
                                JOptionPane.showOptionDialog(null, defeats + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            }
                        } else {
                            for (String defeats : defeat) {
                                JOptionPane.showOptionDialog(null, defeats + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                            }
                        }
                        player.addkill();
                        EXP += 150;
                        won = true;
                        break;
                    }

                    if (spared < 11) {
                        int ea = (int) (Math.random() * 4);
                        switch (ea) {
                            case 0 -> {
                                String[] danger = {"Pellet coming from top.", "Pellet is in your bottom.", "Pellet coming from your left.", "Pellet coming from your right."};
                                for (int i = 0; i < 10; i++) {
                                    int dangerdirection = (int) (Math.random() * 4);
                                    opt = JOptionPane.showOptionDialog(null, toriel.name + "'s turn. \nToriel's Fire Helix scatters on around you.\n" + danger[dangerdirection] + "\n" + quote[spared] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 4 || opt == dangerdirection) {
                                        player.removeHP(enmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                            case 1 -> {
                                int prev = 0;
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, toriel.name + "'s turn. \nToriel's Paw Sweep sweeps your top and bottom.\n" + quote[spared] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 0 || opt == 4 || opt == 1) {
                                        player.removeHP(enmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    } else if (opt == 2 || opt == 3) {
                                        prev = opt;
                                        if (opt != prev) {
                                            player.removeHP(enmydmg);
                                            if (player.HP <= 0) {
                                                break OUTER;
                                            }
                                        }
                                    }
                                }
                            }
                            case 2 -> {
                                int prev = 0;
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, toriel.name + "'s turn. \nToriel's Fire Waves tries to cross you.\n" + quote[spared] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 2 || opt == 4 || opt == 3) {
                                        player.removeHP(enmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    } else if (opt == 1 || opt == 0) {
                                        prev = opt;
                                        if (opt != prev) {
                                            player.removeHP(enmydmg);
                                            if (player.HP <= 0) {
                                                break OUTER;
                                            }
                                        }
                                    }
                                }
                            }
                            case 3 -> {
                                int prev = 0;
                                for (int i = 0; i < 3; i++) {
                                    opt = JOptionPane.showOptionDialog(null, toriel.name + "'s turn. \nToriel's Faltering falls on your sides.\n" + quote[spared] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                                    if (opt == 2 || opt == 3) {
                                        player.removeHP(enmydmg);
                                        if (player.HP <= 0) {
                                            break OUTER;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < 3; i++) {
                            JOptionPane.showOptionDialog(null, toriel.name + "'s turn. \nToriel's Faltering falls on your sides, but not wanting to hurt you.\n" + quote[spared] + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                        }
                    }
                    if (spared == 24) {
                        state = 2;
                    } else {
                        state = 1;
                    }
                    turns++;
                }
            }
        }
        if (won) {
            player.giveEXP(EXP);
            player.earnGold(GOLD);
            JOptionPane.showOptionDialog(null, "You won! \nYou get " + EXP + " EXP and " + GOLD + " gold. \n" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
        }
    }

    void enemyout(String[] info, String enemy, int enemies) {
        int index = indexOf(info, enemy, enemies);
        System.out.println(index);
        if (index >= 0) {
            for (int i = 0; i < (info.length - index - 1); i++) {
                info[index + i] = info[index + 1 + i];
            }
        }
    }

    private int indexOf(String[] info, String enemy, int enemies) {
        for (int i = 0; i < enemies; i++) {
            if (info[i].equals(enemy)) {
                return i;
            }
        }
        return -1;
    }
}
