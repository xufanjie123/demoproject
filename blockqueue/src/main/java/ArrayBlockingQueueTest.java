import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueTest {

    public static class Basket{

        BlockingQueue<String> backet = new ArrayBlockingQueue<String>(3);

        public void produce() throws InterruptedException {
            backet.put("An apple");
        }

        public String comsume() throws InterruptedException {
            String apple = backet.take();
            return apple;
        }


        public int getAppleNumber(){
            return backet.size();
        }

    }

    public static void BasketTest(){

        final Basket basket = new Basket();

        class Producer implements Runnable{
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println("生产者准备生产苹果:"+System.currentTimeMillis());
                        basket.produce();
                        System.out.println("生产者生产苹果完毕:" + System.currentTimeMillis());
                        System.out.println("生产完有苹果:" + basket.getAppleNumber());
                        Thread.sleep(300);
                    }
                }catch (InterruptedException e){

                }
            }
        }

        class Consumer implements Runnable{

            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println("消费者准备消费苹果:" + System.currentTimeMillis());
                        basket.comsume();
                        System.out.println("消费者消费苹果完毕:" + System.currentTimeMillis());
                        System.out.println("消费完有苹果" + basket.getAppleNumber());
                        Thread.sleep(1000);
                    }
                }catch (InterruptedException e){

                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        service.submit(consumer);
        service.submit(producer);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();

    }

    public static void main(String[] args) {

        ArrayBlockingQueueTest.BasketTest();

    }
}
