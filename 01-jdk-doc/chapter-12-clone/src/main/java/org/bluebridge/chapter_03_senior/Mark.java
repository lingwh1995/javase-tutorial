package org.bluebridge.chapter_03_senior;

 /**
 * @author ronin
 * @version V1.0
 * @desc 测试深克隆的用例:
 * @since 2019/7/11 11:13
 */
 public class Mark implements Cloneable {
    private double chinese;
    private double math;

    @Override
    protected Mark clone() throws CloneNotSupportedException {
        return (Mark)super.clone();
    }

    public Mark() {
    }

    public Mark(double chinese, double math) {
        this.chinese = chinese;
        this.math = math;
    }

    public double getChinese() {
        return chinese;
    }

    public void setChinese(double chinese) {
        this.chinese = chinese;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "chinese=" + chinese +
                ", math=" + math +
                '}';
    }
}

