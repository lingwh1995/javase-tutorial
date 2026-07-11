package headfirst.designpatterns.proxy.javaproxy;

/**
 * @author lingwh
 * @desc 人员信息接口
 * @date 2026/7/9 00:00
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
