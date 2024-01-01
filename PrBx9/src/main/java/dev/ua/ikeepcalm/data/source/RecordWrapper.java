package dev.ua.ikeepcalm.data.source;

public class RecordWrapper {

    private final String name;
    private double area;
    private int population;

    public RecordWrapper(String name, double area, int population) {
        this.name = name;
        this.area = area;
        this.population = population;
    }

    @Override
    public String toString() {
        return name + ", " + area + ", " + population;
    }

    public String toPrettyString() {
        return "Region name: " + name + "\nArea: " + area + "\nPopulation: " + population + "\n";
    }

    public static RecordWrapper fromString(String line) throws IllegalArgumentException{
        try {
            line = line.replace("\n", "");
            String[] parts = line.split(",");

            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            if (parts.length == 3) {
                return new RecordWrapper(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
            } else {
                throw new IllegalArgumentException("Invalid record format!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid record format!");
        }
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
