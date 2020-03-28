
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
class pitchFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;
    int inputDataSize = 0;
    ImageIcon screenShootIcon = new ImageIcon("icon/screenshot.png");
    
    public pitchFrame(Object inputDataWork, Color selectedColor)
    {       
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;
        
        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            JButton saveButton = new JButton();
            saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/screenshot.png"))); 
            saveButton.setToolTipText("Cattura uno screenshoot del grafico!");
            saveButton.setVisible(true);

            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData); 
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("PITCH");
            panel.setyAxisName("Q.TY");
            panel.setBarColor(selectedColor);
            panel.add(saveButton);
            panel.setName("pitchFrame");
            add(panel);
            
            getScreenShoot(saveButton);
        }
    }
    
    public void getScreenShoot(JButton inputButton)
    {
        inputButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));  
                String selectedExtension = fileChooser.getFileFilter().getDescription();

                BufferedImage bufImage = new BufferedImage(panel.getWidth(), panel.getHeight(),BufferedImage.TYPE_INT_RGB);
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                inputButton.setVisible(false);
                panel.paint(bufImage.getGraphics());

                int option = fileChooser.showSaveDialog(null);
                if(option == JFileChooser.APPROVE_OPTION)
                {
                    BufferedImage img = bufImage;
                    try
                    {
                        ImageIO.write(img, selectedExtension, new File(fileChooser.getSelectedFile().getAbsolutePath()+"."+selectedExtension));
                        panel.setBorder(BorderFactory.createEmptyBorder());
                        inputButton.setVisible(true);
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(pitchFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void showUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public int getInputDataSize() {
        return inputDataSize;
    }

    public void setGraphName(String newName) {
        String grapName = "";
        if(newName == "" && newName == null)
            grapName = "Graph";
        else
            grapName = newName;
        setTitle(grapName);
    }
    /*
    public static LinkedHashMap<String, Integer> getPitch(String inputName)
    {
        TreeMap<String, Integer> pitchNoteMap = new TreeMap<>();
        LinkedHashMap<String, Integer> finalPitchNoteMap = new LinkedHashMap<>();
        try
        {
            String fileName = inputName;
            pitchNoteMap = drawMusicData_Utils.getXmlStatisticsPitch(fileName, "A");
            finalPitchNoteMap = drawMusicData_Utils.getOrderedResult(pitchNoteMap, false, drawMusicData_Utils.Rappresentation.ANGLOSASSONE);
        }
        catch (Exception e)
        {
            System.out.println("Exception:" + Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }
        return finalPitchNoteMap;
    }
    */
}
