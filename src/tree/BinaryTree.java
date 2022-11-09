package tree;

import model.Passenger;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode root;
    private double averageTicketNumber = 0;
    private int size = 0;

    public TreeNode getRoot() {
        return root;
    }

    public void add(Passenger value) {
        root = addRecursive(root, value);
        averageTicketNumber+=value.getTicketNumber();
        size++;
    }

    public void addByWeight(Passenger value) {
        root = addByWeightRecursive(root, value);
        averageTicketNumber+=value.getTicketNumber();
        size++;
    }

    public void delete(Passenger value) {
        root = deleteRecursive(root, value);
        averageTicketNumber-=value.getTicketNumber();
        size--;
    }

    public boolean containsNode(Passenger value) {
        return containsNodeRecursive(root, value);
    }

    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println("\n" + node.passenger);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(TreeNode node) {
        if (node != null) {
            System.out.println("\n" + node.passenger);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public BinaryTree pour(TreeNode node, BinaryTree targetTree) {
        if (node != null) {
            targetTree.addByWeight(node.passenger);
            pour(node.left, targetTree);
            pour(node.right, targetTree);
        }
        return targetTree;
    }

    public void traversePostOrder(TreeNode node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.println("\n" + node.passenger);
        }
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove();
            System.out.println("\n" + node.passenger);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    public double getTicketNumberAverage() {
        if (averageTicketNumber != 0) {
            return averageTicketNumber / size;
        } else return 0.0;
    }

    public void clear() {
        root = null;
        averageTicketNumber = 0.0;
        size = 0;
    }

    private static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.passenger);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node,  "", true);
    }

    public void deleteLeftSubTree() {
        if (root != null) {
            root.left = null;
        }
    }

    public void deleteRightSubTree() {
        if (root != null) {
            root.right = null;
        }
    }

    private TreeNode addRecursive(TreeNode current, Passenger value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value.getFirstName().compareTo(current.passenger.getFirstName()) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.getFirstName().compareTo(current.passenger.getFirstName()) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    private TreeNode addByWeightRecursive(TreeNode current, Passenger value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value.getLuggageWeight() < current.passenger.getLuggageWeight()) {
            current.left = addByWeightRecursive(current.left, value);
        } else if (value.getLuggageWeight() > current.passenger.getLuggageWeight()) {
            current.right = addByWeightRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    private boolean containsNodeRecursive(TreeNode current, Passenger value) {
        if (current == null) {
            return false;
        }
        if (value.getFirstName().compareTo(current.passenger.getFirstName()) == 0) {
            return true;
        }
        return value.getFirstName().compareTo(current.passenger.toString()) == -1 ?
                containsNodeRecursive(current.left, value) :
                containsNodeRecursive(current.right, value);
    }

    private TreeNode deleteRecursive(TreeNode current, Passenger value) {
        if (current == null) {
            return null;
        }

        if (value.getFirstName().compareTo(current.passenger.getFirstName()) == 0) {
            // if the node has no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // if the node has only one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            // if the node has two children
            Passenger smallestValue = findSmallestValue(current.right);
            current.passenger = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value.getFirstName().compareTo(current.passenger.getFirstName()) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private Passenger findSmallestValue(TreeNode root) {
        return root.left == null ? root.passenger : findSmallestValue(root.left);
    }
}

class TreeNode {
    Passenger passenger;
    TreeNode left;
    TreeNode right;

    public TreeNode(Passenger passenger) {
        this.passenger = passenger;
        this.left = null;
        this.right = null;
    }
}
