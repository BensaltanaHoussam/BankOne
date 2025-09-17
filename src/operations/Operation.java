package operations;

import java.util.UUID;

public abstract class Operation {

    protected UUID num;
    protected String date;
    protected double montant;

    Operation(String date, double montant) {
        this.num = UUID.randomUUID();
        this.date = date;
        this.montant = montant;
    }

    public UUID getNumero() {
        return num;
    }

    public String getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }


}
