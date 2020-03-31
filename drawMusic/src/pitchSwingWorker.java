
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import java.util.Arrays;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class pitchSwingWorker
{
    public SwingWorker createWorker(String inputName, String rappresentationType, String typeOfNotes)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Void>()
        { 
            LinkedHashMap<String, Integer> calculateData;
            @Override
            protected LinkedHashMap<String, Integer> doInBackground()  
            {
                TreeMap<String, Integer> pitchNoteMap = new TreeMap<>();
                
                LinkedHashMap<String, Integer> finalPitchNoteMap = new LinkedHashMap<>();
                
                double stepForProgress = 0;
                setProgress(0);
                try
                {
                    String fileName = inputName;          
                    Document myXmlDocument = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));
                    
                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

                    String xPathPitchNodeList = "//chord/notehead/pitch";
                    NodeList pitchNodeList = (NodeList) (myXPath.evaluate(xPathPitchNodeList, myXmlDocument, XPathConstants.NODESET));
                    Node currentNode;
                    
                    if(pitchNodeList.getLength() > 0)
                    {
                        for(int i=0; i<pitchNodeList.getLength(); i++)
                        {
                            if(pitchNodeList.item(i) != null)
                            {
                                currentNode = pitchNodeList.item(i);
                                Element pitchElem = (Element) currentNode;
                                if (!pitchElem.getAttribute("step").equals("")) 
                                {
                                    String currentPitch = "";
                                    String currentAccidental = "";
                                    if(typeOfNotes.equals("A")) //Anglosassone
                                    {
                                        currentPitch = pitchElem.getAttribute("step").toUpperCase();
                                    }     
                                    else if(typeOfNotes.equals("D")) //Diatonica
                                    {
                                        currentPitch = drawMusicData_Utils.getNoteTranscoding(pitchElem.getAttribute("step"));
                                        currentAccidental = drawMusicData_Utils.getNoteAccidental(pitchElem.getAttribute("actual_accidental"));
                                    }

                                    if(pitchNoteMap.containsKey(currentPitch+currentAccidental))
                                    {
                                        int counter = pitchNoteMap.get(currentPitch+currentAccidental);
                                        counter += 1;
                                        pitchNoteMap.put(currentPitch+currentAccidental,counter);
                                    }
                                    else
                                    {
                                        pitchNoteMap.put(currentPitch+currentAccidental,1);
                                    }
                                }
                            }
                            stepForProgress = (double)(i*100)/(double)pitchNodeList.getLength();
                            if(stepForProgress > (double) 100)
                                stepForProgress = 100;
                            //System.out.println("stepForProgress " + stepForProgress);
                            setProgress((int)Math.ceil(stepForProgress));
                        }
                        if(stepForProgress < (double) 100)
                            setProgress(100);
                    }    

                    if(!pitchNoteMap.isEmpty())
                    {
                        if(rappresentationType.equals("ANGLOSASSONE"))
                        {    
                            finalPitchNoteMap = drawMusicData_Utils.getOrderedResult(pitchNoteMap, false, drawMusicData_Utils.Rappresentation.ANGLOSASSONE);
                        }
                        else if(rappresentationType.equals("DIATONICA"))
                        {
                            finalPitchNoteMap = drawMusicData_Utils.getOrderedResult(pitchNoteMap, false, drawMusicData_Utils.Rappresentation.DIATONICA);
                        }
                    }                    
                }
                catch (Exception e)
                {
                    System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
                    System.exit(1);
                }
                if(!finalPitchNoteMap.isEmpty())
                    return finalPitchNoteMap;
                else
                {
                    finalPitchNoteMap.put("Empty", -1);
                }
                return finalPitchNoteMap;
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
