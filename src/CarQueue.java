public interface CarQueue<T> extends CarCollection<T>{
    T peek();
    T poll();
}
