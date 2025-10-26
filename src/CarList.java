public interface CarList<T> extends CarCollection<T> {
    T get(int index);
    boolean add(T car, int index);
    boolean removeAt(int index);
}
