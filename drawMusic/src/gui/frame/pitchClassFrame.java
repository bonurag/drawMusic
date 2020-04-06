package gui.frame;

import dataUtils.drawMusicData_Utils;
import gui.panel.cartesianGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 *
 * @author Giuseppe
 */
public class pitchClassFrame extends JFrame
{
    private String graphName = "Pitch Class Graph";
    cartesianGui panel;
    private int inputDataSize = 0;
    private Color chooseColor = Color.RED;

    public pitchClassFrame(Object inputDataWork)
    {       
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;
        System.out.println("inputData: " + inputData);
        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            String verticalBarLabel = "";
            String xDrawLineLabel  = "";
            String yDrawLineLabel  = "";
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
            verticalBarLabel = panel.getViewValueOnBar() ? "Disable Bar Label" : "Enable Bar Label";
            checkBoxBarLabel.setText(verticalBarLabel);
            checkBoxBarLabel.setToolTipText("En/Dis visualizzazione valori sulle Vertical Bar");
            checkBoxBarLabel.setSelected(panel.getViewValueOnBar());
            
            JCheckBox checkBoxXdrawLine = new JCheckBox();
            checkBoxXdrawLine.setVisible(true);
            xDrawLineLabel = panel.getDisableXLabelView() ? "Disable X-Line" : "Enable X-Line";
            checkBoxXdrawLine.setText(xDrawLineLabel);
            checkBoxXdrawLine.setToolTipText("En/Dis visualizzazione indicatori asse X");
            checkBoxXdrawLine.setSelected(panel.getDisableXLabelView());
            
            JCheckBox checkBoxYdrawLine = new JCheckBox();
            checkBoxYdrawLine.setVisible(true);
            yDrawLineLabel = panel.getDisableYLabelView() ? "Disable Y-Line" : "Enable Y-Line";
            checkBoxYdrawLine.setText(yDrawLineLabel);
            checkBoxYdrawLine.setToolTipText("En/Dis visualizzazione indicatori asse Y");
            checkBoxYdrawLine.setSelected(panel.getDisableYLabelView());
            
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData);
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("PITCH CLASS");
            panel.setyAxisName("Q.TY");
            panel.setBarColor(chooseColor);
            panel.add(saveButton);
            panel.add(checkBoxBarLabel);
            panel.add(checkBoxXdrawLine);
            panel.add(checkBoxYdrawLine);
            panel.add(colorButton);
            panel.add(xmlExportButton);
            panel.setName("pitchClassFrame");
            add(panel);
            
            drawMusicData_Utils.saveScreenShoot(saveButton, panel);
            drawMusicData_Utils.exportXml(xmlExportButton, inputData, "pitch_class", getGraphName());
            
            checkBoxBarLabel.addItemListener((ItemEvent e) ->
            {
                if(checkBoxBarLabel.isSelected())
                {
                    checkBoxBarLabel.setText("Disable Bar Label");
                    panel.setViewValueOnBar(true);
                    panel.repaint();
                }
                else
                {
                    checkBoxBarLabel.setText("Enable Bar Label");
                    panel.setViewValueOnBar(false);
                    panel.repaint();
                }
            }); 
            
            checkBoxXdrawLine.addItemListener((ItemEvent e) ->
            {
                if(checkBoxXdrawLine.isSelected())
                {
                    checkBoxXdrawLine.setText("Disable X-Line");
                    panel.setDisableXLabelView(true);
                    panel.repaint();
                }
                else
                {
                    checkBoxXdrawLine.setText("Enable X-Line");
                    panel.setDisableXLabelView(false);
                    panel.repaint();
                }
            });
                       
            checkBoxYdrawLine.addItemListener((ItemEvent e) ->
            {
                if(checkBoxYdrawLine.isSelected())
                {
                    checkBoxYdrawLine.setText("Disable Y-Line");
                    panel.setDisableYLabelView(true);
                    panel.repaint();
                }
                else
                {
                    checkBoxYdrawLine.setText("Enable Y-Line");
                    panel.setDisableYLabelView(false);
                    panel.repaint();
                }
            });
            
            colorButton.addActionListener((ActionEvent e) ->
            {
                chooseColor = JColorChooser.showDialog(null,"Scegli un colore",Color.RED);
                panel.setBarColor(chooseColor);
                panel.repaint();
            });
        }
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
        if("".equals(newName) && newName == null)
            grapName = "Graph";
        else
            grapName = newName;
        setTitle(grapName);
    }
    
    public String getGraphName()
    {
        return graphName;
    }
}
