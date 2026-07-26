package org.bluebridge.structure.adapter.adapter_m;

/**
 * 控制器接口
 *
 * @author lingwh
 * @date 2026/7/22 09:27
 */
public interface Controller {

}

class HttpController implements Controller {
    public void doHttpHandler() {
        System.out.println("http...");
    }
}

class SimpleController implements Controller {
    public void doSimplerHandler() {
        System.out.println("simple...");
    }
}

class AnnotationController implements Controller {

    public void doAnnotationHandler() {
        System.out.println("annotation...");
    }
}
