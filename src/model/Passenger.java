package model;

public class Passenger {
    private String firstName;
    private int ticketNumber;
    private int luggageWeight;

    public Passenger(String firstName, int ticketNumber, int luggageWeight) {
        this.firstName = firstName;
        this.ticketNumber = ticketNumber;
        this.luggageWeight = luggageWeight;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getLuggageWeight() {
        return luggageWeight;
    }

    @Override
    public String toString() {
        return "{" + this.firstName + ", #" + this.ticketNumber + ", " + this.luggageWeight + "kg.}";
    }
}
