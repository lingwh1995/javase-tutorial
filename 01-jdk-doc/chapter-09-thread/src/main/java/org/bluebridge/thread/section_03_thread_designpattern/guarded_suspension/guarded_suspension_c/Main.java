package org.bluebridge.thread.section_03_thread_designpattern.guarded_suspension.guarded_suspension_c;

/**
 * 主程序
 *
 * @author lingwh
 * @date 2019/10/16 09:34
 */
public class Main {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        ClientThread alex = new ClientThread(requestQueue, "Alex", 3141592L);
        alex.start();
        ServerThread bobby = new ServerThread(requestQueue, "Bobby", 6535897L);
        bobby.start();
        /*
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         alex.interrupt();
         bobby.interrupt();*/
    }
}
