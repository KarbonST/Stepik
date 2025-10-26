import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarQueueTest {

    private CarQueue<Car> queue;

    @BeforeEach
    void setUp() {
        queue = new CarLinkedList<>();
        for (int i = 0; i < 10; i++){
            queue.add(new Car(i, "Brand" + i));
        }
    }

    @Test
    void add() {
        assertEquals(10, queue.size());
    }

    @Test
    void peek() {
        Car expectedCar = new Car(0, "Brand0");
        assertEquals(expectedCar, queue.peek());
        assertEquals(10, queue.size());
    }

    @Test
    void poll() {
        Car expectedCar = new Car(0, "Brand0");
        assertEquals(expectedCar, queue.poll());
        assertEquals(9, queue.size());
    }
}