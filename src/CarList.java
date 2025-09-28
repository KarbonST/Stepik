public interface CarList extends CarCollection {
    Car get(int index);
//    void add(Car car);
    boolean add(Car car, int index);
//    boolean remove(Car car);
    boolean removeAt(int index);
//    int size();
//    void clear();
}
