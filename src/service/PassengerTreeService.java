package service;

import model.Passenger;
import tree.BinaryTree;
import tree.RedBlackTree;

import java.util.Scanner;

public class PassengerTreeService {
    private final BinaryTree passengers;

    public PassengerTreeService(BinaryTree passengers) {
        this.passengers = passengers;
    }

    public double calculateTicketNumberAverage() {
        return passengers.getTicketNumberAverage();
    }

    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    public void printTree() {
        passengers.prettyPrintTree(passengers.getRoot());
    }

    public void removeLeftSubTree() {
        passengers.deleteLeftSubTree();
    }

    public void removeRightSubTree() {
        passengers.deleteRightSubTree();
    }

    public BinaryTree pourToAnotherTree() {
        BinaryTree targetTree = new BinaryTree();
        return passengers.pour(passengers.getRoot(), targetTree);
    }

    public RedBlackTree convertToRedBlackTree() {
        RedBlackTree redBlackTree = new RedBlackTree();
        passengers.convertToRedBlack(passengers.getRoot(), redBlackTree);

        return redBlackTree;
    }

    public void insertPassengersFromString(String data) {
        Scanner scanner = new Scanner(data);

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String[] properties = currentLine.split(",");
            passengers.add(new Passenger(properties[0],
                        Integer.parseInt(properties[1]),
                        Integer.parseInt(properties[2])));
        }
    }

    public void clear() {
        passengers.clear();
    }
}