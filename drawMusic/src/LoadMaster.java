
/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

public class LoadMaster
{
    public SwingWorker createWorker(String inputName)
    {
        return new SwingWorker<LinkedHashMap<String, Integer>, Void>()
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
                        stepForProgress = (double)(i*100)/(double)voiceItemrefList.getLength();
                        if(stepForProgress > (double) 100)
                            stepForProgress = 100;
                        //System.out.println("stepForProgress " + stepForProgress);
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
                    
                    melodicIntervalMap.forEach((k, v) -> {
                        System.out.println("melodicIntervalMap: " + k + ": " + v);
                    });
                    
                }
                catch (Exception e)
                {
                    System.out.println("Errore nell'elaborazione del file");
                    System.exit(1);
                }
                return melodicIntervalMap;
            }

            @Override
            protected void done()
            {
                try {
                    calculateData = get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //System.out.println("Finished with status " + calculateData);
            }                     
        };      
    }    
}
*/