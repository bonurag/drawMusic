package gui.panel;

import java.awt.Component;
import java.util.HashMap;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class generalInfoTrackGui extends javax.swing.JPanel {

    /**
    * Contains all the components that make up the panel
    */
    private HashMap componentMap;
    
    public generalInfoTrackGui() 
    {
        initComponents();
        createComponentMap();  
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel mainTitleValueLabel
    */
    public void setMainTitleValueLabel(String inputValue)
    {
        mainTitleValueLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel authorsValuesLabel
    */
    public void setAuthorsValuesLabel(String inputValue)
    {
        authorsValuesLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel otherTitleValueLabel
    */
    public void setOtherTitleValueLabel(String inputValue)
    {
        otherTitleValueLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel numberValueLabel
    */
    public void setNumberValueLabel(String inputValue)
    {
        numberValueLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel workTitleValueLabel
    */
    public void setWorkTitleValueLabel(String inputValue)
    {
        workTitleValueLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel workNumberValueLabel
    */
    public void setWorkNumberValueLabel(String inputValue)
    {
        workNumberValueLabel.setText(inputValue);
    }
    
    /**
    * Method for setting the name extracted from the XML file inside the JLabel having a specific name
    * @param  inputValue Value saved in the JLabel genresValueLabel
    */
    public void setGenresValueLabel(String inputValue)
    {
        genresValueLabel.setText(inputValue);
    }
    
    /**
    * Method that generates a list of components present in the JPanel generalInfoTrackGui
    */
    private void createComponentMap()
    {
        componentMap = new HashMap<String,Component>();
        Component[] components = this.getComponents();
        for(int i=0; i < components.length; i++)
        {
            componentMap.put(components[i].getName(), components[i]);
            //System.out.println("Component Name: " + components[i].getName());
        }
    }
    
    /**
    * @param  name The name of the component that we want returned
    * @return The component that is called how the paramter passed in input
    */
    public Component getComponentByName(String name)
    {
        if(componentMap.containsKey(name))
        {
            return (Component) componentMap.get(name);
        }
        else return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTitleLabel = new javax.swing.JLabel();
        mainTitleValueLabel = new javax.swing.JLabel();
        authorsLabel = new javax.swing.JLabel();
        authorsValuesLabel = new javax.swing.JLabel();
        otherTitleLabel = new javax.swing.JLabel();
        otherTitleValueLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        numberValueLabel = new javax.swing.JLabel();
        workTitleLabel = new javax.swing.JLabel();
        workTitleValueLabel = new javax.swing.JLabel();
        workNumberLabel = new javax.swing.JLabel();
        workNumberValueLabel = new javax.swing.JLabel();
        genresLabel = new javax.swing.JLabel();
        genresValueLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(214, 217, 223));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "General Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(500, 170));
        setLayout(null);

        mainTitleLabel.setBackground(new java.awt.Color(255, 255, 255));
        mainTitleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mainTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
        mainTitleLabel.setText("Main Title:");
        mainTitleLabel.setName("mainTitleLabel"); // NOI18N
        add(mainTitleLabel);
        mainTitleLabel.setBounds(10, 20, 64, 18);

        mainTitleValueLabel.setBackground(new java.awt.Color(255, 255, 255));
        mainTitleValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        mainTitleValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        mainTitleValueLabel.setText("mainTitle");
        mainTitleValueLabel.setName("mainTitleValueLabel"); // NOI18N
        add(mainTitleValueLabel);
        mainTitleValueLabel.setBounds(80, 20, 410, 18);

        authorsLabel.setBackground(new java.awt.Color(255, 255, 255));
        authorsLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        authorsLabel.setForeground(new java.awt.Color(0, 0, 0));
        authorsLabel.setText("Authors:");
        authorsLabel.setName("authorsLabel"); // NOI18N
        add(authorsLabel);
        authorsLabel.setBounds(10, 40, 51, 18);

        authorsValuesLabel.setBackground(new java.awt.Color(255, 255, 255));
        authorsValuesLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        authorsValuesLabel.setForeground(new java.awt.Color(0, 0, 0));
        authorsValuesLabel.setText("authors");
        authorsValuesLabel.setName("authorsValuesLabel"); // NOI18N
        add(authorsValuesLabel);
        authorsValuesLabel.setBounds(65, 40, 420, 18);

        otherTitleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        otherTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
        otherTitleLabel.setText("Other Title:");
        otherTitleLabel.setName("otherTitleLabel"); // NOI18N
        add(otherTitleLabel);
        otherTitleLabel.setBounds(10, 60, 73, 18);

        otherTitleValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        otherTitleValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        otherTitleValueLabel.setText("otherTitle");
        otherTitleValueLabel.setName("otherTitleValueLabel"); // NOI18N
        add(otherTitleValueLabel);
        otherTitleValueLabel.setBounds(90, 60, 400, 18);

        numberLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        numberLabel.setForeground(new java.awt.Color(0, 0, 0));
        numberLabel.setText("Number:");
        numberLabel.setName("numberLabel"); // NOI18N
        add(numberLabel);
        numberLabel.setBounds(10, 80, 50, 18);

        numberValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        numberValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        numberValueLabel.setText("number");
        numberValueLabel.setName("numberValueLabel"); // NOI18N
        add(numberValueLabel);
        numberValueLabel.setBounds(70, 80, 410, 18);

        workTitleLabel.setBackground(new java.awt.Color(255, 255, 255));
        workTitleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        workTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
        workTitleLabel.setText("Work Title:");
        workTitleLabel.setName("workTitleLabel"); // NOI18N
        add(workTitleLabel);
        workTitleLabel.setBounds(10, 100, 67, 18);

        workTitleValueLabel.setBackground(new java.awt.Color(255, 255, 255));
        workTitleValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        workTitleValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        workTitleValueLabel.setText("workTitle");
        workTitleValueLabel.setName("workTitleValueLabel"); // NOI18N
        add(workTitleValueLabel);
        workTitleValueLabel.setBounds(80, 100, 400, 18);

        workNumberLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        workNumberLabel.setForeground(new java.awt.Color(0, 0, 0));
        workNumberLabel.setText("Work Number:");
        workNumberLabel.setName("workNumberLabel"); // NOI18N
        add(workNumberLabel);
        workNumberLabel.setBounds(10, 120, 85, 18);

        workNumberValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        workNumberValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        workNumberValueLabel.setText("workNumber");
        workNumberValueLabel.setName("workNumberValueLabel"); // NOI18N
        add(workNumberValueLabel);
        workNumberValueLabel.setBounds(100, 120, 390, 18);

        genresLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        genresLabel.setForeground(new java.awt.Color(0, 0, 0));
        genresLabel.setText("Genres:");
        genresLabel.setName("genresLabel"); // NOI18N
        add(genresLabel);
        genresLabel.setBounds(10, 140, 45, 18);

        genresValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        genresValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        genresValueLabel.setText("genresValue");
        genresValueLabel.setName("genresValueLabel"); // NOI18N
        add(genresValueLabel);
        genresValueLabel.setBounds(60, 140, 430, 18);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorsLabel;
    private javax.swing.JLabel authorsValuesLabel;
    private javax.swing.JLabel genresLabel;
    private javax.swing.JLabel genresValueLabel;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel mainTitleValueLabel;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel numberValueLabel;
    private javax.swing.JLabel otherTitleLabel;
    private javax.swing.JLabel otherTitleValueLabel;
    private javax.swing.JLabel workNumberLabel;
    private javax.swing.JLabel workNumberValueLabel;
    private javax.swing.JLabel workTitleLabel;
    private javax.swing.JLabel workTitleValueLabel;
    // End of variables declaration//GEN-END:variables
}
