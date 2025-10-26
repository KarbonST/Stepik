import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CarArrayList<T> implements CarList<T>{

    private Object[] carsList = new Object[10];
    private int size = 0;

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndexToRemoveAndGet(index);
        return (T) carsList[index];
    }

    @Override
    public boolean add(T car) {
        checkSize();
        carsList[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(T car, int index){
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        checkSize();

        for (int i = size; i > index; i--){
            carsList[i] = carsList[i-1];
        }
        carsList[index] = car;
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
        checkIndexToRemoveAndGet(index);
        for (int i = index; i < size - 1; i++){
            carsList[i] = carsList[i+1];
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
        carsList = new Object[10];
        size = 0;
    }

    private int findElementPosition(T car) {
        for (int i = 0; i < size; i++){
            if (carsList[i].equals(car)){
                return i;
            }
        }
        return -1;
    }

    private void checkIndexToRemoveAndGet(int index){
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkSize(){
        if (size >= carsList.length){
            carsList = Arrays.copyOf(carsList, carsList.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                return (T) carsList[index++];
            }
        };
    }
}
