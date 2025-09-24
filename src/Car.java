import java.util.Objects;

public class Car {
    private final String brand;
    private final int number;

    public Car(int number, String brand) {
        this.number = number;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number == car.number && Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, number);
    }
}
