package org.bluebridge.structure.flyweight.flyweight_c;

/**
 * 描述授权数据的 Model
 *
 * @author lingwh
 * @date 2019/7/30 15:00
 */
public class AuthorizationModel {

    /**
     * 人员
     */
    private String user;

    /**
     * 安全实体
     */
    private String securityEntity;

    /**
     * 权限
     */
    private String authority;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSecurityEntity() {
        return securityEntity;
    }

    public void setSecurityEntity(String securityEntity) {
        this.securityEntity = securityEntity;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
