package structure.decorator.decorator_f;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2019/7/25 18:43
 */
public class Client {
    public static void main(String[] args) {
        // 普通加密
        Cipher concreteCipher = new ConcreteCipher();
        System.out.println(concreteCipher.encrypt("xxx"));

        // 更复杂的加密
        concreteCipher = new ComplexCipherDecotor(concreteCipher);
        System.out.println(concreteCipher.encrypt("xxx"));
    }
}
