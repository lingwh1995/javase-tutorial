package create.abstractfactory.abstractfactory_h;

/**
 * @author lingwh
 * @desc 技嘉主板
 * @date 2019/8/7 15:07
 */
public class GAMainboard implements MainboardApi {

    /**
     * CPU插槽的孔数
     */
    private int cpuHoles = 0;

    /**
     * 构造方法，传入CPU插槽的孔数
     *
     * @param cpuHoles CPU插槽的孔数
     */
    public GAMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    /**
     * 给主板安装CPU
     */
    @Override
    public void installCPU() {
        System.out.println("技嘉主板,cpuHoles=" + cpuHoles);
    }
}
