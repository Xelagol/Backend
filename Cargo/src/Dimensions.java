public class Dimensions {

    private final double lenght;
    private final double width;
    private final double heigth;
    public String volume;
    private final String name;

    public Dimensions(String name, double length, double width, double height) {

        this.name = name;
        this.lenght = length;
        this.width = width;
        this.heigth = height;
    }

    public Dimensions setLenght(double length) {
        return new Dimensions(name, length, width, heigth);
    }
    public Dimensions setWight(double width) {
        return new Dimensions(name, lenght, width, heigth);
    }
    public Dimensions setHeight(double heigth) {
        return new Dimensions(name, lenght, width, heigth);
    }
    public  String getName() {
        return name;
    }

    public double getLenght() {
        return lenght;
    }
    public double getWidht() {
        return width;
    }
    public double getHeight() {
        return heigth;
    }
     public String getVolume() {
        volume = String.format("%.2f", lenght * width * heigth);
        return volume;
    }
    public String toString() {
        return "Размеры" +"\n Длина - " + getLenght() + "\n Ширина - " + getWidht() + "\n Высота - " + getHeight() + "\n Объем - " + getVolume();
    }









    }


