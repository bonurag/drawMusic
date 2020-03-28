
import java.awt.Color;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
class harmonicIntervalFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;
    int inputDataSize = 0;
    
    public harmonicIntervalFrame(Object inputDataWork, Color selectedColor)
    {
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;

        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            JButton saveButton = new JButton();
            saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/screenshot.png"))); 
            saveButton.setToolTipText("Cattura uno screenshoot del grafico!");
            saveButton.setVisible(true);
            
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData);
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("INTERVALLO");
            panel.setyAxisName("Q.TY");
            panel.setBarColor(selectedColor);
            panel.add(saveButton);
            panel.setName("harmonicIntervalFrame");
            add(panel);
            
            drawMusicData_Utils.saveScreenShoot(saveButton, panel);
        }   
    }

    public void showUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public int getInputDataSize() {
        return inputDataSize;
    }

    public void setGraphName(String newName) {
        String grapName = "";
        if(newName == "" && newName == null)
            grapName = "Graph";
        else
            grapName = newName;
        setTitle(grapName);
    }
}
