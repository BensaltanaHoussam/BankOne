import banque.CompteCourant;
import banque.CompteEpargne;

public class Main {
    public static void main(String[] args) {
        CompteCourant cc = new CompteCourant("CPT-12345" , 5000);
        cc.retirer(200);
        cc.afficherDetails();

        CompteEpargne ce = new CompteEpargne("CPT-54321" , 15000);
        ce.retirer(100);
        ce.calculerInteret();
        ce.afficherDetails();

    }
}
