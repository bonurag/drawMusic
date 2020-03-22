
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bibbo
 */
public class drawMusic extends javax.swing.JFrame {
    
    Boolean enableBntpitchClassFrame = false;
    JFileChooser fc = new JFileChooser();
    /**
     * Creates new form drawMusic
     */
    public drawMusic() {
        initComponents();
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openFileButton = new javax.swing.JButton();
        selectedFile = new javax.swing.JLabel();
        generatePitchClassButton = new javax.swing.JButton();
        openFileName = new javax.swing.JLabel();
        nomeGraficoTextField_1 = new javax.swing.JTextField();
        pitchClassBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_1 = new javax.swing.JLabel();
        generatePitchButton = new javax.swing.JButton();
        pitchBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_2 = new javax.swing.JLabel();
        nomeGraficoTextField_2 = new javax.swing.JTextField();
        generateDurationButton = new javax.swing.JButton();
        durationBntTitle = new javax.swing.JLabel();
        nomeGraficoTextField_3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Data Extractor");
        setBackground(new java.awt.Color(255, 255, 255));

        openFileButton.setText("Apri File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        selectedFile.setText("File Selezionato:");

        generatePitchClassButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchClassButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\Progetto Java\\drawMusic\\drawMusic\\icon\\bar-chart-2.png")); // NOI18N
        generatePitchClassButton.setToolTipText("Genera grafico Pitch Class");
        generatePitchClassButton.setAlignmentY(0.0F);
        generatePitchClassButton.setMaximumSize(new java.awt.Dimension(48, 48));
        generatePitchClassButton.setMinimumSize(new java.awt.Dimension(48, 48));
        generatePitchClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePitchClassButtonActionPerformed(evt);
            }
        });

        openFileName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        nomeGraficoTextField_1.setToolTipText("Inserisci il nome del grafico");
        nomeGraficoTextField_1.setAlignmentX(0.0F);
        nomeGraficoTextField_1.setAlignmentY(0.0F);

        pitchClassBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pitchClassBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pitchClassBntTitle.setText("Pitch Class");

        nomeGraficoStaticLabel_1.setText("Nome Grafico:");

        generatePitchButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\Progetto Java\\drawMusic\\drawMusic\\icon\\graphic.png")); // NOI18N
        generatePitchButton.setToolTipText("Genera grafico Pitch");
        generatePitchButton.setAlignmentY(0.0F);
        generatePitchButton.setMaximumSize(new java.awt.Dimension(48, 48));
        generatePitchButton.setMinimumSize(new java.awt.Dimension(48, 48));
        generatePitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePitchButtonActionPerformed(evt);
            }
        });

        pitchBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pitchBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pitchBntTitle.setText("Pitch");

        nomeGraficoStaticLabel_2.setText("Nome Grafico:");

        nomeGraficoTextField_2.setToolTipText("Inserisci il nome del grafico");

        generateDurationButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        generateDurationButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\Progetto Java\\drawMusic\\drawMusic\\icon\\bar-chart.png")); // NOI18N
        generateDurationButton.setToolTipText("Genera grafico Durate");
        generateDurationButton.setAlignmentY(0.0F);
        generateDurationButton.setLabel("");
        generateDurationButton.setMaximumSize(new java.awt.Dimension(48, 48));
        generateDurationButton.setMinimumSize(new java.awt.Dimension(48, 48));
        generateDurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateDurationButtonActionPerformed(evt);
            }
        });

        durationBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        durationBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        durationBntTitle.setText("Duration");

        nomeGraficoTextField_3.setToolTipText("Inserisci il nome del grafico");

        jLabel1.setText("Nome Grafico:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(openFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectedFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openFileName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(generatePitchClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(pitchClassBntTitle))
                                    .addComponent(nomeGraficoStaticLabel_1))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeGraficoStaticLabel_2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pitchBntTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(generatePitchButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomeGraficoTextField_1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(nomeGraficoTextField_2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(durationBntTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(generateDurationButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(nomeGraficoTextField_3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFileButton)
                    .addComponent(selectedFile)
                    .addComponent(openFileName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pitchClassBntTitle)
                    .addComponent(pitchBntTitle)
                    .addComponent(durationBntTitle))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generatePitchClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatePitchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generateDurationButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeGraficoStaticLabel_1)
                    .addComponent(nomeGraficoStaticLabel_2)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeGraficoTextField_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeGraficoTextField_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeGraficoTextField_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Documenti IEEE 1599", "xml");
        fc.setFileFilter(filter);        
        fc.setDialogTitle("Open File");
        String[] fileterExt = filter.getExtensions();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            if (f == null || !f.exists())
                JOptionPane.showMessageDialog(null, "File selezionato non esistente!", "Error", JOptionPane.ERROR_MESSAGE);
            else if(f.exists())
            {
                String name = f.getName();
                String extension = name.substring(name.length()-3,name.length());
                enableBntpitchClassFrame = true;
                if(!fileterExt[0].equals(extension))
                    JOptionPane.showMessageDialog(null, "Attenzione estenzione del file non valida! Selezionare file ."+fileterExt[0], "Error", JOptionPane.ERROR_MESSAGE);
                openFileName.setText(fc.getSelectedFile().getName());
            }        
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void generatePitchClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchClassButtonActionPerformed
        try
        {   
            pitchClassFrame pitchClassFrame = new pitchClassFrame(fc.getSelectedFile().getName());
            pitchClassFrame.showUI();
            String graphName = nomeGraficoTextField_1.getText();
            if(!graphName.equals("") && !graphName.equals(null))
                pitchClassFrame.setGraphName(graphName);
            else
                pitchClassFrame.setGraphName("Default Graph Name");
            pitchClassFrame.addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    Object[] options = {"Si","No"};
                    if (JOptionPane.showOptionDialog(pitchClassFrame, 
                        "Sei sicuro di voler chiudere questa finestra?",
                        "Chiudi Finestra?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION){
                        generatePitchClassButton.setEnabled(true);
                        nomeGraficoTextField_1.setEnabled(true);
                        nomeGraficoTextField_1.setText("");
                    }
                }
            });
            generatePitchClassButton.setEnabled(false);
            nomeGraficoTextField_1.setEnabled(false);
        }
        catch(Exception e)
        {
            String errorMessage = "Prima di generare un grafico selezionare un file!";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generatePitchClassButtonActionPerformed

    private void generatePitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchButtonActionPerformed
        try
        {   
            pitchFrame pitchFrame = new pitchFrame(fc.getSelectedFile().getName());
            pitchFrame.showUI();
            String graphName = nomeGraficoTextField_2.getText();
            if(!graphName.equals("") && !graphName.equals(null))
                pitchFrame.setGraphName(graphName);
            else
                pitchFrame.setGraphName("Default Graph Name");
            pitchFrame.addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    Object[] options = {"Si","No"};
                    if (JOptionPane.showOptionDialog(pitchFrame, 
                        "Sei sicuro di voler chiudere questa finestra?",
                        "Chiudi Finestra?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION){
                        generatePitchButton.setEnabled(true);
                        nomeGraficoTextField_2.setEnabled(true);
                        nomeGraficoTextField_2.setText("");
                    }
                }
            });
            generatePitchButton.setEnabled(false);
            nomeGraficoTextField_2.setEnabled(false);
        }
        catch(Exception e)
        {
            String errorMessage = "Prima di generare un grafico selezionare un file!";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generatePitchButtonActionPerformed

    private void generateDurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateDurationButtonActionPerformed
        try
        {   
            durationFrame durationFrame = new durationFrame(fc.getSelectedFile().getName());
            durationFrame.showUI();
            String graphName = nomeGraficoTextField_3.getText();
            if(!graphName.equals("") && !graphName.equals(null))
                durationFrame.setGraphName(graphName);
            else
                durationFrame.setGraphName("Default Graph Name");
            durationFrame.addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                {
                    Object[] options = {"Si","No"};
                    if (JOptionPane.showOptionDialog(durationFrame, 
                        "Sei sicuro di voler chiudere questa finestra?",
                        "Chiudi Finestra?", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION){
                        generateDurationButton.setEnabled(true);
                        nomeGraficoTextField_3.setEnabled(true);
                        nomeGraficoTextField_3.setText("");
                    }
                }
            });
            generateDurationButton.setEnabled(false);
            nomeGraficoTextField_3.setEnabled(false);
        }
        catch(Exception e)
        {
            String errorMessage = "Prima di generare un grafico selezionare un file!";
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_generateDurationButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(drawMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(drawMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(drawMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(drawMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new drawMusic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel durationBntTitle;
    private javax.swing.JButton generateDurationButton;
    private javax.swing.JButton generatePitchButton;
    private javax.swing.JButton generatePitchClassButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nomeGraficoStaticLabel_1;
    private javax.swing.JLabel nomeGraficoStaticLabel_2;
    private javax.swing.JTextField nomeGraficoTextField_1;
    private javax.swing.JTextField nomeGraficoTextField_2;
    private javax.swing.JTextField nomeGraficoTextField_3;
    private javax.swing.JButton openFileButton;
    private javax.swing.JLabel openFileName;
    private javax.swing.JLabel pitchBntTitle;
    private javax.swing.JLabel pitchClassBntTitle;
    private javax.swing.JLabel selectedFile;
    // End of variables declaration//GEN-END:variables
}
