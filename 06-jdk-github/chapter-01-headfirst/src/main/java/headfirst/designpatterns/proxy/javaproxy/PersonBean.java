package headfirst.designpatterns.proxy.javaproxy;

/**
 * 人员信息接口
 *
 * @author lingwh
 * @date 2023/12/7 13:19
 */
public interface PersonBean {

    String getName();

    String getGender();

    String getInterests();

    int getHotOrNotRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setHotOrNotRating(int rating);
}
