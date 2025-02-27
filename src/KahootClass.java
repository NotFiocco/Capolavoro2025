/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author s.isaic
 */
public class KahootClass {

    private String domande;
    private int[] risposta;
    private String[] opzioni;

    public KahootClass() {
        domande = "";
        opzioni = new String[4];
        risposta = new int[4];
    }

    public KahootClass(String domande, int[] risposta, String[] opzioni) {
        this.risposta = new int[4];
        for (int ii = 0; ii < risposta.length && ii < 4; ii++) {
            if (risposta[ii] > 4 || risposta[ii] < 0) {
                this.risposta = null;
            } else {
                this.risposta[ii] = risposta[ii];
            }
        }
        this.opzioni = opzioni.clone();
        this.domande = domande;
    }

    public KahootClass(KahootClass k) {
        this.domande = k.domande;
        this.opzioni = k.opzioni.clone();
        this.risposta = k.risposta.clone();
    }

    public void setDomande(String domande) {
        this.domande = domande;
    }

    public void setRisposta(int[] risposta) {

        this.risposta = new int[4];
        if (risposta.length != 4) {
            throw new IllegalArgumentException("Risposta array must have exactly 4 values.");
        }
        for (int ii = 0; ii < risposta.length; ii++) {
            if (risposta[ii] > 4 || risposta[ii] < 0) {
                throw new IllegalArgumentException("Invalid answer index: " + risposta[ii]);
            }
            this.risposta[ii] = risposta[ii];
        }
    }

    public void setOpzioni(String[] opzioni) {
        if (opzioni.length != 4) {
            throw new IllegalArgumentException("Opzioni array must have exactly 4 options.");
        }
        this.opzioni = opzioni.clone();
    }

    public String getDomande() {
        return domande;
    }

    public int[] getRisposta() {
        return risposta;
    }

    public String[] getOpzioni() {
        return opzioni;
    }

    public int getRispostaCorretta() {
        return risposta[0];
    }
}
