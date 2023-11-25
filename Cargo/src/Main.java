public class Main {
    public static void main(String[] args) {

        Dimensions cargoSize = new Dimensions("Car", 5, 2, 2);
        Dimensions cargoSize2 = cargoSize.setLenght(10);

        String carSize = cargoSize.toString();
        String planeSize = cargoSize2.toString();

        CargoInfo car = new CargoInfo("Skoda", 1460, carSize, "Moscow", false, "O792PX197", true);
        CargoInfo plane = car.setNameAndSize("Plane", planeSize);
        CargoInfo planeToFlorida = plane.setDeliveryAddress("Florida");


        System.out.println(car);
        System.out.println(plane);



    }
}
