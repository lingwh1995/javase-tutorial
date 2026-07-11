package structure.adapter.adapter_m;

/**
 * @author lingwh
 * @desc 控制器接口
 * @date 2026/7/9 00:00
 */
public interface Controller {}

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
