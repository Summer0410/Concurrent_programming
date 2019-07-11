import java.util.Random;
public class randomDirection{
    String direction;
    public randomDirection(){ }
    public String get_direction( ){
        Random rand = new Random();
        int n = rand.nextInt(3);
        if (n==0)
            return "left";
        else if(n==1)
            return "straight";
        else
            return "right";
    }
}