package org.bluebridge.designpattern_02_guarded_suspension.guarded_suspension_c;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author lingwh
 * @desc 信箱管理
 * @date 2026/7/9 00:00
 */
public class Mailboxes {
    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;

    // 产生唯一 id
    private static synchronized int generateId() {
        return id++;
    }

    /**
     * 根据id得到唯一的GuardedObject,用完给到收信人得移除GuardedObject
     *
     * @param id
     * @return
     */
    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    /**
     * 产生GuardedObject
     *
     * @return
     */
    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    /**
     * 得到map中的所有键，也就是id
     *
     * @return
     */
    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}
