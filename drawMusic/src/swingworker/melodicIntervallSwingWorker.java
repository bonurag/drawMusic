package swingworker;

import dataUtils.drawMusicData_Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class melodicIntervallSwingWorker
{
    /**
    * Used to detect when the worker enters the catch
    */
    private static Boolean isError = false;
    
    /**
    * Method used to perform lengthy GUI-interaction tasks in a background thread, and compute the calculations for the extraction of melodic interval information
    * @param  inputFile The file object passed from main GUI and selected from JChooser
    * @return The value of the operations computed by the worker on the input file
    */
    public SwingWorker createWorker(File inputFile)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Void>()
        { 
            LinkedHashMap<String, Integer> calculateData = null;
            @Override
            protected LinkedHashMap<String, Integer> doInBackground()
            {
                LinkedHashMap<String, Integer> melodicIntervalMap = new LinkedHashMap<>();
                
                double stepForProgress = 0;
                setProgress(0);
                try
                {
                    Document myXmlDocument = drawMusicData_Utils.getDoc(inputFile);

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

                    String xPathVoiceAttributeSelect = "//chord";
                    NodeList voiceItemrefList = (NodeList) (myXPath.evaluate(xPathVoiceAttributeSelect, myXmlDocument, XPathConstants.NODESET));

                    TreeMap<Integer,ArrayList<String>> pitchMap = new TreeMap<>();
                    ArrayList<String> pitchInChordList = null;
                    
                    if(voiceItemrefList.getLength() > 0)
                    {
                        for (int i = 0; i < voiceItemrefList.getLength(); i++)
                        {                      
                            if(voiceItemrefList.item(i) != null)
                            {
                                String singleValue_voice_item_ref = ((Element) (voiceItemrefList.item(i))).getAttribute("event_ref");
                                if(!singleValue_voice_item_ref.equals(""))
                                {
                                    String pitchCounterForNotehead = "count(//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch)";
                                    String pitchCounter = myXPath.evaluate(pitchCounterForNotehead, myXmlDocument);
                                    //System.out.println("pitchCounter: " + pitchCounter);

                                    if(Integer.parseInt(pitchCounter) == 1)
                                    {                                       
                                        pitchInChordList = new ArrayList<>();
                                        String myStringExpression_1 = "string(//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch/@step)";
                                        String note = myXPath.evaluate(myStringExpression_1, myXmlDocument);
                                        //System.out.println("note: " + note);
                                        String myStringExpression_2 = "string(//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch/@actual_accidental)";
                                        String accidental = drawMusicData_Utils.getNoteAccidental(myXPath.evaluate(myStringExpression_2, myXmlDocument));
                                        //System.out.println("accidental: " + accidental);
                                        String myStringExpression_3 = "string(//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch/@octave)";
                                        String octave = myXPath.evaluate(myStringExpression_3, myXmlDocument);
                                        //System.out.println("octave: " + octave);
 
                                        pitchInChordList.add(note+accidental+octave);
                                    }   
                                    else if(Integer.parseInt(pitchCounter) > 1)
                                    {
                                        pitchInChordList = new ArrayList<>();
                                        String xPathChordRefValueMultiplePitch = "//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch";
                                        NodeList multiplPitch = (NodeList) (myXPath.evaluate(xPathChordRefValueMultiplePitch, myXmlDocument, XPathConstants.NODESET));
                                        pitchInChordList = drawMusicData_Utils.getListFromNodeList(multiplPitch);
                                    }
                                    pitchMap.put(i, pitchInChordList); 
                                }
                            }
                            stepForProgress = (double)(i*100)/(double)voiceItemrefList.getLength();
                            if(stepForProgress > (double) 100)
                                stepForProgress = 100;
                            //System.out.println("stepForProgress " + stepForProgress);
                            setProgress((int)Math.ceil(stepForProgress));
                        }
                        if(stepForProgress < (double) 100)
                            setProgress(100);

                        ArrayList<String> intervalNameLis = drawMusicData_Utils.getIntervalName(drawMusicData_Utils.getMelodicBinomialFromChord(pitchMap));

                        //System.out.println("intervalNameLis: " + intervalNameLis);

                        for(String intervalKey : intervalNameLis)
                        {
                            if(melodicIntervalMap.containsKey(intervalKey))
                            {
                                int counter = melodicIntervalMap.get(intervalKey);
                                counter += 1;
                                melodicIntervalMap.put(intervalKey,counter);     
                            }
                            else
                            {
                                melodicIntervalMap.put(intervalKey,1);
                            }
                        }
                        /*
                        melodicIntervalMap.forEach((k, v) -> {
                            System.out.println("melodicIntervalMap: " + k + ": " + v);
                        });
                        */
                        if(!melodicIntervalMap.isEmpty())
                            return melodicIntervalMap;
                    }
                    else
                    {
                        melodicIntervalMap.put("Empty", -1);
                        System.out.println("Nessun File da elaborare");
                    }
                }
                catch (Exception e)
                {
                    isError = true;
                    System.out.println("Errore nell'elaborazione del file - isError Value: " + isError);
                    Logger.getLogger(melodicIntervallSwingWorker.class.getName()).log(Level.SEVERE, null, e);
                }
                return melodicIntervalMap;
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
                    Logger.getLogger(melodicIntervallSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
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
