package gui.panel;

import dataUtils.drawMusicData_Utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.JPanel;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class cartesianGui extends JPanel
{
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int SX_MARGIN_X_AXIS = 50;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int X_AXIS_LENGTH = 600;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int TOP_MARGIN_X_AXIS = 600;

    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int TOP_MARGIN_Y_AXIS = 50;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int Y_AXIS_LENGTH = 600;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int SX_MARGIN_Y_AXIS = 50;

    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int FIRST_CATETO = 10;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int SECOND_CATETO = 5;

    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int ORIGIN_DIAMETER = 6;

    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int NORMAL_DISTANCE_STRING_TO_X_AXIS = 25;
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int MAJOR_DISTANCE_STRING_TO_X_AXIS = 40;

    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int MINIMUM_HISTOGRAM_BAR_WIDTH = 6; //HISTOGRAM_BAR_WIDTH_MIN
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int MEDIUM_HISTOGRAM_BAR_WIDTH = 12; //HISTOGRAM_BAR_WIDTH_MED
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int LARGE_HISTOGRAM_BAR_WIDTH = 24; //HISTOGRAM_BAR_WIDTH
    
    /**
    * Constant indicating that an element for which no results were found [N / A - Not Available]
    */
    public static final int OFFSET_BAR_TO_TOP_PANEL = 100;
    
    /**
    * Used for dynamically set of height of frame
    */
    private Boolean enableView = false;
    
    /**
    * Used for dynamically set of height of frame
    */
    private Boolean disableXLabelView = true;
    
    /**
    * Used for dynamically set of height of frame
    */
    private Boolean disableyYLabelView = true;
    
    /**
    * Used for dynamically set of height of frame
    */
    private String yAxisName = "Y";
    
    /**
    * Used for dynamically set of height of frame
    */
    private String xAxisName = "X";
    
    /**
    * Used for dynamically set of height of frame
    */
    private Color AXIS_COLOR;
    
    /**
    * Used for dynamically set of height of frame
    */
    private LinkedHashMap<String, Integer> inputData;

    public cartesianGui(LinkedHashMap<String, Integer> inputData)
    {   
        if(inputData != null)
            this.inputData = inputData;
    }
    
    public cartesianGui()
    {   

    }
    
    /**
    * Method to enable or disable on the screen the visibility of labels on the bars of the histogram
    * @param  enable True for view otherwise False
    */
    public void setViewValueOnBar(Boolean enable)
    {
        enableView = enable;
    }
    
    /**
    * Method to enable or disable on the screen the horizontal dotted lines and the labels on the Y axis
    * @param  enable True for view otherwise False
    */
    public void setDisableXLabelView(Boolean enable)
    {
        disableXLabelView = enable;
    }
    
    /**
    * Method to enable or disable on the screen the vertical dotted lines and the labels on the X axis
    * @param  enable True for view otherwise False
    */
    public void setDisableYLabelView(Boolean enable)
    {
        disableyYLabelView = enable;
    }
    
    /**
    * Method for change the name on cartesian graph of Y axis
    * @param  y_AxisName Value of the name we want to assign to the Y axis 
    */
    public void setyAxisName(String y_AxisName)
    {
        yAxisName = y_AxisName;
    }
    
    /**
    * Method for change the name on cartesian graph of X axis
    * @param  y_AxisName Value of the name we want to assign to the X axis
    */
    public void setxAxisName(String x_AxisName)
    {
        xAxisName = x_AxisName;
    }
    
    /**
    * Method for setting the color of istogram bar
    * @param  inputColor Color of bar
    */
    public void setBarColor(Color inputColor)
    {
        AXIS_COLOR = inputColor;    
    }
    
    /**
    * Method that return if the value on istogram bar are visible or not
    * @retun True if label are visible, False otherwise
    */
    public Boolean getViewValueOnBar()
    {
        return enableView;
    }

    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @retun Value saved in the JLabel otherTitleValueLabel
    */
    public Boolean getDisableXLabelView()
    {
        return disableXLabelView;
    }

    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @retun Value saved in the JLabel otherTitleValueLabel
    */
    public Boolean getDisableYLabelView()
    {
        return disableyYLabelView;
    }
 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        Font fn = new Font("Comic Sans MS", Font.PLAIN, 10);
        g2.setFont(fn);
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Stroke defaultStroke = g2.getStroke();
        
        // DRAW X-AXIS
        g2.drawLine(SX_MARGIN_X_AXIS, TOP_MARGIN_X_AXIS, X_AXIS_LENGTH, TOP_MARGIN_X_AXIS);
        
        // DRAW X-AXIS
        g2.drawLine(SX_MARGIN_Y_AXIS, TOP_MARGIN_Y_AXIS, SX_MARGIN_Y_AXIS, Y_AXIS_LENGTH);

        // X-AXIS ARROW
        g2.drawLine(X_AXIS_LENGTH - FIRST_CATETO, TOP_MARGIN_X_AXIS - SECOND_CATETO, X_AXIS_LENGTH, TOP_MARGIN_X_AXIS);
        g2.drawLine(X_AXIS_LENGTH - FIRST_CATETO, TOP_MARGIN_X_AXIS + SECOND_CATETO, X_AXIS_LENGTH, TOP_MARGIN_X_AXIS);

        // Y-AXIS ARROW
        g2.drawLine(SX_MARGIN_Y_AXIS - SECOND_CATETO, TOP_MARGIN_Y_AXIS + FIRST_CATETO, SX_MARGIN_Y_AXIS, TOP_MARGIN_Y_AXIS);
        g2.drawLine(SX_MARGIN_Y_AXIS + SECOND_CATETO, TOP_MARGIN_Y_AXIS + FIRST_CATETO, SX_MARGIN_Y_AXIS, TOP_MARGIN_Y_AXIS);

        // DRAW ORIGIN POINT
        g2.fillOval( SX_MARGIN_X_AXIS - (ORIGIN_DIAMETER / 2), Y_AXIS_LENGTH - (ORIGIN_DIAMETER / 2), ORIGIN_DIAMETER, ORIGIN_DIAMETER);
        
        // DRAW ORIGIN LABEL
        g2.drawString("(0,0)", SX_MARGIN_X_AXIS - NORMAL_DISTANCE_STRING_TO_X_AXIS, Y_AXIS_LENGTH + NORMAL_DISTANCE_STRING_TO_X_AXIS);
        
        // AXIS NAME VALUE
        g2.drawString(xAxisName, X_AXIS_LENGTH - NORMAL_DISTANCE_STRING_TO_X_AXIS / 2, TOP_MARGIN_X_AXIS + NORMAL_DISTANCE_STRING_TO_X_AXIS);
        g2.drawString(yAxisName, SX_MARGIN_Y_AXIS - 40, TOP_MARGIN_Y_AXIS + NORMAL_DISTANCE_STRING_TO_X_AXIS / 2);
  
        ArrayList<String> xCoordList = null; 
        if(!inputData.keySet().isEmpty())
            xCoordList = new ArrayList(inputData.keySet());
        
        Boolean isDurationList = false;  //Indicates if it is the list of durations     
        if(xCoordList.size() > 1 && !xCoordList.isEmpty())
        {
            if(xCoordList.contains("1")|| xCoordList.contains("2") || xCoordList.contains("4") || xCoordList.contains("8") || xCoordList.contains("16") ||
               xCoordList.contains("32") || xCoordList.contains("64") || xCoordList.contains("128") || xCoordList.contains("256") || xCoordList.contains("512"))   
            {
                ArrayList<Integer> tmpList = new ArrayList<>();
                isDurationList = true;
                for(int i=0; i<xCoordList.size(); i++)
                {
                    tmpList.add(Integer.parseInt(xCoordList.get(i)));
                }
                Collections.sort(tmpList);
                xCoordList = new ArrayList<>();
                for(int j=0; j<tmpList.size(); j++)
                {
                    xCoordList.add(Integer.toString(tmpList.get(j)));
                }
            }
        }
        
        ArrayList<Integer> yCoordList = null; 
        if(!inputData.keySet().isEmpty())
            yCoordList = new ArrayList(inputData.values());
        
        Integer yGapValue = 0;
        if(yCoordList.size() > 1)
            yGapValue = drawMusicData_Utils.getMinGapInValue(yCoordList);
   
        int maxValuePitch = Collections.max(inputData.values());
        Set<Integer> ySetValue = new HashSet<Integer>(yCoordList); 
        ArrayList<Integer> ySetValueList = new ArrayList<>();
        for (Iterator<Integer> it = ySetValue.iterator(); it.hasNext();)
        {
            Integer f = it.next();
            ySetValueList.add(f);
        }
        Collections.sort(ySetValueList);
        int minValueYaxis = Collections.min(ySetValueList);
        
        /*
        if((yGapValue <= 4 || minValueYaxis < 5) && yCoordList.size() > 1)
        {
            disableXLabelView = false;
            setViewValueOnBar(true);
        }
        */
        
        int xLength = (X_AXIS_LENGTH - SX_MARGIN_X_AXIS) / (xCoordList.size()+1);
        float[] dash = { 4f, 0f, 2f };
        BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f );
        
        int index_X = 0;
        int height = 0;

        //Prints the content on the X axis on the screen
        for (int i = 1; i < xCoordList.size()+1; i++)
        {               
            g2.drawLine(SX_MARGIN_X_AXIS + (i * xLength), TOP_MARGIN_X_AXIS - SECOND_CATETO, SX_MARGIN_X_AXIS + (i * xLength), TOP_MARGIN_X_AXIS);
            
            if(disableyYLabelView)
            {
                //Draw dash line X-axis
                g2.setStroke(bs);
                g2.setColor(new Color(191,191,191));
                g2.drawLine(SX_MARGIN_X_AXIS + (i * xLength), TOP_MARGIN_X_AXIS - SECOND_CATETO, SX_MARGIN_X_AXIS + (i * xLength), SX_MARGIN_X_AXIS);   
            }

            g2.setColor(Color.BLACK);
            String xLabel = !isDurationList ? xCoordList.get(index_X) : "1/"+xCoordList.get(index_X); 
            int widthValueXlabel = g.getFontMetrics().stringWidth(xLabel);
            
            height = Y_AXIS_LENGTH - OFFSET_BAR_TO_TOP_PANEL;
            double barHeight = ((double)inputData.get(xCoordList.get(index_X)) / (double) maxValuePitch) * (double)height;
                
            if(xCoordList.size() <= 18)
            {
                g2.drawString(xLabel, SX_MARGIN_X_AXIS + (i * xLength) - (widthValueXlabel/2), TOP_MARGIN_X_AXIS + NORMAL_DISTANCE_STRING_TO_X_AXIS);
                g2.setStroke(defaultStroke);  
                g2.setColor(AXIS_COLOR);
                g2.fillRect(SX_MARGIN_X_AXIS + (i * xLength) - (LARGE_HISTOGRAM_BAR_WIDTH/2), X_AXIS_LENGTH - (int)barHeight, LARGE_HISTOGRAM_BAR_WIDTH, (int)barHeight);
            }
            else if(xCoordList.size() >= 18 && xCoordList.size() <= 25)
            {
                if(i % 2 == 0)
                {
                    g2.drawString(xLabel, SX_MARGIN_X_AXIS + (i * xLength) - (widthValueXlabel/2), TOP_MARGIN_X_AXIS + MAJOR_DISTANCE_STRING_TO_X_AXIS);
                }
                else
                {
                    g2.drawString(xLabel, SX_MARGIN_X_AXIS + (i * xLength) - (widthValueXlabel/2), TOP_MARGIN_X_AXIS + NORMAL_DISTANCE_STRING_TO_X_AXIS);
                }
                
                g2.setStroke(defaultStroke);  
                g2.setColor(AXIS_COLOR);
                g2.fillRect(SX_MARGIN_X_AXIS + (i * xLength) - (MEDIUM_HISTOGRAM_BAR_WIDTH/2), X_AXIS_LENGTH - (int)barHeight, MEDIUM_HISTOGRAM_BAR_WIDTH, (int)barHeight);
            }
            else if(xCoordList.size() > 25)
            {
                if(i % 2 == 0)
                {
                    g2.drawString(xLabel, SX_MARGIN_X_AXIS + (i * xLength) - (widthValueXlabel/2), TOP_MARGIN_X_AXIS + MAJOR_DISTANCE_STRING_TO_X_AXIS);
                }
                else
                {
                    g2.drawString(xLabel, SX_MARGIN_X_AXIS + (i * xLength) - (widthValueXlabel/2), TOP_MARGIN_X_AXIS + NORMAL_DISTANCE_STRING_TO_X_AXIS);
                }
                
                g2.setStroke(defaultStroke);  
                g2.setColor(AXIS_COLOR);
                g2.fillRect(SX_MARGIN_X_AXIS + (i * xLength) - (MINIMUM_HISTOGRAM_BAR_WIDTH/2), X_AXIS_LENGTH - (int)barHeight, MINIMUM_HISTOGRAM_BAR_WIDTH, (int)barHeight);
            }
            
            if(enableView)
            {
                g2.setColor(Color.BLACK);
                int widthValueYLabel = g2.getFontMetrics().stringWidth(Integer.toString(inputData.get(xCoordList.get(index_X))));
                g2.drawString(Integer.toString(inputData.get(xCoordList.get(index_X))),SX_MARGIN_X_AXIS + (i * xLength) - (widthValueYLabel/2), X_AXIS_LENGTH - (int)barHeight - 10);
            }
            
            g2.setColor(Color.BLACK);
            index_X += 1;           
        }

        //Prints the content on the Y axis on the screen
        for (int k = 0; k < ySetValueList.size(); k++)
        {
            double barHeight = ((double)ySetValueList.get(k) / (double) maxValuePitch) * (double)height;
            if(disableXLabelView)
            {
                g2.drawLine(SX_MARGIN_Y_AXIS, Y_AXIS_LENGTH - (int)barHeight, SX_MARGIN_Y_AXIS + SECOND_CATETO, Y_AXIS_LENGTH - (int)barHeight);
            
                //Draw dash line Y-axis
                g2.setStroke(bs);
                g2.setColor(new Color(191,191,191));
                g2.drawLine(SX_MARGIN_Y_AXIS + SECOND_CATETO, X_AXIS_LENGTH - (int)barHeight, Y_AXIS_LENGTH, X_AXIS_LENGTH - (int)barHeight);
                g2.setColor(Color.BLACK);       
            
                if(k % 2 == 0) //DX elements of the graph
                {
                    g2.drawString(Integer.toString(ySetValueList.get(k)), 630 - NORMAL_DISTANCE_STRING_TO_X_AXIS, Y_AXIS_LENGTH - (int)barHeight + 10);
                }                   
                else //SX elements of the graph
                {
                    g2.drawString(Integer.toString(ySetValueList.get(k)),SX_MARGIN_Y_AXIS - NORMAL_DISTANCE_STRING_TO_X_AXIS, Y_AXIS_LENGTH - (int)barHeight);
                }         
            }
        }
    }
}
