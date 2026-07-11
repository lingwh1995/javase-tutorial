package action.iterator.iterator_b;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lingwh
 * @desc 迭代器模式客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 创建学院
        List<College> collegeList = new ArrayList<>();
        // 创建计算机学院
        College computerCollege = new ComputerCollege();
        // 创建信息工程学院
        College informationCollege = new InformationCollege();
        collegeList.add(computerCollege);
        collegeList.add(informationCollege);

        OutputImpl output = new OutputImpl(collegeList);
        output.printCollege();
    }
}
