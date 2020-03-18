import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.NamedNodeMap;

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
        //getXmlStatisticsDuration();
        //getXmlHarmonicInterval();
        getXmlMelodicInterval();
    }
    
    public static File readFile(String name)
    {
        String fileName = "gounod_ave_maria.xml";  
        File file = new File(fileName);
        return file;      
    }
    
    public static Document getDoc(File inputFile) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return (Document) builder.parse(inputFile);
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
        LinkedHashMap<String,String> noteTranscoding = new LinkedHashMap<>();
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
        LinkedHashMap<String,String> accidentalTranscoding = new LinkedHashMap<>();
        accidentalTranscoding.put("FLAT", "b");
        accidentalTranscoding.put("DOUBLE_FLAT", "bb");
        accidentalTranscoding.put("NATURAL", "");
        accidentalTranscoding.put("SHARP", "#");
        accidentalTranscoding.put("DOUBLE_SHARP", "##");
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
            System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
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
            System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
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
            Document doc = getDoc(readFile(fileName));
            
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
    
    public static TreeMap<Integer, Integer> getXmlStatisticsDuration()
    {
        TreeMap<Integer, Integer> rhythmMap = new TreeMap<>();

        try
        { 
            String fileName = "gounod_ave_maria.xml";
            Document doc = getDoc(readFile(fileName));
            
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
    
    public static TreeMap<String, Integer> getXmlHarmonicInterval()
    {        
        TreeMap<String, Integer> armonicIntervalMap = new TreeMap<>();
        TreeMap<String, ArrayList<Integer>> binomialNoteMap = new TreeMap<>();
        
        ArrayList<String> notesListInChord;
        ArrayList<String> notesListPermutation;
        ArrayList<Integer> binomialParameter;
        
        try
        {
            String fileName = "gounod_ave_maria.xml";          
            Document myXmlDocument = getDoc(readFile(fileName));
            
            XPathFactory myXPathFactory = XPathFactory.newInstance();
            XPath myXPath = myXPathFactory.newXPath();
            
            String xPathChordGreaterThanOne = "//chord[count(./notehead)>1]";
            NodeList currentChord = (NodeList) (myXPath.evaluate(xPathChordGreaterThanOne, myXmlDocument, XPathConstants.NODESET));
            
            String note;
            
            Integer PC;
            Integer NC;
            
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
                        
                        note = currentStep+getNoteAccidental(currentAccidental)+currentOctave;

                        PC = getPitchClass(currentStep+getNoteAccidental(currentAccidental));
                        NC = getNameClass(currentStep);
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
                        Integer cncK = calculateCNC(notesListInChord.get(k));
                        Integer cncW = calculateCNC(notesListInChord.get(w));
                        if(cncK > cncW)
                            singlePermutation = notesListInChord.get(k) + ":" + notesListInChord.get(w);
                        else
                            singlePermutation = notesListInChord.get(w) + ":" + notesListInChord.get(k);
                        notesListPermutation.add(singlePermutation);    
                    }
                }
                //System.out.println("notesListPermutation: " + notesListPermutation); 
                
                ArrayList<String> pciNameLis = getPciName(calculateInterval(notesListPermutation,binomialNoteMap));
                //System.out.println("getPciName: " + pciNameLis);
                        
                for(String intervalKey : pciNameLis)
                {
                    if(armonicIntervalMap.containsKey(intervalKey))
                    {
                        int counter = armonicIntervalMap.get(intervalKey);
                        counter += 1;
                        armonicIntervalMap.put(intervalKey,counter);     
                    }
                    else
                    {
                        armonicIntervalMap.put(intervalKey,1);
                    }
                }    
            }
          
            armonicIntervalMap.forEach((k, v) -> {
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
        return armonicIntervalMap;
    }
    
    public static void getXmlMelodicInterval()
    {
        try
        {
            //String fileName = "gounod_ave_maria.xml";  
            String fileName = "Ul_parisien.xml";
            Document myXmlDocument = getDoc(readFile(fileName));
            
            XPathFactory myXPathFactory = XPathFactory.newInstance();
            XPath myXPath = myXPathFactory.newXPath();
            
            String xPathVoiceAttributeSelect = "//voice[count(./chord) > 1]/..//chord";
            NodeList voiceItemrefList = (NodeList) (myXPath.evaluate(xPathVoiceAttributeSelect, myXmlDocument, XPathConstants.NODESET));
            
            Set<String> voiceItemref_Set = new HashSet<String>(); 
            TreeMap<Integer,ArrayList<String>> pitchMap = new TreeMap<>();
            ArrayList<String> pitchInChordList = null;
            
            for (int i = 0; i < voiceItemrefList.getLength(); i++)
            {                 
                if(voiceItemrefList.item(i) != null)
                {
                    String singleValue_voice_item_ref = ((Element) (voiceItemrefList.item(i))).getAttribute("event_ref");
                    if(!singleValue_voice_item_ref.equals(""))
                        voiceItemref_Set.add(singleValue_voice_item_ref);

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
                        String accidental = getNoteAccidental(stepPitchAttribute.getNamedItem("actual_accidental").getNodeValue());
                        String octave = stepPitchAttribute.getNamedItem("octave").getNodeValue();
                        pitchInChordList.add(note+accidental+octave);
                    }   
                    else if(Integer.parseInt(pitchCounter) > 1)
                    {
                        pitchInChordList = new ArrayList<>();
                        String xPathChordRefValueMultiplePitch = "//chord[@event_ref=\""+singleValue_voice_item_ref+"\"]/notehead/pitch";
                        NodeList multiplPitch = (NodeList) (myXPath.evaluate(xPathChordRefValueMultiplePitch, myXmlDocument, XPathConstants.NODESET));
                        pitchInChordList = getListFromNodeList(multiplPitch);
                    }
                    pitchMap.put(i, pitchInChordList);                  
                }
            }
            getMelodicBinomialFromChord(pitchMap);
            
            pitchMap.forEach((k, v) -> {
		System.out.println("pitchMap: " + k + ": " + v);
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
    }
    
    public static void getMelodicBinomialFromChord(TreeMap<Integer,ArrayList<String>> inputMap)
    {
        ArrayList<String> previousValueList = null;
        ArrayList<String> nextValueList = null;
        ArrayList<String> binomialList = null;
        for(int k=inputMap.size()-1; k>0; k--)
        {          
            previousValueList = inputMap.get(k);
            nextValueList = inputMap.get(k-1);
            //System.out.println("previousValue: " + previousValueList + " and nextValue " + nextValueList);
      
            if(previousValueList.size() > 1 && nextValueList.size() > 1)
            {
                int w=0;
                int j=0;
                
                for(w=0; w<previousValueList.size(); w++)
                {
                    binomialList = new ArrayList<>();
                    for(j=0; j<nextValueList.size(); j++)
                    {
                        int PC_prev = 0;
                        int NC_prev = 0;
                        int PC_next = 0;
                        int NC_next = 0;
                        
                        String result_prev = "";
                        String result_next = "";
                        String mergeResult = "";
                        
                        System.out.println("Permutazioni Primo IF " + previousValueList.get(w) + "*" + nextValueList.get(j));
                        
                        PC_prev = getPitchClass(previousValueList.get(w).substring(0, previousValueList.get(w).length()-1));
                        NC_prev = getNameClass(previousValueList.get(w).substring(0, 1));
                        
                        PC_next = getPitchClass(nextValueList.get(j).substring(0, nextValueList.get(j).length()-1));
                        NC_next = getNameClass(nextValueList.get(j).substring(0, 1));
                        
                        result_prev = PC_prev+":"+NC_prev;
                        result_next = PC_next+":"+NC_next;
                        mergeResult = result_prev+";"+result_next;
                        
                        binomialList.add(mergeResult);
                    }
                }
            }
            else if(previousValueList.size() > 1 && nextValueList.size() == 1)
            {
                int a=0;
                int PC_prev = 0;
                int NC_prev = 0;
                int PC_next = 0;
                int NC_next = 0;
                
                String result_prev = "";
                String result_next = "";
                String mergeResult = "";
                
                binomialList = new ArrayList<>();
                for(a=0; a<previousValueList.size(); a++)
                {
                    System.out.println("Permutazioni Secondo IF " + previousValueList.get(a) + "*" + nextValueList.get(0));
                    PC_prev = getPitchClass(previousValueList.get(a).substring(0, previousValueList.get(a).length()-1));
                    NC_prev = getNameClass(previousValueList.get(a).substring(0, 1));
                    
                    PC_next = getPitchClass(nextValueList.get(0).substring(0, nextValueList.get(0).length()-1));
                    NC_next = getNameClass(nextValueList.get(0).substring(0, 1));
                    
                    result_prev = PC_prev+":"+NC_prev;
                    result_next = PC_next+":"+NC_next;
                    mergeResult = result_prev+";"+result_next;
                    
                    binomialList.add(mergeResult);
                }
            }
            else if(previousValueList.size() == 1 && nextValueList.size() > 1)
            {
                int b=0;
                for(b=0; b<nextValueList.size(); b++)
                {
                    System.out.println("Permutazioni Terzo IF " + previousValueList.get(0) + "*" + nextValueList.get(b)); 
                } 
            }
            else if(previousValueList.size() == 1 && nextValueList.size() == 1)
            {
                //int PC_prev = 0;
                //int NC_prev = 0;
                //int PC_next = 0;
                //int NC_next = 0;
                System.out.println("Permutazioni Quarto IF " + previousValueList.get(0) + "*" + nextValueList.get(0));
                
                //PC_prev = getPitchClass(previousValueList.get(0).substring(previousValueList.size()-1, previousValueList.size()));
                //NC_prev = getNameClass(previousValueList.get(0).substring(previousValueList.size()-1, previousValueList.size()));
                //PC_next = getPitchClass(nextValueList.get(0).substring(nextValueList.size()-1, nextValueList.size()));
                //NC_next = getNameClass(nextValueList.get(0).substring(nextValueList.size()-1, nextValueList.size()));
                
                //String result_prev = PC_prev+":"+NC_prev;
                //String result_next = PC_next+":"+NC_next;
                //System.out.println("Result Quarto IF -> " + result_prev + " - " + result_next); 
            }
            previousValueList = new ArrayList<>();
            nextValueList = new ArrayList<>();
            System.out.println("binomialList " + binomialList);
        }
    }
    
    public static ArrayList<String> getListFromNodeList(NodeList inputNode)
    {
        String[] tempArray = new String [inputNode.getLength()];
        ArrayList<String> tempList;
        for (int i = 0; i < inputNode.getLength(); i++)
        {
            String multipleStepNote = null;
            String multipleStepAccidental = null;
            String multipleStepOcatve = null;
            if(inputNode.item(i) != null)
            {
                multipleStepNote = ((Element) (inputNode.item(i))).getAttribute("step");
                multipleStepAccidental = getNoteAccidental(((Element) (inputNode.item(i))).getAttribute("actual_accidental"));
                multipleStepOcatve = ((Element) (inputNode.item(i))).getAttribute("octave");
                tempArray[i] = multipleStepNote+multipleStepAccidental+multipleStepOcatve;
            }       
        }
        tempList = new ArrayList(Arrays.asList(tempArray));
        return tempList;
    }
    
    public static ArrayList<String> getPciName(ArrayList<Integer> pciInput)
    {
        TreeMap<Integer, String> pciMap = new TreeMap<>();
        ArrayList<String> pciNameList = new ArrayList<>();
        pciMap.put(0, "Unisono giusto/Ottava giusta");
        pciMap.put(1, "2a minore");
        pciMap.put(2, "2a maggiore");
        pciMap.put(3, "3a minore");
        pciMap.put(4, "3a maggiore");
        pciMap.put(5, "4a giusta");
        pciMap.put(6, "4a eccedente/5a diminuita");
        pciMap.put(7, "5a giusta");
        pciMap.put(8, "6a minore");
        pciMap.put(9, "6a maggiore");
        pciMap.put(10, "7a minore");
        pciMap.put(11, "7a maggiore");
        
        for(Integer i : pciInput)
        {
            if(i >= 0 || i < 12)
            {
                pciNameList.add(pciMap.get(i));
            }
        }
        return pciNameList;
    }
    
    //Per determinare l’intervallo tra due note, si sottrae la prima nota dalla
    //seconda. Tale calcolo corrisponde all’operazione di sottrazione
    public static ArrayList<Integer> calculateInterval(ArrayList<String> inputPermutation, TreeMap<String, ArrayList<Integer>> binomialMap)
    {
        ArrayList<Integer> intervalResult = new ArrayList<>();
        if(inputPermutation.size() > 0)
        {
            for(int y=0; y<inputPermutation.size(); y++)
            {  
                List<String> singleNote = Arrays.asList(inputPermutation.get(y).split(":"));
                
                ArrayList<Integer> binomialFirstNote = binomialMap.get(singleNote.get(0));
                ArrayList<Integer> binomialSecondNote = binomialMap.get(singleNote.get(1));
                
                Integer pitchClassFirstNote = binomialFirstNote.get(0);
                Integer NameClassFirstNote = binomialFirstNote.get(1);

                Integer pitchClassSecondNote = binomialSecondNote.get(0);
                Integer NameClassSecondNote = binomialSecondNote.get(1);

                Integer firstElem = (pitchClassFirstNote-pitchClassSecondNote) < 0 ? ((pitchClassFirstNote-pitchClassSecondNote)+12)%12: (pitchClassFirstNote-pitchClassSecondNote)%12; 
                Integer secondElem = (NameClassFirstNote-NameClassSecondNote) < 0 ? ((NameClassFirstNote-NameClassSecondNote)+7)%7: (NameClassFirstNote-NameClassSecondNote)%7;
                //System.out.println("Inside calculateInterval final result: <"+firstElem+","+secondElem+">");
                intervalResult.add(firstElem);
            }
        }    
        return intervalResult;
    }
    
    //Metodo che calcola il CONTINUOUS NAME CODE passanto in input un pitch
    public static Integer calculateCNC(String inputNote)
    {
        Integer octave = Integer.parseInt(inputNote.substring(inputNote.length()-1, inputNote.length()));
        Integer nameClass = getNameClass(inputNote.substring(0, 1));
        //System.out.println("Inside calculateCNC parameter: octave - value: " + octave);
        //System.out.println("Inside calculateCNC parameter: nameClass - value: " + nameClass);
        return (octave * 7) + nameClass;        
    }
    
    public static Integer getPitchClass(String inputNote)
    {
        //System.out.println("Inside getPitchClass inputNote: " + inputNote);
        
        Integer pitchClassValue = -1;
        ArrayList<String> pitch0 = new ArrayList<>(Arrays.asList("C","B#","Dbb"));
        ArrayList<String> pitch1 = new ArrayList<>(Arrays.asList("C#","Db","B##"));
        ArrayList<String> pitch2 = new ArrayList<>(Arrays.asList("D","C##","Ebb"));
        ArrayList<String> pitch3 = new ArrayList<>(Arrays.asList("D#","Eb","Fbb"));
        ArrayList<String> pitch4 = new ArrayList<>(Arrays.asList("E","D##","Fb"));
        ArrayList<String> pitch5 = new ArrayList<>(Arrays.asList("F","E#","Gbb"));
        ArrayList<String> pitch6 = new ArrayList<>(Arrays.asList("F#","Gb","E#"));
        ArrayList<String> pitch7 = new ArrayList<>(Arrays.asList("G","F##","Abb"));
        ArrayList<String> pitch8 = new ArrayList<>(Arrays.asList("G#","Ab"));
        ArrayList<String> pitch9 = new ArrayList<>(Arrays.asList("A","G##","Bbb"));
        ArrayList<String> pitch10 = new ArrayList<>(Arrays.asList("A#","Bb","Cbb"));
        ArrayList<String> pitch11 = new ArrayList<>(Arrays.asList("B","A##","Cb"));
        
        TreeMap<Integer, ArrayList<String>> pitchClassMap = new TreeMap<>();
        pitchClassMap.put(0, pitch0);
        pitchClassMap.put(1, pitch1);
        pitchClassMap.put(2, pitch2);
        pitchClassMap.put(3, pitch3);
        pitchClassMap.put(4, pitch4);
        pitchClassMap.put(5, pitch5);
        pitchClassMap.put(6, pitch6);
        pitchClassMap.put(7, pitch7);
        pitchClassMap.put(8, pitch8);
        pitchClassMap.put(9, pitch9);
        pitchClassMap.put(10, pitch10);
        pitchClassMap.put(11, pitch11);
        
        for(Integer index : pitchClassMap.keySet())
        {
            if(pitchClassMap.get(index).contains(inputNote))
                pitchClassValue = index;
        }
        //System.out.println("Inside getPitchClass pitchClassValue: " + pitchClassValue);
        return pitchClassValue;
    }
    
    public static Integer getNameClass(String inputNote)
    {
        //System.out.println("Inside getNameClass inputNote: " + inputNote);
        
        Integer nameClassValue = -1;
        String nameClass0 = "C";
        String nameClass1 = "D";
        String nameClass2 = "E";
        String nameClass3 = "F";
        String nameClass4 = "G";
        String nameClass5 = "A";
        String nameClass6 = "B";
        
        TreeMap<Integer, String> nameClassMap = new TreeMap<>();
        nameClassMap.put(0, nameClass0);
        nameClassMap.put(1, nameClass1);
        nameClassMap.put(2, nameClass2);
        nameClassMap.put(3, nameClass3);
        nameClassMap.put(4, nameClass4);
        nameClassMap.put(5, nameClass5);
        nameClassMap.put(6, nameClass6);
    
        for(Integer index : nameClassMap.keySet())
        {
            if(nameClassMap.get(index).equals(inputNote))
            {
                nameClassValue = index;
            }    
        }
        //System.out.println("Inside getNameClass nameClassValue: " + nameClassValue);
        return nameClassValue;
    }
}
