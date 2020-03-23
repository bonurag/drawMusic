
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.JPanel;

/*
    This Class it was created to manage the contents of the Cartesian diagram
 */

/**
 *
 * @author Giuseppe
 */
public class cartesianGui extends JPanel
{
    // x-axis coord constants
    public static final int X_AXIS_FIRST_X_COORD = 50;
    public static final int X_AXIS_SECOND_X_COORD = 600;
    public static final int X_AXIS_Y_COORD = 600;

    // y-axis coord constants
    public static final int Y_AXIS_FIRST_Y_COORD = 50;
    public static final int Y_AXIS_SECOND_Y_COORD = 600;
    public static final int Y_AXIS_X_COORD = 50;

    //arrows of axis are represented with "hipotenuse" of triangle
    //now we are define length of cathetas of that triangle
    public static final int FIRST_LENGHT = 10;
    public static final int SECOND_LENGHT = 5;

    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 25;
    
    // width of bar inside the diagram
    public static final int HISTOGRAM_BAR_WIDTH = 30;
    
    // distance from top of panel to the max highest bar
    public static final int OFFSET_BAR_TO_TOP_PANEL = 100;
    
    public Boolean enableView = false;
    
    public Boolean enableHorizontalLabel = true;
    
    public Boolean enableVerticalLabel = false;
    
    public String yAxisName = "Y";
    
    public String xAxisName = "X";
    
    private LinkedHashMap<String, Integer> inputData;

    public cartesianGui(LinkedHashMap<String, Integer> inputData)
    {   
        if(inputData != null)
            this.inputData = inputData;
    }

    public void setViewValueOnBar(Boolean enable)
    {
        enableView = enable;
    }
    
    public void setVerticalLabel(Boolean enable)
    {
        enableVerticalLabel = enable;
    }
    
    public void setHorizontalLabel(Boolean enable)
    {
        enableHorizontalLabel = enable;
    }
    
    public void setyAxisName(String axisName)
    {
        yAxisName = axisName;
    }
    
    public void setxAxisName(String axisName)
    {
        xAxisName = axisName;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Font fn = new Font("Comic Sans MS", Font.PLAIN, 12);
        g2.setFont(fn);
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Stroke defaultStroke = g2.getStroke();
        
        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD, Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

        // x-axis arrow
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD - SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT, X_AXIS_Y_COORD + SECOND_LENGHT, X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);

        // y-axis arrow
        g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
        g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT, Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT, Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

        // draw origin Point
        g2.fillOval( X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2), Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2), ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

        // draw axis name value
        g2.drawString(xAxisName, X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2, X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString(yAxisName, Y_AXIS_X_COORD - 40, Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
        
        g2.drawString("(0,0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE, Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);
            
        ArrayList<String> xCoordList = null; 
        if(!inputData.keySet().isEmpty())
            xCoordList = new ArrayList(inputData.keySet());
        
        Boolean isDurationList = false;
        Boolean disableYLabelView = true;
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
        
        Integer yGapValue = drawMusicData_Utils.getMinGapInValue(yCoordList);
        if(yGapValue <= 5)
        {
            disableYLabelView = false;
            setViewValueOnBar(true);
        }
            
        int maxValuePitch = Collections.max(inputData.values());
        Set<Integer> ySetValue = new HashSet<Integer>(yCoordList); 
        ArrayList<Integer> ySetValueList = new ArrayList<>();
        for (Iterator<Integer> it = ySetValue.iterator(); it.hasNext();)
        {
            Integer f = it.next();
            ySetValueList.add(f);
        }
        ySetValueList.add(0);

        //int xCoordValue = xCoordList.size()+1;
        //int yCoordValue = ySetValueList.size();

        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / (xCoordList.size()+1);
        
        float[] dash = { 4f, 0f, 2f };
        BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f );
        
        int index_X = 0;
        int height = 0;
        System.out.println("Inside Cartesian Class");
        for (int i = 1; i < xCoordList.size()+1; i++)
        {                
            g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                X_AXIS_Y_COORD - SECOND_LENGHT,
                X_AXIS_FIRST_X_COORD + (i * xLength),
                X_AXIS_Y_COORD);

            //draw dash line X-axis
            g2.setStroke(bs);
            g2.setColor(new Color(191,191,191));
            g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_Y_COORD - SECOND_LENGHT,
                    X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_FIRST_X_COORD);     
     
            g2.setColor(Color.BLACK);
            String xLabel = !isDurationList ? xCoordList.get(index_X) : "1/"+xCoordList.get(index_X); 
            int widthValueXlabel = g.getFontMetrics().stringWidth(xLabel);
            int heightValueXlabel = g.getFontMetrics().getAscent();

            if(enableVerticalLabel)
            {
                AffineTransform defaultAt = g2.getTransform();
                AffineTransform at = new AffineTransform();
                at.rotate(- Math.PI / 2);
                g2.setTransform(at);
                g2.drawString(xLabel, -685, X_AXIS_FIRST_X_COORD + (i * xLength) + (heightValueXlabel/2));
                g2.setTransform(defaultAt);
            }
            if(enableHorizontalLabel)
            {    
                g2.drawString(xLabel,
                    X_AXIS_FIRST_X_COORD + (i * xLength) - (widthValueXlabel/2),
                    X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
            }
 
            height = Y_AXIS_SECOND_Y_COORD - OFFSET_BAR_TO_TOP_PANEL;
            double barHeight = ((double)inputData.get(xCoordList.get(index_X)) / (double) maxValuePitch) * (double)height;

            g2.setStroke(defaultStroke);  
            g2.setColor(Color.RED);
            g2.fillRect(X_AXIS_FIRST_X_COORD + (i * xLength) - HISTOGRAM_BAR_WIDTH/2,
                X_AXIS_SECOND_X_COORD - (int)barHeight, 
                HISTOGRAM_BAR_WIDTH, 
                (int)barHeight);
                     
            if(enableView)
            {
                g2.setColor(Color.BLACK);
                int widthValueYLabel = g2.getFontMetrics().stringWidth(Integer.toString(inputData.get(xCoordList.get(index_X))));
                g2.drawString(Integer.toString(inputData.get(xCoordList.get(index_X))),X_AXIS_FIRST_X_COORD + (i * xLength) - (widthValueYLabel/2), X_AXIS_SECOND_X_COORD - (int)barHeight - 10);
            }
            
            g2.setColor(Color.BLACK);
            index_X += 1;           
        }

        //draw y-axis numbers
        for (int i = 1; i < ySetValueList.size(); i++)
        {
            double barHeight = ((double)ySetValueList.get(i-1) / (double) maxValuePitch) * (double)height;
            
            if(disableYLabelView)
            {
                g2.drawLine(Y_AXIS_X_COORD,
                    Y_AXIS_SECOND_Y_COORD - (int)barHeight,
                    Y_AXIS_X_COORD + SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (int)barHeight);
            
                //draw dash line Y-axis
                g2.setStroke(bs);
                g2.setColor(new Color(191,191,191));
                g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT,
                    X_AXIS_SECOND_X_COORD - (int)barHeight,
                    Y_AXIS_SECOND_Y_COORD,
                    X_AXIS_SECOND_X_COORD - (int)barHeight);
                g2.setColor(Color.BLACK);       
            
                if(i%2 != 0)
                    g2.drawString(Integer.toString(ySetValueList.get(i-1)), 630 - AXIS_STRING_DISTANCE, Y_AXIS_SECOND_Y_COORD - (int)barHeight - 5);               
                else
                    g2.drawString(Integer.toString(ySetValueList.get(i-1)),Y_AXIS_X_COORD - AXIS_STRING_DISTANCE, Y_AXIS_SECOND_Y_COORD - (int)barHeight + 8); 
            }
        }
    }
}