import java.util.Arrays;

public class CarArrayList implements CarList{

    private Car[] carsList = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndexToRemoveAndGet(index);
        return carsList[index];
    }

    @Override
    public boolean add(Car car) {
        checkSize();
        carsList[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean add(Car car, int index){
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
    public boolean remove(Car car) {
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
    public boolean contains(Car car){
        return findElementPosition(car) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        carsList = new Car[10];
        size = 0;
    }

    private int findElementPosition(Car car) {
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
}
