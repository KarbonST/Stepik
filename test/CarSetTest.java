import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarSetTest {

    private CarSet carSet;

    @BeforeEach
    public void setUp(){
        carSet = new CarHashSet();

        for (int i = 0; i < 100; i++){
            Car car = new Car(i, "Brand" + i);
            carSet.add(car);
        }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100(){
        assertEquals(100, carSet.size());
    }

    @Test
    public void whenAddedNewElementMustBeTrue(){
        Car car = new Car(110, "BMW");
        assertTrue(carSet.add(car));
        assertEquals(101, carSet.size());
    }

    @Test
    public void whenAddedTheSameObjectMustReturnFalse(){
        Car car = new Car(110, "BMW");
        carSet.add(car);
        assertFalse(carSet.add(car));
        assertEquals(101, carSet.size());
    }

    @Test
    public void removeElement(){
        Car car = new Car(110, "BMW");
        carSet.add(car);
        assertTrue(carSet.remove(car));
        assertEquals(100, carSet.size());
    }

    @Test
    public void removeNonexistentElement(){
        Car car = new Car(110, "BMW");
        assertFalse(carSet.remove(car));
        assertEquals(100, carSet.size());
    }

    @Test
    public void clearSet(){
        carSet.clear();
        assertEquals(0, carSet.size());
    }

    @Test
    public void listContainsCar(){
        Car newCar = new Car(1, "Honda");
        carSet.add(newCar);
        assertTrue(carSet.contains(newCar));
    }

    @Test
    public void listNotContainsCar(){
        Car newCar = new Car(1, "Honda");
        assertFalse(carSet.contains(newCar));
    }

    @Test
    public void testIterator(){
        int index = 0;
        for (Car car: carSet){
            index++;
        }

        assertEquals(100, index);
    }

}