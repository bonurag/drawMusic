
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    //arrows of axis are represented with "hipotenuse" of 
    //triangle
    // now we are define length of cathetas of that triangle
    public static final int FIRST_LENGHT = 10;
    public static final int SECOND_LENGHT = 5;

    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 25;
    
    public static final int HISTOGRAM_BAR_WIDTH = 30;
    
    public TreeMap<String, Integer> inputData;
    
    public Boolean enableView = false;
    
    public cartesianGui(TreeMap<String, Integer> inputData)
    {   
        this.inputData = inputData;
    }
    
    public void setViewValueOnBar(Boolean enable)
    {
        enableView = enable;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Stroke defaultStroke = g2.getStroke();
        
        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD, X_AXIS_Y_COORD,
            X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
            Y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

        // x-axis arrow
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
            X_AXIS_Y_COORD - SECOND_LENGHT,
            X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
            X_AXIS_Y_COORD + SECOND_LENGHT,
            X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);

        // y-axis arrow
        g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
            Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
            Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
        g2.drawLine(Y_AXIS_X_COORD + SECOND_LENGHT,
            Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
            Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

        // draw origin Point
        g2.fillOval(
            X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
            Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
            ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

        // draw text "X" and draw text "Y"
        g2.drawString("PITCH", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
            X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString("Q.TY", Y_AXIS_X_COORD - 35,
            Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);
        
        g2.drawString("(0,0)", X_AXIS_FIRST_X_COORD - AXIS_STRING_DISTANCE,
            Y_AXIS_SECOND_Y_COORD + AXIS_STRING_DISTANCE);
        

        ArrayList<String> xCoordList = new ArrayList(inputData.keySet());
        ArrayList<Integer> yCoordList = new ArrayList(inputData.values());
        int maxValuePitch = Collections.max(inputData.values());

        Set<Integer> ySetValue = new HashSet<Integer>(yCoordList); 
        ArrayList<Integer> ySetValueList = new ArrayList<>();
        for (Iterator<Integer> it = ySetValue.iterator(); it.hasNext();)
        {
            Integer f = it.next();
            ySetValueList.add(f);
        }
        ySetValueList.add(0);

        int xCoordValue = xCoordList.size()+1;
        int yCoordValue = ySetValueList.size();
        
        System.out.println("xCoordNumbers " + xCoordValue);
        System.out.println("yCoordNumbers " + yCoordValue);
        
        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordValue;
        
        float[] dash = { 4f, 0f, 2f };
        BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f );
        // draw x-axis numbers
        int index_X = 0;
        int height = 0;
        
        for (int i = 1; i < xCoordValue; i++) {        
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
            g2.drawString(xCoordList.get(index_X),
                X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
                X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
            
            height = Y_AXIS_SECOND_Y_COORD - Y_AXIS_X_COORD - 50;

            double barHeight = ((double)inputData.get(xCoordList.get(index_X)) / (double) maxValuePitch) * (double)height;
            System.out.println("height " + height);
            System.out.println("maxValuePitch " + maxValuePitch);
            System.out.println("inputData.get(xCoordList.get(i-1) " + (double)inputData.get(xCoordList.get(index_X)));
            System.out.println("barHeight " + barHeight);
            g2.setStroke(defaultStroke);  
            g2.setColor(Color.RED);
            g2.fillRect(X_AXIS_FIRST_X_COORD + (i * xLength) - HISTOGRAM_BAR_WIDTH/2,
                    X_AXIS_SECOND_X_COORD - (int)barHeight, 
                    HISTOGRAM_BAR_WIDTH, 
                    (int)barHeight);
            
            if(enableView)
            {
                g2.setColor(Color.BLACK);
                int widthvALUE = g.getFontMetrics().stringWidth(Integer.toString(inputData.get(xCoordList.get(index_X))));
                g2.drawString(Integer.toString(inputData.get(xCoordList.get(index_X))),X_AXIS_FIRST_X_COORD + (i * xLength) - (widthvALUE/2), X_AXIS_SECOND_X_COORD - (int)barHeight + 20);
            }
            
            g2.setColor(Color.BLACK);
            index_X += 1;           
        }

        //draw y-axis numbers
        for (int i = 1; i < yCoordValue; i++) {
            double barHeight = ((double)ySetValueList.get(i-1) / (double) maxValuePitch) * (double)height;
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
            g2.drawString(Integer.toString(ySetValueList.get(i-1)),
                Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
                Y_AXIS_SECOND_Y_COORD - (int)barHeight);
        }
    }
}
