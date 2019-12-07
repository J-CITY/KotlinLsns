package lec3;

class SomeThread implements Runnable  {
    public void run() {
        System.out.println("Other thread");
    }
}

class MyThread extends Thread  {
    @Override
    public void run() {
        System.out.println("My thread!");
    }
}

public class threadJava {

    //static MyThread myThread;

    public static void main(String[] args) {
        SomeThread t = new SomeThread();

        Thread thread = new Thread(t);
        thread.start();

        //Thread thread2 = new Thread(new Runnable() {
        //    public void run()
        //    {
        //        System.out.println("thread 2!");
        //    }
        //});
        //thread2.start();

        //myThread = new MyThread();	//Создание потока
        //myThread.start();

        System.out.println("Main finish");
    }
}

//Thread.sleep()

//Thread.yield() заставляет процессор переключиться на обработку других потоков системы
/*
while(!smth)		//Пока в очереди нет сообщений
{
	Thread.yield();		//Передать управление другим потокам
}

//Thread.join() ждет другой поток

 */


//Thread.stop() Thread.suspend() Thread.resume(),