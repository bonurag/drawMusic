
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Giuseppe
 */
public class xmlDetailSwingWorker
{
    private String mainTitle = "";
    private String number = "";
    private String workNumber = "";
    
    private LinkedHashMap<String, String> authorsMap = new LinkedHashMap<>();
    private LinkedHashMap<String, String> otherTitleMap = new LinkedHashMap<>();
    private LinkedHashMap<String, String> workTitleMap = new LinkedHashMap<>();
    private LinkedHashMap<String, String> genresMap = new LinkedHashMap<>();
    
    private LinkedHashMap<Integer, LinkedHashMap<String, String>> trackMap = new LinkedHashMap<>();
    private LinkedHashMap<String, String> trackAttributeMap = null;
    
    public SwingWorker createWorker(File inputFile)
    {
        return new SwingWorker<Void, Void>()
        { 
            @Override
            protected Void doInBackground() throws Exception
            {
                double stepForProgress = 0;
                setProgress(0);
                try
                {         
                    Document myXmlDocument = drawMusicData_Utils.getDoc(inputFile);

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

//=================================== Calculate Main Title ==========================================
                    String xPathTitleExpr = "string(//ieee1599/general/description/main_title)";
                    mainTitle = myXPath.evaluate(xPathTitleExpr, myXmlDocument);
                    setProgress(10);
                    
//=================================== Calculate Authors ==========================================
                    String xPathElementAuthorsList = "//ieee1599/general/description/author";
                    NodeList authorList = (NodeList) (myXPath.evaluate(xPathElementAuthorsList, myXmlDocument, XPathConstants.NODESET));
                    Node currenAuthorNode;
                    if(authorList.getLength() > 0)
                    {
                        for (int i = 0; i < authorList.getLength(); i++)
                        {
                            if(authorList.item(i) != null)
                            {
                                currenAuthorNode = authorList.item(i);
                                Element authorElem = (Element) currenAuthorNode;
                                String authorName = authorElem.getTextContent();

                                if(authorElem.hasAttributes())
                                {
                                    if (!authorElem.getAttribute("type").equals("")) 
                                    {
                                        String authorType = authorElem.getAttribute("type");
                                        authorsMap.put(authorName, authorType);
                                    }
                                }
                            }
                        }
                        setProgress(20);
                    }

//=================================== Calculate otherTitle ==========================================                   
                    String xPathElementOtherTitleList = "//ieee1599/general/description/other_title";
                    NodeList otherTitleList = (NodeList) (myXPath.evaluate(xPathElementOtherTitleList, myXmlDocument, XPathConstants.NODESET));
                    Node currenOtherTitleNode;
                    if(otherTitleList.getLength() > 0)
                    {
                        for (int i = 0; i < otherTitleList.getLength(); i++)
                        {
                            if(otherTitleList.item(i) != null)
                            {
                                System.out.println("Inside otherTitleList");
                                currenOtherTitleNode = otherTitleList.item(i);
                                Element otherTitleElem = (Element) currenOtherTitleNode;
                                String otherTitleValue = otherTitleElem.getTextContent();
                                System.out.println("Inside otherTitleValue: " + otherTitleValue);
                                otherTitleMap.put(Integer.toString(i), otherTitleValue);
                            }
                        }
                        setProgress(30);
                    }

//=================================== Calculate Number ==========================================
                    String xPathNumberExpr = "string(//ieee1599/general/description/number)";
                    number = myXPath.evaluate(xPathNumberExpr, myXmlDocument);
                    setProgress(35);
                    
//=================================== Calculate WorkTitle ==========================================
                    String xPathElementWorkTitleList = "//ieee1599/general/description/work_title";
                    NodeList workTitleList = (NodeList) (myXPath.evaluate(xPathElementWorkTitleList, myXmlDocument, XPathConstants.NODESET));
                    Node currenWorkTitleNode;
                    if(workTitleList.getLength() > 0)
                    {
                        for (int i = 0; i < workTitleList.getLength(); i++)
                        {
                            if(workTitleList.item(i) != null)
                            {
                                currenWorkTitleNode = workTitleList.item(i);
                                Element workTitleElem = (Element) currenWorkTitleNode;
                                String workTitleValue = workTitleElem.getTextContent();
                                workTitleMap.put(Integer.toString(i), workTitleValue);
                            }
                        }
                        setProgress(40);
                    }

//=================================== Calculate Work Number ==========================================
                    String xPathWorkNumberExpr = "string(//ieee1599/general/description/work_number)";
                    workNumber = myXPath.evaluate(xPathWorkNumberExpr, myXmlDocument);
                    setProgress(45);
                    
//=================================== Calculate Genres ==========================================
                    String xPathGenresList = "//ieee1599/general/description/genres/genre";
                    NodeList genresList = (NodeList) (myXPath.evaluate(xPathGenresList, myXmlDocument, XPathConstants.NODESET));
                    Node currenGenreNode;
                    if(genresList.getLength() > 0)
                    {
                        for (int i = 0; i < genresList.getLength(); i++)
                        {
                            if(genresList.item(i) != null)
                            {
                                currenGenreNode = genresList.item(i);
                                Element genreElem = (Element) currenGenreNode;

                                if(genreElem.hasAttributes())
                                {
                                    if (!genreElem.getAttribute("name").equals("")) 
                                    {
                                        String genreName = genreElem.getAttribute("name");
                                        genresMap.put(genreName, genreName);
                                    }
                                }
                            }
                        }
                        setProgress(50);
                    }

//=================================== Calculate Track Element ==========================================                   
                    String xPathElementTrackList = "//ieee1599/audio/track";
                    NodeList trackList = (NodeList) (myXPath.evaluate(xPathElementTrackList, myXmlDocument, XPathConstants.NODESET));
                    Node currenTrackNode;
                    if(trackList.getLength() > 0)
                    {
                        for (int i = 0; i < trackList.getLength(); i++)
                        {
                            String trackFileName = "";
                            String performersResult = "";
                            String genresTrackNameResult = "";
                            trackAttributeMap = new LinkedHashMap<>();
                            if(trackList.item(i) != null)
                            {
                                currenTrackNode = trackList.item(i);
                                Element trackElem = (Element) currenTrackNode;

                                if(trackElem.hasAttributes())
                                {
                                    if (!trackElem.getAttribute("file_name").equals(""))
                                    {
                                        trackFileName = trackElem.getAttribute("file_name");
                                        int trackDuration = getDurationFromTrack(trackFileName, myXmlDocument, myXPath);
                                        trackAttributeMap.put("file_name", trackFileName);
                                        trackAttributeMap.put("track_duration", drawMusicData_Utils.trackDurationConverter(trackDuration));
                                    }
                                    if (!trackElem.getAttribute("file_format").equals(""))
                                    {
                                        String fileFormat = trackElem.getAttribute("file_format");
                                        trackAttributeMap.put("file_format", fileFormat);
                                    }
                                    if (!trackElem.getAttribute("encoding_format").equals(""))
                                    {
                                        String encodingFormatName = trackElem.getAttribute("encoding_format");
                                        trackAttributeMap.put("encoding_format", encodingFormatName);
                                    }
                                }
                            }

                            //=================================== Calculate Performers ==========================================
                            String xPathElementPerformersList = "//ieee1599/audio/track[@file_name=\""+trackFileName+"\"]/track_general/performers/performer";
                            NodeList performersList = (NodeList) (myXPath.evaluate(xPathElementPerformersList, myXmlDocument, XPathConstants.NODESET));
                            Node currenPerformerNode;

                            String performerName = "";
                            String performerType = "";

                            if(performersList.getLength() > 0)
                            {
                                for (int w = 0; w < performersList.getLength(); w++)
                                {
                                    if(performersList.item(w) != null)
                                    {
                                        currenPerformerNode = performersList.item(w);
                                        Element performerElem = (Element) currenPerformerNode;

                                        if(performerElem.hasAttributes())
                                        {
                                            if (!performerElem.getAttribute("name").equals("")) 
                                            {
                                                performerName = performerElem.getAttribute("name");    
                                            }
                                            if (!performerElem.getAttribute("type").equals("")) 
                                            {
                                                performerType = performerElem.getAttribute("type");
                                                performerType = !performerType.equals("") ? " ("+performerType+")" : "";
                                            }
                                            performersResult += performerName+performerType+"; ";
                                            trackAttributeMap.put("performers", performersResult.substring(0, performersResult.length()-2));    
                                        }
                                    }
                                }
                            }
                            
                            //=================================== Calculate Genres In Track ==========================================
                            String xPathElementGenresList = "//ieee1599/audio/track[@file_name=\""+trackFileName+"\"]/track_general/genres/genre";
                            NodeList genresTrackList = (NodeList) (myXPath.evaluate(xPathElementGenresList, myXmlDocument, XPathConstants.NODESET));
                            Node currenGenreTrackNode;

                            String genreTrackName = "";

                            if(genresTrackList.getLength() > 0)
                            {
                                for (int k = 0; k < genresTrackList.getLength(); k++)
                                {
                                    if(genresTrackList.item(k) != null)
                                    {
                                        currenGenreTrackNode = genresTrackList.item(k);
                                        Element genreTrakElem = (Element) currenGenreTrackNode;

                                        if(genreTrakElem.hasAttributes())
                                        {
                                            if (!genreTrakElem.getAttribute("name").equals("")) 
                                            {
                                                genreTrackName = genreTrakElem.getAttribute("name"); 
                                                genreTrackName = !genreTrackName.equals("") ? genreTrackName : "";
                                            }
                                           
                                            genresTrackNameResult += genreTrackName+"; ";
                                            trackAttributeMap.put("trackGenres", genresTrackNameResult.substring(0, genresTrackNameResult.length()-2));    
                                        }
                                    }
                                }
                            }
                            setProgress(100);
                            trackMap.put(i, trackAttributeMap);
                        }
                        if(stepForProgress < (double) 100)
                            setProgress(100);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Errore nell'elaborazione del file");
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(1);
                }
                return null;
            }         
        };      
    }
    
    public String getMainTitle()
    {
        return mainTitle;
    }
    
    public LinkedHashMap<String, String> getAuthorsMap()
    {
        return authorsMap;
    }

    public LinkedHashMap<String, String> getOtherTitleMap()
    {
        return otherTitleMap;
    }
    
    public String getNumber()
    {
        return number;
    }
    
    public LinkedHashMap<String, String> getWorkTitleMap()
    {
        return workTitleMap;
    }
    
    public String getWorkNumber()
    {
        return workNumber;
    }
    
    public LinkedHashMap<String, String> getGenresMap()
    {
        return genresMap;
    }
    
    public LinkedHashMap<Integer, LinkedHashMap<String, String>> getTrackMap()
    {
        return trackMap;
    }
    
    public int getDurationFromTrack(String trackName, Document inputDocument, XPath inputXPath ) throws XPathExpressionException
    {
        String xPathTrackDurationExpr = "//ieee1599/audio/track[@file_name=\""+trackName+"\"]/track_indexing[@timing_type=\"seconds\"]/track_event";
        NodeList trackeventList = (NodeList) (inputXPath.evaluate(xPathTrackDurationExpr, inputDocument, XPathConstants.NODESET));
        Node currenTrackEventNode;
        ArrayList<Double> durationEvent = null;
        double maxValueDuratione = 0;
        double minValueDuratione = 0;
        
        if(trackeventList.getLength() > 0)
        {   
            durationEvent = new ArrayList<>();
            for (int k = 0; k < trackeventList.getLength(); k++)
            {
                if(trackeventList.item(k) != null)
                {
                    currenTrackEventNode = trackeventList.item(k);
                    Element trackEventElem = (Element) currenTrackEventNode;
                
                    if(trackEventElem.hasAttributes())
                    {
                        if (!trackEventElem.getAttribute("start_time").equals(""))
                        {
                            String trackDuration = trackEventElem.getAttribute("start_time");
                            durationEvent.add(Double.valueOf(trackDuration));
                        }
                    }
                }
            }
            maxValueDuratione = Collections.max(durationEvent);   
            minValueDuratione = Collections.min(durationEvent);  
        }
        return (int) Math.ceil(maxValueDuratione+minValueDuratione);
    }
}
