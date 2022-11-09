package service;

import tree.BinaryTree;

public class PassengerTreeService {
    private final BinaryTree passengers;

    public PassengerTreeService(BinaryTree passengers) {
        this.passengers = passengers;
    }

    public double calculateTicketNumberAverage() {
        return passengers.getTicketNumberAverage();
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
}
