import model.Passenger;
import service.PassengerTreeService;
import tree.BinaryTree;

public class Test {
    public static void main(String[] args) {
        Passenger passenger1 = new Passenger("Johnson", 4, 14);
        Passenger passenger2 = new Passenger("Thomas", 20, 13);
        Passenger passenger3 = new Passenger("Thomas123", 5, 17);
        Passenger passenger4 = new Passenger("test", 32, 10);
        Passenger passenger5 = new Passenger("John", 1, 0);
        Passenger passenger6 = new Passenger("Karl", 34, 21);
        Passenger passenger7 = new Passenger("Raiee", 3, 13);
        Passenger passenger8 = new Passenger("Bob", 7, 15);
        BinaryTree tree = new BinaryTree();

        tree.add(passenger1);
        tree.add(passenger2);
        tree.add(passenger3);
        tree.add(passenger4);
        tree.add(passenger5);
        tree.add(passenger6);
        tree.add(passenger7);
        tree.add(passenger8);

        PassengerTreeService service = new PassengerTreeService(tree);

        service.printTree();

        System.out.println("\n\n=======");

        PassengerTreeService service1 = new PassengerTreeService(service.pourToAnotherTree());

        service1.printTree();
    }
}
