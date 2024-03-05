import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Global {

    static String[] saved;

    private static String game_type;
    private static int targetScore;

    private static String name;

    private static int sumScore;

    private static int moves;

    private static int n;

    private static int m;

    public static Random rand = new Random();

    public static String getGame_type() {
        return game_type;
    }

    public static void setGame_type(String game_type) {
        Global.game_type = game_type;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Global.name = name;
    }

    public static void text_field_characteristics(JTextField jb){
        jb.setPreferredSize(new Dimension(300,60));
        jb.setHorizontalAlignment(JLabel.CENTER);
        jb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jb.setBackground(Color.WHITE);
        jb.setForeground(new Color(40, 70, 75));
        jb.setFont(new Font("Arial",Font.BOLD,34));
        jb.setOpaque(true);
    }

    public static void label_characteristics(JLabel jb){
        jb.setBackground(new Color(40, 70, 75));
        jb.setForeground(Color.WHITE);
        jb.setFont(new Font("Arial",Font.BOLD,44));
        jb.setOpaque(true);
        jb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    public static void game_end_check(){
        if(sumScore>=targetScore)
            new CloseDialog(1);
        if(moves==0)
            new CloseDialog(0);
    }

    public static void write(String[] arr, String name) {
        try {
            FileWriter myWriter = new FileWriter(name+".txt");
            for(String item : arr)
                myWriter.write(item+" ");
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException ignored) {}
    }

    public static String[] read(String name) {
        String data="";
        try {
            File myObj = new File(name+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) data =myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException ignored) {}
        return data.split(" ");
    }

    public static int getM() {
        return m;
    }

    public static int getN() {
        return n;
    }

    public static void setM(int m) {
        Global.m = m;
    }

    public static void setN(int n) {
        Global.n = n;
    }

    public static void setTargetScore(int targetScore) {
        Global.targetScore = targetScore;
    }

    public static int getTargetScore() {
        return targetScore;
    }

    public static void setSumScore(int sumScore) {
        Global.sumScore = sumScore;
    }

    public static int getSumScore() {
        return sumScore;
    }

    public static void setMoves(int moves) {
        Global.moves = moves;
    }

    public static int getMoves() {
        return moves;
    }

    public static boolean containsLetters(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isLetter(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}