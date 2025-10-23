import java.util.*;

public class CarHashMap implements CarMap{

    private static final int INIT_SIZE_OF_ARRAY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INIT_SIZE_OF_ARRAY];
    private int size = 0;


    @Override
    public void put(CarOwner key, Car value) {
        if (size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean puttedNew = put(key, value, array);
        if (puttedNew){
            size++;
        }

    }

    private boolean put(CarOwner key, Car value, Entry[] dst){
        int position = getElementPosition(key, dst.length);
        Entry existedElement = dst[position];
        if (existedElement == null){
            Entry entry = new Entry(key, value, null);
            dst[position] = entry;
            return true;
        } else {
            while (true) {
                if (existedElement.key.equals(key)){
                    existedElement.value = value;
                    return false;
                }
                if (existedElement.next == null){
                    existedElement.next = new Entry(key, value, null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedElement = array[position];

        while (existedElement != null){
            if (existedElement.key.equals(key)){
                return existedElement.value;
            }
            existedElement = existedElement.next;
        }

        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keys = new HashSet<>(size);
        for (Entry entry: array){
            Entry existedElement = entry;
            while (existedElement != null){
                keys.add(existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return keys;
    }

    @Override
    public List<Car> values() {
        List<Car> cars = new ArrayList<>(size);
        for (Entry entry: array){
            Entry existedElement = entry;
            while (existedElement != null){
                cars.add(existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return cars;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }

        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if (secondLast.key.equals(key)) {
            array[position] = last;
            size--;
            return true;
        }

        while (last != null){
            if (last.key.equals(key)){
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INIT_SIZE_OF_ARRAY];
        size = 0;
    }

    private void increaseArray(){
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry: array) {
            Entry existedElement = entry;
            while (existedElement != null){
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getElementPosition(CarOwner key, int arrayLength){
        return Math.abs(key.hashCode() % arrayLength);
    }

    private static class Entry {
        private final CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
