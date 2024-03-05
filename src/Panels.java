import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Panels extends JPanel implements ActionListener{
    private JLabel TargetScore = new JLabel();

    private static JLabel Moves = new JLabel();

    private static JLabel SumScore = new JLabel();

    private final JButton[][] buttonGrid = new JButton[Global.getN()][Global.getM()];

    static JLabel[] labelGrid= new JLabel[Global.getN()];

    private String temporaryButtonText =" ";

    static JButton TemporaryButton;

    static int temporaryInt;

    static String temporaryOp;

    private static int counter;

    Panels(String type){
        setLayout(new BorderLayout());
        setBackground(new Color(44, 140, 153));
        setForeground(Color.WHITE);
        switch (type){
            case "upper":
                setLayout(new BorderLayout());
                TargetScore = new JLabel();
                Moves = new JLabel();
                upper_labels();
                break;
            case "lower":
                setLayout(new FlowLayout());
                SumScore = new JLabel();
                lower_labels();
                break;
            case "center":
                setLayout(new GridLayout(Global.getN(),Global.getM()));
                center_grid();
                break;
            case "east":
                labelGrid=new JLabel[Global.getN()];
                setLayout(new GridLayout(Global.getN(),1));
                east_panel();
                break;
        }
    }
    private String getRandom(String[] arr) {
        int rdm = Global.rand.nextInt(arr.length);
        return arr[rdm];
    }
    private String pickRandom() {
        String[] operator = {"+", "-", "/", "*"};
        return getRandom(operator);
    }
    private void east_panel(){
        this.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        for (int i=0; i<Global.getN(); i++){
            if(Global.getGame_type().equals("Saved File")) {
                labelGrid[i] = new JLabel(Global.saved[counter]);
                counter++;
            }
            else labelGrid[i] = new JLabel(""+pickRandom());
            Global.label_characteristics(labelGrid[i]);
            if(i==0) labelGrid[i].setBackground(new Color(66, 217, 200));
            this.add(labelGrid[i]);
        }
        temporaryOp=labelGrid[0].getText();
    }
    private void center_grid() {
        this.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        int sum=0;
        int random;
        counter=2;
        for (int i = 0; i < Global.getN(); i++) {
            for (int j = 0; j < Global.getM(); j++) {
                if(Global.getGame_type().equals("Saved File")) {
                    random = Integer.parseInt(Global.saved[counter]);
                    counter++;
                }
                else random = Global.rand.nextInt(10);
                JButton jb = new JButton("" + random);
                button_characteristics(jb);
                sum+=random;
                buttonGrid[i][j] = jb;
                this.add(jb);
                jb.addActionListener(this);

            }
        }
        Global.setSumScore(sum);

    }
    public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            int btn_num = Integer.parseInt(btn.getText().trim());

            int rowPressed = -1;
            int colPressed = -1;


            for (int row = 0; row < buttonGrid.length; row++) {
                for (int col = 0; col < buttonGrid[row].length; col++) {
                    if (btn == buttonGrid[row][col]) {
                        rowPressed = row;
                        colPressed = col;
                    }
                }
            }
            String[] saver_array = new String[Global.getN() * Global.getM() + Global.getN() + 5];
            saver_array[0]=String.valueOf(Global.getN());
            saver_array[1]=String.valueOf(Global.getM());
            int counter=2;
            for (int row = 0; row < buttonGrid.length; row++) {
                for (int col = 0; col < buttonGrid[row].length; col++) {
                    if (!(row == rowPressed || col == colPressed)) {
                        buttonGrid[row][col].setEnabled(false);
                        buttonGrid[row][col].setBackground(new Color(147, 22, 33));
                    } else {
                        buttonGrid[row][col].setEnabled(true);
                        buttonGrid[row][col].setBackground(new Color(40, 70, 75));
                    }
                    saver_array[counter]=buttonGrid[row][col].getText();
                    counter++;
                }
            }
            if (!(temporaryButtonText.equals(" "))) {
                temporaryInt = Integer.parseInt(temporaryButtonText);
                temporaryOp=labelGrid[0].getText();
                switch (temporaryOp) {
                    case "+":
                        temporaryInt = (temporaryInt + btn_num) % 10;
                        break;
                    case "-":
                        temporaryInt = (temporaryInt - btn_num) % 10;
                        break;
                    case "*":
                        temporaryInt = (temporaryInt * btn_num) % 10;
                        break;
                    case "/":
                        try {
                            temporaryInt = (temporaryInt / btn_num) % 10;
                        }catch (ArithmeticException ignored){}
                        break;
                }
                temporaryInt=Math.abs(temporaryInt);
                TemporaryButton.setText("" + temporaryInt);
//                counter;
                JLabel lab = new JLabel();
                for (int i = 0; i < Global.getN(); i++) {
                    if (!(i == 0)) lab.setText(labelGrid[i].getText());
                    lab = labelGrid[i];
                    if (i == Global.getN() - 1) labelGrid[i].setText(pickRandom());
                    saver_array[counter]=labelGrid[i].getText();
                    counter++;
                }

                Global.setSumScore(Global.getSumScore() + temporaryInt);

                Global.setMoves(Global.getMoves() - 1);

                update_labels();

                saver_array[counter]=String.valueOf(Global.getTargetScore());
                saver_array[counter+1]=String.valueOf(Global.getMoves());
                saver_array[counter+2]=String.valueOf(Global.getSumScore());

                Global.write(saver_array,Global.getName());
                Global.game_end_check();
            }
            temporaryButtonText = String.valueOf(btn_num);
            TemporaryButton = btn;
            TemporaryButton.setEnabled(false);
            TemporaryButton.setBackground(new Color(66, 217, 200));
    }

    private void button_characteristics(JButton jb){
        jb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jb.setFocusable(true);
        jb.setBackground(new Color(40, 70, 75));
        jb.setBorderPainted(true);
        jb.setForeground(Color.WHITE);
        jb.setFont(new Font("Arial",Font.BOLD,44));
        jb.setOpaque(true);
    }

    private void upper_labels(){
        TargetScore.setFont(new Font("Arial",Font.PLAIN,34));
        TargetScore.setForeground(Color.WHITE);

        Moves.setFont(new Font("Arial",Font.PLAIN,34));
        Moves.setForeground(Color.WHITE);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        if(Global.getGame_type().equals("Saved File")) {
            Global.setTargetScore(Integer.parseInt(Global.saved[Global.saved.length-3]));
            Global.setMoves(Integer.parseInt(Global.saved[Global.saved.length-2]));
        }
        update_score(TargetScore,"Target Score",Global.getTargetScore());
        update_score(Moves,"Moves left",Global.getMoves());

        this.add(TargetScore,BorderLayout.WEST);
        this.add(Moves,BorderLayout.EAST);
    }

    private void lower_labels(){

        SumScore.setFont(new Font("Arial",Font.PLAIN,44));
        SumScore.setForeground(Color.WHITE);

        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        update_score(SumScore,"Your score",Global.getSumScore());

        this.add(SumScore);
    }


    public void update_labels(){
        update_score(Moves,"Moves left",Global.getMoves());
        update_score(SumScore,"Your score",Global.getSumScore());
    }
    private void update_score(JLabel label, String type,int score){
        label.setText(type+": "+score);
    }
}