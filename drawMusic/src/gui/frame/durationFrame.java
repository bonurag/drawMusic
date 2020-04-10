package gui.frame;

import dataUtils.drawMusicData_Utils;
import gui.panel.cartesianGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class durationFrame extends JFrame
{
    /**
    * Instance of cartesianGui
    */
    cartesianGui panel;
    
    /**
    * Default graph name
    */
    private final String graphName = "Duration Graph";
    
    /**
    * Size of the data calculated by the swing worker
    */
    private int inputDataSize = 0;
    
    /**
    * Default color for chart bars
    */
    private Color chooseColor = Color.RED;
    
    /**
    * Inside the class constructor, everything that the duration graph page must contain has been added,
    * plus other useful methods for passing parameters or interacting with the class itself from the outside.
    * @param  inputDataWork Is an Object that contains the data calculated by the worker
    * @param  inputDurationType The duration to be measured coming from the main GUI, chosen from: CHORD, REST or BOTH
    */
    public durationFrame(Object inputDataWork, String inputDurationType)
    {
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;
        //System.out.println("inputData: " + inputData);
        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData); 
            
            JButton saveButton = new JButton();
            saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/screenshot.png"))); 
            saveButton.setToolTipText("Cattura uno screenshoot del grafico!");
            saveButton.setVisible(true);
            
            JButton colorButton = new JButton();
            colorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pickcolor_small.png"))); 
            colorButton.setToolTipText("Scegli un colore per le barre");
            colorButton.setVisible(true);
            
            JButton xmlExportButton = new JButton();
            xmlExportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xml_export.png"))); 
            xmlExportButton.setToolTipText("Esporta i risultati in un file xml");
            xmlExportButton.setVisible(true);
            
            JCheckBox checkBoxBarLabel = new JCheckBox();
            checkBoxBarLabel.setVisible(true);
            checkBoxBarLabel.setText("Bar Label");
            checkBoxBarLabel.setToolTipText("En/Dis visualizzazione valori sulle Vertical Bar");
            checkBoxBarLabel.setSelected(panel.getViewValueOnBar());
            
            JCheckBox checkBoxXdrawLine = new JCheckBox();
            checkBoxXdrawLine.setVisible(true);
            checkBoxXdrawLine.setText("X-Line");
            checkBoxXdrawLine.setToolTipText("En/Dis visualizzazione indicatori asse X");
            checkBoxXdrawLine.setSelected(panel.getDisableXLabelView());
            
            JCheckBox checkBoxYdrawLine = new JCheckBox();
            checkBoxYdrawLine.setVisible(true);
            checkBoxYdrawLine.setText("Y-Line");
            checkBoxYdrawLine.setToolTipText("En/Dis visualizzazione indicatori asse Y");
            checkBoxYdrawLine.setSelected(panel.getDisableYLabelView());
                                 
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("DURATA");
            panel.setyAxisName("Q.TY");
            panel.setBarColor(chooseColor);
            panel.add(saveButton);
            panel.add(checkBoxBarLabel);
            panel.add(checkBoxXdrawLine);
            panel.add(checkBoxYdrawLine);
            panel.add(colorButton);
            panel.add(xmlExportButton);
            panel.setName("durationFrame");
            add(panel);

            drawMusicData_Utils.saveScreenShoot(saveButton, panel);
            
            String xmlElementName = "";
            if(inputDurationType.equals("CHORD"))     
                xmlElementName = "chord_duration";
            else if(inputDurationType.equals("REST"))
                xmlElementName = "rest_duration";
            else if(inputDurationType.equals("BOTH"))
                xmlElementName = "all_duration";

            drawMusicData_Utils.exportXml(xmlExportButton, inputData, xmlElementName, graphName, inputDurationType);
            
            checkBoxBarLabel.addItemListener((ItemEvent e) ->
            {
                if(checkBoxBarLabel.isSelected())
                {
                    panel.setViewValueOnBar(true);
                    panel.repaint();
                }
                else
                {
                    panel.setViewValueOnBar(false);
                    panel.repaint();
                }
            }); 
            
            checkBoxXdrawLine.addItemListener((ItemEvent e) ->
            {
                if(checkBoxXdrawLine.isSelected())
                {
                    panel.setDisableXLabelView(true);
                    panel.repaint();
                }
                else
                {
                    panel.setDisableXLabelView(false);
                    panel.repaint();
                }
            });
                       
            checkBoxYdrawLine.addItemListener((ItemEvent e) ->
            {
                if(checkBoxYdrawLine.isSelected())
                {
                    panel.setDisableYLabelView(true);
                    panel.repaint();
                }
                else
                {
                    panel.setDisableYLabelView(false);
                    panel.repaint();
                }
            });
            
            colorButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    chooseColor = JColorChooser.showDialog(null,"Scegli un colore",Color.RED);
                    if(chooseColor != null)
                    {
                        panel.setBarColor(chooseColor);
                        panel.repaint();
                    }
                }
            });
        }
    }
    
    /**
    * Used in the main class to make the graph visible at the end of processing
    */
    public void showUI()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    /**
    * @return The size of input data
    */
    public int getInputDataSize()
    {
        return inputDataSize;
    }
    
    /**
    * @param  newName The name we want to give to the graph coming from the test area in the main GUI
    */
    public void setGraphName(String newName)
    {
        String tmpName = "";
        if("".equals(newName) && newName == null)
            tmpName = graphName;
        else
            tmpName = newName;
        setTitle(tmpName);
    }
}
