package banque;

public class CompteCourant extends Compte {

    private double decouvert = 5000.0;

    public CompteCourant(String s, double solde) {
        super(solde);
    }

    @Override
    public boolean peutRetirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        return solde - montant >= -decouvert;
    }

    @Override
    public double calculerInteret() {
        return 0;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Courant: " + code + ", Solde: " + solde + ", Découvert: " + decouvert);
    }


}
