/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author Ryzen
 */
public class NutDealer implements Menu { //Superclass, implements the Menu, it is the main engine of the game.

    static ImageIcon redhart = new ImageIcon(NutDealer.class.getResource("test.png")); //Used for battles
    ImageIcon game_over = new ImageIcon(NutDealer.class.getResource("game over.png")); //Game over
    static String name; //Your name
    Weapon weapon = new Weapon("Stick", 0); //Weapon
    Armor armor = new Armor("Worn Bandage", 0); //Armor
    public static Player player = new Player();
    public static Item bag = new Item();
    Battle encounter = new Battle(player, bag);
    int FunValue;

    {
        FunValue = (int) (Math.random() * 100 + 1);
    }//Random events, not yet implemented.
    public static File0 file0;
    public boolean createdSAVE = player.createdSAVE();
    final String title = "NUTDEALER v0.5";
    String[] items;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NutDealer file1 = new NutDealer(file0);
        file1.initialization();
        if (file1.createdSAVE) {
            file1.SAVEMenu();
        } else {
            file1.newSAVE(name);
            Ruins ruins = new Ruins();
            ruins.initialization();
        }
    }

    public void initialization() { //Introduction (Overriding : Dynamic)
        Object[] key = {"Next"};
        String[] story = {"Long ago, two races ruled over the Earth: Humans and Monsters.",
            "One day, a war broke out between the two races.",
            "After a long battle, the humans were victorious.",
            "They sealed the monsters underground with a magic spell.", "Many years later...", "MT. Ebott", "201X",
            "Legends say that those who climb the mountain never return."};
        int opt;
        for (String story1 : story) {
            opt = JOptionPane.showOptionDialog(null, story1, title, 0, -1, null, key, key[0]);
            if (opt == JOptionPane.CLOSED_OPTION) {
                break;
            }
        }
    }

    void SAVEPoint() {
        try {
            Ruins ruins = new Ruins();
            switch (player.location) {
                case "Start" ->
                    ruins.initialization();
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

    void newSAVE(String name) { //Creating a profile
        int choice = JOptionPane.showConfirmDialog(null, "Create a SAVE file?", title, JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            while (createdSAVE = true) {
                name = JOptionPane.showInputDialog(null, "Name the fallen human.", title, -1);
                if ("Flowey".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "I already CHOSE that name. \n" + name, name, -1);
                } else if ("Toriel".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "I think you should think of your own name, my child. \n" + name, name, -1);
                } else if ("Alphys".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "D-don't do that. \n" + name, name, -1);
                } else if ("Asgore".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "You cannot. \n" + name, name, -1);
                } else if ("Asriel".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "... \n" + name, name, -1);
                } else if ("Sans".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "nope. \n" + name, name, -1);
                } else if ("Undyne".equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(null, "Get your OWN name! \n" + name, name, -1);
                } else if ("Gaster".equalsIgnoreCase(name)) {
                    main(null);
                } else {
                    if ("Papyru".equalsIgnoreCase(name)) {
                        choice = JOptionPane.showConfirmDialog(null, "I'LL ALLOW IT!!!! \n" + name, title, JOptionPane.YES_NO_OPTION);
                    } else if ("Murder".equalsIgnoreCase(name) || "Mercy".equalsIgnoreCase(name)) {
                        choice = JOptionPane.showConfirmDialog(null, "That's a little on-the-nose, isn't it...? \n" + name, title, JOptionPane.YES_NO_OPTION);
                    } else if ("Rolaina".equalsIgnoreCase(name)) {
                        choice = JOptionPane.showConfirmDialog(null, "My story. \n" + name, title, JOptionPane.YES_NO_OPTION);
                    } else if ("Austin".equalsIgnoreCase(name)) {
                        choice = JOptionPane.showConfirmDialog(null, "Sige te. \n" + name, title, JOptionPane.YES_NO_OPTION);
                    } else if ("Justen".equalsIgnoreCase(name)) {
                        choice = JOptionPane.showConfirmDialog(null, "Oki. \n" + name, title, JOptionPane.YES_NO_OPTION);
                    } else {
                        choice = JOptionPane.showConfirmDialog(null, "Is this name correct? \n" + name, title, JOptionPane.YES_NO_OPTION);
                    }
                    if (choice == 0) {
                        player.changeName(name);
                        createdSAVE = true;
                        break;
                    } else {
                    }
                }
            }
        } else {
            System.exit(0);
        }
    }

    public NutDealer(File0 file0) {
        NutDealer.file0 = file0;
    }

    @Override
    public void Menu() { // Implements method from Menu 
        String[] options = {"Item", "Stat", "Quit"};
        String[] options2 = {"Item", "Stat", "Cell", "Quit"}, itemopt = {"Info", "Use"};
        String[] back = {"Back"};
        String[] items = bag.getItem();
        if (player.cellphonecheck() == false) {
            OUTER_1:
            while (true) {
                int opt = JOptionPane.showOptionDialog(null, NutDealer.player.getStat(), title, 0, 1, null, options, options[0]);
                switch (opt) {
                    case 0 -> {
                        //String choice = (String) (JOptionPane.showInputDialog(null, "Use an item.", title, -1, null, bag.getItem(), bag.getItem()[0]));
                        opt = JOptionPane.showOptionDialog(null, "What do you want to do?", title, 0, 1, null, itemopt, itemopt[0]);
                        switch (opt) {
                            //case 0 -> ItemInfo(choice);
                        }
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, NutDealer.player.toString() + "\nLocation: " + NutDealer.player.location, title, 0, 1, null, back, back[0]);
                    case 2 ->
                        System.exit(0);
                    default -> {
                        break OUTER_1;
                    }
                }
            }
        } else {
            OUTER:
            while (true) {
                int opt = JOptionPane.showOptionDialog(null, NutDealer.player.getStat(), title, 0, 1, null, options2, options2[0]);
                switch (opt) {
                    case 0 -> {
                        String choice = (String) (JOptionPane.showInputDialog(null, "Use an item.", title, -1, null, items, items[0]));
                        if (choice != null) {
                            opt = JOptionPane.showOptionDialog(null, "What do you want to do?", title, 0, 1, null, itemopt, itemopt[0]);
                            switch (opt) {
                                case 0 ->
                                    ItemInfo(choice);
                                case 1 -> 
                                    UseItem(choice);
                            }
                        }
                    }
                    case 1 ->
                        JOptionPane.showOptionDialog(null, NutDealer.player.toString() + "\nLocation: " + NutDealer.player.location, title, 0, 1, null, back, back[0]);
                    case 2 -> {
                        Object choice = JOptionPane.showInputDialog(null, "Call someone.", title, -1, null, player.getContact(), player.getContact()[0]);
                        if (choice.equals("Toriel")) {
                            player.calltoriel();
                            break OUTER;
                        }
                    }
                    case 3 ->
                        System.exit(0);
                    default -> {
                        break OUTER;
                    }
                }
            }
        }
    }

    @Override
    public void ItemInfo(String item) {
        String[] key = {"..."};
        switch (item) {
            case "Monster Candy" ->
                JOptionPane.showOptionDialog(null, "Heals 10 HP \nHas a distinct, non-licorice flavor.", title, 0, -1, null, key, key[0]);
            case "Croquet Roll" ->
                JOptionPane.showOptionDialog(null, "Heals 15 HP \nFried dough traditionally served with a mallet.", title, 0, -1, null, key, key[0]);
            case "Stick" ->
                JOptionPane.showOptionDialog(null, "Weapon AT 0 \nIts bark is worse than its bite.", title, 0, -1, null, key, key[0]);
            case "Bandage" ->
                JOptionPane.showOptionDialog(null, "Heals 10 HP \nIt has already been used several times.", title, 0, -1, null, key, key[0]);
        }
    }

    @Override
    public void AddItem(String item) {
        bag.addItem(item);
    }

    @Override
    public void UseItem(String item) {
        Food MnstrCndy = new Food("Monster Candy", 10);
        switch (item) {
            case "Monster Candy" -> {
                player.addHP(MnstrCndy.attribute);
                bag.useItem(item);
            }
        }
    }

    @Override
    public void SAVEMenu() {
        try {
            player.LOAD();
        } catch (IOException ex) {
            System.exit(0);
        }
        String[] choice = {"Continue", "Reset"};
        int opt = JOptionPane.showOptionDialog(null, player.getMenu(), title, 0, -1, null, choice, choice[0]);
        switch (opt) {
            case 0 ->
                SAVEPoint();
            default ->
                System.exit(0);
        }
    }

    @Override
    public void SAVING() {
        try {
            String[] choice = {"Save", "Return"};
            int opt = JOptionPane.showOptionDialog(null, player.SAVING(), title, 0, -1, null, choice, choice[0]);
            switch (opt) {
                case 0 -> {
                    player.SAVE();
                    String[] back = {"File saved."};
                    JOptionPane.showOptionDialog(null, player.SAVING(), title, 0, -1, null, back, back[0]);
                }
            }
        } catch (IOException ex) {
            System.err.println("mali daw tulog na ko");
        }
    }
}

interface Menu extends SAVING { // Interface

    void Menu();

    void SAVEMenu();

    void ItemInfo(String item);

    void UseItem(String item);

    void AddItem(String item);
}

interface SAVING {

    void SAVING();
}

/*NUTDEALER CPatch: 0.5.0
 *0.1.0 - Started project, basic concepts inputted
 *0.1.1 - RUINS part started
 *0.2.1 - Fixed the entire code so there will be a different files for file0 (enemy + player) and Battles (temporary for now)
 *0.3.1 - Implemented the different files in the main file, successful Menu and Stat system for the most important parts.
 *0.3.2 - Created different rooms, two successful battle encounter (scripted), until r007.
 *0.3.3 - Initialized Menu so it will have Stat and Item (Bag) interface. rooms until r009.
 *0.3.4 - Made the CELLPHONE, now you can talk with TORIEL.
 *0.3.5 | 4/17 - Making the Enemies
   *0.3.5.1 | 4/17 - Froggit
   *0.3.5.2 | 4/18 - Whimsun
 *0.4.0 | 4/30 - Made the SAVE Function, now you can SAVE!
   *0.4.0.1 | 5/1 - SAVE on SAVE Points
 *0.5.0 | 5/15 - Until Toriel's fight, still incomplete but playable at least.
   *0.5.0.1 | 5/15 - Fixed some bugs
 */
