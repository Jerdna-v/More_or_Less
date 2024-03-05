import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {

    private final JMenu MenuGame = new JMenu("New");

    private final JMenuItem MenuGameRandom = new JMenuItem("Random");

    private final JMenuItem MenuGameUserInput = new JMenuItem("User Input");

    private final JMenuItem MenuGameSavedFile = new JMenuItem("Saved File");

    private final JMenu MenuGameDifficulty = new JMenu("Difficulty");

    private final JMenuItem MenuGameDifficultyEasy = new JMenuItem("Easy");

    private final JMenuItem MenuGameDifficultyMedium = new JMenuItem("Medium");

    private final JMenuItem MenuGameDifficultyHard = new JMenuItem("Hard");

    private final JMenu MenuHelp = new JMenu("Help");

    private final JMenuItem MenuHelpContact = new JMenuItem("Contact");

    private final JMenuItem MenuHelpInformation = new JMenuItem("Info");

    MenuBar(){
        MenuBarSetup();
        Color();
        Font();
        menuItem();
    }

    private void MenuBarSetup(){
        add(MenuGame);
        MenuGame.add(MenuGameRandom);
        MenuGame.add(MenuGameUserInput);
        MenuGame.add(MenuGameSavedFile);
        MenuGame.add(MenuGameDifficulty);

        MenuGameDifficulty.add(MenuGameDifficultyEasy);
        MenuGameDifficulty.add(MenuGameDifficultyMedium);
        MenuGameDifficulty.add(MenuGameDifficultyHard);

        add(MenuHelp);
        MenuHelp.add(MenuHelpContact);
        MenuHelp.add(MenuHelpInformation);
    }

    private void Color(){
        MenuGame.setForeground(Color.WHITE);
        MenuGameRandom.setBackground(new Color(50, 103, 113));
        MenuGameUserInput.setBackground(new Color(50, 103, 113));
        MenuGameSavedFile.setBackground(new Color(50, 103, 113));

        MenuGameRandom.setForeground(Color.WHITE);
        MenuGameUserInput.setForeground(Color.WHITE);
        MenuGameSavedFile.setForeground(Color.WHITE);

        MenuGameDifficulty.setForeground(new Color(50, 103, 113));
        MenuGameDifficultyEasy.setBackground(new Color(50, 103, 113));
        MenuGameDifficultyMedium.setBackground(new Color(50, 103, 113));
        MenuGameDifficultyHard.setBackground(new Color(50, 103, 113));

        MenuGameDifficultyEasy.setForeground(Color.WHITE);
        MenuGameDifficultyMedium.setForeground(Color.WHITE);
        MenuGameDifficultyHard.setForeground(Color.WHITE);

        MenuHelp.setForeground(Color.WHITE);
        MenuHelpContact.setBackground(new Color(50, 103, 113));
        MenuHelpInformation.setBackground(new Color(50, 103, 113));

        MenuHelpContact.setForeground(Color.WHITE);
        MenuHelpInformation.setForeground(Color.WHITE);
    }

    private void Font(){
        MenuGame.setFont(new Font("Arial",Font.PLAIN,34));
        MenuHelp.setFont(new Font("Arial",Font.PLAIN,34));
    }

    private void menuItem(){

        MenuGameDifficultyEasy.addActionListener(this);
        MenuGameDifficultyEasy.setActionCommand("Easy");
        MenuGameDifficultyMedium.addActionListener(this);
        MenuGameDifficultyMedium.setActionCommand("Medium");
        MenuGameDifficultyHard.addActionListener(this);
        MenuGameDifficultyHard.setActionCommand("Hard");

        MenuGameRandom.addActionListener(this);
        MenuGameRandom.setActionCommand("Random");
        MenuGameUserInput.addActionListener(this);
        MenuGameUserInput.setActionCommand("User Input");
        MenuGameSavedFile.addActionListener(this);
        MenuGameSavedFile.setActionCommand("Saved File");

        MenuHelpContact.addActionListener(this);
        MenuHelpContact.setActionCommand("Contact");
        MenuHelpInformation.addActionListener(this);
        MenuHelpInformation.setActionCommand("Information");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Easy")||e.getActionCommand().equals("Medium")||e.getActionCommand().equals("Hard"))
            Main.x.GameWindowPane(e.getActionCommand(),-1,-1);
        else Main.x.PopUpPane(e.getActionCommand());
    }
}