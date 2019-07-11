public class lightsController extends Thread{
    String controller_name;
    lights pair1;
    lights pair2; 
    public lightsController(String name){
     this.controller_name = name;
     this.pair1 = new lights("light A", "light C", "Green");//lights for rodd A and C
     this.pair2 = new lights("light B", "light D", "Red");// lights for road B and D
    }
    public lights  get_light_pair (String light_pair_name){
        if(light_pair_name == "pair1"){
            return this.pair1;
        }
        else{
            return this.pair2;
        }
    }
    @Override
    public void run() {
        try {
            while (true){
                if(pair1.get_status() == "Red"){
                    System.out.println(pair1.get_pair() + " " + pair1.get_status());
                    System.out.println(pair2.get_pair() + " "+ pair2.get_status());
                    Thread.sleep(17000);
                    pair2.set_status("Yellow");
                    System.out.println(pair1.get_pair() + " " +pair1.get_status());
                    System.out.println(pair2.get_pair() + " " +pair2.get_status());
                    Thread.sleep(3000);
                    pair1.set_status("Green");
                    pair2.set_status("Red"); 
                }
                else{
                    System.out.println(pair1.get_pair() + " " + pair1.get_status());
                    System.out.println(pair2.get_pair() + " "+ pair2.get_status());
                    Thread.sleep(17000);
                    pair1.set_status("Yellow");
                    System.out.println(pair1.get_pair() + " " + pair1.get_status());
                    System.out.println(pair2.get_pair() + " "+ pair2.get_status());
                    Thread.sleep(3000);
                    pair2.set_status("Green");
                    pair1.set_status("Red");
                    // System.out.println(pair1.get_pair() + " " + pair1.get_status());
                    // System.out.println(pair2.get_pair() + " "+ pair2.get_status());
                }
            }
        }
         catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }
}