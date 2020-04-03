
import java.awt.Component;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class singleTrackGui extends javax.swing.JPanel
{
    private HashMap componentMapSingleTrack;
    
    public singleTrackGui()
    {
        initComponents();
        createComponentMap();   
    }
    
    public void setBorderTitle(String inputTitle)
    {
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, inputTitle, javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12), new java.awt.Color(0, 0, 0)));
    }
    
    public void setFileNameValueLabel(String inputValue)
    {
        fileNameValueLabel.setText(inputValue);
    }
    
    public void setFileFormatValueLabel(String inputValue)
    {
        fileFormatValueLabel.setText(inputValue);
    }
    
    public void setEncodingFormatValueLabel(String inputValue)
    {
        encodingFormatValueLabel.setText(inputValue);
    }
    
    public void setDurationValueLabel(String inputValue)
    {
        durationValueLabel.setText(inputValue);
    }
    
    private void createComponentMap()
    {
        componentMapSingleTrack = new HashMap<String,Component>();
        Component[] components = this.getComponents();
        for(int i=0; i < components.length; i++)
        {
            componentMapSingleTrack.put(components[i].getName(), components[i]);
        }
    }

    public Component getComponentSingleTrackByName(String name)
    {
        if(componentMapSingleTrack.containsKey(name))
        {
            return (Component) componentMapSingleTrack.get(name);
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

        fileNameLabel = new javax.swing.JLabel();
        fileFormatLabel = new javax.swing.JLabel();
        encodingFormatLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        fileNameValueLabel = new javax.swing.JLabel();
        durationValueLabel = new javax.swing.JLabel();
        fileFormatValueLabel = new javax.swing.JLabel();
        encodingFormatValueLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(209, 209, 209));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Track x", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        fileNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        fileNameLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        fileNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        fileNameLabel.setText("File Name");
        fileNameLabel.setName("fileNameLabel"); // NOI18N

        fileFormatLabel.setBackground(new java.awt.Color(255, 255, 255));
        fileFormatLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        fileFormatLabel.setForeground(new java.awt.Color(0, 0, 0));
        fileFormatLabel.setText("File Format:");
        fileFormatLabel.setName("fileFormatLabel"); // NOI18N

        encodingFormatLabel.setBackground(new java.awt.Color(255, 255, 255));
        encodingFormatLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        encodingFormatLabel.setForeground(new java.awt.Color(0, 0, 0));
        encodingFormatLabel.setText("Encoding Format:");
        encodingFormatLabel.setName("encodingFormatLabel"); // NOI18N

        durationLabel.setBackground(new java.awt.Color(255, 255, 255));
        durationLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        durationLabel.setForeground(new java.awt.Color(0, 0, 0));
        durationLabel.setText("Duration:");
        durationLabel.setName("durationLabel"); // NOI18N

        fileNameValueLabel.setText("jLabel1");
        fileNameValueLabel.setName("fileNameValueLabel"); // NOI18N

        durationValueLabel.setText("jLabel1");
        durationValueLabel.setName("durationValueLabel"); // NOI18N

        fileFormatValueLabel.setText("jLabel1");
        fileFormatValueLabel.setName("fileFormatValueLabel"); // NOI18N

        encodingFormatValueLabel.setText("jLabel1");
        encodingFormatValueLabel.setName("encodingFormatValueLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fileFormatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileFormatValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(encodingFormatLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encodingFormatValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(durationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(durationValueLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fileNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameValueLabel)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameLabel)
                    .addComponent(fileNameValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileFormatLabel)
                    .addComponent(fileFormatValueLabel)
                    .addComponent(encodingFormatLabel)
                    .addComponent(encodingFormatValueLabel)
                    .addComponent(durationLabel)
                    .addComponent(durationValueLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel durationValueLabel;
    private javax.swing.JLabel encodingFormatLabel;
    private javax.swing.JLabel encodingFormatValueLabel;
    private javax.swing.JLabel fileFormatLabel;
    private javax.swing.JLabel fileFormatValueLabel;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JLabel fileNameValueLabel;
    // End of variables declaration//GEN-END:variables
}
