import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarListTest {

    private CarList carList;

    @BeforeEach
    void setUp() {
        carList = new CarArrayList();

        for (int i = 0; i < 100; i++){
            Car car = new Car(i, "Brand" + i);
            carList.add(car);
        }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100(){
        assertEquals(100, carList.size());
    }

    @Test
    public void whenGetElementBeyondBoundsThenException(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            carList.get(110);
        });
    }

    @Test
    public void whenRemoveElementByIndexArrayMustBeDecrease(){
        assertTrue(carList.removeAt(5));
        assertEquals(99, carList.size());
    }

    @Test
    public void whenRemoveElementArrayMustBeDecrease(){
        Car newCar = new Car(100, "Honda");
        carList.add(newCar);
        assertEquals(101, carList.size());
        assertTrue(carList.remove(newCar));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenRemoveNonExistentElementReturnFalse(){
        Car newCar = new Car(100, "Honda");
        assertFalse(carList.remove(newCar));
        assertEquals(100, carList.size());
    }

    @Test
    public void getElementByIndex(){
        Car newCar = new Car(100, "Honda");
        carList.add(newCar);
        assertEquals(newCar, carList.get(100));
    }

    @Test
    public void whenClearListSizeMustBeZero(){
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test
    public void insertIntoMiddle(){
        Car newCar = new Car(1, "Honda");
        carList.add(newCar, 5);

        assertEquals(newCar, carList.get(5));
        assertEquals(101, carList.size());
    }

    @Test
    public void insertIntoBeginning(){
        Car newCar = new Car(1, "Honda");
        carList.add(newCar, 0);
        assertEquals(newCar, carList.get(0));
        assertEquals(101, carList.size());
    }

    @Test
    public void insertAtTheEnd(){
        Car newCar = new Car(1, "Honda");
        carList.add(newCar, carList.size());
        assertEquals(newCar, carList.get(carList.size() - 1));
        assertEquals(101, carList.size());
    }
}