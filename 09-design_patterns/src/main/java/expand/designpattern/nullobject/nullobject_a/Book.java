package expand.designpattern.nullobject.nullobject_a;

/**
 * 图书
 *
 * @author lingwh
 * @date 2019/7/29 15:02
 */
public class Book {

    private Integer id;
    private String name;
    private Double price;

    public Book(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * 打印图书信息
     */
    public void show() {
        System.out.println(toString());
    }
}
