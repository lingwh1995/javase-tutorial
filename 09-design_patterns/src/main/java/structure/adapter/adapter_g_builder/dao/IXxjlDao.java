package structure.adapter.adapter_g_builder.dao;

import structure.adapter.adapter_g_builder.domain.Xxjl;

/**
 * 标记接口
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IXxjlDao {

    boolean save(Xxjl xxjl);

    boolean delete(Xxjl xxjl);
}
