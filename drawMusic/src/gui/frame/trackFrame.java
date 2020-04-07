package gui.frame;

import gui.panel.generalInfoTrackGui;
import gui.panel.singleTrackGui;
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class trackFrame extends JFrame
{
    /**
    * Instance of singleTrackGui
    */
    singleTrackGui panelSingleTrack;
    
    /**
    * Instance of generalInfoTrackGui
    */
    generalInfoTrackGui panelGeneralInfo;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    private static final String EMPTY_VALUE = "N/A";
    
    /**
    * Used for dynamically set of height of frame
    */
    private int ySize = 0;
    
    /**
    * Used for check if there are data to view or not
    */
    private Boolean dataPresent = true;
    
    /**
    * Inside the class constructor, there are computed and extract all the general information of XML file and the tracks indicated inside him
    * @param  mainTitleInput Value of title present in XML file
    * @param  authorsInput List of authors presents in XML file and the role of the author within the composition
    * @param  otherTitleInput List of other title presents in XML file, used to represent alternative titles of the piece
    * @param  numberInput Value of number, provides the position of the piece within the sorted collection of pieces it belongs to
    * @param  workTitleInput List of work title presents in XML file, contains the title of the collection the piece belongs to
    * @param  workNumberInput Value of work number present in XML file, contains the catalog number of the whole composition this piece belongs to
    * @param  genresInput List of genres refer to the track and not to the piece
    * @param  trackInput List of track presents inside de XML file
    */
    public trackFrame(String mainTitleInput,
                        LinkedHashMap<String, String> authorsInput,
                        LinkedHashMap<String, String> otherTitleInput,
                        String numberInput,
                        LinkedHashMap<String, String> workTitleInput,
                        String workNumberInput,
                        LinkedHashMap<String, String> genresInput,
                        LinkedHashMap<Integer,LinkedHashMap<String, String>> trackInput)
    { 
        if(!(mainTitleInput.length() > 0) &&  !(authorsInput.size() > 0) && !(otherTitleInput.size() > 0) && !(numberInput.length() > 0) &&
        !(workTitleInput.size() > 0) && !(workNumberInput.length() > 0) && !(genresInput.size() > 0) && !(trackInput.size() > 0))
        {
            dataPresent = false;
        }
        else
        {
            panelGeneralInfo = new generalInfoTrackGui();
            panelSingleTrack = new singleTrackGui();     

            inizializeComponentPanelGeneralInfo();     
            inizializeComponentPanelSingleTrack();
            getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

            //============================================= Set Main Title ============================================================
            if(mainTitleInput.length() > 0)
                panelGeneralInfo.setMainTitleValueLabel(mainTitleInput);

            //============================================= Set List Authors ============================================================
            String authorResult = "";
            String authorType = "";
            if(authorsInput.size() > 0)
            {            
                for(String name : authorsInput.keySet())
                {
                    authorType = authorsInput.get(name);
                    if(name.contains(","))
                        name = name.replaceAll(",", "");
                    authorType = !authorType.equals("") ? " ("+authorType+")" : "";
                    authorResult += name+authorType+"; ";
                }
                panelGeneralInfo.setAuthorsValuesLabel(authorResult.substring(0, authorResult.length()-2));
            }

            //============================================= Set List Other Title ============================================================
            String otherTitleResult = "";
            if(otherTitleInput.size() > 0)
            {            
                for(String index : otherTitleInput.keySet())
                {
                    otherTitleResult += otherTitleInput.get(index)+"; ";
                }
                panelGeneralInfo.setOtherTitleValueLabel(otherTitleResult.substring(0, otherTitleResult.length()-2));
            }

            //============================================= Set NUmber ============================================================
            if(numberInput.length() > 0)
                panelGeneralInfo.setNumberValueLabel(numberInput);

            //============================================= Set List Work Title ============================================================       
            String workTitleResult = "";
            if(workTitleInput.size() > 0)
            {            
                for(String index : workTitleInput.keySet())
                {
                    workTitleResult += workTitleInput.get(index)+"; ";
                }
                panelGeneralInfo.setWorkTitleValueLabel(workTitleResult.substring(0, workTitleResult.length()-2));
            }

            //============================================= Set Work Number ============================================================         
            if(workNumberInput.length() > 0)
                panelGeneralInfo.setWorkNumberValueLabel(workNumberInput);

            //============================================= Set List Genres ============================================================
            String genreName = "";
            String genresNameResult = "";
            if(genresInput.size() > 0)
            {            
                for(String name : genresInput.keySet())
                {
                    genreName = genresInput.get(name);
                    genresNameResult += genreName+"; ";
                }
                panelGeneralInfo.setGenresValueLabel(genresNameResult.substring(0, genresNameResult.length()-2));
            }

            if(mainTitleInput.length() > 0 || authorsInput.size() > 0 || otherTitleInput.size() > 0 || numberInput.length() > 0 || workTitleInput.size() > 0 || workNumberInput.length() > 0 || genresInput.size() > 0)
                add(panelGeneralInfo);

            //============================================= Set TrackList Element ============================================================
            if(trackInput.size() > 0)
            {
                for(int i=0; i<trackInput.size(); i++)
                {
                    panelSingleTrack = new singleTrackGui();
                    panelSingleTrack.setBorderTitle("Track "+(i+1));
                    if(!trackInput.get(i).isEmpty())
                    {
                        for(String k : trackInput.get(i).keySet())
                        {
                            //====================== Set Track Name ====================== 
                            if(k.equals("file_name"))
                            {
                                String fileName = trackInput.get(i).get(k);                       
                                if(!fileName.equals(""))
                                {
                                    if(fileName.contains("/"))
                                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                                    if(fileName.contains("."))
                                        fileName = fileName.substring(0, fileName.lastIndexOf("."));
                                    panelSingleTrack.setFileNameValueLabel(fileName);
                                }
                            }  
                            else if(!trackInput.get(i).containsKey("file_name"))
                            {
                                panelSingleTrack.setFileNameValueLabel(EMPTY_VALUE);
                            }

                            //====================== Set Track Duration ======================
                            if(k.equals("track_duration"))
                            {
                                String trackDuration = trackInput.get(i).get(k);
                                if(!trackDuration.equals(""))
                                {
                                    panelSingleTrack.setDurationValueLabel(trackDuration);
                                }
                            } 
                            else if(!trackInput.get(i).containsKey("track_duration"))
                            {
                                panelSingleTrack.setDurationValueLabel(EMPTY_VALUE);
                            }

                            //====================== Set Track File Format ======================
                            if(k.equals("file_format"))
                            {
                                String fileFormat = trackInput.get(i).get(k);
                                if(!fileFormat.equals(""))
                                {
                                    panelSingleTrack.setFileFormatValueLabel(fileFormat.substring( 0, fileFormat.indexOf("_")).toUpperCase());
                                }
                            }
                            else if(!trackInput.get(i).containsKey("file_format"))
                            {
                                panelSingleTrack.setFileFormatValueLabel(EMPTY_VALUE);
                            }

                            //====================== Set Track Encoding File Format ======================
                            if(k.equals("encoding_format"))
                            {
                                String encodingFormat = trackInput.get(i).get(k);
                                if(!encodingFormat.equals(""))
                                {
                                    panelSingleTrack.setEncodingFormatValueLabel("."+encodingFormat.substring(encodingFormat.lastIndexOf("_") + 1));
                                }
                            }  
                            else if(!trackInput.get(i).containsKey("encoding_format"))
                            {
                                panelSingleTrack.setEncodingFormatValueLabel(EMPTY_VALUE);
                            }

                            //====================== Set Track Performers ======================
                            if(k.equals("performers")) 
                            {
                                String performers = trackInput.get(i).get(k);
                                if(!performers.equals(""))
                                {
                                    panelSingleTrack.setPerformersValueLabel(performers);
                                }
                            }
                            else if(!trackInput.get(i).containsKey("performers"))
                            {
                                panelSingleTrack.setPerformersValueLabel(EMPTY_VALUE);
                            }

                            //====================== Set Track Genres ======================
                            if(k.equals("trackGenres"))
                            {
                                String genresTrack = trackInput.get(i).get(k);
                                if(!genresTrack.equals(""))
                                {
                                    panelSingleTrack.setGenresSingleTrackValueLabel(genresTrack);
                                }
                            }
                            else if(!trackInput.get(i).containsKey("trackGenres"))
                            {
                                panelSingleTrack.setGenresSingleTrackValueLabel(EMPTY_VALUE);
                            }
                        }
                    }
                    ySize += (int) panelSingleTrack.getPreferredSize().getHeight();
                    add(panelSingleTrack);
                }
                ySize = (int)ySize + (int)panelGeneralInfo.getPreferredSize().getHeight();
            }
        }
    }
    
    /**
    * Used in the main class to make the graph visible at the end of processing
    */
    public void showUI()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);          
        setTitle("XML Additional Information");
        setSize(500, ySize);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    /**
    * @return True if there is data to display False otherwise
    */
    public Boolean getDataPresent()
    {
        return dataPresent;
    }
    
    /**
    * Method to initialize the JLabel of the panelGeneralInfo panel turned on and with a value equal to the constant EMPTY_VALUE
    */
    public void inizializeComponentPanelGeneralInfo()
    {
        panelGeneralInfo.setMainTitleValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("mainTitleLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("mainTitleValueLabel").setVisible(true);
        
        panelGeneralInfo.setAuthorsValuesLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("authorsLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("authorsValuesLabel").setVisible(true);
        
        panelGeneralInfo.setOtherTitleValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("otherTitleLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("otherTitleValueLabel").setVisible(true);
        
        panelGeneralInfo.setNumberValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("numberLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("numberValueLabel").setVisible(true);
        
        panelGeneralInfo.setWorkTitleValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("workTitleLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("workTitleValueLabel").setVisible(true);
        
        panelGeneralInfo.setWorkNumberValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("workNumberLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("workNumberValueLabel").setVisible(true);
        
        panelGeneralInfo.setGenresValueLabel(EMPTY_VALUE);
        panelGeneralInfo.getComponentByName("genresLabel").setVisible(true);
        panelGeneralInfo.getComponentByName("genresValueLabel").setVisible(true);
    }
    
    /**
    * Method to initialize the JLabel of the panelSingleTrack panel turned on and with a value equal to the constant EMPTY_VALUE
    */
    public void inizializeComponentPanelSingleTrack()
    {
        panelSingleTrack.setFileNameValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("fileNameLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("fileNameValueLabel").setVisible(true);
        
        panelSingleTrack.setDurationValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("durationLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("durationValueLabel").setVisible(true);
        
        panelSingleTrack.setFileFormatValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("fileFormatLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("fileFormatValueLabel").setVisible(true);
        
        panelSingleTrack.setEncodingFormatValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("encodingFormatLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("encodingFormatValueLabel").setVisible(true);
        
        panelSingleTrack.setPerformersValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("performersLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("performersValueLabel").setVisible(true);

        panelSingleTrack.setGenresSingleTrackValueLabel(EMPTY_VALUE);
        panelSingleTrack.getComponentSingleTrackByName("genresSingleTrackLabel").setVisible(true);
        panelSingleTrack.getComponentSingleTrackByName("genresSingleTrackValueLabel").setVisible(true);                        
    }
}
