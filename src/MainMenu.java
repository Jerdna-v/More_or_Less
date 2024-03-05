import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    GameWindow game;

    private static final JPanel PanelLabel = new JPanel();

    private static final JTextField TextName = new JTextField();

    private static final JPanel PanelText = new JPanel();

    private static final JPanel PanelButtons = new JPanel(new GridLayout(5,1));

    private static final JLabel LabelGame = new JLabel("More or less, less is more");

    private static final JButton ButtonGameRandom = new JButton("Random");

    private static final JButton ButtonUserInput = new JButton("User Input");

    private static final JButton ButtonSavedFile = new JButton("Saved File");

    private static final JButton ButtonDifficulty = new JButton("Difficulty");

    private static final JButton ButtonHelp = new JButton("Help");

    private static final JButton[] GameButtons = new JButton[5];

    private static final MenuBar MenuBar = new MenuBar();

    private static Container temporary = new Container();

    MainMenu(){
        colors();
        label_characteristics();
        PanelLabel.add(LabelGame);

        TextName.setText("Username");
        Global.text_field_characteristics(TextName);
        TextName.setForeground(new Color(50, 103, 113));
        PanelText.setLayout(new FlowLayout());
        PanelText.add(TextName);

        PanelButtons.setBorder(BorderFactory.createEmptyBorder(100,40,100,40));
        PanelText.setBorder(BorderFactory.createEmptyBorder(90,0,100,0));

        setLayout(new BorderLayout());
        add(PanelLabel,BorderLayout.NORTH);
        add(PanelText,BorderLayout.CENTER);
        add(PanelButtons,BorderLayout.SOUTH);

        mainMenuCharacteristics();

        temporary = getContentPane();
    }
    private void label_characteristics(){
        MainMenu.LabelGame.setHorizontalAlignment(JLabel.CENTER);
        MainMenu.LabelGame.setVerticalAlignment(JLabel.CENTER);
        Global.label_characteristics(MainMenu.LabelGame);
    }
    private void colors(){
        MenuBar.setBackground(new Color(50, 103, 113));

        PanelLabel.setBackground(new Color(40, 70, 75));
        PanelText.setBackground(new Color(50, 103, 113));
        PanelButtons.setBackground(new Color(50, 103, 113));

        gameButtonsActions();

        for (JButton butt: GameButtons){
            butt.addActionListener(this);
            butt.setForeground(new Color(66, 217, 200));
            butt.setBackground(new Color(40, 70, 75));
            butt.setBorderPainted(true);
            butt.setFont(new Font("Arial",Font.BOLD,34));
            butt.setOpaque(true);
            PanelButtons.add(butt);
        }
    }

    private void gameButtonsActions(){
        ButtonGameRandom.setActionCommand("Random");
        ButtonUserInput.setActionCommand("User Input");
        ButtonSavedFile.setActionCommand("Saved File");
        ButtonDifficulty.setActionCommand("Difficulty");
        ButtonHelp.setActionCommand("Help");

        GameButtons[0] = ButtonGameRandom;
        GameButtons[1] = ButtonUserInput;
        GameButtons[2] = ButtonSavedFile;
        GameButtons[3] = ButtonDifficulty;
        GameButtons[4] = ButtonHelp;
    }

    private void mainMenuCharacteristics(){
        getContentPane().setBackground(new Color(50, 103, 113));
        setTitle("More or less, less is more");
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void GameWindowPane(String type, int ui1, int ui2){
        temporary=getContentPane();
        setJMenuBar(MenuBar);
        game = new GameWindow(type,ui1,ui2);
        setContentPane(game);
        pack();
        setSize(1280,720);
    }
    public void back(){
        setContentPane(temporary);
        if(temporary.getComponentCount()==4) setJMenuBar(MenuBar);
        pack();
        setSize(1280,720);
    }
    public void PopUpPane(String type){
        temporary=getContentPane();
        setJMenuBar(null);
        setContentPane(new PopUp(type));
        pack();
        setSize(1280,720);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Random":
            case "User Input":
            case "Difficulty":
            case "Help":
            case "Saved File":
                Global.setName(TextName.getText());
                setContentPane(new PopUp(e.getActionCommand()));
                pack();
                setSize(1280,720);
                break;
        }
    }

}