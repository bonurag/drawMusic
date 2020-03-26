
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
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
import org.w3c.dom.NamedNodeMap;
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
public class LoadMaster
{
    public SwingWorker createWorker(String inputName)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Integer>()
        {
            double stepForProgress = 0;
            LinkedHashMap<String, Integer> calculateData = null;
            @Override
            protected LinkedHashMap<String, Integer> doInBackground()
            {
                LinkedHashMap<String, Integer> melodicIntervalMap = new LinkedHashMap<>();
                setProgress(0);
                try
                {
                    String fileName = inputName;
                    Document myXmlDocument = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

                    String xPathVoiceAttributeSelect = "//chord";
                    NodeList voiceItemrefList = (NodeList) (myXPath.evaluate(xPathVoiceAttributeSelect, myXmlDocument, XPathConstants.NODESET));

                    TreeMap<Integer,ArrayList<String>> pitchMap = new TreeMap<>();
                    ArrayList<String> pitchInChordList = null;
                    
                    double step = (double) ((double)100/(double)voiceItemrefList.getLength());
                    //System.out.println("step " + step);
                    for (int i = 0; i < voiceItemrefList.getLength(); i++)
                    {   
                        stepForProgress += step;
                        if(stepForProgress > (double) 100)
                            stepForProgress = 100;
                        //System.out.println("stepForProgress " + stepForProgress);
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
                                    String xPathChordRefValueSinglePitch = "//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch";
                                    Node singlePitch = (Node) (myXPath.evaluate(xPathChordRefValueSinglePitch, myXmlDocument, XPathConstants.NODE));
                                    NamedNodeMap stepPitchAttribute = singlePitch.getAttributes();
                                    String note = stepPitchAttribute.getNamedItem("step").getNodeValue();
                                    String accidental = drawMusicData_Utils.getNoteAccidental(stepPitchAttribute.getNamedItem("actual_accidental").getNodeValue());
                                    String octave = stepPitchAttribute.getNamedItem("octave").getNodeValue();
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
                        setProgress((int)Math.ceil(stepForProgress));
                    }

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
                }
                catch (ParserConfigurationException | SAXException | IOException e)
                {
                    System.out.println("Errore nell'elaborazione del file");
                    System.exit(1);
                }
                catch (XPathExpressionException ex)
                {
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, ex);
                }
                return melodicIntervalMap;
            }
            
            @Override
            protected void process(List<Integer> chunks)
            {
                // Get Info
                for (int number : chunks)
                {
                    System.out.println("Found even number: " + number);
                }
            }
            
            @Override
            protected void done()
            {
                try {
                    calculateData = get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Finished with status " + calculateData);
            }
        };
    }    
}