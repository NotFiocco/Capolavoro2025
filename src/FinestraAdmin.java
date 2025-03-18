/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author sebas
 */
public class FinestraAdmin extends javax.swing.JFrame {

    private List<KahootClass> domande; // Lista delle domande
    private static final String PASSWORD_FILE = "src/password.txt"; // Percorso del file delle password
    private String hashedPassword ;

    /**
     * Creates new form FinestraAdmin
     */
    public FinestraAdmin() {
        initComponents();
        domande = new ArrayList<>(); // Inizializzazione della lista delle domande
        setLocationRelativeTo(null); // Centra la finestra sullo schermo
        domande = leggiDomandeDaFile(); // Carica le domande dal file
    }

    // Metodo per leggere le domande dal file
    private List<KahootClass> leggiDomandeDaFile() {
        List<KahootClass> domande = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/domande1.txt"))) {
            String linea;
            // Legge ogni riga del file e crea una domanda con le relative opzioni
            while ((linea = br.readLine()) != null) {
                String domanda = linea;
                String[] opzioni = new String[4];
                for (int i = 0; i < 4; i++) {
                    opzioni[i] = br.readLine(); // Legge le opzioni
                }
                int rispostaCorretta = Integer.parseInt(br.readLine()); // Legge la risposta corretta
                domande.add(new KahootClass(domanda, new int[]{rispostaCorretta}, opzioni)); // Aggiunge la domanda alla lista
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return domande;
    }

    // Metodo per aggiungere una nuova domanda
    private void aggiungiDomanda() {
        JTextField domandaField = new JTextField();
        JTextField[] opzioniFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            opzioniFields[i] = new JTextField();
        }
        JTextField rispostaField = new JTextField();
        // Creazione della finestra di input per la nuova domanda
        Object[] message = {
            "Domanda:", domandaField,
            "Opzione 1:", opzioniFields[0],
            "Opzione 2:", opzioniFields[1],
            "Opzione 3:", opzioniFields[2],
            "Opzione 4:", opzioniFields[3],
            "Risposta corretta (0-3):", rispostaField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Aggiungi Domanda", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String domanda = domandaField.getText();
            String[] opzioni = new String[4];
            for (int i = 0; i < 4; i++) {
                opzioni[i] = opzioniFields[i].getText();
            }
            String rispostaText = rispostaField.getText();
            // Controlla che la risposta corretta non sia vuota
            if (rispostaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La risposta corretta non può essere vuota!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int rispostaCorretta = Integer.parseInt(rispostaText);
                // Controlla che l'indice della risposta corretta sia tra 0 e 3
                if (rispostaCorretta < 0 || rispostaCorretta > 3) {
                    JOptionPane.showMessageDialog(this, "L'indice della risposta corretta deve essere tra 0 e 3!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Aggiunge la domanda alla lista
                domande.add(new KahootClass(domanda, new int[]{rispostaCorretta}, opzioni));
                // Salva le domande nel file
                salvaDomandeNelFile();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Input non valido per la risposta corretta!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }              

    // Metodo per rimuovere una domanda dalla lista
    private void rimuoviDomanda(int index) {
        if (index >= 0 && index < domande.size()) {
            domande.remove(index); // Rimuove la domanda
            salvaDomandeNelFile(); // Salva la lista aggiornata nel file
        } else {
            JOptionPane.showMessageDialog(this, "Indice non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo per salvare le domande nel file
    private void salvaDomandeNelFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/domande1.txt"))) {
            for (KahootClass domanda : domande) {
                bw.write(domanda.getDomande()); // Scrive la domanda
                bw.newLine();
                for (String opzione : domanda.getOpzioni()) {
                    bw.write(opzione); // Scrive le opzioni
                    bw.newLine();
                }
                bw.write(String.valueOf(domanda.getRispostaCorretta())); // Scrive la risposta corretta
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Finestra Admin");

        jButton1.setText("Aggiungi domanda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Rimuovi Domanda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Esci");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Apre la finestra per aggiungere una nuova domanda
        aggiungiDomanda();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Chiede l'indice della domanda da rimuovere
        String indexStr = JOptionPane.showInputDialog(this, "Inserisci l'indice della domanda da rimuovere:");
        if (indexStr != null) {
            try {
                int index = Integer.parseInt(indexStr);
                // Rimuove la domanda dall'elenco
                rimuoviDomanda(index);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Input non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Chiude la finestra e torna alla finestra di login
        dispose();
        new UserRequest().setVisible(true);
    }                                        


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
            java.util.logging.Logger.getLogger(FinestraAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinestraAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinestraAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinestraAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinestraAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
