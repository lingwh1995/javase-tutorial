package org.bluebridge.structure.decorator.decorator_f;

/**
 * 装饰者：持有抽象组件的引用
 *
 * @author lingwh
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
