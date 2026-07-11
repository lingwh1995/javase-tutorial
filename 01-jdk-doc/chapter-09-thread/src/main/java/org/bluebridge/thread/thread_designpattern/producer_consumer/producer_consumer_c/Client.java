package org.bluebridge.thread.thread_designpattern.producer_consumer.producer_consumer_c;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2019/10/17 11:26
 */
public class Client {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MarkerThread("MarkerThread-1", table, 31415).start();
        new MarkerThread("MarkerThread-2", table, 92678).start();
        new MarkerThread("MarkerThread-3", table, 75132).start();
        new EaterThread("EaterThread-1", table, 89845).start();
        new EaterThread("EaterThread-2", table, 45643).start();
        new EaterThread("EaterThread-3", table, 7897356).start();
    }
}
