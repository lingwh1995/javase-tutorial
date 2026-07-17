package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_c;

/**
 * 送信人
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Postman extends Thread {

    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    /**
     * 送信
     */
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        System.out.printf("送出信 id: %s, 内容: %s\n", id, mail);
        guardedObject.complete(mail);
    }
}
