package structure.decorator.decorator_f;

/**
 * @author lingwh
 * @desc 装饰者:持有抽象组件的引用
 * @date 2019/7/25 18:39
 */
public abstract class Decotor implements Cipher {
    private Cipher cipher;

    public Decotor(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public String encrypt(String cipherText) {
        return cipher.encrypt(cipherText);
    }
}
