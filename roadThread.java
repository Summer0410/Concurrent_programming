import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class roadThread extends Thread{
    private final Lock lock = new ReentrantLock();
    String road_name; 
    ArrayList<Car> cars;
    Boolean has_waiting_car;
    lightsController lightsController;
   public roadThread(String road_name, ArrayList<Car> cars, lightsController lightsController){
        this.road_name = road_name;
        this.cars = cars;
        this.lightsController = lightsController;
   }
   public String get_name(){
        return this.road_name;
   }
   public ArrayList<Car> get_car(){
        return this.cars;
   }
   //These 4 funtions to make that when facing the red light, the lane is locked so that no left can be made from other lane
   public void turn_right_D_to_B( String car_name, boolean is_going_straight){//A
    if (is_going_straight)
        System.out.println(car_name+ " is going straight from road D to road B");
    else
        System.out.println(car_name+ " is turning right");

   } 
   public void turn_right_A_to_C(String car_name, boolean is_going_straight){//B
    if (is_going_straight)
        System.out.println(car_name+ " is going straight from road A to road C");
    else
        System.out.println(car_name+ " is turning right");
   }
   public void turn_right_B_to_D( String car_name, boolean is_going_straight){//C
    if (is_going_straight)
        System.out.println(car_name+ " is going straight from road B to road D");
    else
        System.out.println(car_name+ " is turning right");
   }
   public void turn_right_C_to_A(String car_name, boolean is_going_straight ){//D
    if (is_going_straight)
        System.out.println(car_name+ " is going straight from road C to road A ");
    else
        System.out.println(car_name+ " is turning right");   }
//when turning left turn, make sure there is no other car can make left turn
   public void turn_left (String car_name){
    System.out.println(car_name+ " is turing left");
   }

    @Override
    public void run() {
             try{   
                while (cars.size()>0){// the condition should be when the the size of cars is greater than 0
                    Thread.sleep(1000);
                    int car_size = cars.size();//Check how many cars are waiting on this road
                    if(car_size>0){//there are one or more cars waiting 
                        Car current_waiting_car = cars.get(0);
                        String current_waiting_car_direction = current_waiting_car.get_direction();
                        String car_name = current_waiting_car.get_name();
                        lights pair;
                        if (road_name == "A" || road_name == "C")
                            pair = lightsController.get_light_pair("pair1");
                        else
                            pair = lightsController.get_light_pair("pair2"); 
                        String current_light_status = pair.get_status();//Find the light status of current raod
                        if(current_light_status == "Green"){//if the current light is green, the vehicle can go straight and right
                            if(current_waiting_car_direction == "right"){
                                Thread.sleep(2000);//Assuming it takes 2 seconds for the car to turn right or go straight
                                System.out.println("cars on road " + this.road_name + " has gone to " + current_waiting_car_direction);
                                cars.remove(0);
                            }
                            else if(current_waiting_car_direction == "straight"){
                                 Boolean go_straight = false;
                                while(go_straight ==false){
                                    lock.lock();//Try to acquire the lock 
                                    try{
                                        if(road_name == "A")
                                            turn_right_A_to_C(car_name,true);
                                        else if(road_name == "B")
                                            turn_right_B_to_D(car_name,true);
                                        else if(road_name == "C")
                                            turn_right_C_to_A(car_name,true);
                                        else
                                            turn_right_D_to_B(car_name,true);
                                        cars.remove(0);
                                        go_straight = true;
                                    }finally{
                                        lock.unlock();
                                        System.out.println(current_waiting_car.get_name()+ " go straight SUCCESSFULLY");
                                    }
                                }
                            }
                            else{//Whenever there is a car turning left, it must lock the turn_eft function so that there won't be 2 car turning left at the same time
                                lock.lock();
                                try{
                                    turn_left(current_waiting_car.get_name());
                                    Thread.sleep(3000);//Assuming it takes 3 seconds for the car to turn left
                                    cars.remove(0);
                                }finally{
                                    lock.unlock();
                                    System.out.println(current_waiting_car.get_name()+ " made left turn SUCCESSFULLY");
                                }
                            }    
                        }
                        else{
                            //System.out.println("the light is red, only can turn right");
                            if(current_waiting_car_direction == "right"){
                                Boolean turned_right = false;
                                while(turned_right ==false){
                                    Thread.sleep(3000);//wait for 3 seconds
                                    lock.lock();//Try to acquire the lock 
                                    try{
                                        if(road_name == "A")
                                            turn_right_D_to_B(car_name,false);
                                        else if(road_name == "B")
                                            turn_right_A_to_C(car_name,false);
                                        else if(road_name == "C")
                                            turn_right_B_to_D(car_name,false);
                                        else
                                            turn_right_C_to_A(car_name, false);
                                        cars.remove(0);
                                        turned_right = true;
                                    }finally{
                                        lock.unlock();
                                        System.out.println(current_waiting_car.get_name()+ " made right turn SUCCESSFULLY");
                                    }
                                }
                                
                                
                            }
                        }
                    }   
             }
             System.out.println("cars remaing on road " + this.road_name + " : " + cars.size());
            }catch(InterruptedException e){
                e.printStackTrace();
        }
    }
}