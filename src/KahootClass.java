/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author s.isaic
 */
public class KahootClass {

    private String domande;  // Variabile per memorizzare la domanda
    private int[] risposta;  // Array per memorizzare le risposte (corrette e errate)
    private String[] opzioni; // Array per memorizzare le opzioni di risposta

    // Costruttore predefinito che inizializza la domanda e le opzioni vuote
    public KahootClass() {
        domande = "";
        opzioni = new String[4]; // Imposta 4 opzioni vuote
        risposta = new int[4]; // Imposta 4 risposte vuote
    }

    // Costruttore con parametri per inizializzare la domanda, le risposte e le opzioni
    public KahootClass(String domande, int[] risposta, String[] opzioni) {
        this.risposta = new int[4]; // Inizializza l'array delle risposte a 4
        // Controlla che le risposte siano valide (compreso tra 0 e 4)
        for (int ii = 0; ii < risposta.length && ii < 4; ii++) {
            if (risposta[ii] > 4 || risposta[ii] < 0) {
                this.risposta = null; // Se la risposta non è valida, setta a null
            } else {
                this.risposta[ii] = risposta[ii]; // Altrimenti imposta la risposta
            }
        }
        this.opzioni = opzioni.clone(); // Clona l'array delle opzioni per evitare modifiche esterne
        this.domande = domande; // Imposta la domanda
    }

    // Costruttore di copia per creare una nuova istanza di KahootClass da un altro oggetto
    public KahootClass(KahootClass k) {
        this.domande = k.domande; // Copia la domanda
        this.opzioni = k.opzioni.clone(); // Clona le opzioni
        this.risposta = k.risposta.clone(); // Clona le risposte
    }

    // Metodo per impostare la domanda
    public void setDomande(String domande) {
        this.domande = domande; // Imposta la domanda
    }

    // Metodo per impostare le risposte (deve contenere esattamente 4 risposte)
    public void setRisposta(int[] risposta) {
        this.risposta = new int[4]; // Inizializza un nuovo array di risposte
        if (risposta.length != 4) {
            throw new IllegalArgumentException("Risposta array must have exactly 4 values."); // Lancia eccezione se l'array non ha 4 risposte
        }
        for (int ii = 0; ii < risposta.length; ii++) {
            if (risposta[ii] > 4 || risposta[ii] < 0) {
                throw new IllegalArgumentException("Invalid answer index: " + risposta[ii]); // Lancia eccezione se la risposta è fuori dall'intervallo valido
            }
            this.risposta[ii] = risposta[ii]; // Imposta la risposta
        }
    }

    // Metodo per impostare le opzioni (deve contenere esattamente 4 opzioni)
    public void setOpzioni(String[] opzioni) {
        if (opzioni.length != 4) {
            throw new IllegalArgumentException("Opzioni array must have exactly 4 options."); // Lancia eccezione se l'array non ha 4 opzioni
        }
        this.opzioni = opzioni.clone(); // Clona l'array delle opzioni
    }

    // Metodo per ottenere la domanda
    public String getDomande() {
        return domande; // Restituisce la domanda
    }

    // Metodo per ottenere le risposte
    public int[] getRisposta() {
        return risposta; // Restituisce l'array delle risposte
    }

    // Metodo per ottenere le opzioni
    public String[] getOpzioni() {
        return opzioni; // Restituisce l'array delle opzioni
    }

    // Metodo per ottenere la risposta corretta (la prima risposta nell'array)
    public int getRispostaCorretta() {
        return risposta[0]; // Restituisce la risposta corretta
    }
}
