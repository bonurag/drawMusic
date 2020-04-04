
import java.util.LinkedHashMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class trackFrame extends JFrame
{
    singleTrackGui panelSingleTrack;
    generalInfoTrackGui panelGeneralInfo;
    int xSize = 0;
    int ySize = 0;
    
    public trackFrame(String mainTitleInput,
                        LinkedHashMap<String, String> authorsInput,
                        LinkedHashMap<String, String> otherTitleInput,
                        String numberInput,
                        LinkedHashMap<String, String> workTitleInput,
                        String workNumberInput,
                        LinkedHashMap<String, String> genresInput,
                        LinkedHashMap<Integer,LinkedHashMap<String, String>> trackInput)
    { 
        panelGeneralInfo = new generalInfoTrackGui();
        panelSingleTrack = new singleTrackGui();     

//============================================= Set Main Title ============================================================
        if(mainTitleInput.length() > 0)
        {
            mainTitleInput = !mainTitleInput.equals("") ? mainTitleInput : "Titolo non presente";
            panelGeneralInfo.setMainTitleValueLabel(mainTitleInput);
        }
        else
        {
            panelGeneralInfo.getComponentByName("mainTitleLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("mainTitleValueLabel").setVisible(false);
        }

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
        else
        {
            panelGeneralInfo.getComponentByName("authorsLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("authorsValuesLabel").setVisible(false);
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
        else
        {
            panelGeneralInfo.getComponentByName("otherTitleLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("otherTitleValueLabel").setVisible(false);
        }
        
        //Set Number
        if(numberInput.length() > 0)
        {
            numberInput = !numberInput.equals("") ? numberInput : "Numero non presente";
            panelGeneralInfo.setNumberValueLabel(numberInput);
        }
        else
        {
            panelGeneralInfo.getComponentByName("numberLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("numberValueLabel").setVisible(false);
        }
        
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
        else
        {
            panelGeneralInfo.getComponentByName("workTitleLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("workTitleValueLabel").setVisible(false);
        }

//============================================= Set Work Number ============================================================         
        if(workNumberInput.length() > 0)
        {
            workNumberInput = !workNumberInput.equals("") ? workNumberInput : "Valore non presente";
            panelGeneralInfo.setWorkNumberValueLabel(workNumberInput);
        }
        else
        {
            panelGeneralInfo.getComponentByName("workNumberLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("workNumberValueLabel").setVisible(false);
        }

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
            System.out.println("genresNameResult: " + genresNameResult);
            panelGeneralInfo.setGenresValueLabel(genresNameResult.substring(0, genresNameResult.length()-2));
        }
        else
        {
            panelGeneralInfo.getComponentByName("genresLabel").setVisible(false);
            panelGeneralInfo.getComponentByName("genresValueLabel").setVisible(false);
        }

        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
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
                        if(k.equals("file_name"))
                        {
                            String fileName = trackInput.get(i).get(k);                       
                            if(!fileName.equals(""))
                            {
                                panelSingleTrack.setFileNameValueLabel(fileName.substring(fileName.lastIndexOf("/") + 1));
                            }
                            else
                            {
                                panelSingleTrack.getComponentSingleTrackByName("fileNameLabel").setVisible(false);
                                panelSingleTrack.getComponentSingleTrackByName("fileNameValueLabel").setVisible(false);
                            }
                        }    
                        if(k.equals("track_duration"))
                        {
                            String trackDuration = trackInput.get(i).get(k);
                            if(!trackDuration.equals(""))
                            {
                                panelSingleTrack.setDurationValueLabel(trackDuration);
                            }
                            else
                            {
                                panelSingleTrack.getComponentSingleTrackByName("durationLabel").setVisible(false);
                                panelSingleTrack.getComponentSingleTrackByName("durationValueLabel").setVisible(false);
                            }
                        }    
                        if(k.equals("file_format"))
                        {
                            String fileFormat = trackInput.get(i).get(k);
                            if(!fileFormat.equals(""))
                            {
                                panelSingleTrack.setFileFormatValueLabel(fileFormat);
                            }
                            else
                            {
                                panelSingleTrack.getComponentSingleTrackByName("fileFormatLabel").setVisible(false);
                                panelSingleTrack.getComponentSingleTrackByName("fileFormatValueLabel").setVisible(false);
                            }
                        }

                        if(k.equals("encoding_format"))
                        {
                            String encodingFormat = trackInput.get(i).get(k);
                            if(!encodingFormat.equals(""))
                            {
                                panelSingleTrack.setEncodingFormatValueLabel(encodingFormat);
                            }
                            else
                            {
                                panelSingleTrack.getComponentSingleTrackByName("encodingFormatLabel").setVisible(false);
                                panelSingleTrack.getComponentSingleTrackByName("encodingFormatValueLabel").setVisible(false);
                            }
                        }     
                    }
                }
                ySize += (int) panelSingleTrack.getPreferredSize().getHeight();
                add(panelSingleTrack);
            }
        } 
        int xSizePanelSingleTrack = (int)panelSingleTrack.getPreferredSize().getWidth();
        int xSizePanelGeneralInfo = (int)panelGeneralInfo.getPreferredSize().getWidth();
        System.out.println("xSizePanelSingleTrack: " + xSizePanelSingleTrack);
        System.out.println("xSizePanelGeneralInfo: " + xSizePanelGeneralInfo);
        ySize = (int)ySize + (int)panelGeneralInfo.getPreferredSize().getHeight();
    }
    
    public void showUI()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
        setSize(xSize, ySize);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("XML Additional Information");
        pack();
    }
}
