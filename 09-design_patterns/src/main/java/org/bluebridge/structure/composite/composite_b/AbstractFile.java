package org.bluebridge.structure.composite.composite_b;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用组合模式模拟杀毒软件架构 - 模拟抽象组件
 *
 * @author lingwh
 * @date 2019/3/23 13:54
 */
public interface AbstractFile {

    /**
     * 杀毒
     */
    void killViruls();
}

/**
 * 模拟 Leaf 节点：单个文件
 *
 * @author lingwh
 * @date 2019/3/23 13:57
 */
class imageFiles implements AbstractFile {

    private String name;

    public imageFiles(String name) {
        this.name = name;
    }

    @Override
    public void killViruls() {
        System.out.println("图像文件" + name + "，进行查杀......");
    }
}

/**
 * 模拟 Leaf 节点：单个文件
 *
 * @author lingwh
 * @date 2019/3/23 14:04
 */
class TextFiles implements AbstractFile {

    private String name;

    public TextFiles(String name) {
        this.name = name;
    }

    @Override
    public void killViruls() {
        System.out.println("文本文件" + name + "，进行查杀......");
    }
}

/**
 * 模拟容器组件：文件夹
 *
 * @author lingwh
 * @date 2019/3/23 14:14
 */
class Folder implements AbstractFile {

    private String name;

    public Folder(String name) {
        this.name = name;
    }

    /**
     * 定义容器，用来存放本容器构件下的子节点
     */
    private List<AbstractFile> list = new ArrayList<AbstractFile>();

    public void add(AbstractFile file) {
        list.add(file);
    }

    public void remove(AbstractFile file) {
        list.remove(file);
    }

    public AbstractFile getChild(int index) {
        return list.get(index);
    }

    @Override
    public void killViruls() {
        System.out.println("文件夹" + name + "，进行查杀......");
        for (AbstractFile file : list) {
            file.killViruls();
        }
    }
}
