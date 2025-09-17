package operations;

public class Retrait extends Operation {
    private String destination;

    public Retrait(String date, double montant, String destination) {
        super(date, montant);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
