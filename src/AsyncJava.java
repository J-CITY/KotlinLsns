import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sun.awt.Mutex;

class MyRun implements Runnable {
    @Override
    public void run() {
        System.out.println("my runnable");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("my thread");
    }
}

class MyJFXService extends Service<String> {

    public MyJFXService() {
        setOnSucceeded(s -> {

        });
        setOnFailed(s -> {

        });
        setOnCancelled(s -> {

        });
        setOnReady(s-> {

        });
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                return "";
            }
        };

    }

}


public class AsyncJava {
    static Integer val = 42;
    static Mutex m = new Mutex();
    public static void main(String[] args) {


        Thread t = new Thread(new MyRun());
        t.start();

        MyThread mt = new MyThread();
        mt.run();

        System.out.println("main");

        synchronized(val) {

        }

    }

}



