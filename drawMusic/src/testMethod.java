import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

/**
 *
 * @author Giuseppe
 */
public class testMethod
{
    enum Rappresentation {
        DIATONICA,
        ANGLOSASSONE,
        PITCH_CLASS
    }
    
    public static void main(String[] args) throws XPathExpressionException 
    {
        //getPitchClass();
        //getPitch();
        //getDuration();
        getXmlHarmonicInterval();
    }
    
    public static Document readFile(String name) throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        
        String fileName = "gounod_ave_maria.xml";
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File(fileName);
        return (Document) builder.parse(file);      
    }
    
    public static LinkedHashMap<String, Integer> getOrderedResult(TreeMap<String, Integer> inputMap, Boolean debug, Rappresentation typeOrder) {
        
        Boolean mergeNote = false;
        Boolean getIndex = false;
        
        LinkedHashMap<String, Integer> outputMap = new LinkedHashMap<>();
        
        ArrayList<String> anglosaxonClassOrder = new ArrayList<String>(Arrays.asList("C","C#","Db","D","D#","Eb","E","F","F#","Gb","G","G#","Ab","A","A#","Bb","B"));
        ArrayList<String> diatonicNoteOrder = new ArrayList<String>(Arrays.asList("Do","Do#","Reb","Re","Re#","Mib","Mi","Fa","Fa#","Solb","Sol","Sol#","Lab","La","La#","Sib","Si"));
        ArrayList<String> pitchClassOrder = new ArrayList<String>(Arrays.asList("C","C#/Db","D","D#/Eb","E","F","F#/Gb","G","G#/Ab","A","A#/Bb","B"));
        
        ArrayList<String> listToOrder = new ArrayList<String>();
        
        if(typeOrder.name().equals("ANGLOSASSONE"))
        {
            System.out.println("Inside IF ANGLOSASSONE");
            listToOrder = anglosaxonClassOrder;
            mergeNote = true;
        }
        else if(typeOrder.name().equals("DIATONICA"))
        {
            System.out.println("Inside IF DIATONICA");
            listToOrder = diatonicNoteOrder;
        }
        else if(typeOrder.name().equals("PITCH_CLASS"))
        {
            System.out.println("Inside IF PITCH_CLASS");
            listToOrder = anglosaxonClassOrder;
            getIndex = true;
        }

        for(int k=0; k<listToOrder.size(); k++)
        {

            String keyMap = "";
            if(inputMap.containsKey(listToOrder.get(k)))
            {
                if(mergeNote)
                {
                    if(listToOrder.get(k).equals("C#") || listToOrder.get(k).equals("Db"))
                        keyMap = "C#/Db";
                    else if(listToOrder.get(k).equals("D#") || listToOrder.get(k).equals("Eb"))
                        keyMap = "D#/Eb";
                    else if(listToOrder.get(k).equals("F#") || listToOrder.get(k).equals("Gb"))
                        keyMap = "F#/Gb";
                    else if(listToOrder.get(k).equals("G#") || listToOrder.get(k).equals("Ab"))
                        keyMap = "G#/Ab";
                    else if(listToOrder.get(k).equals("A#") || listToOrder.get(k).equals("Bb"))
                        keyMap = "A#/Bb";
                    else
                        keyMap = listToOrder.get(k);
                }
                else
                {
                    if(!getIndex)
                        keyMap = listToOrder.get(k);
                    else
                    {
                        String tmpIndex = "";
                        if(listToOrder.get(k).equals("C#") || listToOrder.get(k).equals("Db"))
                        {
                            tmpIndex = "C#/Db";
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }

                        else if(listToOrder.get(k).equals("D#") || listToOrder.get(k).equals("Eb"))
                        {
                            tmpIndex = "D#/Eb";
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }
                        else if(listToOrder.get(k).equals("F#") || listToOrder.get(k).equals("Gb"))
                        {
                            tmpIndex = "F#/Gb";
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }
                        else if(listToOrder.get(k).equals("G#") || listToOrder.get(k).equals("Ab"))
                        {
                            tmpIndex = "G#/Ab";
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }
                        else if(listToOrder.get(k).equals("A#") || listToOrder.get(k).equals("Bb"))
                        {
                            tmpIndex = "A#/Bb";
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }
                        else
                        {
                            tmpIndex = listToOrder.get(k);
                            keyMap = Integer.toString(pitchClassOrder.indexOf(tmpIndex));
                            System.out.println("k: " + k + " tmpIndex: " + tmpIndex + " keyMap: " + keyMap);
                        }
                    }         
                }
                outputMap.put(keyMap, inputMap.get(listToOrder.get(k)));                   
            }   
        }
        if(debug)
        {
            System.out.println("getOrderResult keySet " + outputMap.keySet());
            System.out.println("getOrderResult values " + outputMap.values());
        }
        return outputMap;
    }
    
    public static void getOrderedDurationResult(TreeMap<Integer, Integer> inputMap, Boolean debug) {
        
        if(debug)
        {
            System.out.println("getOrderResult keySet " + inputMap.keySet());
            System.out.println("getOrderResult values " + inputMap.values());
        }  
    }
    
    public static String getNoteTranscoding(String note)
    {
        LinkedHashMap<String,String> noteTranscoding = new LinkedHashMap<String,String>();
        noteTranscoding.put("C", "Do");
        noteTranscoding.put("D", "Re");
        noteTranscoding.put("E", "Mi");
        noteTranscoding.put("F", "Fa");
        noteTranscoding.put("G", "Sol");
        noteTranscoding.put("A", "La");
        noteTranscoding.put("B", "Si");
        return noteTranscoding.get(note.toUpperCase());
    }
    
    public static String getNoteAccidental(String accident)
    {
        LinkedHashMap<String,String> accidentalTranscoding = new LinkedHashMap<String,String>();
        accidentalTranscoding.put("FLAT", "b");
        accidentalTranscoding.put("NATURAL", "");
        accidentalTranscoding.put("SHARP", "#");
        return accidentalTranscoding.get(accident.toUpperCase());
    }
    
    public static LinkedHashMap<String, Integer> getPitchClass()
    {
        TreeMap<String, Integer> pitchClassMap = new TreeMap<>();
        LinkedHashMap<String, Integer> finalPitchClassMap = new LinkedHashMap<>();
        try
        {
            String fileName = "gounod_ave_maria.xml";
            pitchClassMap = getXmlStatisticsPitch(fileName, "D");
            finalPitchClassMap = getOrderedResult(pitchClassMap, true, Rappresentation.DIATONICA);
        }
        catch (Exception e)
        {
            System.out.println("Exception:" + e.getStackTrace());
            System.exit(1);
        }
        return finalPitchClassMap;
    }
  
    public static LinkedHashMap<String, Integer> getPitch()
    {
        TreeMap<String, Integer> pitchNoteMap = new TreeMap<>();
        LinkedHashMap<String, Integer> finalPitchNoteMap = new LinkedHashMap<>();
        try
        {
            String fileName = "gounod_ave_maria.xml";
            pitchNoteMap = getXmlStatisticsPitch(fileName, "A");
            finalPitchNoteMap = getOrderedResult(pitchNoteMap, true, Rappresentation.ANGLOSASSONE);
        }
        catch (Exception e)
        {
            System.out.println("Exception:" + e.getStackTrace());
            System.exit(1);
        }
        return finalPitchNoteMap;
    }
    
    public static TreeMap<String, Integer> getXmlStatisticsPitch(String xmlToOpen, String typeOfNotes)
    {
        TreeMap<String, Integer> pitchNoteMap = new TreeMap<>();
        try
        {
            String fileName = xmlToOpen;
            Document doc = readFile(fileName);
            
            //Altezza
            NodeList pitchNodeList = doc.getElementsByTagName("pitch");
            // Non è possibile usare un foreach perché NodeList non implementa l'interfaccia Iterable
            Node currentNode;
            for(int i=0; i<pitchNodeList.getLength(); i++)
            {
                if(pitchNodeList.item(i) != null)
                {
                    currentNode = pitchNodeList.item(i);
                    Element pitchElem = (Element) currentNode;
                    if (!pitchElem.getAttribute("step").equals("")) 
                    {
                        String currentPitch = "";
                        if(typeOfNotes.equals("A")) //Anglosassone
                            currentPitch = pitchElem.getAttribute("step").toUpperCase();
                        else if(typeOfNotes.equals("D")) //Diatonica
                            currentPitch = getNoteTranscoding(pitchElem.getAttribute("step"));
                        
                        String currentAccidental = getNoteAccidental(pitchElem.getAttribute("actual_accidental"));
                        
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
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.out.println("Errore nell'elaborazione del file");
            System.exit(1);
        }
        return pitchNoteMap;
    }
    
    public static TreeMap<Integer, Integer> getDuration()
    {
        TreeMap<Integer, Integer> rhythmMap = new TreeMap<Integer, Integer>();

        try
        { 
            String fileName = "gounod_ave_maria.xml";
            Document doc = readFile(fileName);
            
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
                        int currentRythm = Integer.parseInt(rythmElem.getAttribute("den"));
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
            
            getOrderedDurationResult(rhythmMap, true);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.out.println("Errore nell'elaborazione del file");
            System.exit(1);
        }
        return rhythmMap;
    }
    
    public static String calculatePCI(int pitchA, int pitchB) {
        int pciInt = 0;
        TreeMap<Integer, String> pciMap = new TreeMap<>();
        if((pitchA >= 0 || pitchA < 12) && (pitchB >= 0 || pitchB < 12))
        {
            pciMap.put(0, "Unisono giusto/Ottava giusta");
            pciMap.put(1, "Seconda minore");
            pciMap.put(2, "Seconda maggiore");
            pciMap.put(3, "Terza minore");
            pciMap.put(4, "Terza maggiore");
            pciMap.put(5, "Quarta giusta");
            pciMap.put(6, "Quarta eccedente/Quinta diminuita");
            pciMap.put(7, "Quinta giusta");
            pciMap.put(8, "Sesta minore");
            pciMap.put(9, "Sesta maggiore");
            pciMap.put(10, "Settima minore");
            pciMap.put(11, "Settima maggiore");
        }
        pciInt = (pitchB - pitchA) % 12;
        pciInt = pciInt < 0 ? pciInt + 12 : pciInt;
        
        return pciMap.get(pciInt);
    }
    
    public static ArrayList<String> getXmlHarmonicInterval()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true); 
        
        TreeMap<String, Integer> harmonicIntervalMap = new TreeMap<>();

        ArrayList<String> notesSharp = new ArrayList<>(Arrays.asList("C","C#","D","D#","E","F","F#","G","G#","A","A#","B"));
        ArrayList<String> notesFlat = new ArrayList<>(Arrays.asList("C","Cb","D","Db","E","F","Fb","G","Gb","A","Ab","B"));
        
        ArrayList<String> PCI = new ArrayList<>();
        
        try
        {
            String directory = "C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\Esercizi_Prog.Musica\\FirstDOMParsing\\src\\firstdomparsing\\";
            String fileName = "gounod_ave_maria.xml";
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(directory+fileName);            
            Document myXmlDocument = builder.parse(file);
            
            XPathFactory myXPathFactory = XPathFactory.newInstance();
            XPath myXPath = myXPathFactory.newXPath();
            
            String xPathChordGreaterThanOne = "//chord[count(./notehead)>1]";
            NodeList currentChord = (NodeList) (myXPath.evaluate(xPathChordGreaterThanOne, myXmlDocument, XPathConstants.NODESET));
            
            String note = "";
            String nextNote = "";
            
            int pciA = 0;
            int pciB = 0;
            
            System.out.println("currentDuration: " + currentChord.getLength());  
            
            for (int i = 0; i < currentChord.getLength(); i++)
            {
                PCI = new ArrayList<>();
                String currentChordRef = ((Element) (currentChord.item(i))).getAttribute("event_ref");
                
                System.out.println("currentChordRef: " + currentChordRef); 
                
                String xPathPitchForEachChord = "//chord[@event_ref = \""+currentChordRef+"\"]/notehead/pitch"; 
                NodeList currentPitchInChord = (NodeList) (myXPath.evaluate(xPathPitchForEachChord, myXmlDocument, XPathConstants.NODESET));
                
                System.out.println("xPathPitchForEachChord: " + xPathPitchForEachChord); 
                
                for (int j = 0; j < currentPitchInChord.getLength()-1; j++)
                {
                    //System.out.println("currentPitchInChord.getLength(): " + currentPitchInChord.getLength());
                    
                    if(currentPitchInChord.item(j) != null)
                    {
                        String currentPitch = ((Element) (currentPitchInChord.item(j))).getAttribute("step");
                        String currentAccidental = ((Element) (currentPitchInChord.item(j))).getAttribute("actual_accidental");
                        note = currentPitch+getNoteAccidental(currentAccidental);
                        System.out.println("------note: " + note);
                        
                        if(notesSharp.contains(note))
                            pciB = notesSharp.indexOf(note);
                        else if(notesFlat.contains(note))
                            pciB = notesFlat.indexOf(note);
                        System.out.println("pciB: " + pciB);
                    }
                    
                    if(currentPitchInChord.item(j+1) != null)
                    {                    
                        String nextPitch = ((Element) (currentPitchInChord.item(j+1))).getAttribute("step");
                        String nextAccidental = ((Element) (currentPitchInChord.item(j+1))).getAttribute("actual_accidental");
                        nextNote = nextPitch+getNoteAccidental(nextAccidental);
                        System.out.println("------nextNote: " + nextNote);
                        
                        if(notesSharp.contains(nextNote))
                            pciA = notesSharp.indexOf(nextNote);
                        else if(notesFlat.contains(nextNote))
                            pciA = notesFlat.indexOf(nextNote);
                        System.out.println("pciA: " + pciA);
                    }

                    PCI.add(calculatePCI(pciA,pciB));   
                } 
                System.out.println("PCI: " + PCI);
                System.out.println("PCI Size" + PCI.size());
                
                for(String intervalKey : PCI)
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
            }

            harmonicIntervalMap.forEach((k, v) -> {
		System.out.println("Occorrenze: " + k + ": " + v);
            });
            
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
        return PCI;
    }
}
