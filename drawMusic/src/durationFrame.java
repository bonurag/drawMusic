
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
class durationFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;

    public durationFrame(String inputName) {
        LinkedHashMap<String, Integer> inputData = getXmlStatisticsDuration(inputName);
        panel = new cartesianGui(inputData);
        panel.setBackground(Color.WHITE);
        panel.setViewValueOnBar(false);
        panel.setxAxisName("DURATION");
        panel.setyAxisName("Q.TY");
        add(panel);
    }

    public void showUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void setGraphName(String newName) {
        String grapName = "";
        if(newName == "" && newName == null)
            grapName = "Graph";
        else
            grapName = newName;
        setTitle(grapName);
    }
    
    public static LinkedHashMap<String, Integer> getXmlStatisticsDuration(String inputName)
    {
        LinkedHashMap<String, Integer> rhythmMap = new LinkedHashMap<>();

        try
        { 
            String fileName = inputName;
            Document doc = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));
            
            //Valore Ritmico
            NodeList rythmNodeList = doc.getElementsByTagName("duration");
            // Non è possibile usare un foreach perché NodeList non implementa l'interfaccia Iterable
            Node currenRythmtNode;
            for(int i=0; i<rythmNodeList.getLength(); i++)
            {
                if(rythmNodeList.item(i) != null)
                {
                    currenRythmtNode = rythmNodeList.item(i);
                    Element rythmElem = (Element) currenRythmtNode;
                    if (!rythmElem.getAttribute("den").equals("")) 
                    {
                        String currentRythm = rythmElem.getAttribute("den");
                        if(rhythmMap.containsKey(currentRythm))
                        {
                            int counter = rhythmMap.get(currentRythm);
                            counter += 1;
                            rhythmMap.put(currentRythm,counter);     
                        }
                        else
                        {
                            rhythmMap.put(currentRythm,1);
                        }  
                    }
                }
            }  
            drawMusicData_Utils.getOrderedDurationResult(rhythmMap, true);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.out.println("Errore nell'elaborazione del file");
            System.exit(1);
        }
        return rhythmMap;
    }
}
