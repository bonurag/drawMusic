/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class generalInfoTrackGui extends javax.swing.JPanel {

    /**
     * Creates new form generalInfoTrackGui
     */
    public generalInfoTrackGui() {
        initComponents();
    }
    
    public void setMainTitleValueLabel(String inputValue)
    {
        mainTitleValueLabel.setText(inputValue);
    }
    
    public void setAuthorsValuesLabel(String inputValue)
    {
        authorsValuesLabel.setText(inputValue);
    }
    
    public void setWorkTitleValueLabel(String inputValue)
    {
        workTitleValueLabel.setText(inputValue);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mainTitleLabel = new javax.swing.JLabel();
        workTitleLabel = new javax.swing.JLabel();
        authorsLabel = new javax.swing.JLabel();
        mainTitleValueLabel = new javax.swing.JLabel();
        workTitleValueLabel = new javax.swing.JLabel();
        authorsValuesLabel = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jLabel2.setText("jLabel2");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "General Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));

        mainTitleLabel.setBackground(new java.awt.Color(255, 255, 255));
        mainTitleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mainTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
        mainTitleLabel.setText("Main Title:");

        workTitleLabel.setBackground(new java.awt.Color(255, 255, 255));
        workTitleLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        workTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
        workTitleLabel.setText("Work Title:");

        authorsLabel.setBackground(new java.awt.Color(255, 255, 255));
        authorsLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        authorsLabel.setForeground(new java.awt.Color(0, 0, 0));
        authorsLabel.setText("Authors:");

        mainTitleValueLabel.setBackground(new java.awt.Color(255, 255, 255));
        mainTitleValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        mainTitleValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        mainTitleValueLabel.setText("jLabel1");

        workTitleValueLabel.setBackground(new java.awt.Color(255, 255, 255));
        workTitleValueLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        workTitleValueLabel.setForeground(new java.awt.Color(0, 0, 0));
        workTitleValueLabel.setText("jLabel4");

        authorsValuesLabel.setBackground(new java.awt.Color(255, 255, 255));
        authorsValuesLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        authorsValuesLabel.setForeground(new java.awt.Color(0, 0, 0));
        authorsValuesLabel.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainTitleValueLabel)
                        .addGap(98, 375, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(authorsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(authorsValuesLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(workTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(workTitleValueLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainTitleLabel)
                    .addComponent(mainTitleValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorsValuesLabel)
                    .addComponent(authorsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workTitleLabel)
                    .addComponent(workTitleValueLabel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorsLabel;
    private javax.swing.JLabel authorsValuesLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JLabel mainTitleValueLabel;
    private javax.swing.JLabel workTitleLabel;
    private javax.swing.JLabel workTitleValueLabel;
    // End of variables declaration//GEN-END:variables
}