package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{

    JFrame frame1 = new JFrame();
    JTextField textMinArrivalTime = new JTextField();
    JTextField textMaxArrivalTime = new JTextField();
    JTextField textMinServiceTime = new JTextField();
    JTextField textMaxServiceTime = new JTextField();
    JTextField textNumberOfTasks = new JTextField();
    JTextField textNumberOfServers = new JTextField();
    JTextField textSimulationTime = new JTextField();

    JLabel labelMinArrivalTime = new JLabel("Min Arriving Time:");
    JLabel labelMaxArrivalTime = new JLabel("Max Arriving Time:");
    JLabel labelMinServiceTime= new JLabel("Min Serving Time:");
    JLabel labelMaxServiceTime= new JLabel("Max Serving Time:");
    JLabel labelNumberOfTasks= new JLabel("Number Of Tasks:");
    JLabel labelNumberOfServers= new JLabel("Number Of Servers:");
    JLabel labelSimulationTime= new JLabel("Simulating Time:");

    JButton validateButton = new JButton("VALIDATE");
    JButton startButton = new JButton("START");

    JLabel etichetaArea = new JLabel("Displaying Data");
    JTextArea DataArea = new JTextArea();
    JScrollPane scroll;
    JScrollBar scrollBar = new JScrollBar();

    public JTextArea getDataArea() {
        return DataArea;
    }

    public void setDataArea(JTextArea dataArea) {
        DataArea = dataArea;
    }

    public View(){

        frame1.setTitle("Orders Management");
        frame1.setVisible(true);
        frame1.setResizable(false);
        frame1.setSize(520, 600);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setBackground(new Color(218,165,32));
        frame1.setLayout(null);

        labelNumberOfTasks.setBounds(10,10,120,20);
        labelNumberOfTasks.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelNumberOfTasks);
        textNumberOfTasks.setFont(new Font("TimesRoman",Font.BOLD,12));
        textNumberOfTasks.setBounds(140,10,50,20);
        frame1.add(textNumberOfTasks);

        labelNumberOfServers.setBounds(250,10,120,20);
        labelNumberOfServers.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelNumberOfServers);
        textNumberOfServers.setFont(new Font("TimesRoman",Font.BOLD,12));
        textNumberOfServers.setBounds(410,10,50,20);
        frame1.add(textNumberOfServers);

        labelMinArrivalTime.setBounds(10,50,120,20);
        labelMinArrivalTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelMinArrivalTime);
        textMinArrivalTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        textMinArrivalTime.setBounds(140,50,50,20);
        frame1.add(textMinArrivalTime);

        labelMaxArrivalTime.setBounds(250,50,120,20);
        labelMaxArrivalTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelMaxArrivalTime);
        textMaxArrivalTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        textMaxArrivalTime.setBounds(410,50,50,20);
        frame1.add(textMaxArrivalTime);

        labelMinServiceTime.setBounds(10,90,120,20);
        labelMinServiceTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelMinServiceTime);
        textMinServiceTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        textMinServiceTime.setBounds(140,90,50,20);
        frame1.add(textMinServiceTime);

        labelMaxServiceTime.setBounds(250,90,120,20);
        labelMaxServiceTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelMaxServiceTime);
        textMaxServiceTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        textMaxServiceTime.setBounds(410,90,50,20);
        frame1.add(textMaxServiceTime);

        labelSimulationTime.setBounds(10,130,120,20);
        labelSimulationTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(labelSimulationTime);
        textSimulationTime.setFont(new Font("TimesRoman",Font.BOLD,12));
        textSimulationTime.setBounds(140,130,50,20);
        frame1.add(textSimulationTime);

        validateButton.setBounds(250,130,100,20);
        validateButton.setFont(new Font("Tahoma",Font.BOLD,12));
        validateButton.setBackground(new Color(180,180,180));
        validateButton.setToolTipText("");
        validateButton.setForeground(new Color(0, 0, 90));
        frame1.add(validateButton);

        startButton.setFont(new Font("Tahoma",Font.BOLD,12));
        startButton.setBounds(380,130,100,20);
        startButton.setBackground(new Color(180,180,180));
        startButton.setToolTipText("");
        startButton.setForeground(new Color(0, 0, 90));
        frame1.add(startButton);

        etichetaArea.setBounds(200,180,100,20);
        etichetaArea.setFont(new Font("TimesRoman",Font.BOLD,12));
        frame1.add(etichetaArea);

        DataArea.setFont(new Font("TimesRoman",Font.BOLD,12));
        DataArea.setBounds(10,200,470,280);

        JScrollPane scrollPane = new JScrollPane(DataArea);
        scrollPane.setBounds(10, 200, 470, 280);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame1.add(scrollPane);

    }

    public void addText(String string){
        DataArea.setText(string);
    }

    public void addActionListenerForValidation(ActionListener actionListener)
    {
        validateButton.addActionListener(actionListener);
    }

    public void addActionListenerForStart(ActionListener actionListener)
    {
        startButton.addActionListener(actionListener);
    }


}
