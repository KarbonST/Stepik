import java.util.*;

public class CarHashMap<K, V> implements CarMap<K, V>{

    private static final int INIT_SIZE_OF_ARRAY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Object[] array = new Object[INIT_SIZE_OF_ARRAY];
    private int size = 0;


    @Override
    public void put(K key, V value) {
        if (size >= (array.length * LOAD_FACTOR)){
            increaseArray();
        }
        boolean puttedNew = put(key, value, array);
        if (puttedNew){
            size++;
        }

    }

    @SuppressWarnings("unchecked")
    private boolean put(K key, V value, Object[] dst){
        int position = getElementPosition(key, dst.length);
        Entry<K, V> existedElement = (Entry<K, V>) dst[position];
        if (existedElement == null){
            Entry<K, V> entry = new Entry<>(key, value, null);
            dst[position] = entry;
            return true;
        } else {
            while (true) {
                if (existedElement.key.equals(key)){
                    existedElement.value = value;
                    return false;
                }
                if (existedElement.next == null){
                    existedElement.next = new Entry<>(key, value, null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        int position = getElementPosition(key, array.length);
        Entry<K, V> existedElement = (Entry<K, V>) array[position];

        while (existedElement != null){
            if (existedElement.key.equals(key)){
                return existedElement.value;
            }
            existedElement = existedElement.next;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>(size);
        for (Object entry: array){
            Entry<K, V> existedElement = (Entry<K, V>) entry;
            while (existedElement != null){
                keys.add(existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return keys;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> values() {
        List<V> cars = new ArrayList<>(size);
        for (Object entry: array){
            Entry<K, V> existedElement = (Entry<K, V>) entry;
            while (existedElement != null){
                cars.add(existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return cars;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(K key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }

        Entry<K, V> secondLast = (Entry<K, V>) array[position];
        Entry<K, V> last = secondLast.next;
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

    @SuppressWarnings("unchecked")
    private void increaseArray(){
        Object[] newArray = new Object[array.length * 2];
        for (Object entry: array) {
            Entry<K, V> existedElement = (Entry<K, V>) entry;
            while (existedElement != null){
                put(existedElement.key, existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }

    private int getElementPosition(K key, int arrayLength){
        return Math.abs(key.hashCode() % arrayLength);
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
