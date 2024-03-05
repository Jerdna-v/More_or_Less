import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp extends JPanel implements ActionListener {

    private final JLabel LabelTitle = new JLabel("Please fill the options in in order to start the game.");

    private final JLabel LabelTitle2 = new JLabel( "Pay attention to the restrictions");

    private final JPanel OptionPanel = new JPanel();

    private final JButton ButtonDone = new JButton("Done");

    private final JButton ButtonBack = new JButton("Back");

    private final String type;

    PopUp(String type){
        this.type=type;
        switch (type){
            case "Random":
                characteristics();
                input( new String[]{"Upper bound for random N/M:", "Lower bound for random N/M:"}, new String[]{"min difference: 1", "min difference: 1"});
                break;
            case "User Input":
                characteristics();
                input( new String[]{"Value of N:","Value of M:","Target Score:","Moves:"}, new String[]{"min value: 2", "min value: 2","careful", "careful"});
                break;
            case "Saved File":
                characteristics();
                input( new String[]{"Username:"}, new String[]{"careful"});
                break;
            case "Difficulty":
                characteristics3();
                LabelTitle.setText("Please choose your difficulty");
                break;
            case "Help":
            case "Information":
                info("<html> <center> The sum of the numbers on the buttons of the game field should be as close as possible to the target value displayed above the game field. If at the end of the move counter, the sum of the numbers on the buttons of the game field is greater than the target value, the player has lost the game. <html>");
                break;
            case "Contact":
                info("<html> <center >Created by Andrej Natev <br> Student ID 89221050 <br> More or less, less is more <br> Improvised version <html>");
                break;
        }
    }
    private void info(String text) {
        setBackground(new Color(50, 103, 113));
        setLayout(new BorderLayout());

        LabelTitle.setText(text);
        label_characteristics(LabelTitle);

        JPanel Title = new JPanel(new BorderLayout());
        panel_characteristics(Title);
        Title.add(LabelTitle, BorderLayout.CENTER);
        add(Title, BorderLayout.CENTER);

        button_characteristics(ButtonBack);
        ButtonBack.setActionCommand("Back");
        ButtonBack.addActionListener(this);
        add(ButtonBack, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void characteristics(){
        setBackground(new Color(50, 103, 113));
        setLayout(new BorderLayout());

        label_characteristics(LabelTitle);
        label_characteristics(LabelTitle2);

        JPanel Title = new JPanel(new BorderLayout());
        Title.add(LabelTitle,BorderLayout.NORTH);
        Title.add(LabelTitle2,BorderLayout.SOUTH);
        add(Title,BorderLayout.NORTH);

        JPanel Buttons = new JPanel(new FlowLayout());
        panel_characteristics(Buttons);
        button_characteristics(ButtonBack);
        ButtonBack.setActionCommand("Back");
        ButtonBack.addActionListener(this);
        Buttons.add(ButtonBack);

        button_characteristics(ButtonDone);
        ButtonDone.setActionCommand("Done");
        ButtonDone.addActionListener(this);
        Buttons.add(ButtonDone);

        add(Buttons,BorderLayout.SOUTH);
        setVisible(true);
    }

    private void characteristics3(){
        setBackground(new Color(50, 103, 113));
        setLayout(new BorderLayout());

        JPanel Title = new JPanel(new FlowLayout());
        LabelTitle.setText("Game guide and user information");
        label_characteristics(LabelTitle);
        Title.add(LabelTitle,BorderLayout.NORTH);
        panel_characteristics(Title);

        Title.setBackground(new Color(40, 70, 75));
        add(Title,BorderLayout.NORTH);
        if(this.type.equals("Help"))
            pick(new String[]{"Information","Contact"},new String[]{"Information","Contact"});
        else pick(new String[]{"Easy","Medium","Hard"},new String[]{"Easy","Medium","Hard"});

        JPanel Buttons = new JPanel(new FlowLayout());
        panel_characteristics(Buttons);
        button_characteristics(ButtonBack);
        ButtonBack.setActionCommand("Back");
        ButtonBack.addActionListener(this);
        Buttons.add(ButtonBack);
        add(Buttons,BorderLayout.SOUTH);

        setVisible(true);
    }

    private void input(String[] options_array, String[] restrictions_array){
        for (int j = 0; j<options_array.length; j++){
            JPanel temporary_panel = new JPanel(new FlowLayout());

            JLabel temporary_label = new JLabel(options_array[j]);
            label_characteristics(temporary_label);
            temporary_panel.add(temporary_label);

            JTextField temporary_text_field = new JTextField(restrictions_array[j]);
            Global.text_field_characteristics(temporary_text_field);
            temporary_panel.add(temporary_text_field);

            panel_characteristics(temporary_panel);
            OptionPanel.add(temporary_panel);
        }
        panel_characteristics(OptionPanel);
        add(OptionPanel,BorderLayout.CENTER);
    }

    private void pick(String[] arr1,String[] arr2){
        OptionPanel.setLayout(new BorderLayout());

        JPanel temporary_panel = new JPanel(new GridLayout(arr1.length,1));
        for (int j = 0; j<arr1.length;j++){
            JButton temporary_button=new JButton(arr1[j]);

            temporary_button.setActionCommand(arr2[j]);
            temporary_button.addActionListener(this);

            button_characteristics(temporary_button);
            temporary_button.setPreferredSize(new Dimension(300,100));

            temporary_panel.add(temporary_button);
            panel_characteristics(temporary_panel);
            OptionPanel.add(temporary_panel,BorderLayout.CENTER);
        }
        panel_characteristics(OptionPanel);
        add(OptionPanel);
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Done")) {
            String[] option= new String[4];
            int counter=0;
            for (Component jp: OptionPanel.getComponents()) {
                if(jp instanceof JPanel) {
                    JPanel panel = (JPanel) jp;
                    for (int i = 0; i < panel.getComponents().length; i++) {
                        if (panel.getComponents()[i] instanceof JTextField) {
                            JTextField temporary_field = (JTextField) panel.getComponents()[i];
                            String temporary_text = temporary_field.getText();
//                            Global.setName(temporary_text);
                            option[counter] = temporary_text;
                            counter++;
                        }
                    }
                }
            }
            if(Global.containsLetters(option[0])) Global.setName(option[0]);
            if(counter==2) {
                Main.x.GameWindowPane(type,Integer.parseInt(option[1]),Integer.parseInt(option[0]));
            }else if(counter==4){
                Global.setTargetScore(Integer.parseInt(option[2]));
                Global.setMoves(Integer.parseInt(option[3]));
                Main.x.GameWindowPane(type, Integer.parseInt(option[1]), Integer.parseInt(option[0]));
            }else if(counter==1)Main.x.GameWindowPane(type, 1, 1);

        }
        else if (e.getActionCommand().equals("Contact") || e.getActionCommand().equals("Information")){
            Main.x.PopUpPane(e.getActionCommand());
        }
        else if (e.getActionCommand().equals("Back")) Main.x.back();
        else Main.x.GameWindowPane(e.getActionCommand(),-1,-1);
    }

    private void panel_characteristics(JPanel jb){
        jb.setBackground(new Color(50, 103, 113));
        jb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void button_characteristics(JButton jb){
        jb.setFocusable(false);
        jb.setBackground(new Color(40, 70, 75));
        jb.setBorderPainted(true);
        jb.setForeground(new Color(66, 217, 200));
        jb.setFont(new Font("Arial",Font.BOLD,34));
        jb.setOpaque(true);
        jb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    private void label_characteristics(JLabel jb){
        jb.setHorizontalAlignment(JLabel.CENTER);
        jb.setVerticalAlignment(JLabel.CENTER);
        Global.label_characteristics(jb);
    }
}