package structure.adapter.adapter_g_builder.dao;

import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * @author lingwh
 * @date 2026/7/9 00:00
 */
public class XxjlFjrDao implements IXxjlDao {
    @Override
    public boolean save(Xxjl xxjl) {
        System.out.println("保存发件人发送的信息交流......");
        return true;
    }

    @Override
    public boolean delete(Xxjl xxjl) {
        System.out.println("删除发件人发送的信息交流......");
        return true;
    }
}
