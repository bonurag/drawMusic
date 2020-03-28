
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;
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
public class harmonicIntervallSwingWorker
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
                LinkedHashMap<String, Integer> harmonicIntervalMap = new LinkedHashMap<>();
                TreeMap<String, ArrayList<Integer>> binomialNoteMap = new TreeMap<>();

                ArrayList<String> notesListInChord = new ArrayList<>();
                ArrayList<String> notesListPermutation = new ArrayList<>();
                ArrayList<Integer> binomialParameter = new ArrayList<>();
                
                double stepForProgress = 0;
                setProgress(0);
                try
                {
                    String fileName = inputName;          
                    Document myXmlDocument = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

                    String xPathChordGreaterThanOne = "//chord[count(./notehead)>1]";
                    NodeList currentChord = (NodeList) (myXPath.evaluate(xPathChordGreaterThanOne, myXmlDocument, XPathConstants.NODESET));

                    String note = "";

                    Integer PC = 0;
                    Integer NC = 0;
                    
                    if(currentChord.getLength() > 0)
                    {
                        for (int i = 0; i < currentChord.getLength(); i++)
                        {
                            String currentChordRef = ((Element) (currentChord.item(i))).getAttribute("event_ref");
                            //System.out.println("currentChordRef: " + currentChordRef); 

                            String xPathPitchForEachChord = "//chord[@event_ref = \""+currentChordRef+"\"]/notehead/pitch"; 
                            NodeList currentPitchInChord = (NodeList) (myXPath.evaluate(xPathPitchForEachChord, myXmlDocument, XPathConstants.NODESET));
                            //System.out.println("xPathPitchForEachChord: " + xPathPitchForEachChord); 

                            notesListInChord = new ArrayList<>();
                            for (int j = 0; j < currentPitchInChord.getLength(); j++)
                            {
                                //System.out.println("currentPitchInChord.getLength(): " + currentPitchInChord.getLength());
                                binomialParameter = new ArrayList<>();
                                if(currentPitchInChord.item(j) != null)
                                {
                                    String currentStep = ((Element) (currentPitchInChord.item(j))).getAttribute("step");
                                    String currentOctave = ((Element) (currentPitchInChord.item(j))).getAttribute("octave");
                                    String currentAccidental = ((Element) (currentPitchInChord.item(j))).getAttribute("actual_accidental");

                                    note = currentStep+drawMusicData_Utils.getNoteAccidental(currentAccidental)+currentOctave;

                                    PC = drawMusicData_Utils.getPitchClass(currentStep+drawMusicData_Utils.getNoteAccidental(currentAccidental));
                                    NC = drawMusicData_Utils.getNameClass(currentStep);
                                    binomialParameter.add(PC);
                                    binomialParameter.add(NC);
                                    //System.out.println("------currentNote: " + note);
                                    //System.out.println("------Pitch Class Value: " + PC);

                                    //System.out.println("------currentStep: " + currentStep);
                                    //System.out.println("------Name Class Value: " + NC);

                                    notesListInChord.add(note);

                                    //System.out.println("calculateCNC(note): " + calculateCNC(note));
                                    binomialNoteMap.put(note, binomialParameter);
                                }                   
                            }
                            //System.out.println("binomialNoteMap: " + binomialNoteMap);

                            notesListPermutation = new ArrayList<>();

                            for(int k=0; k<notesListInChord.size(); k++)
                            {               
                                for(int w=k+1; w<notesListInChord.size(); w++)
                                {
                                    String singlePermutation;
                                    Integer cncK = drawMusicData_Utils.calculateCNC(notesListInChord.get(k));
                                    Integer cncW = drawMusicData_Utils.calculateCNC(notesListInChord.get(w));
                                    if(cncK > cncW)
                                        singlePermutation = notesListInChord.get(k) + ":" + notesListInChord.get(w);
                                    else
                                        singlePermutation = notesListInChord.get(w) + ":" + notesListInChord.get(k);
                                    notesListPermutation.add(singlePermutation);    
                                }
                            }
                            //System.out.println("notesListPermutation: " + notesListPermutation); 

                            ArrayList<String> pciNameLis = drawMusicData_Utils.getPciName(drawMusicData_Utils.calculateInterval(notesListPermutation,binomialNoteMap));
                            //System.out.println("getPciName: " + pciNameLis);

                            for(String intervalKey : pciNameLis)
                            {
                                if(harmonicIntervalMap.containsKey(intervalKey))
                                {
                                    int counter = harmonicIntervalMap.get(intervalKey);
                                    counter += 1;
                                    harmonicIntervalMap.put(intervalKey,counter);     
                                }
                                else
                                {
                                    harmonicIntervalMap.put(intervalKey,1);
                                }
                            }  
                            stepForProgress = (double)(i*100)/(double)currentChord.getLength();
                            if(stepForProgress > (double) 100)
                                stepForProgress = 100;
                            //System.out.println("stepForProgress: % " + stepForProgress);
                            setProgress((int)Math.ceil(stepForProgress));
                        }                                                          
                        if(stepForProgress < (double) 100)
                                stepForProgress = 100;
                            //System.out.println("stepForProgress Final Value: % " + stepForProgress);
                            setProgress(100);
                        /*
                        harmonicIntervalMap.forEach((k, v) -> {
                            System.out.println("harmonicIntervalMap: " + k + ": " + v);
                        });
                        */
                        if(!harmonicIntervalMap.isEmpty())
                            return harmonicIntervalMap;
                    }
                    else
                    {
                        harmonicIntervalMap.put("Empty", -1);
                        System.out.println("Nessun File da elaborare");
                    }        
                }
                catch (ParserConfigurationException | SAXException | IOException | NullPointerException | XPathExpressionException e)
                { 
                    System.out.println("Errore nell'elaborazione del file");
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(1);
                }
                return harmonicIntervalMap;      
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
