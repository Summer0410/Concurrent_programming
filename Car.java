public class Car{
    String car_name;
    String direction;
    String car_status;
    public Car(String name, String direction, String status){
        this.car_name = name;
        this.direction = direction;
        this.car_status = status;
    }
    public void set_status(String status){
        this.car_status = status;
    }
    public String get_status (){
        return this.car_status;
    }
    public String get_direction(){
        return this.direction;
    }
    public String get_name(){
        return this.car_name;
    }
}