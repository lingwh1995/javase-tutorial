package org.bluebridge.structure.decorator.decorator_e;

/**
 * 具体的组件
 *
 * @author lingwh
 * @date 2019/7/25 18:38
 */
public class ConcreteCipher extends Cipher {

    @Override
    public String encrypt(String cipherText) {
        return "我是加密后的暗号";
    }
}
