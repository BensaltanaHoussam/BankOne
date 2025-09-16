package banque;

import com.sun.corba.se.spi.orb.Operation;

import java.util.ArrayList;
import java.util.List;

abstract class Compte {
    protected String code;
    protected double solde;
    protected ArrayList<Operation> listeOperations;

    public Compte(String code, double solde) {
        if (!code.matches("CPT-\\d{5}")) {
            throw new IllegalArgumentException("Code invalide : format CPT-XXXXX requis.");
        }
        this.code = code;
        this.solde = solde;
        this.listeOperations = new ArrayList<>();
    }

    public abstract void retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public List<Operation> getListeOperations() {
        return listeOperations;
    }
}
