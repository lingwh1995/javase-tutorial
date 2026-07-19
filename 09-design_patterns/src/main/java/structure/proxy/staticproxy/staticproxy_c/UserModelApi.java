package structure.proxy.staticproxy.staticproxy_c;

/**
 * 定义用户数据对象的接口
 *
 * @author lingwh
 * @date 2019/8/15 9:10
 */
public interface UserModelApi {

    String getUserId();

    void setUserId(String userId);

    String getName();

    void setName(String name);

    String getDepId();

    void setDepId(String depId);

    String getSex();

    void setSex(String sex);
}
