package org.bluebridge.expand.designpattern.nullobject.nullobject_b;

/**
 * 图书工厂
 *
 * @author lingwh
 * @date 2019/7/29 15:03
 */
public class BookFactory {

    public static AbstractBook getBookById(Integer id) {
        AbstractBook book = null;
        switch (id) {
            case 1:
                book = new Book(1, "数据结构", 20.0);
                break;
            case 2:
                book = new Book(2, "设计模式", 30.0);
            default:
                book = new NullBook();
                break;
        }
        return book;
    }
}
