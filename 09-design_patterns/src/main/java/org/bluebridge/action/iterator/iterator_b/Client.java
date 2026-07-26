package org.bluebridge.action.iterator.iterator_b;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式客户端
 *
 * @author lingwh
 * @date 2026/7/22 15:03
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
