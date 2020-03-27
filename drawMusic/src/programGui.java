
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Giuseppe
 */
public class programGui extends javax.swing.JFrame
{   
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Documenti IEEE 1599", "xml");
    JFileChooser openFileChoseer = new JFileChooser();
   
    ImageIcon trueIcon = new ImageIcon("icon/green_check.png");
    ImageIcon falseIcon = new ImageIcon("red_cross.png");

    /**
     * Creates new form programGui
     */
    public programGui()
    {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        lockIcon();
        lockTextBox();
        loadDataProgressBar.setVisible(false);
        openFileName.setText("Nessun file selezionato!");
    }
    
    public void lockIcon() {
        generatePitchClassButton.setEnabled(false);
        generatePitchButton.setEnabled(false);
        generateDurationButton.setEnabled(false);
        generateMelodicIntervalButton.setEnabled(false);
        generateHarmonicIntervalButton.setEnabled(false);
    }
    
    public void lockTextBox() {
        nomeGraficoTextField_1.setEnabled(false);
        nomeGraficoTextField_2.setEnabled(false);
        nomeGraficoTextField_3.setEnabled(false);
        nomeGraficoTextField_4.setEnabled(false);
        nomeGraficoTextField_5.setEnabled(false);
    }
    
    public void unLockIcon() {
        generatePitchClassButton.setEnabled(true);
        generatePitchButton.setEnabled(true);
        generateDurationButton.setEnabled(true);
        generateMelodicIntervalButton.setEnabled(true);
        generateHarmonicIntervalButton.setEnabled(true);
    }
    
    public void unLockTextBox() {
        nomeGraficoTextField_1.setEnabled(true);
        nomeGraficoTextField_2.setEnabled(true);
        nomeGraficoTextField_3.setEnabled(true);
        nomeGraficoTextField_4.setEnabled(true);
        nomeGraficoTextField_5.setEnabled(true);
    }
    
    public void setProgressBarValue(int inputValue){
        System.out.println("setProgressBarValue " + inputValue);
        loadDataProgressBar.setValue(inputValue);
    }
    
    public void setProgressBarVisibleint(Boolean inputValue){
        System.out.println("setProgressBarVisibleint " + inputValue); 
        loadDataProgressBar.setVisible(inputValue);
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
        generatePitchClassButton = new javax.swing.JButton();
        generatePitchButton = new javax.swing.JButton();
        generateDurationButton = new javax.swing.JButton();
        generateMelodicIntervalButton = new javax.swing.JButton();
        generateHarmonicIntervalButton = new javax.swing.JButton();
        nomeGraficoTextField_1 = new javax.swing.JTextField();
        nomeGraficoTextField_2 = new javax.swing.JTextField();
        nomeGraficoTextField_3 = new javax.swing.JTextField();
        nomeGraficoTextField_4 = new javax.swing.JTextField();
        nomeGraficoTextField_5 = new javax.swing.JTextField();
        selectedFileIcon = new javax.swing.JLabel();
        selectedFile = new javax.swing.JLabel();
        openFileName = new javax.swing.JLabel();
        pitchClassBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_1 = new javax.swing.JLabel();
        pitchBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_2 = new javax.swing.JLabel();
        durationBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_3 = new javax.swing.JLabel();
        melodicIntervalBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_4 = new javax.swing.JLabel();
        harmonicIntervalBntTitle = new javax.swing.JLabel();
        nomeGraficoStaticLabel_5 = new javax.swing.JLabel();
        openFileIconDescriptioon = new javax.swing.JLabel();
        loadDataProgressBar = new javax.swing.JProgressBar();
        statusProgressBarText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Data Extractor");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(680, 320));
        setPreferredSize(new java.awt.Dimension(700, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        openFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/open_doc.jpg"))); // NOI18N
        openFileButton.setToolTipText("Apri File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(openFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        generatePitchClassButton.setBackground(new java.awt.Color(255, 255, 255));
        generatePitchClassButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchClassButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bar-chart-2.png"))); // NOI18N
        generatePitchClassButton.setToolTipText("Genera grafico Pitch Class");
        generatePitchClassButton.setAlignmentY(0.0F);
        generatePitchClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePitchClassButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generatePitchClassButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        generatePitchButton.setBackground(new java.awt.Color(255, 255, 255));
        generatePitchButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/graphic.png"))); // NOI18N
        generatePitchButton.setToolTipText("Genera grafico Pitch");
        generatePitchButton.setAlignmentY(0.0F);
        generatePitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePitchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generatePitchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 190, -1, -1));

        generateDurationButton.setBackground(new java.awt.Color(255, 255, 255));
        generateDurationButton.setForeground(new java.awt.Color(255, 255, 255));
        generateDurationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bar-chart.png"))); // NOI18N
        generateDurationButton.setToolTipText("Genera grafico Durate");
        generateDurationButton.setAlignmentY(0.0F);
        generateDurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateDurationButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateDurationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        generateMelodicIntervalButton.setBackground(new java.awt.Color(255, 255, 255));
        generateMelodicIntervalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pyramid-chart.png"))); // NOI18N
        generateMelodicIntervalButton.setToolTipText("Genera grafico Intervalli Melodici");
        generateMelodicIntervalButton.setAlignmentY(0.0F);
        generateMelodicIntervalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMelodicIntervalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateMelodicIntervalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, -1));

        generateHarmonicIntervalButton.setBackground(new java.awt.Color(255, 255, 255));
        generateHarmonicIntervalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/line-chart-4.png"))); // NOI18N
        generateHarmonicIntervalButton.setToolTipText("Genera grafico Intervalli Armonici");
        generateHarmonicIntervalButton.setAlignmentY(0.0F);
        generateHarmonicIntervalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateHarmonicIntervalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateHarmonicIntervalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, -1));

        nomeGraficoTextField_1.setToolTipText("Inserisci il nome del grafico");
        nomeGraficoTextField_1.setAlignmentX(0.0F);
        nomeGraficoTextField_1.setAlignmentY(0.0F);
        getContentPane().add(nomeGraficoTextField_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 100, -1));

        nomeGraficoTextField_2.setToolTipText("Inserisci il nome del grafico");
        getContentPane().add(nomeGraficoTextField_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 100, -1));

        nomeGraficoTextField_3.setToolTipText("Inserisci il nome del grafico");
        getContentPane().add(nomeGraficoTextField_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 100, -1));

        nomeGraficoTextField_4.setToolTipText("Inserisci il nome del grafico");
        nomeGraficoTextField_4.setAlignmentX(0.0F);
        nomeGraficoTextField_4.setAlignmentY(0.0F);
        getContentPane().add(nomeGraficoTextField_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 100, -1));

        nomeGraficoTextField_5.setToolTipText("Inserisci il nome del grafico");
        nomeGraficoTextField_5.setAlignmentX(0.0F);
        nomeGraficoTextField_5.setAlignmentY(0.0F);
        getContentPane().add(nomeGraficoTextField_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 100, -1));

        selectedFileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/red_cross.png"))); // NOI18N
        getContentPane().add(selectedFileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 20, -1, -1));

        selectedFile.setText("File Selezionato:");
        getContentPane().add(selectedFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, 20));

        openFileName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        openFileName.setText("text");
        getContentPane().add(openFileName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 20));

        pitchClassBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pitchClassBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pitchClassBntTitle.setText("Pitch Class");
        getContentPane().add(pitchClassBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        nomeGraficoStaticLabel_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeGraficoStaticLabel_1.setLabelFor(nomeGraficoTextField_1);
        nomeGraficoStaticLabel_1.setText("Nome Grafico");
        getContentPane().add(nomeGraficoStaticLabel_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 100, -1));

        pitchBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pitchBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pitchBntTitle.setText("Pitch");
        getContentPane().add(pitchBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 58, -1));

        nomeGraficoStaticLabel_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeGraficoStaticLabel_2.setLabelFor(nomeGraficoTextField_2);
        nomeGraficoStaticLabel_2.setText("Nome Grafico");
        getContentPane().add(nomeGraficoStaticLabel_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 100, -1));

        durationBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        durationBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        durationBntTitle.setText("Duration");
        getContentPane().add(durationBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        nomeGraficoStaticLabel_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeGraficoStaticLabel_3.setLabelFor(nomeGraficoTextField_3);
        nomeGraficoStaticLabel_3.setText("Nome Grafico");
        getContentPane().add(nomeGraficoStaticLabel_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 100, -1));

        melodicIntervalBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        melodicIntervalBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        melodicIntervalBntTitle.setText("Melodic");
        melodicIntervalBntTitle.setAlignmentY(0.0F);
        getContentPane().add(melodicIntervalBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

        nomeGraficoStaticLabel_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeGraficoStaticLabel_4.setLabelFor(nomeGraficoTextField_4);
        nomeGraficoStaticLabel_4.setText("Nome Grafico");
        getContentPane().add(nomeGraficoStaticLabel_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 100, -1));

        harmonicIntervalBntTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        harmonicIntervalBntTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        harmonicIntervalBntTitle.setText("Harmonic");
        harmonicIntervalBntTitle.setAlignmentY(0.0F);
        getContentPane().add(harmonicIntervalBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, -1, -1));

        nomeGraficoStaticLabel_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeGraficoStaticLabel_5.setLabelFor(nomeGraficoTextField_5);
        nomeGraficoStaticLabel_5.setText("Nome Grafico");
        getContentPane().add(nomeGraficoStaticLabel_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 100, -1));

        openFileIconDescriptioon.setText("Apri File");
        getContentPane().add(openFileIconDescriptioon, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 60, -1, -1));

        loadDataProgressBar.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        loadDataProgressBar.setStringPainted(true);
        getContentPane().add(loadDataProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 15, 210, 30));
        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        openFileChoseer.setFileFilter(filter);        
        openFileChoseer.setDialogTitle("Open File");
        String[] fileterExt = filter.getExtensions();
        if (openFileChoseer.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File f = openFileChoseer.getSelectedFile();
            if (f == null)
            {
                lockIcon();
                lockTextBox();
                openFileName.setText("Nessun file selezionato!");
                JOptionPane.showMessageDialog(null, "File selezionato non esistente!", "Error", JOptionPane.ERROR_MESSAGE);
            }      
            else if(f.exists())
            {
                String name = f.getName();
                String extension = name.substring(name.length()-3,name.length());
                selectedFileIcon.setIcon(trueIcon);  
                openFileName.setText(name);
                unLockIcon();
                unLockTextBox();
                if(!fileterExt[0].equals(extension))
                {
                    lockIcon();
                    lockTextBox();
                    selectedFileIcon.setIcon(falseIcon);
                    openFileName.setText("File selezionato non riconosciuto!");
                    JOptionPane.showMessageDialog(null, "Attenzione estenzione del file non valida! Selezionare file ."+fileterExt[0], "Error", JOptionPane.ERROR_MESSAGE);
                }      
            }        
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void generatePitchClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchClassButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generatePitchClassButtonActionPerformed

    private void generatePitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generatePitchButtonActionPerformed

    private void generateDurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateDurationButtonActionPerformed
        
    }//GEN-LAST:event_generateDurationButtonActionPerformed

    private void generateMelodicIntervalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateMelodicIntervalButtonActionPerformed
        try
        {                        
            melodicIntervallSwingWorker l = new melodicIntervallSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile().getName());
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                work.execute();
            }

            work.addPropertyChangeListener(new PropertyChangeListener()
            {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("state".equals(evt.getPropertyName())) {
                        SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                        switch (state) {
                            case DONE:
                            {
                                try 
                                {
                                    melodicIntervalFrame melodicIntervalFrame = new melodicIntervalFrame(work.get());
                                    int dataSize = melodicIntervalFrame.getInputDataSize();
                                    if(dataSize > 0)
                                    {
                                        statusProgressBarText.setText("Caricamento Completato!");
                                        generateMelodicIntervalButton.setEnabled(false);
                                        nomeGraficoTextField_4.setEnabled(false);
                                        melodicIntervalFrame.showUI();
                                    }
                                    else
                                    {
                                        statusProgressBarText.setText("");
                                        generateMelodicIntervalButton.setEnabled(true);
                                        nomeGraficoTextField_4.setEnabled(true);
                                        nomeGraficoTextField_4.setText("");
                                        loadDataProgressBar.setValue(0);
                                        loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }
                                    
                                    String graphName = nomeGraficoTextField_4.getText();
                                    if(!graphName.equals(""))
                                        melodicIntervalFrame.setGraphName(graphName);
                                    else
                                        melodicIntervalFrame.setGraphName("Default Graph Name");
                                    melodicIntervalFrame.addWindowListener(new java.awt.event.WindowAdapter()
                                    {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                        {
                                            Object[] options = {"Si","No"};
                                            int state = JOptionPane.showOptionDialog(melodicIntervalFrame, 
                                                        "Sei sicuro di voler chiudere questa finestra?",
                                                        "Chiudi Finestra?", 
                                                        JOptionPane.YES_NO_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                            if(state == JOptionPane.YES_OPTION)
                                            {
                                                windowEvent.getWindow().dispose();
                                                statusProgressBarText.setText("");
                                                generateMelodicIntervalButton.setEnabled(true);
                                                nomeGraficoTextField_4.setEnabled(true);
                                                nomeGraficoTextField_4.setText("");
                                                loadDataProgressBar.setValue(0);
                                                loadDataProgressBar.setVisible(false);
                                            }
                                        }
                                    });
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ExecutionException ex) {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                loadDataProgressBar.setVisible(true);
                                generateMelodicIntervalButton.setEnabled(false);
                                nomeGraficoTextField_4.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int progress = (Integer)evt.getNewValue();
                        generateMelodicIntervalButton.setEnabled(false);
                        nomeGraficoTextField_4.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(Exception e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        }  
    }//GEN-LAST:event_generateMelodicIntervalButtonActionPerformed

    private void generateHarmonicIntervalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateHarmonicIntervalButtonActionPerformed
        try
        {                        
            harmonicIntervallSwingWorker l = new harmonicIntervallSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile().getName());
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                work.execute();
            }

            work.addPropertyChangeListener(new PropertyChangeListener()
            {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("state".equals(evt.getPropertyName())) {
                        SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                        switch (state) {
                            case DONE:
                            {
                                try 
                                {
                                    harmonicIntervalFrame harmonicIntervalFrame = new harmonicIntervalFrame(work.get());
                                    int dataSize = harmonicIntervalFrame.getInputDataSize();
                                    if(dataSize > 0)
                                    {
                                        statusProgressBarText.setText("Caricamento Completato!");
                                        generateHarmonicIntervalButton.setEnabled(false);
                                        nomeGraficoTextField_5.setEnabled(false);
                                        harmonicIntervalFrame.showUI();
                                    }
                                    else
                                    {
                                        statusProgressBarText.setText("");
                                        generateHarmonicIntervalButton.setEnabled(true);
                                        nomeGraficoTextField_5.setEnabled(true);
                                        nomeGraficoTextField_5.setText("");
                                        loadDataProgressBar.setValue(0);
                                        loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }

                                    String graphName = nomeGraficoTextField_5.getText();
                                    if(!graphName.equals(""))
                                        harmonicIntervalFrame.setGraphName(graphName);
                                    else
                                        harmonicIntervalFrame.setGraphName("Default Graph Name");
                                    harmonicIntervalFrame.addWindowListener(new java.awt.event.WindowAdapter()
                                    {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                        {
                                            Object[] options = {"Si","No"};
                                            int state = JOptionPane.showOptionDialog(harmonicIntervalFrame, 
                                                        "Sei sicuro di voler chiudere questa finestra?",
                                                        "Chiudi Finestra?", 
                                                        JOptionPane.YES_NO_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                            if(state == JOptionPane.YES_OPTION)
                                            {
                                                windowEvent.getWindow().dispose();
                                                statusProgressBarText.setText("");
                                                generateHarmonicIntervalButton.setEnabled(true);
                                                nomeGraficoTextField_5.setEnabled(true);
                                                nomeGraficoTextField_5.setText("");
                                                loadDataProgressBar.setValue(0);
                                                loadDataProgressBar.setVisible(false);
                                            }
                                        }
                                    });
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ExecutionException ex) {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                loadDataProgressBar.setVisible(true);
                                generateHarmonicIntervalButton.setEnabled(false);
                                nomeGraficoTextField_5.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int progress = (Integer)evt.getNewValue();
                        generateHarmonicIntervalButton.setEnabled(false);
                        nomeGraficoTextField_5.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(Exception e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_generateHarmonicIntervalButtonActionPerformed
    
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
            java.util.logging.Logger.getLogger(programGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(programGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(programGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(programGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new programGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel durationBntTitle;
    private javax.swing.JButton generateDurationButton;
    private javax.swing.JButton generateHarmonicIntervalButton;
    public javax.swing.JButton generateMelodicIntervalButton;
    private javax.swing.JButton generatePitchButton;
    private javax.swing.JButton generatePitchClassButton;
    private javax.swing.JLabel harmonicIntervalBntTitle;
    private javax.swing.JProgressBar loadDataProgressBar;
    private javax.swing.JLabel melodicIntervalBntTitle;
    private javax.swing.JLabel nomeGraficoStaticLabel_1;
    private javax.swing.JLabel nomeGraficoStaticLabel_2;
    private javax.swing.JLabel nomeGraficoStaticLabel_3;
    private javax.swing.JLabel nomeGraficoStaticLabel_4;
    private javax.swing.JLabel nomeGraficoStaticLabel_5;
    private javax.swing.JTextField nomeGraficoTextField_1;
    private javax.swing.JTextField nomeGraficoTextField_2;
    private javax.swing.JTextField nomeGraficoTextField_3;
    public javax.swing.JTextField nomeGraficoTextField_4;
    private javax.swing.JTextField nomeGraficoTextField_5;
    private javax.swing.JButton openFileButton;
    private javax.swing.JLabel openFileIconDescriptioon;
    private javax.swing.JLabel openFileName;
    private javax.swing.JLabel pitchBntTitle;
    private javax.swing.JLabel pitchClassBntTitle;
    private javax.swing.JLabel selectedFile;
    private javax.swing.JLabel selectedFileIcon;
    private javax.swing.JLabel statusProgressBarText;
    // End of variables declaration//GEN-END:variables
}
