import java.util.ArrayList;

public class main{
    public static void main(String args[]) throws InterruptedException {
        lightsController lightsControl = new lightsController("controller 1");//Create lights contorller
        lightsControl.start();//Start the light controller
        ArrayList<Car> car_A = new ArrayList<>();
        ArrayList<Car> car_B = new ArrayList<>();
        ArrayList<Car> car_C = new ArrayList<>();
        ArrayList<Car> car_D = new ArrayList<>();
        randomDirection direction = new randomDirection();//Randomly generate the car driving direction
        //Create 3 vehicles for road A
        for (int i = 0; i < 3; i++){
            String car_direction = direction.get_direction();
            Car new_car = new Car("carA_" + Integer.toString(i+1), car_direction, "waiting");
            car_A.add(new_car);
        }
         //Create 2 vehicles for road B
        for (int i = 0; i < 2; i++){
            String car_direction = direction.get_direction();
            Car new_car = new Car("carB_" + Integer.toString(i+1), car_direction, "waiting");
            car_B.add(new_car);
        }
        //Create a Car for raod C
        String car_direction = direction.get_direction();
        Car new_car_c = new Car("carC",car_direction, "waiting");
        car_C.add(new_car_c);
        //Create a car for raod D
        car_direction = direction.get_direction();
        Car new_car_d = new Car("carD" , car_direction, "waiting");
        car_D.add(new_car_d);
        roadThread road_A = new roadThread("A", car_A, lightsControl);
        roadThread road_B = new roadThread("B", car_B, lightsControl);
        roadThread road_C = new roadThread("C", car_C, lightsControl);
        roadThread road_D = new roadThread("D", car_D, lightsControl);
        road_A.start();
        road_B.start();
        road_C.start();
        road_D.start();
    }
}
