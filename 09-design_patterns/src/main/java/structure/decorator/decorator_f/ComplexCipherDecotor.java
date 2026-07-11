package structure.decorator.decorator_f;

/**
 * @author lingwh
 * @desc 复杂加密装饰者
 * @date 2019/7/25 18:42
 */
public class ComplexCipherDecotor extends Decotor {
    public ComplexCipherDecotor(Cipher cipher) {
        super(cipher);
    }

    @Override
    public String encrypt(String cipherText) {
        return "更复杂的加密规则:" + super.encrypt(cipherText);
    }
}
