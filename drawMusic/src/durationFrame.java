
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
class durationFrame extends JFrame
{
    String graphName = "Graph";
    cartesianGui panel;
    int inputDataSize = 0;

    public durationFrame(Object inputDataWork, Color selectedColor)
    {
        LinkedHashMap<String, Integer> inputData = (LinkedHashMap<String, Integer>) inputDataWork;
        if(inputData.containsKey("Empty"))
        {
            inputDataSize = 0;
        }
        else if(inputData.size() > 0 && inputData != null)
        {
            String checkBoxLabel1 = "";
            String checkBoxLabel2  = "";
            inputDataSize = inputData.size();
            panel = new cartesianGui(inputData); 
            
            JButton saveButton = new JButton();
            saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/screenshot.png"))); 
            saveButton.setToolTipText("Cattura uno screenshoot del grafico!");
            saveButton.setVisible(true);
            
            JCheckBox checkBoxEnableLabel = new JCheckBox();
            checkBoxEnableLabel.setVisible(true);
            checkBoxLabel2 = panel.getViewValueOnBar() ? "Disable Draw Line" : "Enalble Draw Line";
            checkBoxEnableLabel.setText(checkBoxLabel2);
            checkBoxEnableLabel.setSelected(panel.getViewValueOnBar());
            
            JCheckBox checkBoxEnableDrawLine= new JCheckBox();
            checkBoxEnableDrawLine.setVisible(true);
            checkBoxLabel1 = panel.getDisableYLabelView() ? "Disable Draw Line" : "Enalble Draw Line";
            checkBoxEnableDrawLine.setText(checkBoxLabel1);
            checkBoxEnableDrawLine.setSelected(panel.getDisableYLabelView());
            
                                 
            panel.setBackground(Color.WHITE);
            panel.setxAxisName("DURATA");
            panel.setyAxisName("Q.TY");
            panel.setBarColor(selectedColor);
            panel.add(saveButton);
            panel.add(checkBoxEnableLabel);
            panel.add(checkBoxEnableDrawLine);
            panel.setName("durationFrame");
            add(panel);

            drawMusicData_Utils.saveScreenShoot(saveButton, panel);
            
            checkBoxEnableLabel.addItemListener(new ItemListener()
            {
                Boolean newValue = false;
                public void itemStateChanged(ItemEvent e)
                {
                    if(checkBoxEnableLabel.isSelected())
                    {
                        checkBoxEnableLabel.setText("Disable Bar Label");
                        panel.setViewValueOnBar(true);
                        panel.repaint();
                    }
                    else
                    {
                        checkBoxEnableLabel.setText("Enable Bar Label");
                        panel.setViewValueOnBar(false);
                        panel.repaint();
                    }
                    
                    System.out.println("Checked? " + checkBoxEnableLabel.isSelected());
                }
            }); 
            
            checkBoxEnableDrawLine.addItemListener(new ItemListener()
            {
                Boolean newValue = false;
                public void itemStateChanged(ItemEvent e)
                {
                    if(checkBoxEnableDrawLine.isSelected())
                    {
                        checkBoxEnableDrawLine.setText("Disable Draw Line");
                        panel.setDisableYLabelView(true);
                        panel.repaint();
                    }
                    else
                    {
                        checkBoxEnableDrawLine.setText("Enable Draw Line");
                        panel.setDisableYLabelView(false);
                        panel.repaint();
                    }
                    
                    System.out.println("Checked? " + checkBoxEnableDrawLine.isSelected());
                }
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
        if(newName == "" && newName == null)
            grapName = "Graph";
        else
            grapName = newName;
        setTitle(grapName);
    }
}
