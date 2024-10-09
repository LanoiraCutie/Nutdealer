/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ryzen
 */
public class Ruins extends NutDealer implements Menu { //Subclass

    String[] fight = {"Hit", "Intentional Miss"},
            mercy = {"Spare", "Flee"},
            turn = {"Fight", "Act", "Item", "Mercy"};
    Object[] move = {"Up", "Down", "Left", "Right"};
    Object[] key = {"..."};
    Enemy dummy = new Enemy();
    String newlocation = "Ruins";
    Ruins ruinlocation;

    public Ruins() {
        super(file0);
    }

    @Override
    public void initialization() {
        ruinlocation = new Ruins();
        player.changeLocation("Ruins - Flowergrass");
        if (ruinlocation instanceof Ruins) {
            try {
                thisisflowey(ruinlocation.newlocation);
                ruinlocation.r001();
            } catch (IOException ex) {
                System.exit(0);
            }
        }
    }

    @Override
    void SAVEPoint() {
        try {
            Ruins ruins = new Ruins();
            switch (player.location) {
                case "Start" ->
                    ruins.initialization();
                case "Ruins - Flowergrass" ->
                    ruins.r001();
                case "Ruins - Home" ->
                    ruins.r002(true);
                case "Ruins - Leaf Pile" ->
                    ruins.r009();
                case "Ruins - The Home" ->
                    ruins.r027();
                default -> {
                }
            }
        } catch (IOException ex) {
            System.err.println("Detected empty File0. Try removing it before launching the game again.");
        }
    }

    void thisisflowey(String newlocation) { //Flowey's Welcome!
        String[] dialogue = {"[You spawned in a small patch of flowers. After walking away from the flowers, you walk through a gate and up to a single flower in the ground.]",
            "Flowey: Howdy! I'm FLOWEY. FLOWEY the FLOWER!",
            "Hmmm... You're new to the " + newlocation + ", aren'tcha?",
            "Golly, you must be so confused. Someone ought to teach you how things work around here!",
            "I guess little old me will have to do. Ready? Here we go!"
        };
        int opt;
        for (String dialogues : dialogue) {
            opt = JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            if (opt == JOptionPane.CLOSED_OPTION) {
                ruinlocation.Menu();
            }
        }
        ruinlocation.thisisflowey2(ruinlocation.newlocation);
    }

    void thisisflowey2(String newlocation) { //You idiot...
        int opt;
        String[] dialogue = {"FLOWEY: See that heart? \nThat is your SOUL, the very culmination of your being!",
            "FLOWEY: Your SOUL starts off weak, but can grow strong if you gain a lot of LV.",
            "FLOWEY: What does LV stand for?", "FLOWEY: What does LV stand for? \nWhy, LOVE, of course!",
            "FLOWEY: You want some LOVE, don't you?",
            "FLOWEY: Dont worry! \nI'll share some with you!",
            "[FLOWEY winks; a small star appear out of the right side of his face, dissipating into the darkness behind him]",
            "FLOWEY: Down here, LOVE is spread through...",
            "FLOWEY: Little white \n'friendliness pellets.'",
            "FLOWEY: Are you ready?"};
        for (String dialogues : dialogue) {
            opt = JOptionPane.showOptionDialog(null, dialogues + player.getHealth(), title, 0, -1, redhart, move, move[0]);
            if (opt == JOptionPane.CLOSED_OPTION) {
            }
        }
        String[] fpellet = {"FLOWEY: Go up! Get as many as you can. [press 'UP']",
            "FLOWEY: Hey buddy, you missed them. \nLet's try this again, okay?",
            "FLOWEY: Is this a joke? \nAre you braindead? \nRUN.INTO.THE.friendliness pellets"
        };
        String[] trolledflowey = {"FLOWEY: [face has changed from innocent to evil] \nYou know what's going on here, don't you?",
            "FLOWEY: You just wanted to see me suffer.",
            "[FLOWEY circled you in a ring of bullets]",
            "FLOWEY: \n\n                     D i e. \n\n"
        };
        String[] floweytroll = {"FLOWEY: You idiot...",
            "FLOWEY: In this world, it's kill or BE killed.",
            "FLOWEY: Why would ANYONE pass up an opportunity like this?!",
            "[FLOWEY circled you in a ring of bullets]",
            "FLOWEY: \n\n                     D i e. \n\n"
        };
        int trollflowey = 0;
        for (String fpellets : fpellet) {
            opt = JOptionPane.showOptionDialog(null, fpellets + player.getHealth(), title, 0, -1, redhart, move, move[0]);
            if (opt == 0) {
                player.removeHP(19);
                for (String floweytrolls : floweytroll) {
                    opt = JOptionPane.showOptionDialog(null, floweytrolls + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                    if (opt == JOptionPane.CLOSED_OPTION) {
                    }
                }
                break;
            } else if (opt != 0 || opt == JOptionPane.CANCEL_OPTION) {
                trollflowey++;
            }
            if (trollflowey == 3) {
                for (String trolledfloweys : trolledflowey) {
                    opt = JOptionPane.showOptionDialog(null, trolledfloweys + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                    if (opt == JOptionPane.CLOSED_OPTION) {
                    }
                }
            }
        }
        String[] toriel = {"[FLOWEY begins to laugh as the pellets close in on you.]",
            "[But as you touch the pellets, FLOWEY gets knocked away by a stray fireball.\nSomeone came in to your rescue.]",
            "TORIEL: What a terrible creature, torturing such a poor, innocent youth...",
            "TORIEL: Ah, do not be afraid, my child.",
            "TORIEL: I am TORIEL, caretaker of the " + newlocation + ".",
            "TORIEL: I pass through this place every day to see if anyone has fallen down.",
            "TORIEL: You are the first human to come down here in a long time.",
            "TORIEL: Come! \nI will guide you through the catacombs."
        };
        for (String toriels : toriel) {
            opt = JOptionPane.showOptionDialog(null, toriels + player.getHealth(), title, 0, -1, redhart, move, move[0]);
            if (opt == JOptionPane.CLOSED_OPTION) {
            }
        }
    }

    //All methods below are the rooms.
    void r001() throws IOException {
        player.SAVE();
        System.out.println(player.room_event_checker());
        ruinlocation = new Ruins();
        int opt;
        Object move[] = {"Go Up"};
        if (player.room_event_checker() == 0) {
            opt = JOptionPane.showOptionDialog(null, "TORIEL: This way.", title, 0, -1, null, key, key[0]);
            if (opt == JOptionPane.CLOSED_OPTION) {
            }
            player.event_finish();
        }
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a pad of grass below your feet, ahead of you is a gate.", title, 0, -1, null, move, move[0]);
            if (opt == 0) {
                if (player.room_event_checker() > 0) {
                    ruinlocation.r002();
                    break;
                } else {
                    ruinlocation.r002();
                    break;
                }
            } else if (opt == JOptionPane.CLOSED_OPTION) {
                ruinlocation.Menu();
            }
        }
    }

    void r002() throws IOException {
        if (player.room_event_checker() == 1) {
            player.event_finish();
        }
        player.changeLocation("Ruins - Home");
        ruinlocation = new Ruins();
        int opt;
        Object[] move = {"Go Up with Toriel", "Go to the SAVE Point", "Go Back Down"};
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You are what it seems to be an outside room, ahead of you contains a staircase with an entrance at the end;"
                    + "\nAt your back, you see a gate, and you see a SAVE Point near.", title, 0, -1, null, move, move[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r003();
                    break OUTER;
                }
                case 1 -> {
                    JOptionPane.showOptionDialog(null, "(The shadow of the ruins looms above, \nfilling you with determination.)", title, 0, -1, null, key, key[0]);
                    if (player.HP < player.maxHP) {
                        JOptionPane.showOptionDialog(null, "(HP fully restored.)", title, 0, -1, null, key, key[0]);
                        player.recoverHP();
                    }
                    ruinlocation.SAVING();
                }
                case 2 -> {
                    ruinlocation.r001();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r003() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] tdialogue = {"[You proceed to the room ahead of you.]",
            "TORIEL: Welcome to your new home, innocent one.",
            "TORIEL: Allow me to educate you in the operation of the RUINS.",
            "[TORIEL walks across the second and fourth row of the four-row stumps you see. \nTORIEL flips a switch on the wall to the left of the final stump the goat walks upon.]",
            "TORIEL: The RUINS are full of puzzles.",
            "TORIEL: Ancient fusions between diversions and doorkeys.",
            "TORIEL: One must solve them to move from room to room.",
            "TORIEL: Please adjust yourself to the sight of them."
        };
        if (player.room_event_checker() == 2) {
            for (String tdialogues : tdialogue) {
                opt = JOptionPane.showOptionDialog(null, tdialogues, title, 0, -1, null, key, key[0]);
                if (opt == JOptionPane.CLOSED_OPTION) {

                }
            }
            player.event_finish();
        }
        Object move[] = {"Go Up", "Press the buttons", "Pull the lever", "Read the sign", "Go Down"};
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see some unpressed buttons, a pulled lever, a sign, and rooms up ahead and back down.", title, 0, -1, null, move, move[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r004();
                    break OUTER;
                }
                case 1 ->
                    JOptionPane.showOptionDialog(null, "You pressed the buttons. \nNothing happened. They go back to their original state.", title, 0, -1, null, key, key[0]);
                case 2 ->
                    JOptionPane.showOptionDialog(null, "You try to pull the lever. \nIt is already pulled by TORIEL.", title, 0, -1, null, key, key[0]);
                case 3 -> {
                    String[] sign = {"Only the fearless may proceed.", "Brave ones, foolish ones.", "Both not walk the middle road."};
                    for (String signs : sign) {
                        opt = JOptionPane.showOptionDialog(null, signs, title, 0, -1, null, key, key[0]);
                        if (opt == JOptionPane.CLOSED_OPTION) {
                        }
                    }
                }
                case 4 -> {
                    ruinlocation.r002(true);
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r002(boolean r002done) throws IOException { //Overloading : Static from r002()
        player.changeLocation("Ruins - Home");
        ruinlocation = new Ruins();
        int opt;
        Object[] move = {"Go Up", "Go to the SAVE Point", "Go Back Down"};
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You are what it seems to be an outside room, ahead of you contains a staircase with an entrance at the end;"
                    + "\nAt your back, you see a gate, and you see a SAVE Point near.", title, 0, -1, null, move, move[0]);
            switch (opt) {
                case 0 -> {
                    if (r002done == true) {
                        ruinlocation.r003();
                        break OUTER;
                    } else {
                        ruinlocation.r003();
                        break OUTER;
                    }
                }
                case 1 -> {
                    if (player.ruin_geno) {
                        JOptionPane.showOptionDialog(null, "Determination.", title, 0, -1, null, key, key[0]);
                    } else {
                    JOptionPane.showOptionDialog(null, "(The shadow of the ruins looms above, \nfilling you with determination.)", title, 0, -1, null, key, key[0]);
                    }
                    if (player.HP < player.maxHP) {
                        JOptionPane.showOptionDialog(null, "(HP fully restored.)", title, 0, -1, null, key, key[0]);
                        player.recoverHP();
                    }
                    ruinlocation.SAVING();
                }
                case 2 -> {
                    ruinlocation.r001();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r004() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] dialogue = {"You entered the next room.",
            "TORIEL: To make progress here, you will need to trigger several switches.",
            "TORIEL: Do not worry, I have labelled the ones that you need to flip."
        };
        String[] move1 = {"Read the sign", "Flip the switch", "Go left"};
        String[] move2 = {"Go back down", "Read the sign", "Flip the switch", "Go left"};
        if (player.room_event_checker() == 3) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            boolean switch1 = false;
            OUTER:
            while (true) {
                opt = JOptionPane.showOptionDialog(null, "You see a sign, a switch ahead on you, and more to the left.", title, 0, -1, null, move1, move1[0]);
                switch (opt) {
                    case 0 ->
                        JOptionPane.showOptionDialog(null, "Stay on the path.", title, 0, -1, null, key, key[0]);
                    case 1 -> {
                        if (switch1 == false) {
                            JOptionPane.showOptionDialog(null, "You flipped the switch.", title, 0, -1, null, key, key[0]);
                            switch1 = true;
                        } else {
                            JOptionPane.showOptionDialog(null, "The switch is already flipped.", title, 0, -1, null, key, key[0]);
                        }
                    }
                    case 2 -> {
                        if (switch1 == false) {
                            JOptionPane.showOptionDialog(null, "TORIEL: The first switch is on the wall.", title, 0, -1, null, key, key[0]);
                        } else {
                            player.event_finish();
                            ruinlocation.r005();
                            break OUTER;
                        }
                    }
                    default ->
                        ruinlocation.Menu();
                }
            }
        } else {
            OUTER:
            while (true) {
                opt = JOptionPane.showOptionDialog(null, "You see a sign, a switch ahead on you, and more to the left.", title, 0, -1, null, move2, move2[0]);
                switch (opt) {
                    case 0 -> {
                        ruinlocation.r003();
                        break OUTER;
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, "Stay on the path.", title, 0, -1, null, key, key[0]);
                    case 2 -> {
                        JOptionPane.showOptionDialog(null, "The switch is already flipped.", title, 0, -1, null, key, key[0]);
                    }
                    case 3 -> {
                        ruinlocation.r005();
                        break OUTER;
                    }
                    default ->
                        ruinlocation.Menu();
                }
            }
        }
    }

    void r005() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] move2 = {"Pull the left switch", "Pull the right switch", "Go talk to TORIEL"};
        String[] move3 = {"Go right", "Pull the left switch", "Pull the right switch", "Go to the next room"};
        String[] dialogue = {"Splendid! \nI am proud of you, little one.",
            "Let us move to the next room.",
            "[TORIEL moved on to the next room.]"
        };
        if (player.room_event_checker() == 4) {
            OUTER:
            while (true) {
                opt = JOptionPane.showOptionDialog(null, "You see two switches on either side, the left switch is marked with arrows, and TORIEL is blocking the next room.", title, 0, -1, null, move2, move2[0]);
                switch (opt) {
                    case 0 -> {
                        for (String dialogues : dialogue) {
                            JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
                        }
                        break OUTER;
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, "TORIEL: No no no! \nYou want to press the other switch.\nI even labelled it for you...", title, 0, -1, null, key, key[0]);
                    case 2 ->
                        JOptionPane.showOptionDialog(null, "TOREIL: Go on, press the switch on the left.", title, 0, -1, null, key, key[0]);
                    default -> {
                        ruinlocation.Menu();
                    }
                }
            }
            player.event_finish();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see two switches on either side, the left switch is marked with arrows, and the next room is on the left.", title, 0, -1, null, move3, move3[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r004();
                    break OUTER;
                }
                case 1 ->
                    JOptionPane.showOptionDialog(null, "The switch is aleady pulled.", title, 0, -1, null, key, key[0]);
                case 2 ->
                    JOptionPane.showOptionDialog(null, "This switch does not even work.", title, 0, -1, null, key, key[0]);
                case 3 -> {
                    ruinlocation.r006();
                    break OUTER;
                }
                default -> {
                    ruinlocation.Menu();
                }
            }
        }
    }

    void r006() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] dialogue = {"TORIEL: As a human living in the UNDERGROUND, monsters may attack you.",
            "TORIEL: You will need to be prepared for this situation.",
            "TORIEL: However, worry not! \nThe process is simple.",
            "TORIEL: When you encounter a monster, you will enter a FIGHT.",
            "TORIEL: While you are in a FIGHT, strike up a friendly conversation.",
            "TORIEL: Stall for time. \nI will come to resolve the conflict.",
            "TORIEL: Practice talking to the dummy."
        };
        String[] move1 = {"Go back left", "Talk to the dummy", "Talk to TORIEL"};
        String[] move2 = {"Go back left", "Go north"};
        if (player.room_event_checker() == 5) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            int toriel = 0;
            OUTER:
            while (true) {
                opt = JOptionPane.showOptionDialog(null, "You see a dummy and TORIEL in the room.", title, 0, -1, null, move1, move1[0]);
                switch (opt) {
                    case 0 ->
                        JOptionPane.showOptionDialog(null, dialogue[6], title, 0, -1, null, key, key[0]);
                    case 1 -> {
                        ruinlocation.dummyfight();
                        break OUTER;
                    }
                    case 2 -> {
                        if (toriel == 1) {
                            JOptionPane.showOptionDialog(null, "TORIEL: You can say anything to the dummy. They will not be bothered.", title, 0, -1, null, key, key[0]);
                        } else {
                            JOptionPane.showOptionDialog(null, dialogue[6], title, 0, -1, null, key, key[0]);
                            toriel++;
                        }
                    }
                    default -> {
                        ruinlocation.Menu();
                    }
                }
            }
            player.event_finish();
        }
        OUTER_1:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a room at your left, and another north.", title, 0, -1, null, move2, move2[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r005();
                    break OUTER_1;
                }
                case 1 -> {
                    ruinlocation.r007();
                    break OUTER_1;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r007() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        int battle = 0;
        String[] dialogue = {"TORIEL: There is another puzzle in this room...", "TORIEL: I wonder if you can solve it?",
            "[You follow TORIEL, but suddenly...]",
            "[You follow TORIEL into the front of a large grid of spike blocks.]",
            "TORIEL: This is the puzzle, but... \nHere, take my hand for a moment.",
            "[You join hands with TORIEL, and TORIEL guides you through the puzzle.]",
            "TORIEL: Puzzles seem a little too dangerous for now."
        };
        String[] movement = {"Go back down", "Go left"};
        if (player.room_event_checker() == 6) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
                battle++;
                if (battle == 3) {
                    ruinlocation.froggitoriel();
                }
            }
            player.event_finish();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a room down, the solved spike puzzle, and another room left.", title, 0, -1, null, movement, movement[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r006();
                    break OUTER;
                }
                case 1 -> {
                    ruinlocation.r008();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r008() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        int walk = 0;
        String dialogue[] = {"TORIEL: You have done excellently thus far, my child.",
            "TORIEL: However... I have a difficult request to ask of you.",
            "TORIEL: ...",
            "TORIEL: I would like you to walk to the end of the room by yourself.",
            "TORIEL: Forgive me for this."
        };
        String walking[] = {"Walk"};
        String[] dialogue2 = {"[You saw TORIEL.]",
            "TORIEL: Greetings, my child. \nDo not worry, I did not leave you.",
            "TORIEL: I was merely behind this pillar the whole time.",
            "TORIEL: Thank you for trusting me.",
            "TORIEL: However, there was an important reason for this exercise.",
            "TORIEL: ... to test your independence.",
            "TORIEL: I must attend to some business, and you must stay alone for a while.",
            "TORIEL: Please remain here. \nIt's dangerous to explore by yourself.",
            "TORIEL: I have an idea. \nI will give you a CELL PHONE.",
            "TORIEL: If you have a need for anything, just call.",
            "TORIEL: Be good, alright?",};
        String[] movement = {"Go left", "Go right"};
        if (player.room_event_checker() == 7) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            while (true) {
                opt = JOptionPane.showOptionDialog(null, "You see a long hallway. TORIEL moved faster than you forward.", title, 0, -1, null, walking, walking[0]);
                if (opt == 0) {
                    walk++;
                } else {
                    ruinlocation.Menu();
                }
                if (walk == 12) {
                    break;
                }
            }
            for (String dialogues2 : dialogue2) {
                JOptionPane.showOptionDialog(null, dialogues2, title, 0, -1, null, key, key[0]);
            }
            player.event_finish();
            player.obtcp();
            player.addcontact();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You are in a long hallway. There is a room left and right.", title, 0, -1, null, movement, movement[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r007();
                    break OUTER;
                }
                case 1 -> {
                    ruinlocation.r009();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    //All rooms at this point will be updated at a later date.
    void r009() throws IOException {
        player.changeLocation("Ruins - Leaf Pile");
        ruinlocation = new Ruins();
        int opt;
        String[] dialogue = {"[Ring...]",
            "TORIEL: Hello? \nThis is TORIEL.",
            "TORIEL: You have not left the room, have you?",
            "TORIEL: There are a few puzzles ahead that I have yet to explain.",
            "TORIEL: It would be dangerous to try to solve them yourself.",
            "TORIEL: Be good, alright?", "[Click...]"
        };
        String[] froggit = {"Ribbit, ribbit. \n(Excuse me, human.)",
            "(I have some advice for you about battling the monsters.)",
            "(If you ACT a certain way or FIGHT until you almost defeat them...)",
            "(They might not want to battle you anymore.)",
            "(If a monster does not want to fight you, please...)",
            "(Use some MERCY, human...) \nRibbit."
        };
        String[] movement = {"Go left", "Go right", "Go North", "Talk to the froggit.", "Go to the SAVE Point."};
        if (player.room_event_checker() == 8) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            player.event_finish();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see rooms at your left, north and right, a froggit and a SAVE Point.", title, 0, -1, null, movement, movement[1]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r008();
                    break OUTER;
                }
                case 1 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    ruinlocation.r011();
                    break OUTER;
                }
                case 2 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    ruinlocation.r010();
                    break OUTER;
                }
                case 3 -> {
                    for (String froggits : froggit) {
                        JOptionPane.showOptionDialog(null, froggits, title, 0, -1, null, key, key[0]);
                    }
                    ruinlocation.encounter((int) (Math.random() * 2));
                }
                case 4 -> {
                    if (player.ruin_geno) {
                        JOptionPane.showOptionDialog(null, "Determination.", title, 0, -1, null, key, key[0]);
                    } else {
                    JOptionPane.showOptionDialog(null, "(Playfully crinkling through the leaves fills you with determination.)", title, 0, -1, null, key, key[0]);
                    }
                    if (player.HP < player.maxHP) {
                        JOptionPane.showOptionDialog(null, "(HP fully restored.)", title, 0, -1, null, key, key[0]);
                        player.recoverHP();
                    }
                    ruinlocation.SAVING();
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r010() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] movement = {"Go Down", "Interact with the table"};
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a room down and a table here.", title, 0, -1, null, movement, movement[1]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r009();
                    break OUTER;
                }
                case 1 -> {
                    opt = JOptionPane.showConfirmDialog(null, "It says 'take one' \nTake a piece of candy?", title, JOptionPane.YES_NO_OPTION);
                    switch (opt) {
                        case 0 -> {
                            if (player.ruincandy == 4) {
                                JOptionPane.showOptionDialog(null, "You took too much that you cant take anymore.", title, 0, -1, null, key, key[0]);
                            } else {
                                Food MnstrCndy = new Food("Monster Candy", 10);
                                ruinlocation.AddItem(MnstrCndy.getFood());
                                player.getMCandy();
                                switch (player.ruincandy) {
                                    case 1 ->
                                        JOptionPane.showOptionDialog(null, "You took a piece of candy, \nOpen the menu by pressing the \"X\" button", title, 0, -1, null, key, key[0]);
                                    case 2 ->
                                        JOptionPane.showOptionDialog(null, "You took more candy. \nHow disgusting...", title, 0, -1, null, key, key[0]);
                                    case 3 ->
                                        JOptionPane.showOptionDialog(null, "You take another piece. \nYou feel like the scum of the earth...", title, 0, -1, null, key, key[0]);
                                    case 4 ->
                                        JOptionPane.showOptionDialog(null, "You took too much too fast. \nThe candy spills onto the floor.", title, 0, -1, null, key, key[0]);
                                }
                            }
                        }
                    }
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r011() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] movement = {"Go left", "Go right"};
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a room left and right, a crack in the floor is in the middle of it.", title, 0, -1, null, movement, movement[1]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    ruinlocation.r009();
                    break OUTER;
                }
                case 1 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    JOptionPane.showOptionDialog(null, "You fell from the crack, but managed to get up again anyway.", title, 0, -1, null, key, key[0]);
                    ruinlocation.r012();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r012() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] movement = {"Go left", "Check the sign", "Go right"}, pref = {"Cinnamon", "Bscotch"};
        String[] dialogue = {"[Ring...]", "TORIEL: Hello? \nThis is TORIEL.",
            "TORIEL: For no reason in particular...\nWhich do you prefer?",};
        String[] dialogue2 = {"TORIEL: Oh, I see.\nThank you very much!",
            "[Click...]"
        };

        if (player.room_event_checker() == 9) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            opt = JOptionPane.showOptionDialog(null, "TORIEL: Cinnamon or butterscotch?", title, 0, -1, null, pref, pref[0]);
            player.preference(opt + 1);
            for (String dialogues : dialogue2) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            String[] dialogue3 = {"[Ring...]", "TORIEL: Hello?\nThis is TORIEL.",
                "TORIEL: You do not DISLIKE " + pref[opt] + ", do you?",
                "I know your preference is, but...",
                "Would you turn up your nose if you found it on your plate?",
                "Right, right, I understand.",
                "Thank you for being patient, by the way.",
                "[Click...]"
            };
            for (String dialogues : dialogue3) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            player.event_finish();
        }

        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see two rooms and a sign. There is a rock as well, needs to be pushed to move.", title, 0, -1, null, movement, movement[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    ruinlocation.r011();
                    break OUTER;
                }
                case 1 -> {
                    JOptionPane.showOptionDialog(null, "Three out of four grey rocks recommend you to push them.", title, 0, -1, null, key, key[0]);
                    ruinlocation.encounter((int) (Math.random() * 2));
                }
                case 2 -> {
                    ruinlocation.encounter((int) (Math.random() * 2));
                    ruinlocation.r027();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r027() throws IOException {
        ruinlocation = new Ruins();
        player.changeLocation("Ruins - The Home");
        int opt;
        String[] movement = {"Go back", "Go to the SAVE Point", "Go Inside the Home"};
        String[] dialogue = {"You soon come in to a home.", "TORIEL: Oh dear, that took longer than I thought it would.",
            "TORIEL: How did you get here, my child? \nAre you hurt?",
            "TORIEL: There, there. I will heal you.",
            "TORIEL: I should not have left you alone for so long.",
            "TORIEL: It was irresponsible to try to surprise you like this.", "TORIEL: Err...",
            "TORIEL: Well, I suppose I cannot hide it any longer.",
            "TORIEL: Come, small one!"
        };
        if (player.room_event_checker() == 10) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            player.event_finish();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You see a home and a SAVE Point.", title, 0, -1, null, movement, movement[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r012();
                    break OUTER;
                }
                case 1 -> {
                    if (player.ruin_geno) {
                        JOptionPane.showOptionDialog(null, "Determination.", title, 0, -1, null, key, key[0]);
                    } else {
                    JOptionPane.showOptionDialog(null, "(Seeing such a cute, tidy house in the RUINS gives you determination.)", title, 0, -1, null, key, key[0]);
                    }
                    if (player.HP < player.maxHP) {
                        JOptionPane.showOptionDialog(null, "(HP fully restored.)", title, 0, -1, null, key, key[0]);
                        player.recoverHP();
                    }
                    ruinlocation.SAVING();
                }
                case 2 -> {
                    ruinlocation.r035();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r035() throws IOException {
        ruinlocation = new Ruins();
        int opt;
        String[] movement = {"Go back", "Go ahead"};
        String[] dialogue = {"You enter the home, TORIEL shows you your bedroom and you slept on it. \nYou wake up, finding a butterscotch-cinnamon pie beside you, on which you eat it. \nHowever, you want to go outside the ruins. \n"
            + "TORIEL tried her best to prevent you, but you insist. This, however, led to this moment.",
            "TORIEL: You want to leave this badly?", "TORIEL: Hmph.", "TORIEL: You are just like the others.",
            "TORIEL: There is only one solution to this.", "TORIEL: Prove yourself...",
            "TORIEL: Prove to me you are strong enough to survive."};
        if (player.room_event_checker() == 11) {
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
            encounter.encounter(18);
            player.event_finish();
        }
        OUTER:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You can go ahead to end this.", title, 0, -1, null, movement, movement[0]);
            switch (opt) {
                case 0 -> {
                    ruinlocation.r027();
                    break OUTER;
                }
                case 1 -> {
                    ruinlocation.r036();
                    break OUTER;
                }
                default ->
                    ruinlocation.Menu();
            }
        }
    }

    void r036() {
        String[] dialogue = {"NUTDEALER", "Thank you for playing."};
        for (String dialogues : dialogue) {
            JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
        }
    }

    //The two methods below are the battle interfaces.
    void froggitoriel() {
        int opt;
        boolean killed = false;
        boolean won = false;
        String[] act = {"Check", "Compliment", "Threaten"};
        Enemy froggit = new Enemy();
        froggit.changeName("Froggit");
        froggit.setAT(4);
        froggit.setDF(1);
        froggit.setEnemyInfo("Life is difficult for this enemy.");
        froggit.setMHP(20);
        OUTER:
        OUTER_1:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, froggit.name + " attacks you!" + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
            if (opt == 0) {
                opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                switch (opt) {
                    case 0 -> {
                        JOptionPane.showOptionDialog(null, "The froggit is hit." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        froggit.removeHP(21);
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    default -> {
                        continue;
                    }
                }
            } else if (opt == 1) {
                opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                switch (opt) {
                    case 0 ->
                        JOptionPane.showOptionDialog(null, froggit.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    case 1 ->
                        JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was flattered anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    case 2 ->
                        JOptionPane.showOptionDialog(null, "Froggit didn't understand what you said, but was scared anyway." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    default -> {
                        continue;
                    }
                }
            } else if (opt == 2) {
                JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                continue;
            } else if (opt == 3) {
                opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                switch (opt) {
                    case 0 -> {
                    }
                    case 1 -> {
                        JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        won = true;
                        break OUTER_1;
                    }
                    default -> {
                        continue;
                    }
                }
            }
            if (froggit.HP <= 0) {
                killed = true;
                break;
            }
            JOptionPane.showOptionDialog(null, "[TORIEL came in, making the Froggit go away.]" + player.getHealth(), title, 0, -1, redhart, key, key[0]);
            won = true;
            break;
        }
        if (won) {
            JOptionPane.showOptionDialog(null, "You won! \nYou get 0 EXP and 0 gold." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
        }
        if (killed) {
            player.giveEXP(10);
            player.earnGold(20);
            player.addkill();
            JOptionPane.showOptionDialog(null, "You won! \nYou get 10 EXP and 20 gold. \n" + player.getlevelup() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
        }
    }

    void dummyfight() {
        int opt;
        boolean won = false;
        boolean ddied = false;
        boolean dtired = false;
        boolean dtalked = false;
        boolean dfled = false;
        String[] act = {"Check", "Talk"};
        dummy.changeName("Dummy");
        dummy.setAT(0);
        dummy.setDF(0);
        dummy.setEnemyInfo("A cotton heart and a button eye\nYou're the apple of my eye");
        dummy.setMHP(15);
        int dummyangry = 0;
        OUTER:
        OUTER_1:
        while (true) {
            opt = JOptionPane.showOptionDialog(null, "You encountered the " + dummy.name + "." + player.getHealth(), title, 0, -1, redhart, turn, turn[0]);
            if (opt == 0) {
                opt = JOptionPane.showOptionDialog(null, "You chose to FIGHT." + player.getHealth(), title, 0, -1, redhart, fight, fight[0]);
                switch (opt) {
                    case 0 -> {
                        JOptionPane.showOptionDialog(null, "The dummy is hit." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        dummy.removeHP(15);
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, "Your attack missed." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    default -> {
                        continue;
                    }
                }
            } else if (opt == 1) {
                opt = JOptionPane.showOptionDialog(null, "You chose to Act." + player.getHealth(), title, 0, -1, redhart, act, act[0]);
                switch (opt) {
                    case 0 ->
                        JOptionPane.showOptionDialog(null, dummy.getInfo() + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                    case 1 -> {
                        JOptionPane.showOptionDialog(null, "You talk to the DUMMY. ...\nIt doesn't seem much for conversation.\nTORIEL seems happy with you." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        won = true;
                        dtalked = true;
                        break OUTER;
                    }
                    default -> {
                        continue;
                    }
                }
            } else if (opt == 2) {
                JOptionPane.showOptionDialog(null, "You have no items." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                continue;
            } else if (opt == 3) {
                opt = JOptionPane.showOptionDialog(null, "You chose Mercy." + player.getHealth(), title, 0, -1, redhart, mercy, mercy[0]);
                switch (opt) {
                    case 0 -> {
                    }
                    case 1 -> {
                        JOptionPane.showOptionDialog(null, "You fled." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                        won = true;
                        dfled = true;
                        break OUTER_1;
                    }
                    default -> {
                        continue;
                    }
                }
            }
            if (dummy.HP <= 0) {
                won = true;
                ddied = true;
                break;
            }
            if (dummyangry == 7) {
                JOptionPane.showOptionDialog(null, dummy.name + " tires of your aimless shenanigans." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
                dtired = true;
                won = true;
                break;
            } else {
                JOptionPane.showOptionDialog(null, dummy.name + "'s turn. \n '...'" + player.getHealth(), title, 0, -1, redhart, move, move[0]);
                dummyangry++;
            }
        }
        if (won) {
            JOptionPane.showOptionDialog(null, "You won! \nYou get 0 EXP and 0 gold." + player.getHealth(), title, 0, -1, redhart, key, key[0]);
        }
        if (ddied == true) {
            JOptionPane.showOptionDialog(null, "TORIEL: Ahh, the dummies are not for fighting!\nThey are for talking!", title, 0, -1, null, key, key[0]);
            JOptionPane.showOptionDialog(null, "TORIEL: We do not want to hurt anybody, do we..? \nCome now.", title, 0, -1, null, key, key[0]);
        }
        if (dtired == true) {
            JOptionPane.showOptionDialog(null, "TORIEL: ...", title, 0, -1, null, key, key[0]);
            JOptionPane.showOptionDialog(null, "TORIEL: ...", title, 0, -1, null, key, key[0]);
            JOptionPane.showOptionDialog(null, "TORIEL: The next room awaits.", title, 0, -1, null, key, key[0]);
        }
        if (dtalked == true) {
            JOptionPane.showOptionDialog(null, "TORIEL: Ah, very good! \nYou are very good.", title, 0, -1, null, key, key[0]);
        }
        if (dfled == true) {
            String[] dialogue = {"TORIEL: ...", "TORIEL: ...you ran away...", "TORIEL: Truthfully, that was not a poor choice.",
                "TORIEL: It is better to avoid conflict whenever possible.", "TORIEL: That... however, is only a dummy. \nIt cannot harm you.",
                "TORIEL: it is made of cotton. \nIt has no desire for revenge.", "TORIEL: Nevermind. \nStay close to me and I will keep you safe."
            };
            for (String dialogues : dialogue) {
                JOptionPane.showOptionDialog(null, dialogues, title, 0, -1, null, key, key[0]);
            }
        }
    }

    void encounter(int id) throws IOException {
        ruinlocation = new Ruins();
        if (id > 0) {
            if (player.ruin_geno) {
                encounter.encounter(0);
            } else {
            encounter.encounter((int) (Math.random() * 5 + 1));
            }
        }
        if (player.HP <= 0) {
            JOptionPane.showOptionDialog(null, "GAME OVER", title, 0, -1, game_over, key, key[0]);
            JOptionPane.showOptionDialog(null, "GAME OVER\nYou cannot give up just yet...", title, 0, -1, game_over, key, key[0]);
            JOptionPane.showOptionDialog(null, "GAME OVER\n" + player.name + "! \nStay determined...", title, 0, -1, game_over, key, key[0]);
            player.LOAD();
            ruinlocation.SAVEPoint();
        }
    }
}
