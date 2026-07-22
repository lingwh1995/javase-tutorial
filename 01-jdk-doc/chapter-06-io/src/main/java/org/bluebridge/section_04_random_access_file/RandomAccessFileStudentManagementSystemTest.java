package org.bluebridge.section_04_random_access_file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 基于RandomAccessFile的学生管理系统 使用固定长度记录存储学生信息
 *
 * @author lingwh
 * @date 2026/6/22 18:04
 */
@Slf4j
public class RandomAccessFileStudentManagementSystemTest {

    // 定义学生记录的固定长度
    // ID(4) + 姓名(50字节) + 年龄(4) + 成绩(8) = 66字节
    private static final int RECORD_SIZE = 4 + 50 + 4 + 8;
    private static final String FILE_PATH = "d:/io/students.dat";

    /**
     * 测试学生管理系统
     */
    @Test
    public void testRandomAccessFileStudentManagementSystem() throws IOException {
        // 先清空文件内容，避免之前测试数据干扰
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            raf.setLength(0);
        }

        RandomAccessFileStudentManagementSystemTest rafsms = new RandomAccessFileStudentManagementSystemTest();

        // 添加学生
        rafsms.addStudent(new Student(1, "张三", 20, 85.5));
        rafsms.addStudent(new Student(2, "李四", 21, 92.0));
        rafsms.addStudent(new Student(3, "王五", 19, 78.5));

        // 显示所有学生
        rafsms.showStudents();

        // 查找学生
        Student student = rafsms.findStudentById(2);
        log.info("查找学生ID为2的学生: {}", student);

        // 更新学生信息
        if (student != null) {
            student.setScore(95.0);
            rafsms.updateStudent(student);
        }

        // 删除学生
        rafsms.deleteStudent(1);

        // 再次显示所有学生
        log.info("=== 删除和更新后的学生记录 ===");
        rafsms.showStudents();
    }

    /**
     * 添加学生记录
     */
    public void addStudent(Student student) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            // 移动到文件末尾添加新记录
            raf.seek(raf.length());
            writeStudent(raf, student);
            log.info("添加学生成功: {}", student);
        }
    }

    /**
     * 根据学生ID查找学生
     */
    public Student findStudentById(int id) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            long fileLength = raf.length();

            // 确保文件长度是记录大小的整数倍
            if (fileLength == 0 || fileLength % RECORD_SIZE != 0) {
                return null;
            }

            for (long position = 0; position < fileLength; position += RECORD_SIZE) {
                // 确保有足够的数据可读
                if (position + RECORD_SIZE > fileLength) {
                    break;
                }

                try {
                    raf.seek(position);
                    Student student = readStudent(raf);

                    // 跳过已删除的记录
                    if (student.getId() != -1 && student.getId() == id) {
                        return student;
                    }
                } catch (IOException e) {
                    log.warn("读取位置 {} 的学生记录时出错: {}", position, e.getMessage());
                    continue;
                }
            }
            return null;
        }
    }

    /**
     * 更新学生信息
     */
    public boolean updateStudent(Student updatedStudent) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            long fileLength = raf.length();

            for (long position = 0; position < fileLength; position += RECORD_SIZE) {
                raf.seek(position);
                Student student = readStudent(raf);

                if (student.getId() == updatedStudent.getId()) {
                    // 找到匹配的学生，更新记录
                    raf.seek(position); // 回到记录开始位置
                    writeStudent(raf, updatedStudent);
                    log.info("更新学生信息成功: {}", updatedStudent);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 删除学生记录（标记删除）
     */
    public boolean deleteStudent(int id) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
            long fileLength = raf.length();

            for (long position = 0; position < fileLength; position += RECORD_SIZE) {
                raf.seek(position);
                Student student = readStudent(raf);

                if (student.getId() == id) {
                    // 标记为删除（将ID设为-1）
                    raf.seek(position);
                    raf.writeInt(-1); // 标记删除
                    log.info("删除学生成功，ID: {}", id);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 显示所有学生记录
     */
    public void showStudents() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "r")) {
            long fileLength = raf.length();
            log.info("=== 所有学生记录 ===");

            // 检查文件是否为空
            if (fileLength == 0) {
                log.info("暂无学生记录");
                return;
            }

            // 确保文件长度是记录大小的整数倍
            if (fileLength % RECORD_SIZE != 0) {
                log.warn("文件格式不正确，长度不是记录大小的整数倍");
                return;
            }

            boolean hasStudents = false;
            for (long position = 0; position < fileLength; position += RECORD_SIZE) {
                // 确保有足够的数据可读
                if (position + RECORD_SIZE > fileLength) {
                    break;
                }

                try {
                    raf.seek(position);
                    Student student = readStudent(raf);

                    // 跳过已删除的记录
                    if (student.getId() != -1) {
                        log.info("{}", student);
                        hasStudents = true;
                    }
                } catch (IOException e) {
                    log.warn("读取位置 {} 的学生记录时出错: {}", position, e.getMessage());
                    continue;
                }
            }

            if (!hasStudents) {
                log.info("暂无学生记录");
            }
        }
    }

    /**
     * 写入学生记录到文件
     */
    private void writeStudent(RandomAccessFile raf, Student student) throws IOException {
        raf.writeInt(student.getId());

        // 写入固定长度的姓名
        byte[] nameBytes = new byte[50];
        if (student.getName() != null) {
            byte[] actualName = student.getName().getBytes("UTF-8");
            System.arraycopy(actualName, 0, nameBytes, 0, Math.min(actualName.length, 50));
        }
        raf.write(nameBytes);

        raf.writeInt(student.getAge());
        raf.writeDouble(student.getScore());
    }

    /**
     * 从文件读取学生记录
     */
    private Student readStudent(RandomAccessFile raf) throws IOException {
        Student student = new Student();

        // 检查是否还有足够的数据可读（至少需要4字节读取ID）
        if (raf.getFilePointer() + 4 > raf.length()) {
            throw new IOException("文件数据不足，无法读取学生ID");
        }
        student.setId(raf.readInt());

        // 检查是否还有足够的数据可读（至少需要50字节读取姓名）
        if (raf.getFilePointer() + 50 > raf.length()) {
            throw new IOException("文件数据不足，无法读取学生姓名");
        }

        // 读取姓名
        byte[] nameBytes = new byte[50];
        raf.readFully(nameBytes);
        String name = new String(nameBytes, "UTF-8").trim();
        student.setName(name);

        // 检查是否还有足够的数据可读（至少需要4字节读取年龄）
        if (raf.getFilePointer() + 4 > raf.length()) {
            throw new IOException("文件数据不足，无法读取学生年龄");
        }
        student.setAge(raf.readInt());

        // 检查是否还有足够的数据可读（至少需要8字节读取成绩）
        if (raf.getFilePointer() + 8 > raf.length()) {
            throw new IOException("文件数据不足，无法读取学生成绩");
        }
        student.setScore(raf.readDouble());

        return student;
    }
}

/**
 * 学生类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {

    private int id;
    private String name;
    private int age;
    private double score;
}
