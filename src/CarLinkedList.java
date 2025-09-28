import java.util.Iterator;
import java.util.NoSuchElementException;

public class CarLinkedList implements CarList{

    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(Car car) {
        if (size == 0) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node newLastNode = new Node(last, car, null);
            last.next = newLastNode;
            last = newLastNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size){
            return add(car);
        }

        Node nodeNext = getNode(index);
        Node nodePrevious = nodeNext.previous;
        Node newNode = new Node(nodePrevious, car, nodeNext);
        nodeNext.previous = newNode;

        if (nodePrevious != null){
            nodePrevious.next = newNode;
        } else {
            first = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Car car) {
        int elementPos = findElementPosition(car);
        if (elementPos != -1){
            return removeAt(elementPos);
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node nodeToRemove = getNode(index);
        Node previousNode = nodeToRemove.previous;
        Node nextNode = nodeToRemove.next;

        if (previousNode != null){
            previousNode.next = nextNode;
        } else {
            first = nextNode;
        }

        if (nextNode != null){
            nextNode.previous = previousNode;
        } else {
            last = previousNode;
        }

        size--;
        return true;
    }

    @Override
    public boolean contains(Car car){
        return findElementPosition(car) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private int findElementPosition(Car car){
        Node node = first;
        for (int i = 0; i < size; i++){
            if (node.value.equals(car)){
                return i;
            }
            node = node.next;
        }

        return -1;
    }

    private Node getNode (int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node node = first;
        for (int i = 0; i < index; i++){
            node = node.next;
        }

        return node;
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            Node currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Car next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }

                Car car = currentNode.value;
                currentNode = currentNode.next;
                return car;
            }
        };
    }

    private static class Node {
        private Node previous;
        private Car value;
        private Node next;

        public Node(Node previous, Car value, Node next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
