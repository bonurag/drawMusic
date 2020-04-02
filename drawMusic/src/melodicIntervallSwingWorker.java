
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class melodicIntervallSwingWorker
{
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

                        ArrayList<String> pciNameLis = drawMusicData_Utils.getPciName(drawMusicData_Utils.getMelodicBinomialFromChord(pitchMap));

                        //System.out.println("getPciName: " + pciNameLis);

                        for(String intervalKey : pciNameLis)
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
                    System.out.println("Errore nell'elaborazione del file");
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(1);
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
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("Finished with status " + calculateData);
            }                     
        };      
    }
}
