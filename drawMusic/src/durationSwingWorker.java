
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
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
public class durationSwingWorker
{
    public SwingWorker createWorker(String inputName, String inputDurationType)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Void>()
        { 
            LinkedHashMap<String, Integer> calculateData = null;
            @Override
            protected LinkedHashMap<String, Integer> doInBackground() throws XPathExpressionException
            {
                LinkedHashMap<String, Integer> rhythmMap = new LinkedHashMap<>();
                
                double stepForProgress = 0;
                setProgress(0);
                try
                { 
                    String fileName = inputName;          
                    Document myXmlDocument = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));

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
                            System.out.println("inside FOR");
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
                        //drawMusicData_Utils.getOrderedDurationResult(rhythmMap, false);    
                        if(!rhythmMap.isEmpty())
                            return rhythmMap;
                    }
                    else
                    {
                        rhythmMap.put("Empty", -1);
                        System.out.println("Nessun File da elaborare");
                    }
                }
                catch (ParserConfigurationException | SAXException | IOException | NullPointerException e)
                {
                    System.out.println("Errore nell'elaborazione del file");
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(1);
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
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Finished with status " + calculateData);
            }                     
        };      
    }
}
