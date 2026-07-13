package action.state.state_f;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/27 10:39
 */
public class Client {

    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        for (int i = 0; i < 10; i++) {
            vm.vote("u1", "A");
        }
    }
}
