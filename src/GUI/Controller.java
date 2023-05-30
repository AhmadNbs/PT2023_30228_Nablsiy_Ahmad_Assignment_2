package GUI;

import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller {

    private View view;
    int numberOfTasks;
    int numberOfServers;
    int limitTime;
    int minArrivalTime;
    int maxArrivalTime;
    int minServiceTime;
    int maxServiceTime;


    public Controller (View v)
    {
        this.view=v;

        this.view.addActionListenerForValidation(new ValidateDataBtnListener());
        this.view.addActionListenerForStart(new StartProgramtBtnListener());
    }

    public boolean IsDataValid()
    {

        try {
            if ( view.textNumberOfTasks.getText().isEmpty()|| view.textNumberOfServers.getText().isEmpty() ||
                 view.textSimulationTime.getText().isEmpty() || view.textMinArrivalTime.getText().isEmpty() ||
                 view.textMaxArrivalTime.getText().isEmpty() || view.textMinServiceTime.getText().isEmpty()||
                 view.textMaxServiceTime.getText().isEmpty() )
                JOptionPane.showMessageDialog(null, "All Fields Must Contain Values");
            else if ( Integer.parseInt(view.textMinServiceTime.getText()) > Integer.parseInt((view.textMaxServiceTime.getText())))
            JOptionPane.showMessageDialog(null,
                    "Minimum Serving Time Cannot Be Greater Than Maximum Serving Time!");
                else if ( Integer.parseInt(view.textMinArrivalTime.getText()) > Integer.parseInt(view.textMaxArrivalTime.getText()))
                JOptionPane.showMessageDialog(null,
                        "Minimum Arrival Time Cannot Be Greater Than Maximum Arrival Time!");
            else {
                Integer.parseInt(view.textNumberOfTasks.getText());
                Integer.parseInt(view.textNumberOfServers.getText());
                Integer.parseInt(view.textSimulationTime.getText());
                //JOptionPane.showMessageDialog(null, "Data is Valid!");
                return true;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "All Fields Must Contain Numbers Values!");
        }
        return false;
    }

    class ValidateDataBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if (IsDataValid())
                JOptionPane.showMessageDialog(null, "Data is Valid! You can Start Simulating");
        }
    }

    class StartProgramtBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            minArrivalTime = Integer.parseInt(view.textMinArrivalTime.getText());
            maxArrivalTime = Integer.parseInt(view.textMaxArrivalTime.getText());
            minServiceTime = Integer.parseInt(view.textMinServiceTime.getText());
            maxServiceTime = Integer.parseInt(view.textMaxServiceTime.getText());
            numberOfTasks = Integer.parseInt(view.textNumberOfTasks.getText());
            numberOfServers = Integer.parseInt(view.textNumberOfServers.getText());
            limitTime = Integer.parseInt(view.textSimulationTime.getText());

            if (IsDataValid()) {
                SimulationManager gen = new SimulationManager(numberOfTasks ,  numberOfServers, limitTime , minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime,view);
                System.out.println(gen.generateRandomTasks());
                gen.start();
            }
        }
    }
}
