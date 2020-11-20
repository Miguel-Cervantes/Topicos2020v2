package sample.components;

public class Hilo extends  Thread{
    
    public Hilo(String name){
        setName(name);
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i <=10 ; i++) {
            System.out.println("vuelta "+i+ "-->"+getName());
            try {
                sleep((long) (Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName()+" llego a la meta");
    }
}
