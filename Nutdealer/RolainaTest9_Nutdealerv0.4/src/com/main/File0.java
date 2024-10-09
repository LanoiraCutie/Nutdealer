/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author Ryzen
 */
class Weapon extends Item { //extends, all weapon

    int attribute;

    public Weapon(String newname, int attribute) {
        this.attribute = attribute;
        name = newname;
    }

    @Override
    public String toString() {
        return "Weapon: '" + name + "' (" + attribute + ")";
    }

    public String getWeapon() {
        return name;
    }
}

class Armor extends Item { //extends, all armor

    int attribute;

    public Armor(String newname, int attribute) {
        name = newname;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Armor: '" + name + "' (" + attribute + ")";
    }

    public String getWeapon() {
        return name;
    }
}

class Food extends Item {//extends, all the food (heal items)

    int attribute;

    public Food(String newname, int attribute) {
        name = newname;
        this.attribute = attribute;
    }

    public String getFood() {
        return name;
    }
}

class Item { //The item (bag) is stored here.
    //private Object[] item = new Object[8];

    String name;
    public String[] item = {null, null, null, null, null, null, null, null};
    public int size = 0;

    Item() {
        //this.item = new String[8];
    }

    public void addItem(String newitem) {
        if (size == 8) {

        } else {
            item[size] = newitem;
            size++;
        }
    }

    void useItem(String use) {
        int index = indexOf(use);
        if (index >= 0) {
            for (int i = 0; i < size; i++) {
                item[index + i] = item[index + 1 + i];
            }
        }
    }

    private int indexOf(String use) {
        for (int i = 0; i < size; i++) {
            if (item[i].equals(use)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getItem() {
        return item;
    }
}

class Player extends File0 { // Extends, the Player info is here

    String path = "File0.txt";
    File file0 = new File(path);
    String location = "";
    int EXP = 0;
    int LV = 1;
    int EXPleft = 10;
    Weapon weapon = new Weapon("Stick", 0); //Weapon
    Armor armor = new Armor("Worn Bandage", 0); //Armor
    int gold = 0;
    int kills = 0;
    private int room_event = 0;
    private boolean cellphone = false;
    private int cell_cont = 1;
    public int ruincandy = 0, ruinkills = 0, pref = 0; //Ruins counters
    public boolean ruin_geno = false; //Ruins - Genocide
    private String[] contacts = new String[cell_cont];
    private String[] torielphone = {"Say Hello", "About Yourself", "Call her 'Mom'", "Flirt"};
    private String[] dia = {"..."};
    private int tsh = 1, tf = 1, tchm = 0, HPmod = 0;

    public Player() {
        calculation();
        HP = maxHP;
    }

    public boolean createdSAVE() {
        return file0.exists();
    }

    public void SAVE() throws IOException {
        try {
            if (!file0.exists()) {
                file0.createNewFile();
            }
            write = new BufferedWriter(new FileWriter(path));
            write.write(name);//0
            write.newLine();
            write.write(String.valueOf(LV));//1
            write.newLine();
            write.write(String.valueOf(maxHP));//2
            write.newLine();
            write.write(String.valueOf(AT));//3
            write.newLine();
            write.write(String.valueOf(weapon.attribute));//4
            write.newLine();
            write.write(String.valueOf(DF));//5
            write.newLine();
            write.write(String.valueOf(armor.attribute));//6
            write.newLine();
            write.write(String.valueOf(EXP));//7
            write.newLine();
            write.write(String.valueOf(EXPleft));//8
            write.newLine();
            write.write(String.valueOf(gold));//9
            write.newLine();
            write.write(String.valueOf(kills));//10
            write.newLine();
            write.write(String.valueOf(room_event));//11
            write.newLine();
            if (cellphone) { //12
                write.write(String.valueOf(1));
            } else {
                write.write(String.valueOf(0));
            } //12
            write.newLine();
            write.write(String.valueOf(cell_cont)); //13
            write.newLine();
            write.write(location);
            write.newLine(); //14
            write.write(String.valueOf(ruincandy)); //15
            write.newLine();
            write.write(String.valueOf(ruinkills)); //16
            write.newLine();
            if (ruin_geno) { //13
                write.write(String.valueOf(1));
            } else {
                write.write(String.valueOf(0));
            } //17
            write.newLine();
            write.write(String.valueOf(pref)); //18
            write.newLine();
        } finally {
            write.close();
        }
    }

    public void LOAD() throws FileNotFoundException, IOException {
        read = new BufferedReader(new FileReader(path));
        name = read.readLine();
        LV = LOADValue(1);
        calculation();
        HP = maxHP;
        EXP = LOADValue(7);
        EXPleft = LOADValue(8);
        gold = LOADValue(9);
        kills = LOADValue(10);
        room_event = LOADValue(11);
        int celll = LOADValue(12);
        if (celll == 1) {
            cellphone = true;
            addcontact();
        }
        cell_cont = LOADValue(13);
        location = LOADString(14);
        ruincandy = LOADValue(15);
        ruinkills = LOADValue(16);
        int rgeno = LOADValue(17);
        if (rgeno == 1) {
            ruin_geno = true;
        }
        pref = LOADValue(18);
    }

    public String LOADString(int value) throws FileNotFoundException, IOException {
        read = new BufferedReader(new FileReader(path));
        for (int i = 0; i < value; i++) {
            read.readLine();
        }
        return read.readLine();
    }

    public int LOADValue(int value) throws FileNotFoundException, IOException {
        try {
            read = new BufferedReader(new FileReader(path));

            for (int i = 0; i < value; i++) {
                read.readLine();
            }
        } catch (Exception e) {
            System.err.println("Detected empty File0. Try removing it before launching the game again.");
            //System.exit(0);
        }
        return Integer.parseInt(read.readLine());
    }

    public int damage() {
        return AT + weapon.attribute;
    }

    public int HPmod() {
        if (maxHP == 20) {
            return HPmod;
        } else {
            HPmod = (int) (Math.min((maxHP / 10) - 2, 8));
            return HPmod;
        }
    }

    public int cell_cont() {
        return cell_cont;
    }

    public void addcontact() {
        if (cell_cont == 1) {
            contacts[cell_cont - 1] = "Toriel";
        }
        cell_cont++;
    }

    public void calltoriel() {
        String[] tsh1 = {"This is TORIEL.", "You only wanted to say hello...? Well then.", "'Hello!'", "I hope that suffices. Hee hee."};
        String[] tsh2 = {"This is TORIEL.", "You want to say hello again?", "'Salutations!'", "Is that enough?"};
        String[] tsh3 = {"This is TORIEL.", "Are you bored? I should have given a book to you.", "My apologies.", "Why not use your imagination to divert yourself?", "Pretend you are... A monarch!", "Rule over the leaf pile with a fist of iron.", "Can you do that for me?"};
        String[] tsh4 = {"This is TORIEL.", "Hello, my child.", "Sorry, I do not have much to say.", "It was nice to hear your voice, though."};
        String[] tay = {"This is TORIEL.", "You want to know more about me?", "Well, I am afraid there is not much to say.", "I am just a silly little lady who worries too much!"};
        String[] tchm1 = {"This is TORIEL.", "Huh? Did you just call me... \"Mom\"?", "Well... I suppose...", "Would that make you happy?", "To call me... \"Mother?\"", "Well then, call me whatever you like!"};
        String[] tf1 = {"... huh???", "Oh, heh.. heh... Ha ha ha!", "How adorable... I could pinch your cheek!", "You can certainly find better than an old woman like me."};
        String[] tf2 = {"Oh dear, are you serious...?", "I do not know if this is pathetic, or endearing."};
        String[] tf3 = {"Oh dear, are you serious...?", "And after you said you want to call me \"mother...\"", "You are an... ... \"interesting\" child."};
        int opt;

        opt = JOptionPane.showOptionDialog(null, "Calling TORIEL.", title, 0, -1, null, torielphone, torielphone[0]);
        switch (opt) {
            case 0 -> {
                switch (tsh) {
                    case 1 -> {
                        for (String dial : tsh1) {
                            JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                        }
                    }
                    case 2 -> {
                        for (String dial : tsh2) {
                            JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                        }
                    }
                    case 3 -> {
                        for (String dial : tsh3) {
                            JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                        }
                    }
                    default -> {
                        for (String dial : tsh4) {
                            JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                        }
                    }
                }
                tsh++;
            }
            case 1 -> {
                for (String dial : tay) {
                    JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                }
            }
            case 2 -> {
                for (String dial : tchm1) {
                    JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                }
                tchm++;
            }
            case 3 -> {
                switch (tf) {
                    case 1 -> {
                        for (String dial : tf1) {
                            JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                        }
                    }
                    default -> {
                        if (tchm == 0) {
                            for (String dial : tf2) {
                                JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                            }
                        } else {
                            for (String dial : tf3) {
                                JOptionPane.showOptionDialog(null, dial, title, 0, -1, toriel, dia, dia[0]);
                            }
                        }
                    }
                }
                tf++;
            }
            default -> {
            }
        }
    }
    
    public void getMCandy() {
        ruincandy++;
    }
    
    public void preference(int choice) {
        pref = choice;
    }

    public String[] getContact() {
        return contacts;
    }

    public boolean cellphonecheck() {
        return cellphone;
    }

    public void obtcp() {
        cellphone = true;
    }

    public int room_event_checker() {
        return room_event;
    }

    public void event_finish() {
        room_event++;
    }

    public void levelup() { //levelup
        LV++;
        calculation();
        requirement();
    }

    public void calculation() {
        AT = -2 + (2 * LV) + 10;
        DF = ((LV - 1) / 4) + 10;
        maxHP = 16 + (4 * LV);
        damage = AT + weapon.attribute;
    }

    public void requirement() {
        switch (LV) {
            case 2 ->
                EXPleft += 20;
            case 3 ->
                EXPleft += 40;
            case 4 ->
                EXPleft += 50;
            case 5 ->
                EXPleft += 80;
            case 6 ->
                EXPleft += 100;
            case 7 ->
                EXPleft += 200;
            case 8 ->
                EXPleft += 300;
            case 9 ->
                EXPleft += 400;
            case 10 ->
                EXPleft += 500;
            case 11 ->
                EXPleft += 800;
            case 12 ->
                EXPleft += 1000;
            case 13 ->
                EXPleft += 1500;
            case 14 ->
                EXPleft += 2000;
            case 15 ->
                EXPleft += 3000;
            case 16 ->
                EXPleft += 5000;
            case 17 ->
                EXPleft += 10000;
            case 18 ->
                EXPleft += 25000;
            case 19 ->
                EXPleft += 49999;
        }
    }

    public String getlevelup() {
        return "Your LOVE increased.";
    }

    public void earnGold(int obtGold) {
        gold = gold + obtGold;
    }

    public void giveEXP(int addedEXP) {
        EXP = EXP + addedEXP;
        EXPleft -= addedEXP;
        while (EXPleft <= 0) {
            levelup();
        }
    }

    public void recoverHP() {
        HP = maxHP;
    }

    public void changeLocation(String newlocation) {
        location = newlocation;
    }

    public int getDF() {
        return DF += armor.attribute;
    }

    public void addkill() {
        kills++;
        if (location.contains("Ruins")) {
            ruinkills++;
            if (ruinkills >= 20) {
                ruin_geno = true;
            }
        }
    }

    public String getStat() {
        return name + "\nLV " + LV + "\nHP " + HP + "/" + maxHP + "\nGOLD " + gold;
    }

    public String toString() {
        return "'" + name + "' \nEXP: " + EXP + "\nLV: " + LV + "\nHP: " + HP + "/" + maxHP + "\nAT: " + (AT - 10) + "\nDF: " + (DF - 10) + "\nNEXT: " + EXPleft + "\nWeapon: " + weapon + "\nArmor: " + armor + "\nGold: " + gold + "\nKills: " + kills;
    }

    public String getHealth() {
        return "\n\n\n" + name.toUpperCase() + "             LV " + LV + "                HP " + HP + "/" + maxHP;
    }

    public String getMenu() {
        return name + "         LV " + LV + "\n" + location;
    }

    public String SAVING() throws FileNotFoundException, IOException {
        read = new BufferedReader(new FileReader(path));
        int prevLV = LOADValue(1);
        String prevLocation = LOADString(14);
        return name + "         LV " + prevLV + "\n" + prevLocation;
    }

}

class Enemy extends File0 { //extends, the enemy info is stored here.

    String enemyinfo = "";

    public void setEnemyInfo(String enemyinfo) {
        this.enemyinfo = enemyinfo;
    }

    public int getAT() {
        return AT;
    }

    public int getDF() {
        return DF;
    }

    public String getInfo() {
        return name + "     -   ATK " + AT + "   DEF    " + DF + "\n" + enemyinfo;
    }

    public String getHealth() {
        return name + "        HP " + HP + "/" + maxHP;
    }

    public String getName() {
        return name;
    }
}

public class File0 { //Here compiles the information of both the player and the enemy.

    BufferedReader read = null;
    BufferedWriter write = null;
    String title = "NUTDEALER";
    String name;
    int damage;
    int AT = 1;
    int DF = 1;
    int HP = 1;
    int maxHP = 1;
    ImageIcon toriel = new ImageIcon(NutDealer.class.getResource("toriel.png"));

    public void changeName(String newName) {
        name = newName;
    }

    public void addHP(int addedHP) {
        HP = HP + addedHP;
        if (HP > maxHP) {
            HP = maxHP;
        }
    }

    public void removeHP(int removedHP) {
        HP = HP - removedHP;
    }

    public void setAT(int AT) {
        this.AT = AT;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDF(int DF) {
        this.DF = DF;
    }

    public void setMHP(int maxHP) {
        this.maxHP = maxHP;
        HP = maxHP;
    }

}
