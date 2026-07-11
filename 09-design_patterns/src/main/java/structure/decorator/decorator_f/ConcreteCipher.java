package structure.decorator.decorator_f;

/**
 * 具体的组件
 *
 * @author lingwh
 * @date 2019/7/25 18:38
 */
public class ConcreteCipher implements Cipher {

    @Override
    public String encrypt(String cipherText) {
        return "我是加密后的暗号";
    }
}
