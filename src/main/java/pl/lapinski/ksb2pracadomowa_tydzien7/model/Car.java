package pl.lapinski.ksb2pracadomowa_tydzien7.model;

public class Car {

    private long carId;
    private String mark;
    private String model;
    private long productionYear;
    private String colour;

    public Car(long carId, String mark, String model, long productionYear, String colour) {
        this.carId = carId;
        this.mark = mark;
        this.model = model;
        this.productionYear = productionYear;
        this.colour = colour;
    }

    public Car() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(long productionYear) {
        this.productionYear = productionYear;
    }


    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
