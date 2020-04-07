package swingworker;

import dataUtils.drawMusicData_Utils;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class durationSwingWorker
{
    /**
    * Used to detect when the worker enters the catch
    */
    private static Boolean isError = false;
    
    /**
    * Method used to perform lengthy GUI-interaction tasks in a background thread, and compute the calculations for the extraction of duration information
    * @param  inputFile The file object passed from main GUI and selected from JChooser
    * @param  inputDurationType The duration to be measured coming from the main GUI, chosen from: CHORD, REST or BOTH
    * @return The value of the operations computed by the worker on the input file
    */
    public SwingWorker createWorker(File inputFile, String inputDurationType)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Void>()
        { 
            LinkedHashMap<String, Integer> calculateData = null;
            @Override
            protected LinkedHashMap<String, Integer> doInBackground() 
            {
                LinkedHashMap<String, Integer> rhythmMap = new LinkedHashMap<>();
                
                double stepForProgress = 0;
                setProgress(0);
                try
                {         
                    Document myXmlDocument = drawMusicData_Utils.getDoc(inputFile);

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();
                    
                    String xPathDurationNodeList = "";
                    if(inputDurationType.equals("CHORD"))     
                        xPathDurationNodeList = "//chord/duration";
                    else if(inputDurationType.equals("REST"))
                        xPathDurationNodeList = "//rest/duration";
                    else if(inputDurationType.equals("BOTH"))
                        xPathDurationNodeList = "//duration";
                    
                    NodeList rythmNodeList = (NodeList) (myXPath.evaluate(xPathDurationNodeList, myXmlDocument, XPathConstants.NODESET));
                    Node currenRythmtNode;

                    if(rythmNodeList.getLength() > 0)
                    {
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
                            stepForProgress = (double)(i*100)/(double)rythmNodeList.getLength();
                            if(stepForProgress > (double) 100)
                                stepForProgress = 100;
                            //System.out.println("stepForProgress " + stepForProgress);
                            setProgress((int)Math.ceil(stepForProgress));
                        }
                        if(stepForProgress < (double) 100)
                            setProgress(100);
                        drawMusicData_Utils.getOrderedDurationResult(rhythmMap, false);    
                        if(!rhythmMap.isEmpty())
                            return rhythmMap;
                    }
                    else
                    {
                        rhythmMap.put("Empty", -1);
                        System.out.println("Nessun File da elaborare");
                    }
                }
                catch (Exception e)
                {
                    isError = true;
                    System.out.println("Errore nell'elaborazione del file - isError Value: " + isError);
                    Logger.getLogger(durationSwingWorker.class.getName()).log(Level.SEVERE, null, e);
                }
                return rhythmMap;
            }

            @Override
            protected void done()
            {
                try
                {
                    calculateData = get();
                }
                catch (InterruptedException | ExecutionException ex)
                {
                    Logger.getLogger(durationSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Finished with status " + calculateData);
            }                     
        };      
    }
    
    /**
    * @return True if the worker caught during his execution, False otherwise
    */
    public static Boolean getIsError()
    {
        return isError;
    }
}
