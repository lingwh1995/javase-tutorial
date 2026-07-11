package org.bluebridge.lock_24_semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author lingwh
 * @desc 信号量资源池测试
 * @date 2026/7/9 00:00
 */
public class ResourcePoolTest {
    private static final int MAX_RESOURCES = 5;
    private static final Semaphore semaphore = new Semaphore(MAX_RESOURCES);
    private static final Resource[] resources = new Resource[MAX_RESOURCES];

    static {
        for (int i = 0; i < MAX_RESOURCES; i++) {
            resources[i] = new Resource(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Resource resource = null;
                try {
                    semaphore.acquire();
                    resource = getResource();
                    useResource(resource);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    releaseResource(resource);
                    semaphore.release();
                }
            }).start();
        }
    }

    private static Resource getResource() {
        for (Resource resource : resources) {
            if (!resource.isInUse()) {
                resource.setInUse(true);
                return resource;
            }
        }
        return null;
    }

    private static void useResource(Resource resource) {
        System.out.println(Thread.currentThread().getName() + " start using resource " + resource.getId());
        try {
            // 模拟资源使用
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done using resource " + resource.getId());
    }

    private static void releaseResource(Resource resource) {
        if (resource != null) {
            resource.setInUse(false);
        }
    }

    static class Resource {
        private final int id;
        private boolean inUse;

        public Resource(int id) {
            this.id = id;
            this.inUse = false;
        }

        public int getId() {
            return id;
        }

        public boolean isInUse() {
            return inUse;
        }

        public void setInUse(boolean inUse) {
            this.inUse = inUse;
        }
    }
}
