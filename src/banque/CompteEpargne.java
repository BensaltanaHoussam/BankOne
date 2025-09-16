package banque;

public class CompteEpargne extends Compte {
    private double tauxInteret ;

    public CompteEpargne(String code, double solde) {
        super (code, solde);
        this.tauxInteret = 0.03;
    }

    @Override
    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        if (montant <= solde) {
            solde -= montant;
        } else {
            throw new IllegalArgumentException("Retrait refusé : fonds insuffisants.");
        }
    }

    @Override
    public double calculerInteret() {
        return solde * tauxInteret;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Epargne: " + code + ", Solde: " + solde + ", Taux d'intérêt: " + tauxInteret);
    }
}
