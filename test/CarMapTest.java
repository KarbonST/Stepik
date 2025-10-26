import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {

    private CarMap<CarOwner, Car> carMap;
    private CarMap<CarOwner, Car> littleCarMap;

    @BeforeEach
    void setUp() {
        carMap = new CarHashMap<>();
        littleCarMap = new CarHashMap<>();

        for (int i = 0; i < 100; i++){
            Car car = new Car(i, "Brand" + i);
            CarOwner carOwner = new CarOwner(i, "John" + i, "Jackson" + i);

            carMap.put(carOwner, car);
        }
    }

    @Test
    public void whenPut100ElementsThenSizeMustBe100() {
        assertEquals(100, carMap.size());
    }

    @Test
    public void whenPut100ElementsWith10DifferentKeysThenSizeMustBe10(){
        for (int i = 0; i < 100; i++){
            int index = i % 10;
            Car car = new Car(index, "Brand" + index);
            CarOwner carOwner = new CarOwner(index, "John" + index, "Jackson" + index);
            littleCarMap.put(carOwner, car);
        }

        assertEquals(10, littleCarMap.size());
    }

    @Test
    public void whenGetElementWithUnknownKeyThenNull(){
        CarOwner carOwner = new CarOwner(200,"John200", "Jackson200" );
        assertNull(carMap.get(carOwner));
    }

    @Test
    public void whenGetExistingKeyThenReturnsValue(){
        CarOwner carOwner = new CarOwner(42, "John42", "Jackson42");
        Car car = carMap.get(carOwner);
        assertNotNull(car);
        assertEquals(42, car.getNumber());
        assertEquals("Brand42", car.getBrand());
    }

    @Test
    public void whenKeySetThenReturnsTheWholeSetOfKeys(){
        assertEquals(100, carMap.keySet().size());
    }

    @Test
    public void whenValuesThenReturnsTheWholeValuesFromSet(){
        assertEquals(100, carMap.values().size());
    }

    @Test
    public void sizeOfKeysMustBeEqualSizeOfValues(){
        assertEquals(100, carMap.size());
        assertEquals(100, carMap.values().size());
        assertEquals(100, carMap.keySet().size());
    }

    @Test
    public void removeValueByKey(){
        assertEquals(100, carMap.size());
        CarOwner carOwner = new CarOwner(50, "John50", "Jackson50");
        assertTrue(carMap.remove(carOwner));
        assertEquals(99, carMap.size());
        assertFalse(carMap.remove(carOwner));
    }

    @Test
    public void removeValueByUnknownKey(){
        assertEquals(100, carMap.size());
        CarOwner carOwner = new CarOwner(200, "John200", "Jackson200");
        assertFalse(carMap.remove(carOwner));
        assertEquals(100, carMap.size());
    }

    @Test
    public void clearMap(){
        carMap.clear();
        assertEquals(0, carMap.size());
    }
}