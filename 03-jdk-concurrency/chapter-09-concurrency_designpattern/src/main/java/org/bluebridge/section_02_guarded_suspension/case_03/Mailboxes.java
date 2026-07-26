package org.bluebridge.section_02_guarded_suspension.case_03;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * 信箱管理
 *
 * @author lingwh
 * @date 2025/2/25 15:44
 */
public class Mailboxes {

    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;

    // 产生唯一 id
    private static synchronized int generateId() {
        return id++;
    }

    /**
     * 根据 id 得到唯一的 GuardedObject，用完给到收信人得移除 GuardedObject
     *
     * @param id
     * @return
     */
    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    /**
     * 产生 GuardedObject
     *
     * @return
     */
    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    /**
     * 得到 map 中的所有键，也就是 id
     *
     * @return
     */
    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}
