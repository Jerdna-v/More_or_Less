import javax.swing.*;
import java.awt.*;

public class CloseDialog extends JOptionPane{
    CloseDialog(int check){
        String message;

        if(check==0) message ="You have lost\nYou needed "+(Global.getTargetScore()-Global.getSumScore())+" points more";
        else message ="You have won";

        setBackground(new Color(44, 140, 153));
        setForeground(Color.WHITE);

        showOptionDialog(Main.x, message, "Game Finished",
                DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null,new String[] {"Close"}, null);

        Main.x.dispose();
    }
}