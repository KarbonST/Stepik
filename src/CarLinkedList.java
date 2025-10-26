import java.util.Iterator;
import java.util.NoSuchElementException;

public class CarLinkedList<T> implements CarList<T>, CarQueue<T>{

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(T car) {
        if (size == 0) {
            first = new Node<>(null, car, null);
            last = first;
        } else {
            Node<T> newLastNode = new Node<>(last, car, null);
            last.next = newLastNode;
            last = newLastNode;
        }
        size++;
        return true;
    }

    @Override
    public T peek() {
        return (size == 0) ? null : first.value;
    }

    @Override
    public T poll() {
        if (size == 0) return null;
        T car = get(0);
        removeAt(0);
        return car;
    }

    @Override
    public boolean add(T car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size){
            return add(car);
        }

        Node<T> nodeNext = getNode(index);
        Node<T> nodePrevious = nodeNext.previous;
        Node<T> newNode = new Node<T>(nodePrevious, car, nodeNext);
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
    public boolean remove(T car) {
        int elementPos = findElementPosition(car);
        if (elementPos != -1){
            return removeAt(elementPos);
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node<T> nodeToRemove = getNode(index);
        Node<T> previousNode = nodeToRemove.previous;
        Node<T> nextNode = nodeToRemove.next;

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
    public boolean contains(T car){
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

    private int findElementPosition(T car){
        Node<T> node = first;
        for (int i = 0; i < size; i++){
            if (node.value.equals(car)){
                return i;
            }
            node = node.next;
        }

        return -1;
    }

    private Node<T> getNode (int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++){
            node = node.next;
        }

        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }

                T car = currentNode.value;
                currentNode = currentNode.next;
                return car;
            }
        };
    }

    private static class Node<T> {
        private Node<T> previous;
        private T value;
        private Node<T> next;

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
