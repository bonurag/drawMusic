
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class drawMusicData_Utils
{
    enum Rappresentation
    {
        DIATONICA,
        ANGLOSASSONE,
        PITCH_CLASS
    }

    public static File readFile(String name)
    {
        String fileName = name; 
        File tmpFile = new File(fileName);
        File file = null;
        if(tmpFile.exists())
            file = tmpFile;
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
    
    public static LinkedHashMap<String, Integer> getOrderedResult(TreeMap<String, Integer> inputMap, Boolean debug, Rappresentation typeOrder)
    {      
        Boolean mergeNote = false;
        Boolean getIndex = false;
        
        LinkedHashMap<String, Integer> outputMap = new LinkedHashMap<>();
        
        ArrayList<String> anglosaxonClassOrder = new ArrayList<>(Arrays.asList("C","C#","Db","D","D#","Eb","E","F","F#","Gb","G","G#","Ab","A","A#","Bb","B"));
        ArrayList<String> diatonicNoteOrder = new ArrayList<>(Arrays.asList("Do","Do#","Reb","Re","Re#","Mib","Mi","Fa","Fa#","Solb","Sol","Sol#","Lab","La","La#","Sib","Si"));
        ArrayList<String> pitchClassOrder = new ArrayList<>(Arrays.asList("C","C#/Db","D","D#/Eb","E","F","F#/Gb","G","G#/Ab","A","A#/Bb","B"));
        
        ArrayList<String> listToOrder = new ArrayList<>();
        
        if(typeOrder.name().equals("ANGLOSASSONE"))
        {
            //System.out.println("Inside IF ANGLOSASSONE");
            listToOrder = anglosaxonClassOrder;
            mergeNote = true;
        }
        else if(typeOrder.name().equals("DIATONICA"))
        {
            //System.out.println("Inside IF DIATONICA");
            listToOrder = diatonicNoteOrder;
        }
        else if(typeOrder.name().equals("PITCH_CLASS"))
        {
            //System.out.println("Inside IF PITCH_CLASS");
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
        if(!outputMap.isEmpty())
            return outputMap;
        else
        {
            outputMap.put("Empty", -1);
        }
        return outputMap;
    }
    
    public static void getOrderedDurationResult(LinkedHashMap<String, Integer> inputMap, Boolean debug) {
        
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
        String emptyValue = "";
        if(accident != null && !"".equals(accident))
        {
            LinkedHashMap<String,String> accidentalTranscoding = new LinkedHashMap<>();
            accidentalTranscoding.put("FLAT", "b");
            accidentalTranscoding.put("DOUBLE_FLAT", "bb");
            accidentalTranscoding.put("NATURAL", "");
            accidentalTranscoding.put("SHARP", "#");
            accidentalTranscoding.put("DOUBLE_SHARP", "##");
            return accidentalTranscoding.get(accident.toUpperCase());
        }
        else
            return emptyValue;
    }
    
    public static ArrayList<String> getListFromNodeList(NodeList inputNode)
    {
        String[] tempArray = new String [inputNode.getLength()];
        ArrayList<String> tempList;
        for (int i = 0; i < inputNode.getLength(); i++)
        {
            String multipleStepNote = "";
            String multipleStepAccidental = "";
            String multipleStepOcatve = "";
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
    
    public static ArrayList<String> getPciName(ArrayList<ArrayList<Integer>> pciInput)
    {
        ArrayList<String> pciNameList = new ArrayList<>();
        ArrayList<Integer> tmpListPci = new ArrayList<>();
        ArrayList<ArrayList<String>> intervalMatrix = generateMatrixBRI();
        if(!pciInput.isEmpty())
        {
            for(int k=0; k<pciInput.size(); k++)
            {
                tmpListPci = pciInput.get(k);
                int PC = tmpListPci.get(0);
                int NC = tmpListPci.get(1);

                //System.out.println("tmpListPci: " + tmpListPci);
                //System.out.println("PC: " + PC);
                //System.out.println("NC: " + NC);

                String intervalValue = intervalMatrix.get(PC).get(NC);
                //System.out.println("intervalValue: " + intervalValue);

                pciNameList.add(intervalValue);
            }
        }
        return pciNameList;
    }
    
    //Per determinare l’intervallo tra due note, si sottrae la prima nota dalla
    //seconda. Tale calcolo corrisponde all’operazione di sottrazione
    public static ArrayList<ArrayList<Integer>> calculateInterval(ArrayList<String> inputPermutation, TreeMap<String, ArrayList<Integer>> binomialMap)
    {
        ArrayList<ArrayList<Integer>> intervalResult = new ArrayList<>();
        ArrayList<Integer> intervalForSinglePermutation = new ArrayList<>();
        if(inputPermutation.size() > 0)
        {
            for(int y=0; y<inputPermutation.size(); y++)
            {  
                intervalForSinglePermutation = new ArrayList<>();
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
                intervalForSinglePermutation.add(firstElem);
                intervalForSinglePermutation.add(secondElem);
                intervalResult.add(intervalForSinglePermutation);
            }
            //System.out.println("calculateInterval: " + intervalResult);
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
    
    public static ArrayList<ArrayList<Integer>> getMelodicBinomialFromChord(TreeMap<Integer,ArrayList<String>> inputMap)
    {
        ArrayList<String> previousValueList = new ArrayList<>();
        ArrayList<String> nextValueList = new ArrayList<>();
        
        ArrayList<String> permutationList = new ArrayList<>();
        ArrayList<Integer> singleBinomialPrev = new ArrayList<>();
        ArrayList<Integer> singleBinomialNext = new ArrayList<>();
        
        TreeMap<String,ArrayList<Integer>> binomialMap = new TreeMap<>();
        
        for(int k=inputMap.size()-1; k>0; k--)
        {          
            previousValueList = inputMap.get(k);
            nextValueList = inputMap.get(k-1);
            
            if(previousValueList.size() > 0 && nextValueList.size() > 0)
            {
                int w=0;
                int j=0;
                
                for(w=0; w<previousValueList.size(); w++)
                {
                    for(j=0; j<nextValueList.size(); j++)
                    {
                        int PC_prev = 0;
                        int NC_prev = 0;
                        int PC_next = 0;
                        int NC_next = 0;
                                              
                        singleBinomialPrev = new ArrayList<>();
                        singleBinomialNext = new ArrayList<>();
                        
                        String singlePermutation = previousValueList.get(w) + ":" + nextValueList.get(j);
                        String notePrev = previousValueList.get(w);
                        String noteNext = nextValueList.get(j);
                        //System.out.println("Permutazioni " + previousValueList.get(w) + "*" + nextValueList.get(j));
                        
                        PC_prev = getPitchClass(previousValueList.get(w).substring(0, previousValueList.get(w).length()-1));
                        NC_prev = getNameClass(previousValueList.get(w).substring(0, 1));
                        singleBinomialPrev.add(PC_prev);
                        singleBinomialPrev.add(NC_prev);
                        
                        PC_next = getPitchClass(nextValueList.get(j).substring(0, nextValueList.get(j).length()-1));
                        NC_next = getNameClass(nextValueList.get(j).substring(0, 1));
                        singleBinomialNext.add(PC_next);
                        singleBinomialNext.add(NC_next);
                        
                        if((notePrev != null && !"".equals(notePrev)) && singleBinomialPrev.size() > 0)
                            binomialMap.put(notePrev, singleBinomialPrev);
                        
                        if((noteNext != null && !"".equals(noteNext)) && singleBinomialPrev.size() > 0)
                            binomialMap.put(noteNext, singleBinomialNext);
                        
                        if(singlePermutation != null && !"".equals(singlePermutation))
                            permutationList.add(singlePermutation);
                    }
                }
            }
        }
        //System.out.println("permutationList " + permutationList);
        //System.out.println("permutationList Size " + permutationList.size());
        //System.out.println("binomialMap " + binomialMap);
        //System.out.println("binomialMap Size " + binomialMap.size());
        return calculateInterval(permutationList, binomialMap);    
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
                        String currentAccidental = "";
                        if(typeOfNotes.equals("A")) //Anglosassone
                        {
                            currentPitch = pitchElem.getAttribute("step").toUpperCase();
                        }     
                        else if(typeOfNotes.equals("D")) //Diatonica
                        {
                            currentPitch = getNoteTranscoding(pitchElem.getAttribute("step"));
                            currentAccidental = getNoteAccidental(pitchElem.getAttribute("actual_accidental"));
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
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.out.println("Errore nell'elaborazione del file");
            System.exit(1);
        }
        return pitchNoteMap;
    }
    
    public static Integer getMinGapInValue(ArrayList<Integer> inputValue)
    {
        /*
        Collections.sort(inputValue);
        Integer minValue = Collections.min(inputValue);
        Integer succesorOfMinValue = inputValue.get(1);
        //System.out.println("minValue: " + minValue);
        //System.out.println("Succesor of minValue: " + succesorOfMinValue);
        Integer result = Math.abs((succesorOfMinValue - minValue));
        //System.out.println("Result: " + result);
        */
        System.out.println("inputValue: " + inputValue);
        Collections.sort(inputValue);
        System.out.println("minDiff After Sort: " + inputValue);
        int minDiff = inputValue.get(1)-inputValue.get(0);
        for (int i = 2 ; i != inputValue.size() ; i++) {
            minDiff = Math.min(minDiff, inputValue.get(i)-inputValue.get(i-1));
        }
        
        System.out.println("minDiff: " + minDiff);
        return minDiff;
    }
    
    public static ArrayList<ArrayList<String>> generateMatrixBRI()
    {
	ArrayList<ArrayList<String>> binomialRapInterval = new ArrayList<>();
	ArrayList<String> r = null;
	
	for(int i=0; i<12; i++)
	{
            r = new ArrayList<>();
            if(i == 0)
            {  
                r.add(0, "P1");
                r.add(1, "d2");
                r.add(2, "3d3");
                r.add(3, "5d4");
                r.add(4, "5A5");
                r.add(5, "3A6");
                r.add(6, "A7");
            }

            if(i == 1)
            {
                r.add(0, "A1");
                r.add(1, "m2");
                r.add(2, "2d3");
                r.add(3, "4d4");
                r.add(4, "6A5");
                r.add(5, "4A6");
                r.add(6, "2A7");
            }

            if(i == 2)
            {
                r.add(0, "2A1");
                r.add(1, "M2");
                r.add(2, "d3");
                r.add(3, "3d4");
                r.add(4, "5d5");
                r.add(5, "5A6");
                r.add(6, "3A7");
            }

            if(i == 3)
            {
                r.add(0, "3A1");
                r.add(1, "A2");
                r.add(2, "m3");
                r.add(3, "2d4");
                r.add(4, "4d5");
                r.add(5, "5d6");
                r.add(6, "4A7");
            }

            if(i == 4)
            {
                r.add(0, "4A1");
                r.add(1, "2A2");
                r.add(2, "M3");
                r.add(3, "d4");
                r.add(4, "3d5");
                r.add(5, "4d6");
                r.add(6, "5A7");
            }

            if(i == 5)
            {
                r.add(0, "5A1");
                r.add(1, "3A2");
                r.add(2, "A3");
                r.add(3, "P4");
                r.add(4, "2d5");
                r.add(5, "3d6");
                r.add(6, "5d7");
            }

            if(i == 6)
            {
                r.add(0, "6A1");
                r.add(1, "4A2");
                r.add(2, "2A3");
                r.add(3, "A4");
                r.add(4, "d5");
                r.add(5, "2d6");
                r.add(6, "4d7");
            }

            if(i == 7)
            {
                r.add(0, "5d1");
                r.add(1, "5A2");
                r.add(2, "3A3");
                r.add(3, "2A4");
                r.add(4, "P5");
                r.add(5, "d6");
                r.add(6, "3d7");
            }

            if(i == 8)
            {
                r.add(0, "4d1");
                r.add(1, "5d2");
                r.add(2, "4A3");
                r.add(3, "3A4");
                r.add(4, "A5");
                r.add(5, "m6");
                r.add(6, "2d7");
            }

            if(i == 9)
            {
                r.add(0, "3d1");
                r.add(1, "4d2");
                r.add(2, "5A3");
                r.add(3, "4A4");
                r.add(4, "2A5");
                r.add(5, "M6");
                r.add(6, "d7");
            }

            if(i == 10)
            {
                r.add(0, "2d1");
                r.add(1, "3d2");
                r.add(2, "5d3");
                r.add(3, "5A4");
                r.add(4, "3A5");
                r.add(5, "A6");
                r.add(6, "m7");
            }

            if(i == 11)
            {
                r.add(0, "d1");
                r.add(1, "2d2");
                r.add(2, "4d3");
                r.add(3, "6A4");
                r.add(4, "4A5");
                r.add(5, "2A6");
                r.add(6, "M7");
            }
            binomialRapInterval.add(r);
	}
	return binomialRapInterval;
    }
}
