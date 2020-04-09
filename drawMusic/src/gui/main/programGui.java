package gui.main;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import swingworker.pitchSwingWorker;
import swingworker.durationSwingWorker;
import swingworker.melodicIntervallSwingWorker;
import swingworker.harmonicIntervallSwingWorker;
import swingworker.xmlDetailSwingWorker;
import gui.frame.durationFrame;
import gui.frame.harmonicIntervalFrame;
import gui.frame.melodicIntervalFrame;
import gui.frame.pitchFrame;
import gui.frame.pitchClassFrame;
import gui.frame.trackFrame;
import dataUtils.drawMusicData_Utils;
import java.awt.HeadlessException;

/**
 * @author      Giuseppe Bonura giuseppe.bonura@studenti.unimi.it
 * @version     1.0
 */
public class programGui extends javax.swing.JFrame
{   
    /**
    * Instance of FileNameExtensionFilter it is a useful object to define a list of extensions that are accepted by the JFileChooser
    */
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Documenti IEEE 1599", "xml");
    
    /**
    * Instance of JFileChooser provides a simple mechanism for the user to choose a file
    */
    private final JFileChooser openFileChoseer = new JFileChooser();
    
    /**
    * Used to save the accepted file icon
    */
    private final ImageIcon trueIcon = new ImageIcon("src/icon/green_check.png");
    
    /**
    * Used to save the rejected file icon
    */
    private final ImageIcon falseIcon = new ImageIcon("src/icon/gred_cross.png");
    
    /**
    * Used to save the timestamp when invoking the pitch calculation method
    */
    private long startTimePitch;
    
    /**
    * Used to save the timestamp when invoking the pitch class calculation method
    */
    private long startTimePitchClass;
    
    /**
    * Used to save the timestamp when invoking the duration calculation method
    */
    private long startTimeDuration;
    
    /**
    * Used to save the timestamp when invoking the melodic interval calculation method
    */
    private long startTimeMelodic;
    
    /**
    * Used to save the timestamp when invoking the harmonic interval calculation method
    */
    private long startTimeHarmonic;
    
    /**
    * Used to save the timestamp when invoking the general information calculation method
    */
    private long startTimeInformation;
    
    /**
    * Constant used to identify when the calculation operation of Pitch
    */
    public static final String WORKERPITCHSTART = "workerPitchStart";
    
    /**
    * Constant used to identify when the calculation operation of Pitch Class
    */
    public static final String WORKERPITCHCLASSSTART = "workerPitchClassStart";
    
    /**
    * Constant used to identify when the calculation operation of Duration
    */
    public static final String WORKERDURATIONSTART = "workerDurationStart";
    
    /**
    * Constant used to identify when the calculation operation of Melodic Interval
    */
    public static final String WORKERMELODICSTART = "workerMelodicStart";
    
    /**
    * Constant used to identify when the calculation operation of Harmonic Interval
    */
    public static final String WORKERHARMONICSTART = "workerHarmonicStart";
    
    /**
    * Constant used to identify when the calculation operation of General Information
    */
    public static final String WORKERGETINFORMATIONSTART = "workerGetInformationStart";
    
    /**
    * List use to save when all works are in progress
    */
    private final ArrayList<String> workInProgress = new ArrayList<>();

    public programGui()
    {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        lockIcon();
        lockTextBox();
        lockComboBox();
        lockCheckBoxValidationXml();
        lockCheckBoxWhiteSpaceXml();
        loadDataProgressBar.setVisible(false);
        openFileName.setText("Nessun file selezionato!");
        xmlValidationEnableCheckBox.setText("Abilita validazione XML");
        whiteSpaceEnableCheckBox.setText("Abilita Ignora Spazi XML");      
    }
    
    /**
    * Method to initialize all JFrame buttons in a disabled state
    */
    private void lockIcon()
    {
        generatePitchClassButton.setEnabled(false);
        generatePitchButton.setEnabled(false);
        generateDurationButton.setEnabled(false);
        generateMelodicIntervalButton.setEnabled(false);
        generateHarmonicIntervalButton.setEnabled(false);
        xmlFileDetailButton.setEnabled(false);
    }
    
    /**
    * Method to initialize all JFrame text field in a disabled state
    */
    private void lockTextBox()
    {
        nomeGraficoTextField_1.setEnabled(false);
        nomeGraficoTextField_2.setEnabled(false);
        nomeGraficoTextField_3.setEnabled(false);
        nomeGraficoTextField_4.setEnabled(false);
        nomeGraficoTextField_5.setEnabled(false);
    }
    
    /**
    * Method to initialize all JFrame combo box in a disabled state
    */
    private void lockComboBox()
    {
        durationTypeComboBox.setEnabled(false);
    }
    
    /**
    * Method to initialize all JFrame check box in a disabled state
    */
    private void lockCheckBoxValidationXml()
    {
        xmlValidationEnableCheckBox.setEnabled(false);
    }
    
    /**
    * Method to initialize all JFrame check box in a disabled state
    */
    private void lockCheckBoxWhiteSpaceXml()
    {
        whiteSpaceEnableCheckBox.setEnabled(false);
    }
    
    /**
    * Method to initialize all JFrame buttons in a enable state
    */
    private void unLockIcon()
    {
        generatePitchClassButton.setEnabled(true);
        generatePitchButton.setEnabled(true);
        generateDurationButton.setEnabled(true);
        generateMelodicIntervalButton.setEnabled(true);
        generateHarmonicIntervalButton.setEnabled(true);
        xmlFileDetailButton.setEnabled(true);
    }
    
    /**
    * Method to initialize all JFrame text field in a enable state
    */
    private void unLockTextBox()
    {
        nomeGraficoTextField_1.setEnabled(true);
        nomeGraficoTextField_2.setEnabled(true);
        nomeGraficoTextField_3.setEnabled(true);
        nomeGraficoTextField_4.setEnabled(true);
        nomeGraficoTextField_5.setEnabled(true);
    }
    
    /**
    * Method to initialize all JFrame combo box in a enable state
    */
    private void unLockComboBox()
    {
        durationTypeComboBox.setEnabled(true);
    }
    
    /**
    * Method to initialize all JFrame check box in a enable state
    */
    private void unLockCheckBoxValidationXml()
    {
        xmlValidationEnableCheckBox.setEnabled(true);
    }
    
    /**
    * Method to initialize all JFrame check box in a enable state
    */
    private void unLockCheckBoxWhiteSpaceXml()
    {
        whiteSpaceEnableCheckBox.setEnabled(true);
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
        durationTypeComboBox = new javax.swing.JComboBox<>();
        xmlValidationEnableCheckBox = new javax.swing.JCheckBox();
        whiteSpaceEnableCheckBox = new javax.swing.JCheckBox();
        xmlFileDetailButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Music Data Extractor");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(680, 320));
        setName("programGui"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        openFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/folder.png"))); // NOI18N
        openFileButton.setToolTipText("Apri File");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(openFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        generatePitchClassButton.setBackground(new java.awt.Color(255, 255, 255));
        generatePitchClassButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchClassButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/quaver.png"))); // NOI18N
        generatePitchClassButton.setToolTipText("Genera grafico Pitch Class");
        generatePitchClassButton.setAlignmentY(0.0F);
        generatePitchClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePitchClassButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generatePitchClassButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 190, -1, -1));

        generatePitchButton.setBackground(new java.awt.Color(255, 255, 255));
        generatePitchButton.setForeground(new java.awt.Color(255, 255, 255));
        generatePitchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/musical-note.png"))); // NOI18N
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
        generateDurationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/metronome.png"))); // NOI18N
        generateDurationButton.setToolTipText("Genera grafico Durate");
        generateDurationButton.setAlignmentY(0.0F);
        generateDurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateDurationButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateDurationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 190, -1, -1));

        generateMelodicIntervalButton.setBackground(new java.awt.Color(255, 255, 255));
        generateMelodicIntervalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/equalizer.png"))); // NOI18N
        generateMelodicIntervalButton.setToolTipText("Genera grafico Intervalli Melodici");
        generateMelodicIntervalButton.setAlignmentY(0.0F);
        generateMelodicIntervalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMelodicIntervalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateMelodicIntervalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 190, -1, -1));

        generateHarmonicIntervalButton.setBackground(new java.awt.Color(255, 255, 255));
        generateHarmonicIntervalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/equalizer (2).png"))); // NOI18N
        generateHarmonicIntervalButton.setToolTipText("Genera grafico Intervalli Armonici");
        generateHarmonicIntervalButton.setAlignmentY(0.0F);
        generateHarmonicIntervalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateHarmonicIntervalButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateHarmonicIntervalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 190, -1, -1));

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
        getContentPane().add(openFileName, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 20, -1, 20));

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
        getContentPane().add(melodicIntervalBntTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 170, -1, -1));

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
        getContentPane().add(loadDataProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 15, 230, 30));

        statusProgressBarText.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 50, -1, -1));

        durationTypeComboBox.setMaximumRowCount(3);
        durationTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHORD", "REST", "BOTH" }));
        durationTypeComboBox.setToolTipText("Seleziona la durata che vuoi misurare");
        durationTypeComboBox.setAlignmentX(0.0F);
        durationTypeComboBox.setAlignmentY(0.0F);
        durationTypeComboBox.setOpaque(true);
        getContentPane().add(durationTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 100, -1));

        xmlValidationEnableCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        xmlValidationEnableCheckBox.setText("XML Validation Enable");
        xmlValidationEnableCheckBox.setToolTipText("Abilita la validazione del file XML");
        xmlValidationEnableCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlValidationEnableCheckBoxActionPerformed(evt);
            }
        });
        getContentPane().add(xmlValidationEnableCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 50, -1, -1));

        whiteSpaceEnableCheckBox.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        whiteSpaceEnableCheckBox.setText("Ignoring WhiteSpace");
        whiteSpaceEnableCheckBox.setToolTipText("Esclusione Spazi Bianchi Elementi XML Abilitata");
        whiteSpaceEnableCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteSpaceEnableCheckBoxActionPerformed(evt);
            }
        });
        getContentPane().add(whiteSpaceEnableCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 70, -1, -1));

        xmlFileDetailButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/information_icon.png"))); // NOI18N
        xmlFileDetailButton.setToolTipText("Informazioni generali sul file XML caricato");
        xmlFileDetailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlFileDetailButtonActionPerformed(evt);
            }
        });
        getContentPane().add(xmlFileDetailButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        openFileChoseer.setFileFilter(filter);        
        openFileChoseer.setDialogTitle("Apri File");
        openFileName.setText("Nessun file selezionato!");
        selectedFileIcon.setIcon(falseIcon);
        String[] fileterExt = filter.getExtensions();
        File f = openFileChoseer.getSelectedFile();

        if(openFileChoseer.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            f = openFileChoseer.getSelectedFile();
            if (f == null)
            {
                lockIcon();
                lockTextBox();
                lockComboBox();
                lockCheckBoxValidationXml();
                lockCheckBoxWhiteSpaceXml();
                openFileName.setText("Nessun file selezionato!");
                JOptionPane.showMessageDialog(null, "File selezionato non esistente!", "Error", JOptionPane.ERROR_MESSAGE);
            }      
            else if(f.exists())
            {
                String name = f.getName();
                String extension = name.substring(name.length()-3,name.length());
                selectedFileIcon.setIcon(trueIcon);  
                openFileName.setText(name.substring(0, name.indexOf(".")));
                unLockIcon();
                unLockTextBox();
                unLockComboBox();
                unLockCheckBoxValidationXml();
                unLockCheckBoxWhiteSpaceXml();
                if(!fileterExt[0].equals(extension))
                {
                    lockIcon();
                    lockTextBox();
                    lockComboBox();
                    lockCheckBoxValidationXml();
                    lockCheckBoxWhiteSpaceXml();
                    selectedFileIcon.setIcon(falseIcon);
                    openFileName.setText("File selezionato non riconosciuto!");
                    JOptionPane.showMessageDialog(null, "Attenzione estenzione del file non valida! Selezionare file ."+fileterExt[0], "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                lockIcon();
                lockTextBox();
                lockComboBox();
                lockCheckBoxValidationXml();
                lockCheckBoxWhiteSpaceXml();
                selectedFileIcon.setIcon(falseIcon);
                openFileName.setText("File selezionato non esistente!");
                JOptionPane.showMessageDialog(null, "Attenzione il file selezionato non è stato trovato!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    private void generatePitchClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchClassButtonActionPerformed
        try
        {                        
            pitchSwingWorker l = new pitchSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile(), "DIATONICA", "D");
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                startTimePitch = System.nanoTime();
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
                                    workInProgress.remove(WORKERPITCHCLASSSTART);
                                    System.out.println("workInProgress DONE: " + workInProgress);
                                    pitchClassFrame pitchClassFrame = new pitchClassFrame(work.get());
                                    int dataSize = pitchClassFrame.getInputDataSize();
                                    long finish = System.nanoTime();
                                    long timeElapsed = finish - startTimePitch;
                                    String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                                    if(dataSize > 0)
                                    {
                                        String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                        statusProgressBarText.setText(loadCompletedMessage);
                                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                        generatePitchClassButton.setEnabled(false);
                                        nomeGraficoTextField_1.setEnabled(false);
                                        pitchClassFrame.showUI();
                                    }
                                    else if(!(dataSize > 0) || pitchSwingWorker.getIsError())
                                    {
                                        statusProgressBarText.setText("");
                                        generatePitchClassButton.setEnabled(true);
                                        nomeGraficoTextField_1.setEnabled(true);
                                        nomeGraficoTextField_1.setText("");
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }
                                    
                                    String graphName = nomeGraficoTextField_4.getText();
                                    if(!graphName.equals(""))
                                        pitchClassFrame.setGraphName(graphName);
                                    else
                                        pitchClassFrame.setGraphName("Pitch Class Graph");
                                    pitchClassFrame.addWindowListener(new java.awt.event.WindowAdapter()
                                    {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                        {
                                            Object[] options = {"Si","No"};
                                            int state = JOptionPane.showOptionDialog(pitchClassFrame, 
                                                        "Sei sicuro di voler chiudere questa finestra?",
                                                        "Chiudi Finestra?", 
                                                        JOptionPane.YES_NO_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                            if(state == JOptionPane.YES_OPTION)
                                            {
                                                windowEvent.getWindow().dispose();
                                                statusProgressBarText.setText("");
                                                generatePitchClassButton.setEnabled(true);
                                                nomeGraficoTextField_1.setEnabled(true);
                                                nomeGraficoTextField_1.setText("");
                                                loadDataProgressBar.setValue(0);
                                                if(workInProgress.isEmpty())
                                                    loadDataProgressBar.setVisible(false);
                                            }
                                        }
                                    });
                                }
                                catch (InterruptedException | ExecutionException ex)
                                {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                workInProgress.add(WORKERPITCHCLASSSTART);
                                System.out.println("workInProgress STARTED: " + workInProgress);
                                loadDataProgressBar.setVisible(true);
                                generatePitchClassButton.setEnabled(false);
                                nomeGraficoTextField_1.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                        int progress = (Integer)evt.getNewValue();
                        generatePitchClassButton.setEnabled(false);
                        nomeGraficoTextField_1.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(HeadlessException e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_generatePitchClassButtonActionPerformed

    private void generatePitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePitchButtonActionPerformed
        try
        {                        
            pitchSwingWorker l = new pitchSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile(), "ANGLOSASSONE", "A");
            
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                startTimePitchClass = System.nanoTime();
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
                                    workInProgress.remove(WORKERPITCHSTART);
                                    System.out.println("workInProgress DONE: " + workInProgress);
                                    pitchFrame pitchFrame = new pitchFrame(work.get());
                                    int dataSize = pitchFrame.getInputDataSize();
                                    long finish = System.nanoTime();
                                    long timeElapsed = finish - startTimePitchClass;
                                    String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                                    if(dataSize > 0)
                                    {      
                                        String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                        statusProgressBarText.setText(loadCompletedMessage);
                                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                        generatePitchButton.setEnabled(false);
                                        nomeGraficoTextField_2.setEnabled(false);
                                        pitchFrame.showUI();   
                                    }
                                    else if(!(dataSize > 0) || pitchSwingWorker.getIsError())
                                    {
                                        statusProgressBarText.setText("");
                                        generatePitchButton.setEnabled(true);
                                        nomeGraficoTextField_2.setEnabled(true);
                                        nomeGraficoTextField_2.setText("");
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);        
                                    }
                                    
                                    String graphName = nomeGraficoTextField_4.getText();
                                    if(!graphName.equals(""))
                                        pitchFrame.setGraphName(graphName);
                                    else
                                        pitchFrame.setGraphName("Pitch Graph");
                                    pitchFrame.addWindowListener(new java.awt.event.WindowAdapter()
                                    {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                        {
                                            Object[] options = {"Si","No"};
                                            int state = JOptionPane.showOptionDialog(pitchFrame, 
                                                        "Sei sicuro di voler chiudere questa finestra?",
                                                        "Chiudi Finestra?", 
                                                        JOptionPane.YES_NO_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                            if(state == JOptionPane.YES_OPTION)
                                            {    
                                                windowEvent.getWindow().dispose();
                                                statusProgressBarText.setText("");
                                                generatePitchButton.setEnabled(true);
                                                nomeGraficoTextField_2.setEnabled(true);
                                                nomeGraficoTextField_2.setText("");
                                                loadDataProgressBar.setValue(0);
                                                if(workInProgress.isEmpty())
                                                    loadDataProgressBar.setVisible(false);   
                                            }
                                        }
                                    });
                                }
                                catch (InterruptedException | ExecutionException ex)
                                {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;
                           
                            case STARTED:
                                workInProgress.add(WORKERPITCHSTART);
                                System.out.println("workInProgress STARTED: " + workInProgress);
                                loadDataProgressBar.setVisible(true);
                                generatePitchButton.setEnabled(false);
                                nomeGraficoTextField_2.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                break;
                            
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                        int progress = (Integer)evt.getNewValue();
                        generatePitchButton.setEnabled(false);
                        nomeGraficoTextField_2.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(HeadlessException e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_generatePitchButtonActionPerformed

    private void generateDurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateDurationButtonActionPerformed
        try
        {          
            String inputDurationType = (String) durationTypeComboBox.getSelectedItem();
            durationSwingWorker l = new durationSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile(), inputDurationType);
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                startTimeDuration = System.nanoTime();
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
                                    workInProgress.remove(WORKERDURATIONSTART);
                                    System.out.println("workInProgress DONE: " + workInProgress);
                                    durationFrame durationFrame = new durationFrame(work.get(), inputDurationType);
                                    int dataSize = durationFrame.getInputDataSize();
                                    long finish = System.nanoTime();
                                    long timeElapsed = finish - startTimeDuration;
                                    String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                                    if(dataSize > 0)
                                    {
                                        String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                        statusProgressBarText.setText(loadCompletedMessage);
                                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                        generateDurationButton.setEnabled(false);
                                        nomeGraficoTextField_3.setEnabled(false);
                                        durationTypeComboBox.setEnabled(false);
                                        durationFrame.showUI();
                                    }
                                    else if(!(dataSize > 0) || durationSwingWorker.getIsError())
                                    {
                                        statusProgressBarText.setText("");
                                        generateDurationButton.setEnabled(true);
                                        nomeGraficoTextField_3.setEnabled(true);
                                        durationTypeComboBox.setEnabled(true);
                                        nomeGraficoTextField_3.setText("");
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }
                                    
                                    String graphName = nomeGraficoTextField_3.getText();
                                    if(!graphName.equals(""))
                                        durationFrame.setGraphName(graphName);
                                    else
                                        durationFrame.setGraphName("Duration Graph");
                                    durationFrame.addWindowListener(new java.awt.event.WindowAdapter()
                                    {
                                        @Override
                                        public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                        {
                                            Object[] options = {"Si","No"};
                                            int state = JOptionPane.showOptionDialog(durationFrame, 
                                                        "Sei sicuro di voler chiudere questa finestra?",
                                                        "Chiudi Finestra?", 
                                                        JOptionPane.YES_NO_OPTION,
                                                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                            if(state == JOptionPane.YES_OPTION)
                                            {
                                                windowEvent.getWindow().dispose();
                                                statusProgressBarText.setText("");
                                                generateDurationButton.setEnabled(true);
                                                nomeGraficoTextField_3.setEnabled(true);
                                                durationTypeComboBox.setEnabled(true);
                                                nomeGraficoTextField_3.setText("");
                                                loadDataProgressBar.setValue(0);
                                                if(workInProgress.isEmpty())
                                                    loadDataProgressBar.setVisible(false);
                                            }
                                        }
                                    });
                                }
                                catch (InterruptedException | ExecutionException ex)
                                {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                workInProgress.add(WORKERDURATIONSTART);
                                System.out.println("workInProgress STARTED: " + workInProgress);
                                loadDataProgressBar.setVisible(true);
                                generateDurationButton.setEnabled(false);
                                nomeGraficoTextField_3.setEnabled(false);
                                durationTypeComboBox.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                        int progress = (Integer)evt.getNewValue();
                        generateDurationButton.setEnabled(false);
                        nomeGraficoTextField_3.setEnabled(false);
                        durationTypeComboBox.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(HeadlessException e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_generateDurationButtonActionPerformed

    private void generateMelodicIntervalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateMelodicIntervalButtonActionPerformed
        try
        {     
            melodicIntervallSwingWorker l = new melodicIntervallSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile());
            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                startTimeMelodic = System.nanoTime();
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
                                    workInProgress.remove(WORKERMELODICSTART);
                                    System.out.println("workInProgress DONE: " + workInProgress);
                                    melodicIntervalFrame melodicIntervalFrame = new melodicIntervalFrame(work.get());
                                    int dataSize = melodicIntervalFrame.getInputDataSize();
                                    long finish = System.nanoTime();
                                    long timeElapsed = finish - startTimeMelodic;
                                    String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                                    if(dataSize > 0)
                                    {
                                        String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                        statusProgressBarText.setText(loadCompletedMessage);
                                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                        generateMelodicIntervalButton.setEnabled(false);
                                        nomeGraficoTextField_4.setEnabled(false);
                                        melodicIntervalFrame.showUI();    
                                    }
                                    else if(!(dataSize > 0) || melodicIntervallSwingWorker.getIsError())
                                    {
                                        statusProgressBarText.setText("");
                                        generateMelodicIntervalButton.setEnabled(true);
                                        nomeGraficoTextField_4.setEnabled(true);
                                        nomeGraficoTextField_4.setText("");
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }
                                    
                                    String graphName = nomeGraficoTextField_4.getText();
                                    if(!graphName.equals(""))
                                        melodicIntervalFrame.setGraphName(graphName);
                                    else
                                        melodicIntervalFrame.setGraphName("Melodic Interval Graph");
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
                                                if(workInProgress.isEmpty())
                                                    loadDataProgressBar.setVisible(false);    
                                            }
                                        }
                                    });
                                }
                                catch (InterruptedException | ExecutionException ex)
                                {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                workInProgress.add(WORKERMELODICSTART);
                                System.out.println("workInProgress STARTED: " + workInProgress);
                                loadDataProgressBar.setVisible(true);
                                generateMelodicIntervalButton.setEnabled(false);
                                nomeGraficoTextField_4.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                        int progress = (Integer)evt.getNewValue();
                        generateMelodicIntervalButton.setEnabled(false);
                        nomeGraficoTextField_4.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            }); 
        }
        catch(HeadlessException e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        }     
    }//GEN-LAST:event_generateMelodicIntervalButtonActionPerformed

    private void generateHarmonicIntervalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateHarmonicIntervalButtonActionPerformed
        try
        {                        
            harmonicIntervallSwingWorker l = new harmonicIntervallSwingWorker();
            SwingWorker work = l.createWorker(openFileChoseer.getSelectedFile());

            Object[] options = {"Si","No"};
            int state = JOptionPane.showOptionDialog(null, 
                        "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                        "Informazione", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, null);
            if(state == JOptionPane.YES_OPTION)
            {
                startTimeHarmonic = System.nanoTime();
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
                                    workInProgress.remove(WORKERHARMONICSTART);
                                    System.out.println("workInProgress DONE: " + workInProgress);
                                    harmonicIntervalFrame harmonicIntervalFrame = new harmonicIntervalFrame(work.get());
                                    int dataSize = harmonicIntervalFrame.getInputDataSize();
                                    long finish = System.nanoTime();
                                    long timeElapsed = finish - startTimeHarmonic;
                                    String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                                    if(dataSize > 0)
                                    {
                                        String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                        statusProgressBarText.setText(loadCompletedMessage);
                                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                        generateHarmonicIntervalButton.setEnabled(false);
                                        nomeGraficoTextField_5.setEnabled(false);
                                        harmonicIntervalFrame.showUI();
                                    }
                                    else if(!(dataSize > 0) || harmonicIntervallSwingWorker.getIsError())
                                    {
                                        statusProgressBarText.setText("");
                                        generateHarmonicIntervalButton.setEnabled(true);
                                        nomeGraficoTextField_5.setEnabled(true);
                                        nomeGraficoTextField_5.setText("");
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                        String informationMessage = "Non sono presenti dati da elaborare!";
                                        JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                                    }

                                    String graphName = nomeGraficoTextField_5.getText();
                                    if(!graphName.equals(""))
                                        harmonicIntervalFrame.setGraphName(graphName);
                                    else
                                        harmonicIntervalFrame.setGraphName("Harmonic Interval Graph");
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
                                                if(workInProgress.isEmpty())
                                                    loadDataProgressBar.setVisible(false);
                                            }
                                        }
                                    });
                                }
                                catch (InterruptedException | ExecutionException ex)
                                {
                                    Logger.getLogger(programGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                                break;

                            case STARTED:
                                workInProgress.add(WORKERHARMONICSTART);
                                System.out.println("workInProgress STARTED: " + workInProgress);
                                loadDataProgressBar.setVisible(true);
                                generateHarmonicIntervalButton.setEnabled(false);
                                nomeGraficoTextField_5.setEnabled(false);
                                loadDataProgressBar.setForeground(Color.BLACK);                               
                                loadDataProgressBar.setValue(0);
                                statusProgressBarText.setText("Caricamento in corso");
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                break;
                        }
                    } else if ("progress".equals(evt.getPropertyName())) {
                        statusProgressBarText.setText("Caricamento in corso");
                        int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                        getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                        int progress = (Integer)evt.getNewValue();
                        generateHarmonicIntervalButton.setEnabled(false);
                        nomeGraficoTextField_5.setEnabled(false);
                        loadDataProgressBar.setValue(progress);
                    }
                }
            });         
        }
        catch(HeadlessException e)
        {
            String informationMessage = "Non sono presenti dati da elaborare!";
            JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);
        } 
    }//GEN-LAST:event_generateHarmonicIntervalButtonActionPerformed

    private void xmlValidationEnableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlValidationEnableCheckBoxActionPerformed
        if(xmlValidationEnableCheckBox.isSelected() && !whiteSpaceEnableCheckBox.isSelected())
        {
            xmlValidationEnableCheckBox.setText("Disabilita validazione XML");
            drawMusicData_Utils.setXmlEnableValidationFromGui(true);

            whiteSpaceEnableCheckBox.setText("Disabilita Ignora Spazi XML");
            whiteSpaceEnableCheckBox.setSelected(true);
            drawMusicData_Utils.setXmlEnableIgnoringWhitespaceFromGui(true);
        }
        else
        {
            xmlValidationEnableCheckBox.setText("Abilita validazione XML");
            drawMusicData_Utils.setXmlEnableValidationFromGui(false);
        }
    }//GEN-LAST:event_xmlValidationEnableCheckBoxActionPerformed

    private void whiteSpaceEnableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteSpaceEnableCheckBoxActionPerformed
        if(whiteSpaceEnableCheckBox.isSelected())
        {
            whiteSpaceEnableCheckBox.setText("Disabilita Ignora Spazi XML");
            drawMusicData_Utils.setXmlEnableIgnoringWhitespaceFromGui(true);
        }
        else
        {
            whiteSpaceEnableCheckBox.setText("Abilita Ignora Spazi XML");
            drawMusicData_Utils.setXmlEnableIgnoringWhitespaceFromGui(false);
        }
    }//GEN-LAST:event_whiteSpaceEnableCheckBoxActionPerformed

    private void xmlFileDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlFileDetailButtonActionPerformed
        xmlDetailSwingWorker cdi = new xmlDetailSwingWorker();
        SwingWorker work = cdi.createWorker(openFileChoseer.getSelectedFile());
        
        Object[] options = {"Si","No"};
        int state = JOptionPane.showOptionDialog(null, 
                    "Sei sicuro di voler procedere con l'elaborazione dei dati?",
                    "Informazione", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(state == JOptionPane.YES_OPTION)
        { 
            startTimeInformation = System.nanoTime();
            work.execute();
        }

        work.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                    switch (state)
                    {
                        case DONE:
                        {
                            workInProgress.remove(WORKERGETINFORMATIONSTART);
                            System.out.println("workInProgress DONE: " + workInProgress);

                            trackFrame trackFrame = new trackFrame(cdi.getMainTitle(), cdi.getAuthorsMap(),cdi.getOtherTitleMap(),cdi.getNumber() ,cdi.getWorkTitleMap(),cdi.getWorkNumber(), cdi.getGenresMap(), cdi.getTrackMap());  
                            Boolean dataPresent = trackFrame.getDataPresent();
                            long finish = System.nanoTime();
                            long timeElapsed = finish - startTimeInformation;
                            String executionTime = drawMusicData_Utils.getElapsedTimeFromMilliseconds(timeElapsed);
                            if(dataPresent)
                            {
                                String loadCompletedMessage = "Caricamento Completato in "+executionTime; 
                                statusProgressBarText.setText(loadCompletedMessage);
                                int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                                getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                                xmlFileDetailButton.setEnabled(false);
                                
                                trackFrame.showUI();
                            }
                            else if(!dataPresent || xmlDetailSwingWorker.getIsError())
                            {
                                statusProgressBarText.setText("");
                                xmlFileDetailButton.setEnabled(true);

                                loadDataProgressBar.setValue(0);
                                if(workInProgress.isEmpty())
                                    loadDataProgressBar.setVisible(false);
                                String informationMessage = "Non sono presenti dati da elaborare!";
                                JOptionPane.showMessageDialog(null, informationMessage, "Informazione", JOptionPane.INFORMATION_MESSAGE);    
                            }

                            trackFrame.addWindowListener(new java.awt.event.WindowAdapter()
                            {
                                @Override
                                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                {
                                    Object[] options = {"Si","No"};
                                    int state = JOptionPane.showOptionDialog(trackFrame, 
                                                "Sei sicuro di voler chiudere questa finestra?",
                                                "Chiudi Finestra?", 
                                                JOptionPane.YES_NO_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE, null, options, null);
                                    if(state == JOptionPane.YES_OPTION)
                                    {
                                        windowEvent.getWindow().dispose();
                                        statusProgressBarText.setText("");
                                        xmlFileDetailButton.setEnabled(true);
                                        loadDataProgressBar.setValue(0);
                                        if(workInProgress.isEmpty())
                                            loadDataProgressBar.setVisible(false);
                                    }
                                }
                            });
                            /*
                            System.out.println("Title: " + cdi.getMainTitle());
                            System.out.println("Authors: " + cdi.getAuthorsMap());
                            System.out.println("Work Title: " + cdi.getWorkTitleMap()); 
                            System.out.println("Track: " + cdi.getTrackMap());
                            System.out.println("Genres: " + cdi.getGenresMap());
                            */
                        }
                        break;
                        
                        case STARTED:
                            workInProgress.add(WORKERGETINFORMATIONSTART);
                            System.out.println("workInProgress STARTED: " + workInProgress);
                            xmlFileDetailButton.setEnabled(false);                              
                            loadDataProgressBar.setVisible(true);
                            loadDataProgressBar.setForeground(Color.BLACK);                               
                            loadDataProgressBar.setValue(0);
                            statusProgressBarText.setText("Caricamento in corso");
                            int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                            getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                            break;
                    }
                } else if ("progress".equals(evt.getPropertyName())){ 
                    statusProgressBarText.setText("Caricamento in corso");
                    int labelTarget = drawMusicData_Utils.alignMessageToJBar(loadDataProgressBar, statusProgressBarText);
                    getContentPane().add(statusProgressBarText, new org.netbeans.lib.awtextra.AbsoluteConstraints(labelTarget, 50, -1, -1));
                    int progress = (Integer)evt.getNewValue();
                    xmlFileDetailButton.setEnabled(false);
                    loadDataProgressBar.setValue(progress);
                }
            }
        });            
    }//GEN-LAST:event_xmlFileDetailButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Object[] options = {"Si","No"};
        int state = JOptionPane.showOptionDialog(this, 
                    "Sei sicuro di voler chiudere questa finestra?",
                    "Chiudi Finestra?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(state == JOptionPane.YES_OPTION)
        {
            evt.getWindow().dispose();
        }
    }//GEN-LAST:event_formWindowClosing
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(programGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new programGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel durationBntTitle;
    private javax.swing.JComboBox<String> durationTypeComboBox;
    private javax.swing.JButton generateDurationButton;
    private javax.swing.JButton generateHarmonicIntervalButton;
    private javax.swing.JButton generateMelodicIntervalButton;
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
    private javax.swing.JTextField nomeGraficoTextField_4;
    private javax.swing.JTextField nomeGraficoTextField_5;
    private javax.swing.JButton openFileButton;
    private javax.swing.JLabel openFileIconDescriptioon;
    private javax.swing.JLabel openFileName;
    private javax.swing.JLabel pitchBntTitle;
    private javax.swing.JLabel pitchClassBntTitle;
    private javax.swing.JLabel selectedFile;
    private javax.swing.JLabel selectedFileIcon;
    private javax.swing.JLabel statusProgressBarText;
    private javax.swing.JCheckBox whiteSpaceEnableCheckBox;
    private javax.swing.JButton xmlFileDetailButton;
    private javax.swing.JCheckBox xmlValidationEnableCheckBox;
    // End of variables declaration//GEN-END:variables
}