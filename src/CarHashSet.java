import java.util.Iterator;
import java.util.NoSuchElementException;

public class CarHashSet implements CarSet{

    private static final int INIT_SIZE_OF_ARRAY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INIT_SIZE_OF_ARRAY];
    private int size = 0;

    @Override
    public boolean add(Car car) {
        if (size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean added = add(car, array);
        if (added){
            size++;
        }
        return added;
    }

    private boolean add(Car car, Entry[] dst){
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null){
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existedElement = dst[position];
            while (true) {
                if (existedElement.value.equals(car)) {
                    return false;
                } else if (existedElement.next == null){
                    existedElement.next = new Entry(car, null);
                    return true;
                } else {
                    existedElement = existedElement.next;
                }
            }
        }
    }

    @Override
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null){
            return false;
        }
        Entry secondLast = array[position];
        Entry last = secondLast.next;
        if (secondLast.value.equals(car)){
            array[position] = last;
            size--;
            return true;
        }
        while(last != null) {
            if (last.value.equals(car)) {
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
    public boolean contains(Car car){
        int index = getElementPosition(car, array.length);
        Entry entry = array[index];
        while (entry != null){
            if (entry.value.equals(car)){
                return true;
            }
            entry = entry.next;
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
        for (Entry entry: array){
            Entry existedElement = entry;
            while (existedElement != null){
                add(existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getElementPosition(Car car, int arrayLength){
        return Math.abs(car.hashCode() % arrayLength);
    }

    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            int index = 0;
            int bucket = 0;
            Entry currentEntry;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Car next() {
                while (array[bucket] == null){
                    bucket++;
                }

                if (currentEntry == null){
                    currentEntry = array[bucket];
                }

                Car car = currentEntry.value;
                index++;

                currentEntry = currentEntry.next;
                if (currentEntry == null){
                    bucket++;
                }

                return car;

            }

        };
    }

    private static class Entry {
        private final Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }
}
