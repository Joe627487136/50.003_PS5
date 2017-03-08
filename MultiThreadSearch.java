package Week6;

import java.util.ArrayList;
import java.util.List;

class MultiThreadSearch extends Thread{
    private int id;
    private int[] array = {11,12,13,14,15,16,17,18,2,3,5,6};

    public MultiThreadSearch (int id) {
        this.id = id;
    }

    public void run(){
        if(!isInterrupted()){
            for(int i=1;i<=array.length&&!isInterrupted();i++){
                if (array[i]==15){
                    System.out.println("Found! My friend Blyat!");
                    terminate();
                }else {
                    System.out.println(array[i]);
                }
            }
        }
    }
    public void terminate() {
        if (super.isAlive()) {
            ThreadGroup group = super.getThreadGroup();
            group.interrupt();
        }
    }

    public static void main(String args[]){
        try {
            MultiThreadSearch t1 = new MultiThreadSearch(1);
            MultiThreadSearch t2 = new MultiThreadSearch(2);
            t1.start();
            t1.sleep(1000);
            t2.start();
        }catch (InterruptedException e){
            System.out.println("t1 is interrupted");
        }
    }
}
