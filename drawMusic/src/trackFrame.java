
import java.awt.Dimension;
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
    int numberOfTrackElement = 0;
    int pstWidth = 0;
    int pstHeight = 0;
    int gniWidth = 0;
    int gniHeight = 0;
    int xSize = 0;
    int ySize = 0;
    
    public trackFrame(String mainTitleInput, LinkedHashMap<String, String> authorsInput, LinkedHashMap<String, String> workTitleInput, LinkedHashMap<Integer, LinkedHashMap<String, String>> trackInput)
    { 
        numberOfTrackElement = trackInput.size();
        panelGeneralInfo = new generalInfoTrackGui();
        panelSingleTrack = new singleTrackGui();
        
        Dimension pst = panelSingleTrack.getPreferredSize();        
        pstWidth = pst.width;
        pstHeight = pst.height;
        
        Dimension gni = panelGeneralInfo.getPreferredSize();
        gniWidth = gni.width;
        gniHeight = gni.height;
        
        xSize = gniWidth+100;
        ySize = ((pstHeight * numberOfTrackElement)+gniHeight+20);
        
        
        System.out.println("xSize: " + xSize);
        System.out.println("ySize: " + ySize);
        
        //Set Main Title
        mainTitleInput = !mainTitleInput.equals("") ? mainTitleInput : "Titolo non presente";
        panelGeneralInfo.setMainTitleValueLabel(mainTitleInput);
        
        //Set List Authors
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
            authorResult = "Nessun autore disponibile";
            panelGeneralInfo.setAuthorsValuesLabel(authorResult);
        }
        
        //Set List Work Title
        String workTitleResult = "";
        if(workTitleInput.size() > 0)
        {            
            for(String index : workTitleInput.keySet())
            {
                System.out.println("keySet: " + index);
                workTitleResult += workTitleInput.get(index)+"; ";
            }
            panelGeneralInfo.setWorkTitleValueLabel(workTitleResult.substring(0, workTitleResult.length()-2));
        }
        else
        {
            workTitleResult = "Nessun titolo disponibile";
            panelGeneralInfo.setWorkTitleValueLabel(workTitleResult);
        }
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(panelGeneralInfo);
        for(int i=0; i<trackInput.size(); i++)
        {
            panelSingleTrack = new singleTrackGui();
            panelSingleTrack.setBorderTitle("Track "+(i+1));
            add(panelSingleTrack);
        }
    }
    
    public void showUI()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setSize(xSize, ySize);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
