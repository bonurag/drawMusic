
import java.awt.Color;
import java.util.LinkedHashMap;
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
class pitchClassFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;
    int inputDataSize = 0;

    public pitchClassFrame(Object inputDataWork)
    {       
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;
        
        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData);
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("PITCH CLASS");
            panel.setyAxisName("Q.TY");
            add(panel);
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
    
    /*
    public static LinkedHashMap<String, Integer> getPitchClass(String inpuName)
    {
        TreeMap<String, Integer> pitchClassMap = new TreeMap<>();
        LinkedHashMap<String, Integer> finalPitchClassMap = new LinkedHashMap<>();
        try
        {
            String fileName = inpuName;
            pitchClassMap = drawMusicData_Utils.getXmlStatisticsPitch(fileName, "D");
            finalPitchClassMap = drawMusicData_Utils.getOrderedResult(pitchClassMap, false, drawMusicData_Utils.Rappresentation.DIATONICA);
        }
        catch (Exception e)
        {
            System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }
        return finalPitchClassMap;
    }
    */
}
