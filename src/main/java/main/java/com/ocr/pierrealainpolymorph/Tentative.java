package main.java.com.ocr.pierrealainpolymorph;

import java.util.ArrayList;
import java.util.List;

public class Tentative {

    public List<Integer> combi = new ArrayList<Integer>();
    public List<String> comparatif = new ArrayList<String>();
    public List<Integer> borneSup = new ArrayList<Integer>();
    public List<Integer> borneInf = new ArrayList<Integer>();


    public List<Integer> getCombi() {
        return combi;
    }

    public void setCombi(List<Integer> combi) {
        this.combi = combi;
    }

    public List<String> getComparatif() {
        return comparatif;
    }

    public void setComparatif(List<String> comparatif) {
        this.comparatif = comparatif;
    }

    public List<Integer> getBorneSup() {
        return borneSup;
    }

    public void setBorneSup(List<Integer> borneSup) {
        this.borneSup = borneSup;
    }

    public List<Integer> getBorneInf() {
        return borneInf;
    }

    public void setBorneInf(List<Integer> borneInf) {
        this.borneInf = borneInf;
    }
}
