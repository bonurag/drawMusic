
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
}
