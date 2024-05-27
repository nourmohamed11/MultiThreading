public class Multithreading extends Thread {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Odd();
        Thread t = new Thread(r);
        System.out.println();
        Runnable r2 = new Even();
        Thread t2 = new Thread(r2);
        t.start();
        //t.join();
        t2.stop();
        t2.start();
        //t2.join();


        }
        static class Odd implements Runnable {
            public void run() {
                System.out.println("Odd Numbers between 1 & 1000 are:");
                for (int i = 1; i <= 1000; i++) {
                    if (i % 2 != 0) {
                        System.out.print(i + " ");
                       /* try{
                            Thread.sleep(2000);
                        }
                        catch(InterruptedException ex){

                        }*/
                       /* if (i == 3){
                            Thread.yield();
                        }*/
                    }

                }
                System.out.println("Listing is finished");
            }
        }

        static class Even implements Runnable {

            public void run() {
                System.out.println("Even Numbers between 1 & 1000 are:");
                for (int i = 1; i <= 1000; i++) {
                    if (i % 2 == 0) {
                        System.out.print(i + " ");
                       /* try{
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException ex){

                        }*/

                        /*if (i ==3){
                            Thread.yield();
                        }*/
                    }
                }
                System.out.println("Listing is finished");
            }
        }


    }
