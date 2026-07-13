package structure.flyweight.flyweight_a;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2019/6/27 12:53
 */
public class Client {

    public static void b(String[] args) {
        User tom = new User("tom");
        User jerry = new User("jerry");
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite news = webSiteFactory.getWebSite("新闻");
        news.use(tom);
        news.use(jerry);

        WebSite blog = webSiteFactory.getWebSite("博客");
        blog.use(jerry);
        User cart = new User("jerry");
        User smith = new User("jerry");
        blog.use(cart);
        blog.use(smith);
        System.out.println("网站种类:" + webSiteFactory.getWbSiteCount());
    }
}
