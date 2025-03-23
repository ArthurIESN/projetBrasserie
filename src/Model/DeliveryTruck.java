package Model;

public class DeliveryTruck {
    private Integer id;
    private String licensePlate;
    private float fuelQuantity;
    private float mileage;

    public DeliveryTruck(Integer id,String licensePlate,float fuelQuantity,float mileage){
        this.id = id;
        this.licensePlate = licensePlate;
        this.fuelQuantity = fuelQuantity;
        this.mileage = mileage;
    }

    public Integer getId(){
        return this.id;
    }

    public String getLicensePlate(){
        return this.licensePlate;
    }

    public float getFuelQuantity(){
        return this.fuelQuantity;
    }

    public float getMileage(){
        return this.mileage;
    }

    @Override
    public String toString(){
        return "DeliveryTruck{ \n" +
                "       id=" + id + ", \n" +
                "       licensePlate='" + licensePlate + '\n' +
                "       fuelQuantity=" + fuelQuantity + "\n" +
                "       mileage=" + mileage + "\n" +
                '}';
    }
}
