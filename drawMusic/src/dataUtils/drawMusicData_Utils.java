package dataUtils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.sql.Timestamp;
import java.util.Date;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class drawMusicData_Utils
{
    /**
    * Enable xml validation from the GUI
    */
    private static Boolean enableValidationFromGui = false;
    
    /**
    * Setting to ignore whitespace in the xml file from the GUI
    */
    private static Boolean enableIgnoringWhitespaceFromGui = false;
    
    /**
    * @author Giuseppe Bonura
    */    
    public enum Rappresentation
    {
        /**
        * This Value is used inside the method getOrderedResult for represent data using diatonic scale
        */
        DIATONICA,
        /**
        * This Value is used inside the method getOrderedResult for represent data using anglosaxon scale
        */
        ANGLOSASSONE,
        /**
        * This Value is used inside the method getOrderedResult for represent pitch data
        */
        PITCH_CLASS
    }
    
    /**
    * Useful function for reading a file given its name.
    * @param  inputName file name to read
    * @return Returns a file object with name inputName
    * @throws java.io.IOException This class is the general class of exceptions produced by failed or interrupted I/O operations
    */
    public static File readFile(String inputName) throws IOException
    {
        String fileName = inputName; 
        File file = new File(fileName);
        if (!file.canRead())
        {
            System.err.println(file.getCanonicalPath() + ": cannot be read");   
        }
        return file;      
    }
    
    /**
    * Useful function for return an XML document.
    * @param  inputFile object file reading from source
    * @return Root of the XML document tree, usefull for access into the document's data
    * @throws javax.xml.parsers.ParserConfigurationException rise an exception during parse file.
    * @throws java.io.IOException This class is the general class of exceptions produced by failed or interrupted I/O operations
    */
    public static Document getDoc(File inputFile) throws ParserConfigurationException, IOException
    {
        Document parsedDocument = null;
        DocumentBuilderFactory f = null;
        try
        {
            //System.out.println("Validazione XML abilitata: " + enableValidationFromGui);
            //System.out.println("Esclusione Spazi Bianchi Elementi XML Abilitata: " + enableIgnoringWhitespaceFromGui);
            File xmlFile = inputFile;
            f = DocumentBuilderFactory.newInstance();
            f.setValidating(enableValidationFromGui); 
            f.setIgnoringElementContentWhitespace(enableIgnoringWhitespaceFromGui);
            DocumentBuilder b = f.newDocumentBuilder();
            ErrorHandler h = new xmlValidationErrorHandler();
            b.setErrorHandler(h);
            parsedDocument = (Document) b.parse(xmlFile);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            System.out.println(e.toString());      
        }
        if(f.isValidating() && enableValidationFromGui)
            return parsedDocument;
        else
            return parsedDocument;     
    }
    
    /**
    * Method for set static variable that enable or disable xml validation from the GUI
    * @param  inputStatus input status for option true: Active false: Disable
    */
    public static void setXmlEnableValidationFromGui(Boolean inputStatus)
    {
        enableValidationFromGui = inputStatus;
    }
    
    /**
    * Method for set static variable that enable or disable xml blank ignoring option
    * @param  inputStatus input status for option true: Active false: Disable
    */
    public static void setXmlEnableIgnoringWhitespaceFromGui(Boolean inputStatus)
    {
        enableIgnoringWhitespaceFromGui = inputStatus;
    }
   
    private static class xmlValidationErrorHandler implements ErrorHandler
    {
        /**
        * Method used for catch Warning of SAXParseException and print it during xml validation
        * @throws org.xml.sax.SAXParseException
        */
        @Override
        public void warning(SAXParseException e) throws SAXException
        {
           System.out.println("Warning: "); 
           printInfo(e);
        }
        
        /**
        * Method used for catch Error of SAXParseException and print it during xml validation
        * @throws org.xml.sax.SAXParseException
        */
        @Override
        public void error(SAXParseException e) throws SAXException
        {
           System.out.println("Error: "); 
           printInfo(e);
        }
        
        /**
        * Method used for catch Fattal error of SAXParseException and print it during xml validation
        * @throws org.xml.sax.SAXParseException
        */
        @Override
        public void fatalError(SAXParseException e) throws SAXException
        {
           System.out.println("Fattal error: "); 
           printInfo(e);
        }
        
        /**
        * Method for print all infomation catched during xml validation
        */
        private void printInfo(SAXParseException e)
        {
           System.out.println("   Public ID: "+e.getPublicId());
           System.out.println("   System ID: "+e.getSystemId());
           System.out.println("   Line number: "+e.getLineNumber());
           System.out.println("   Column number: "+e.getColumnNumber());
           System.out.println("   Message: "+e.getMessage());
        }
    }
    
    /**
    * Method used for order data at the end of pitch computation
    * @param  inputMap It is a map that contains the result of the data extracted from the xml file
    * @param  debug Is a flag useful for enable or disable system debug inside the method
    * @param  typeOrder Is the type of rappresentation that is possible use for note
    * @return ordered input data 
    */
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
    
    /**
    * Method used for print order data at the end of duration computation
    * @param  inputMap It is a map that contains the duration result extracted from the xml file
    * @param  debug Is a flag useful for enable or disable system debug inside the method
    */
    public static void getOrderedDurationResult(LinkedHashMap<String, Integer> inputMap, Boolean debug)
    { 
        if(debug)
        {
            System.out.println("getOrderResult keySet " + inputMap.keySet());
            System.out.println("getOrderResult values " + inputMap.values());
        }  
    }
    
    /**
    * Method used for given in output the diatonic corresponding anglo-saxon note 
    * @param  angloSaxonNote It's a note in anglo-saxon format
    * @return Diatonic corresponding note
    */
    public static String getNoteTranscoding(String angloSaxonNote)
    {
        LinkedHashMap<String,String> noteTranscoding = new LinkedHashMap<>();
        noteTranscoding.put("C", "Do");
        noteTranscoding.put("D", "Re");
        noteTranscoding.put("E", "Mi");
        noteTranscoding.put("F", "Fa");
        noteTranscoding.put("G", "Sol");
        noteTranscoding.put("A", "La");
        noteTranscoding.put("B", "Si");
        return noteTranscoding.get(angloSaxonNote.toUpperCase());
    }
    
    /**
    * Method used to encode abstract literal incidents from an xml file
    * @param  accident Litteral accidental form
    * @return Encoded output in symbol if input is one of this value FLAT, DOUBLE_FLAT, NATURAL, DOUBLE_FLAT SHARP otherwise empty
    */
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
    
    /**
    * Method used for extract to the pitch nodelist the element attributes
    * @param  inputNode Take in input the result of xpath evalution //chord/notehead/pitch
    * @return A list of notes composed by: step, actual_accidental, octave. Example C#2
    */
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
    
    /**
    * Method used to get the name of a range
    * @param  binomialInput List of binomial coefficient (PC,NC)
    * @return Short name of corresponding interval 
    */
    public static ArrayList<String> getIntervalName(ArrayList<ArrayList<Integer>> binomialInput)
    {
        ArrayList<String> pciNameList = new ArrayList<>();
        ArrayList<Integer> tmpListPci = new ArrayList<>();
        ArrayList<ArrayList<String>> intervalMatrix = generateMatrixBRI();
        if(!binomialInput.isEmpty())
        {
            for(int k=0; k<binomialInput.size(); k++)
            {
                tmpListPci = binomialInput.get(k);
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
    
    /**
    * Method used to compute the calculation of an interval between two notes
    * @param  inputPermutation List of all of permutation founded during process of xml file reading
    * @param  binomialMap Map of binomial coefficient for each note
    * @return A list of (PC,NC) 
    */
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
    
    /**
    * Method used to compute the Continuous Name Code of a input pitch
    * @param  inputNote Pitch note value
    * @return Continuous Name Code 
    */
    public static Integer calculateCNC(String inputNote)
    {
        Integer octave = Integer.parseInt(inputNote.substring(inputNote.length()-1, inputNote.length()));
        Integer nameClass = getNameClass(inputNote.substring(0, 1));
        //System.out.println("Inside calculateCNC parameter: octave - value: " + octave);
        //System.out.println("Inside calculateCNC parameter: nameClass - value: " + nameClass);
        return (octave * 7) + nameClass;        
    }
    
    /**
    * Method used to get the pitch value of a input note
    * @param  inputNote Input note composed by: step, actual_accidental, octave. Example C#2
    * @return Pitch value
    */
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
    
    /**
    * Method used to get the corresponding name class value of a input step note
    * @param  inputStep Input note composed by: step. Example C, A, E
    * @return Name Class value otherwise -1
    */
    public static Integer getNameClass(String inputStep)
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
            if(nameClassMap.get(index).equals(inputStep))
            {
                nameClassValue = index;
            }    
        }
        //System.out.println("Inside getNameClass nameClassValue: " + nameClassValue);
        return nameClassValue;
    }
    
    /**
    * Method used to get the corresponding binomial coefficient of a list of note in chord
    * @param  inputMap Map that have for value a list of pitch for each chord compute in xml file and by key an incremental integer
    * @return A list of (PC,NC)
    */
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
    
    /**
    * Method used to find minimum difference between any two elements in a list
    * @param  inputValue List of integer value
    * @return minimum value in a list
    */
    public static Integer getMinGapInValue(ArrayList<Integer> inputValue)
    {
        //System.out.println("inputValue Before Sort: " + inputValue);
        Collections.sort(inputValue);
        //System.out.println("inputValue After Sort: " + inputValue);
        int minDiff = inputValue.get(1)-inputValue.get(0);
        for (int i = 2 ; i != inputValue.size() ; i++)
        {
            minDiff = Math.min(minDiff, inputValue.get(i)-inputValue.get(i-1));
        }
        
        //System.out.println("minDiff: " + minDiff);
        return minDiff;
    }
    
    /**
    * Method used to generate a matrix that contains the binomial interval representation
    * @return Binomial Interval Rappresentation Matrix
    */
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
    
    /**
    * Method used to catch a screenshot of generated graph and save copy in jpg or png format
    * @param  inputButton An instance of JButton
    * @param  inputPanel An instance JPanel that contain a JButton
    */
    public static void saveScreenShoot(JButton inputButton, JPanel inputPanel)
    {
        inputButton.addActionListener((ActionEvent e) ->
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
            String selectedExtension = fileChooser.getFileFilter().getDescription();
            
            BufferedImage bufImage = new BufferedImage(inputPanel.getWidth(), inputPanel.getHeight(),BufferedImage.TYPE_INT_RGB);
            inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            inputButton.setVisible(false);
            inputPanel.paint(bufImage.getGraphics());
            
            int option = fileChooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                BufferedImage img = bufImage;
                try
                {
                    ImageIO.write(img, selectedExtension, new File(fileChooser.getSelectedFile().getAbsolutePath()+"."+selectedExtension));
                    inputPanel.setBorder(BorderFactory.createEmptyBorder());
                    inputButton.setVisible(true);
                }
                catch (IOException ex)
                {
                     System.out.println(ex.getMessage());
                }
            }
            else
            {
                inputPanel.setBorder(BorderFactory.createEmptyBorder());
                inputButton.setVisible(true);
            }
        });
    }
    
    /**
    * Method used to compute elapsed time during the swing worker execution
    * @param  inputTime Millisedond of elapsed time
    * @return A string of format HH:mm:ss:ms
    */
    public static String getElapsedTimeFromMilliseconds(long inputTime)
    {
        String format = String.format("%%0%dd", 2);
        long elapsedTime = inputTime / 1000000;
        String millisecond = String.format(format, elapsedTime % 1000);
        String seconds = String.format(format, (elapsedTime/1000) % 60);
        String minutes = String.format(format, ((elapsedTime/1000) % 3600) / 60);
        String hours = String.format(format, (elapsedTime/1000) / 3600);

        hours = hours.equals("00") ? "" : (Integer.parseInt(hours) < 9 ? hours.substring(hours.lastIndexOf("0") + 1)+":" :hours+":");
        minutes = minutes.equals("00") ? "" : (Integer.parseInt(minutes) < 9 ? minutes.substring(minutes.lastIndexOf("0") + 1)+":" :minutes+":");
        seconds = seconds.equals("00") ? "" : (Integer.parseInt(seconds) < 9 ? seconds.substring(seconds.lastIndexOf("0") + 1)+"," :seconds+",");
        millisecond = millisecond.equals("00") ? "" : millisecond+(seconds.equals("") ? "ms":(minutes.equals("") ? " sec" : " min"));
        String time =  hours+minutes+seconds+millisecond;
        return time;
    }
    
    /**
    * Method used to auto-center the message under the JProgressbar dynamically
    * @param  inputBar An instance of JProgressBar
    * @param  inputLabel An instance of JLabel
    * @return An integer value of X axis to use in input at AbsoluteConstraints method
    */
    public static int alignMessageToJBar(JProgressBar inputBar, JLabel inputLabel)
    {
        FontMetrics fm = inputLabel.getFontMetrics(inputLabel.getFont());
        int labelWidth = fm.stringWidth(inputLabel.getText());
        int progressBarWidth = inputBar.getWidth();
        int coordinateX = inputBar.getX();  
        int centerPoint = (int)coordinateX + (int)(progressBarWidth/2);       
        int labelMiddlePoint = (int)(labelWidth/2);      
        int result = centerPoint - labelMiddlePoint;
        return result;
    }
    
    /**
    * Method used to get an indicative track duration time with an approximation of half a minute
    * @param  secondsInput The value in seconds extracted from the start_time attribute of the track_event tags
    * @return A string that corrisponign a circa mm:ss track duration
    */
    public static String trackDurationConverter(int secondsInput)
    {
        String result;
        if(secondsInput > 0)
        {
            String tmpSec = ""; 
            int tmpMin = 0;

            String seconds = Integer.toString(secondsInput % 60);
            String minutes = Integer.toString((secondsInput/60) % 60);
            String hours = Integer.toString((secondsInput/60) / 60);

            seconds = Integer.parseInt(seconds) < 9 ? "0"+seconds : seconds;
            minutes = Integer.parseInt(minutes) < 9  ? "0"+minutes : minutes;
            hours = Integer.parseInt(hours) < 9  ? "0"+hours : hours;

            if(Integer.parseInt(seconds) <= 30)
            {
                tmpSec = "30";
                tmpMin = Integer.parseInt(minutes);
            }
            else
            {
                tmpSec = "00";
                tmpMin = Integer.parseInt(minutes) + 1;
            }
            String tmpResult = tmpMin < 9 ? "0"+Integer.toString(tmpMin) : Integer.toString(tmpMin);
            String typeOfTime = tmpResult.equals("00") ? " sec" : " min";

            result = "circa " + tmpResult + ":" + tmpSec + typeOfTime;
            return result;
        }
        else
        {
            return result = "-1";
        }
    }
    
    /**
    * Method used to find export an XML version of the data represented in the generated graph
    * @param  inputButton Export button on which add an actionlistener
    * @param  inputData Map that have for value the count element and for key the name of counted element
    * @param  elementInGraph Name of a list of element that contains the input data 
    * @param  graphName Value of attribute name in a element graph
    */
    public static void exportXml(JButton inputButton, LinkedHashMap<String, Integer> inputData, String elementInGraph, String graphName)
    {
        inputButton.addActionListener((ActionEvent e) -> 
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
            String selectedExtension = fileChooser.getFileFilter().getDescription();
            Document resultDoc = null;
            int option = fileChooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    resultDoc = generateXmlFile(inputData, elementInGraph, graphName);
                } 
                catch (ParserConfigurationException ex)
                {
                    Logger.getLogger(drawMusicData_Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Salvataggio nuovo file XML prendendo il file selezionato dal chooser e concatenando l'estensione
                Result output = new StreamResult(new File(fileChooser.getSelectedFile().getAbsolutePath()+"."+selectedExtension));
                Source input = new DOMSource(resultDoc);
                try
                {
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer t;
                    t = tf.newTransformer();
                    t.setOutputProperty(OutputKeys.INDENT, "yes");
                    t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
                    t.transform(input, output);
                }
                catch (IllegalArgumentException | TransformerException ex)
                {
                     
                }
            }
        });    
    }
    
    /**
    * Method used to export an XML version of the data represented in the generated graph with duration type specific
    * @param  inputButton Export button on which add an actionlistener
    * @param  inputData Map that have for value the count element and for key the name of counted element
    * @param  elementInGraph Name of a list of element that contains the input data 
    * @param  graphName Value of attribute name in a element graph
    * @param  inputDurationType Type of duration choice in GUI before generate graph, permitted value: CHORD, REST and BOTH
    */
    public static void exportXml(JButton inputButton, LinkedHashMap<String, Integer> inputData, String elementInGraph, String graphName, String inputDurationType)
    {
        inputButton.addActionListener((ActionEvent e) -> 
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
            String selectedExtension = fileChooser.getFileFilter().getDescription();
            Document resultDoc = null;
            int option = fileChooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    if(!inputDurationType.equals(""))
                        resultDoc = generateXmlFile(inputData, elementInGraph, graphName, inputDurationType);
                    else
                        resultDoc = generateXmlFile(inputData, elementInGraph, graphName);        
                } 
                catch (ParserConfigurationException ex)
                {
                    Logger.getLogger(drawMusicData_Utils.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Salvataggio nuovo file XML prendendo il file selezionato dal chooser e concatenando l'estensione
                Result output = new StreamResult(new File(fileChooser.getSelectedFile().getAbsolutePath()+"."+selectedExtension));
                Source input = new DOMSource(resultDoc);
                try
                {
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer t;
                    t = tf.newTransformer();
                    t.setOutputProperty(OutputKeys.INDENT, "yes");
                    t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
                    t.transform(input, output);
                }
                catch (IllegalArgumentException | TransformerException ex)
                {
                     
                }
            }
        });    
    }
    
    /**
    * Method used to generate the contents of the xml file using the calculated data
    * @param  inputButton Export button on which add an actionlistener
    * @param  inputData Map that have for value the count element and for key the name of counted element
    * @param  elementInGraph Name of a list of element that contains the input data 
    * @param  graphName Value of attribute name in a element graph
    * @return  A new XML document
    * @throws javax.xml.parsers.ParserConfigurationException;
    */
    private static Document generateXmlFile(LinkedHashMap<String, Integer> inputData, String elementInGraph, String graphName) throws ParserConfigurationException
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        
        //Nodo Radice export_results
        Element export_results = doc.createElement("export_results");
        export_results.setAttribute("date", ""+ts);
        doc.appendChild(export_results);
        
        //Figlo del nodo radice export_results
        Element graph = doc.createElement("graph");
        graph.setAttribute("name", graphName);
        export_results.appendChild(graph);
        
        //Popolo iterando sulla mappa i nodi interni del TAG graph
        for(String mapKey : inputData.keySet())
        {
            //System.out.println("Key: " + mapKey + " - - Value: " +  inputData.get(mapKey));
            
            Element singleElementInGraph = doc.createElement(elementInGraph);
            singleElementInGraph.setTextContent(Integer.toString(inputData.get(mapKey)));
            singleElementInGraph.setAttribute("type", mapKey);
            graph.appendChild(singleElementInGraph);
        }
        return doc;
    }
    
    /**
    * Method used to generate the contents of the xml file using the calculated data with duration type specific
    * @param  inputButton Export button on which add an actionlistener
    * @param  inputData Map that have for value the count element and for key the name of counted element
    * @param  elementInGraph Name of a list of element that contains the input data 
    * @param  graphName Value of attribute name in a element graph
    * @param  inputDurationType Type of duration choice in GUI before generate graph, permitted value: CHORD, REST and BOTH
    * @return  A new XML document
    * @throws javax.xml.parsers.ParserConfigurationException;
    */
    private static Document generateXmlFile(LinkedHashMap<String, Integer> inputData, String elementInGraph, String graphName, String inputDurationType) throws ParserConfigurationException
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        
        //Nodo Radice export_results
        Element export_results = doc.createElement("export_results");
        export_results.setAttribute("date", ""+ts);
        doc.appendChild(export_results);
        
        //Figlo del nodo radice export_results
        Element graph = doc.createElement("graph");
        graph.setAttribute("name", graphName);
        export_results.appendChild(graph);
        
        //Popolo iterando sulla mappa i nodi interni del TAG graph
        for(String mapKey : inputData.keySet())
        {
            //System.out.println("Key: " + mapKey + " - - Value: " +  inputData.get(mapKey));
            
            Element singleElementInGraph = doc.createElement(elementInGraph);
            singleElementInGraph.setTextContent(Integer.toString(inputData.get(mapKey)));
            if(inputDurationType.equals("CHORD") || inputDurationType.equals("REST") || inputDurationType.equals("BOTH"))
            {
                singleElementInGraph.setAttribute("den", mapKey);
                singleElementInGraph.setAttribute("num", "1");    
            }
            graph.appendChild(singleElementInGraph);
        }
        return doc;
    }
}


