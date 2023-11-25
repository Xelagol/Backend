public class CargoInfo {


    private final String name;
    private final double weight;
    private final String deliveryAddress;
    private final boolean rotate;
    private final String regNumber;
    private final boolean fragile;
    private final String size;

    public CargoInfo(String name, double weight, String size, String deliveryAddress, boolean rotate, String regNumber, boolean fragile) {
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.deliveryAddress = deliveryAddress;
        this.rotate = rotate;
        this.regNumber = regNumber;
        this.fragile = fragile;

    }

    public CargoInfo setNameAndSize(String name, String size) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }

    public CargoInfo setWeight(double weight) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }

    public CargoInfo setDeliveryAddress(String deliveryAddress) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }

    public CargoInfo setRotate(boolean rotate) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }

    public CargoInfo setRegNumber(String regNumber) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }

    public CargoInfo setFragile(boolean fragile) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }
    public CargoInfo setSize(String size) {
        return new CargoInfo(name, weight, size, deliveryAddress, rotate, regNumber, fragile);
    }




    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean getRotate() {
        return rotate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public boolean getFragile() {
        return fragile;
    }

    public String toString() {
        return name +
                "\nВес " + weight +
                size +
                "\nАдрес доставки " + deliveryAddress +
                "\nВозможность переворачивать: " + (fragile ? "Можно" : "Нет") +
                "\nРег. номер " + regNumber +
                "\nХрупкое: " + (fragile ? "Хрупкое" : "Нет");
    }

}








