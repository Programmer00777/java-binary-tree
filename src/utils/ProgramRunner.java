package utils;

import model.Passenger;
import service.PassengerTreeService;
import tree.BinaryTree;
import tree.RedBlackTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ProgramRunner {
    private BinaryTree binaryTree = new BinaryTree();
    private RedBlackTree redBlackTree = new RedBlackTree();
    private PassengerTreeService passengerTreeService = new PassengerTreeService(binaryTree);
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        int answer = 0;
        System.out.println("Hello! It's a Passenger Service.");
        while(answer != 1 && answer != 2) {
            System.out.println("Please, choose a way you want to add passengers:");
            System.out.println("1 - from the console");
            System.out.println("2 - from the file");
            answer = scanner.nextInt();
        }

        switch (answer) {
            case (1):
                System.out.println("Please, enter a number of passengers you want to create:");
                answer = scanner.nextInt();
                String firstName = "";
                int ticketNumber = 0;
                int luggageWeight = 0;
                String fName;
                for (int i = 0; i < answer; i++) {
                    System.out.println("Enter a firstname:");
                    fName = scanner.nextLine();
                    System.out.println("Enter a ticket number:");
                    ticketNumber = scanner.nextInt();
                    System.out.println("Enter a luggage weight:");
                    luggageWeight = scanner.nextInt();
                    Passenger passenger = new Passenger(fName, ticketNumber, luggageWeight);
                    passengerTreeService.add(passenger);
                }
                break;
            case (2):
                try {
                    FileReader fileReader = new FileReader();
                    String data = fileReader.readFile("passengers.txt");
                    passengerTreeService.insertPassengersFromString(data);
                    passengerTreeService.printTree();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Unknown case.");
                System.exit(0);
                break;
        }

        answer = -1;
        while (answer != 0) {
            System.out.println("Choose an operation you want to execute:");
            System.out.println("1 - print a binary tree");
            System.out.println("2 - find an average by a ticket number");
            System.out.println("3 - print in order");
            System.out.println("4 - print pre order");
            System.out.println("5 - print post order");
            System.out.println("6 - clear the tree");
            System.out.println("7 - remove left subtree");
            System.out.println("8 - remove right subtree");
            System.out.println("9 - pour the tree to another tree (organized by luggage weight)");
            System.out.println("10 - print a red-black tree");

            System.out.println("\n0 - exit");
            answer = scanner.nextInt();

            switch (answer) {
                case (1):
                    passengerTreeService.printTree();
                    break;
                case (2):
                    System.out.println("An average by a ticket number is " +
                            passengerTreeService.calculateTicketNumberAverage());
                    break;
                case (3):
                    System.out.println("Printing in order...");
                    binaryTree.traverseInOrder(binaryTree.getRoot());
                    break;
                case (4):
                    System.out.println("Printing pre order...");
                    binaryTree.traversePreOrder(binaryTree.getRoot());
                    break;
                case (5):
                    System.out.println("Printing post order...");
                    binaryTree.traversePostOrder(binaryTree.getRoot());
                    break;
                case (6):
                    passengerTreeService.clear();
                    System.out.println("Binary Tree has been cleared!");
                    break;
                case (7):
                    passengerTreeService.removeLeftSubTree();
                    System.out.println("Left subtree was removed.");
                    break;
                case (8):
                    passengerTreeService.removeRightSubTree();
                    System.out.println("Right subtree was removed.");
                    break;
                case (9):
                    System.out.println("Binary Tree is poured to another tree.");
                    binaryTree = passengerTreeService.pourToAnotherTree();
                    passengerTreeService = new PassengerTreeService(binaryTree);
                    break;
                case (10):
                    redBlackTree = passengerTreeService.convertToRedBlackTree();
                    System.out.println("Red Black tree is created:");
                    redBlackTree.printTree();
                    break;
                default:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
            }
        }
    }
}
