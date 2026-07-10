package org.bluebridge.lock_08_synchronized_demo_transfer;

/**
 * @author lingwh
 * @desc 银行账户
 * @date 2026/7/9 00:00
 */
public class Account {

    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 为什么这个方法会出现线程安全问题？ 下面代码中对 this.money 和 target.money 这两个值既有读取操作，也有写入操作，是临界区，所以多线程情况下会发生竞态条件 解决方案：
     * 给Account.class对象加锁
     *
     * @param target
     * @param amount
     */
    public void transfer(Account target, int amount) {
        synchronized (Account.class) {
            if (this.money > amount) {
                this.setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        }
    }
}
