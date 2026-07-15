package algorithm.knapsackproblem;

/**
 * 背包问题
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class Knapsack {

    public static void main(String[] args) {
        // 物品的重量
        int[] w = { 1, 4, 3 };
        // 物品的价值
        int[] value = { 1500, 3000, 2000 };
        // 背包的容量
        int m = 4;
        // 物品的个数
        int n = value.length;
        // 创建二维数组
        // v[i][j]:表示在前i个物品中能装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // 为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];
        // 初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            // 将第一行设置为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            // 将第一列设置为0
            v[0][i] = 0;
        }

        // 根据前面得到的公式来动态规划处理
        // 不处理第一行
        for (int i = 1; i < v.length; i++) {
            // 不处理第列行
            for (int j = 1; j < v[0].length; j++) {
                // 因为程序中i是从1开始的,所以w[i]修改成w[i-1]
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 说明: 因为程序中i是从1开始的,所以公式调整成下面代码
                    // v[i][j] = Math.max(v[i-1][j],value[i-1]+v[i-1][j-w[i-1]]);

                    // 为了记录商品存放在背包的情况,代码调整为下面
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "    ");
            }
            System.out.println();
        }

        /**
         * 会输出冗余数据
         */
        // 输出最后放入的是那些商品
        // for(int i=0;i<path.length;i++){
        // for(int j=0;j<path[i].length;j++){
        // if(path[i][j] == 1){
        // System.out.printf("第%d个商品放入到背包\n",i);
        // }
        // }
        // }

        // 行的最大下标
        int i = path.length - 1;
        // 列的最大下标
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
