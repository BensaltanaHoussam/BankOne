package banque;

public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(double solde) {
        super(solde);
        this.tauxInteret = 0.03;
    }

    @Override
    public boolean peutRetirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        return montant <= solde;
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
