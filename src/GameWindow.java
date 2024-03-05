import javax.swing.*;
import java.awt.*;
public class GameWindow extends JPanel {

    private final Panels panelCenter;

    private final Panels panelUpper;

    private final Panels panelEast;

    private final Panels panelLower;

    private void target_set(){
        Global.setTargetScore(Global.getSumScore()+Global.rand.nextInt(50-25)+25);
    }

    GameWindow(String type, int userInput1, int userInput2){
        switch (type){
            case "Random":
                Global.setMoves(Global.rand.nextInt(100-50)+50);
                try {
                    Global.setN(Global.rand.nextInt(userInput2 - userInput1) + userInput1);
                    Global.setM(Global.rand.nextInt(userInput2 - userInput1) + userInput1);
                }catch (IllegalArgumentException e){
                    Global.setN(Global.rand.nextInt(userInput1 - userInput2) + userInput2);
                    Global.setM(Global.rand.nextInt(userInput1 - userInput2) + userInput2);}
                break;
            case "User Input":
                Global.setN(userInput1);
                Global.setM(userInput2);
                break;
            case "Saved File":
                input_save();
                break;
            case "Easy":
                Global.setMoves(30);
                Global.setN(8);
                Global.setM(9);
                break;

            case "Medium":
                Global.setMoves(20);
                Global.setN(5);
                Global.setM(6);
                break;

            case "Hard":
                Global.setMoves(15);
                Global.setN(3);
                Global.setM(4);
                break;
        }
        Global.setGame_type(type);
        panelCenter =  new Panels("center");
        panelEast = new Panels("east");


        if(!(type.equals("User Input") || type.equals("Saved File")))target_set();
        panelUpper = new Panels("upper");
        panelLower =  new Panels("lower");
        GameWindowLayout();
    }
    private void input_save(){
        Global.saved = Global.read(Global.getName());
        Global.setN(Integer.parseInt(Global.saved[0]));
        Global.setM(Integer.parseInt(Global.saved[1]));
    }

    private void GameWindowLayout(){
        setLayout(new BorderLayout());
        add(panelUpper, BorderLayout.NORTH);
        add(panelEast, BorderLayout.EAST);
        add(panelCenter, BorderLayout.CENTER);
        add(panelLower, BorderLayout.SOUTH);
    }
}