public class lights{
    String light_status;
    String light1;
    String light2;
    public lights(String light1, String light2, String status){
        this.light1 = light1;
        this.light2 = light2;
        this.light_status = status;
    }
    public void set_status(String status){
        this.light_status = status;
    }
    public String get_status(){
        return this.light_status;
    }
    public String get_pair(){
        return this.light1+ " " +this.light2;
    }
    
}