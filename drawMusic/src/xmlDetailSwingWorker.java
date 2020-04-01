
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class xmlDetailSwingWorker
{
    String mainTitle = "";
    LinkedHashMap<String, String> authorsMap = new LinkedHashMap<>();
    LinkedHashMap<String, String> workTitleMap = new LinkedHashMap<>();
    
    LinkedHashMap<Integer, LinkedHashMap<String, String>> trackMap = new LinkedHashMap<>();
    LinkedHashMap<String, String> trackAttributeMap = null;
    
    public SwingWorker createWorker(String inputName, String inputTag)
    {
        return new SwingWorker<Void, Void>()
        { 
            @Override
            protected Void doInBackground() throws Exception
            {
                try
                { 
                    String fileName = inputName;          
                    Document myXmlDocument = drawMusicData_Utils.getDoc(drawMusicData_Utils.readFile(fileName));

                    XPathFactory myXPathFactory = XPathFactory.newInstance();
                    XPath myXPath = myXPathFactory.newXPath();

                    String xPathTitleExpr = "string(//ieee1599/general/description/main_title)";
                    mainTitle = myXPath.evaluate(xPathTitleExpr, myXmlDocument);


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
                        if(authorsMap.isEmpty())
                        {
                            authorsMap.put("author", "Nessun autore presente");
                        }
                    }
                    else
                    {
                        System.out.println("Nessun File da elaborare");
                    } 
                    
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
                        if(workTitleMap.isEmpty())
                        {
                            workTitleMap.put("work_title", "Nessun work title presente");
                        }
                    }
                    else
                    {
                        System.out.println("Nessun File da elaborare");
                    }
                    
                    String xPathElementTrackList = "//ieee1599/audio/track";
                    NodeList trackList = (NodeList) (myXPath.evaluate(xPathElementTrackList, myXmlDocument, XPathConstants.NODESET));
                    Node currenTrackNode;
                    if(trackList.getLength() > 0)
                    {
                        for (int i = 0; i < trackList.getLength(); i++)
                        {
                            trackAttributeMap = new LinkedHashMap<>();
                            if(trackList.item(i) != null)
                            {
                                currenTrackNode = trackList.item(i);
                                Element trackElem = (Element) currenTrackNode;

                                if(trackElem.hasAttributes())
                                {
                                    if (!trackElem.getAttribute("file_name").equals(""))
                                    {
                                        String trackFileName = trackElem.getAttribute("file_name");
                                        int trackDuration = getDurationFromTrack(trackFileName, myXmlDocument, myXPath);
                                        trackAttributeMap.put("file_name", trackFileName);
                                        trackAttributeMap.put("track_duration", Integer.toString(trackDuration));
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
                            trackMap.put(i, trackAttributeMap);
                        }    
                    }
                    else
                    {
                        System.out.println("Nessun File da elaborare");
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Errore nell'elaborazione del file");
                    Logger.getLogger(testMethod.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(1);
                }
                //return authorsMap;
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
    
    public LinkedHashMap<String, String> getWorkTitleMap()
    {
        return workTitleMap;
    }
    
    public LinkedHashMap<Integer, LinkedHashMap<String, String>> getTrackMap()
    {
        return trackMap;
    }
    
    public int getDurationFromTrack(String trackName, Document inputDocument, XPath inputXPath ) throws XPathExpressionException
    {
        System.out.println("trackName getDurationFromTrack: " + trackName);
        String xPathTrackDurationExpr = "//ieee1599/audio/track[@file_name=\""+trackName+"\"]/track_indexing[@timing_type=\"seconds\"]/track_event";
        System.out.println("xPathTrackDurationExpr: " + xPathTrackDurationExpr);
        NodeList trackeventList = (NodeList) (inputXPath.evaluate(xPathTrackDurationExpr, inputDocument, XPathConstants.NODESET));
        Node currenTrackEventNode;
        ArrayList<Double> durationEvent = null;
        double maxValueDuratione = 0;
        
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
                            System.out.println("trackDuration: " + trackDuration);
                            durationEvent.add(Double.valueOf(trackDuration));
                            //trackAttributeMap.put("trackDuration", trackDuration);
                        }
                    }
                }
            }
            maxValueDuratione = Collections.max(durationEvent);    
        }
        return (int) Math.ceil(maxValueDuratione);
    }
}
