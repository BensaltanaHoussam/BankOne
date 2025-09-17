package banque;

import operations.Operation;
import operations.Retrait;
import operations.Versement;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
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

    public abstract boolean peutRetirer(double montant);

    public abstract double calculerInteret();

    public abstract void afficherDetails();

    public void verser(double montant, String source) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        solde += montant;
        Versement v = new Versement(java.time.LocalDate.now().toString(), montant, source);
        listeOperations.add(v);

    }

    public void retirer(double montant, String destination) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        if (peutRetirer(montant)) {
            solde -= montant;
            Retrait r = new Retrait(java.time.LocalDate.now().toString(), montant, destination);
            listeOperations.add(r);
        } else {
            throw new IllegalArgumentException("Retrait refusé : fonds insuffisants.");
        }

    }

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
