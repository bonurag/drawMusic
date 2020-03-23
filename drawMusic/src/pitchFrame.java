
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.TreeMap;
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
class pitchFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;
    int inputDataSize = 0;

    public pitchFrame(String inputName) {
        LinkedHashMap<String, Integer> inputData = getPitch(inputName);
        if(!inputData.isEmpty())
        {
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData);
        }
        panel.setBackground(Color.WHITE);
        panel.setViewValueOnBar(false);
        panel.setxAxisName("PITCH");
        panel.setyAxisName("Q.TY");
        add(panel);
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
    
    public static LinkedHashMap<String, Integer> getPitch(String inputName)
    {
        TreeMap<String, Integer> pitchNoteMap = new TreeMap<>();
        LinkedHashMap<String, Integer> finalPitchNoteMap = new LinkedHashMap<>();
        try
        {
            String fileName = inputName;
            pitchNoteMap = drawMusicData_Utils.getXmlStatisticsPitch(fileName, "A");
            finalPitchNoteMap = drawMusicData_Utils.getOrderedResult(pitchNoteMap, false, testMethod.Rappresentation.ANGLOSASSONE);
        }
        catch (Exception e)
        {
            System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }
        return finalPitchNoteMap;
    }
}
