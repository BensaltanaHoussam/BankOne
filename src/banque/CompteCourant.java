package banque;

public class CompteCourant extends Compte {

    private double decouvert = 5000.0;

    public CompteCourant(String code, double solde) {
        super(code, solde);
    }

    @Override
    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide : doit être positif.");
        }
        if (solde - montant >= -decouvert) {
            solde -= montant;
        } else {
            throw new IllegalArgumentException("Retrait refusé : dépassement du découvert autorisé.");
        }


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
