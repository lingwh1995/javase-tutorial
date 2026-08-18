package org.bluebridge.section_03_jdk3.unit_03_sound;

import org.junit.Test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * JDK 1.3 JavaSound API 特性测试
 *
 * JavaSound API 是 JDK 1.3 引入的一套用于处理音频数据的标准 API,
 * 位于 javax.sound.sampled 包中。它提供了录制、播放和处理音频的能力。
 *
 * 核心接口和类 (javax.sound.sampled 包):
 * 1. AudioSystem: 音频系统的入口类, 提供访问音频资源和音频设备的方法
 * 2. AudioFormat: 描述音频数据的格式, 如采样率、采样位数、声道数等
 * 3. AudioInputStream: 音频输入流, 用于读取音频数据
 * 4. SourceDataLine: 源数据线, 用于播放音频数据
 * 5. TargetDataLine: 目标数据线, 用于录制音频数据
 * 6. Clip: 剪辑, 用于播放预加载的音频片段
 * 7. DataLine: 数据线接口, SourceDataLine 和 Clip 的父接口
 * 8. Mixer: 混音器, 管理多条数据线
 *
 * 注意: 以下代码仅展示 JavaSound API 的代码结构和 API 用法,
 * 不实际播放音频。如果需要在有音频硬件的环境中运行, 可以取消注释相关代码。
 *
 * @author lingwh
 * @date 2026/08/05 19:08
 */
public class JavaSoundTest {

    /**
     * 测试 AudioSystem 的基本用法
     *
     * AudioSystem 是 JavaSound API 的入口点, 提供了以下功能:
     * - 获取音频文件格式信息
     * - 获取音频输入流
     * - 获取音频设备(Mixer、DataLine)
     * - 播放和录制音频
     */
    @Test
    public void testAudioSystem() {
        System.out.println("========== AudioSystem 基本用法 ==========");

        // 获取所有可用的混音器(Mixer)
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println("可用混音器数量: " + mixerInfos.length);

        for (int i = 0; i < mixerInfos.length; i++) {
            Mixer.Info info = mixerInfos[i];
            System.out.println("混音器 " + (i + 1) + ":");
            System.out.println("  名称: " + info.getName());
            System.out.println("  供应商: " + info.getVendor());
            System.out.println("  描述: " + info.getDescription());
            System.out.println("  版本: " + info.getVersion());
        }

        // 获取默认音频格式
        // AudioFormat.Encoding 定义了音频数据的编码方式
        System.out.println();
        System.out.println("常用音频格式编码:");
        System.out.println("  - PCM_SIGNED: 有符号 PCM 编码");
        System.out.println("  - PCM_UNSIGNED: 无符号 PCM 编码");
        System.out.println("  - ALAW: A-Law 编码");
        System.out.println("  - ULAW: mu-Law 编码");

        // 获取支持的音频文件类型
        AudioFileFormat.Type[] fileTypes = AudioSystem.getAudioFileTypes();
        System.out.println();
        System.out.println("支持的音频文件类型:");
        for (AudioFileFormat.Type type : fileTypes) {
            System.out.println("  - " + type.getExtension());
        }
    }

    /**
     * 测试 AudioFormat 音频格式
     *
     * AudioFormat 描述了音频数据的具体格式, 包括:
     * - encoding: 编码方式(PCM、ALAW、ULAW 等)
     * - sampleRate: 采样率(每秒采样次数, 如 44100Hz、8000Hz)
     * - sampleSizeInBits: 采样位数(8bit、16bit、24bit 等)
     * - channels: 声道数(1=单声道, 2=立体声)
     * - frameRate: 帧率(每秒帧数)
     * - frameSize: 帧大小(字节数)
     * - bigEndian: 是否大端字节序
     */
    @Test
    public void testAudioFormat() {
        System.out.println("========== AudioFormat 音频格式测试 ==========");

        // 创建 CD 音质格式: PCM 有符号, 44100Hz, 16bit, 立体声, 小端序
        AudioFormat cdFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,  // 编码方式: PCM 有符号
                44100.0f,                          // 采样率: 44.1kHz
                16,                                 // 采样位数: 16bit
                2,                                  // 声道数: 2(立体声)
                4,                                  // 帧大小: 16bit*2ch/8 = 4 字节
                44100.0f,                          // 帧率: 44.1kHz
                false                               // 小端序
        );
        System.out.println("CD 音质格式: ");
        printAudioFormat(cdFormat);

        // 创建电话音质格式: PCM 有符号, 8000Hz, 8bit, 单声道
        AudioFormat phoneFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                8000.0f,    // 采样率: 8kHz
                8,          // 采样位数: 8bit
                1,          // 声道数: 1(单声道)
                1,          // 帧大小: 8bit*1ch/8 = 1 字节
                8000.0f,    // 帧率: 8kHz
                false       // 小端序
        );
        System.out.println("电话音质格式: ");
        printAudioFormat(phoneFormat);

        // 创建 MP3 质量格式: PCM 有符号, 22050Hz, 16bit, 单声道
        AudioFormat mp3QualityFormat = new AudioFormat(22050.0f, 16, 1, true, false);
        System.out.println("MP3 质量格式: ");
        printAudioFormat(mp3QualityFormat);

        // 使用 AudioFormat 的便捷构造器
        // new AudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian)
        // 编码方式默认为 PCM_SIGNED 或 PCM_UNSIGNED

        // 判断格式是否匹配
        AudioFormat format1 = new AudioFormat(44100.0f, 16, 2, true, false);
        AudioFormat format2 = new AudioFormat(44100.0f, 16, 2, true, false);
        System.out.println("format1 与 format2 是否匹配: " + format1.matches(format2));
    }

    /**
     * 打印 AudioFormat 信息
     */
    private void printAudioFormat(AudioFormat format) {
        System.out.println("  编码方式: " + format.getEncoding());
        System.out.println("  采样率: " + format.getSampleRate() + " Hz");
        System.out.println("  采样位数: " + format.getSampleSizeInBits() + " bit");
        System.out.println("  声道数: " + format.getChannels());
        System.out.println("  帧大小: " + format.getFrameSize() + " 字节");
        System.out.println("  帧率: " + format.getFrameRate() + " fps");
        System.out.println("  大端序: " + format.isBigEndian());
        System.out.println();
    }

    /**
     * 测试 Clip 接口
     *
     * Clip 是 DataLine 的子接口, 用于播放预加载的音频数据。
     * 与 SourceDataLine 的不同之处在于:
     * - Clip: 将音频数据预加载到内存中, 支持循环播放、随机定位
     * - SourceDataLine: 实时流式播放, 适用于长时间或流式音频
     *
     * Clip 不支持直接实例化, 需要通过 AudioSystem.getClip() 获取。
     */
    @Test
    public void testClip() {
        System.out.println("========== Clip 接口测试 ==========");

        // Clip 的典型使用流程:
        // 1. 获取 Clip 对象
        // 2. 打开 Clip 并加载音频数据
        // 3. 设置循环播放参数
        // 4. 启动播放
        // 5. 停止播放
        // 6. 关闭 Clip 释放资源

        System.out.println("Clip 使用流程:");
        System.out.println("  1. Clip clip = AudioSystem.getClip();");
        System.out.println("  2. AudioInputStream ais = AudioSystem.getAudioInputStream(file);");
        System.out.println("  3. clip.open(ais);");
        System.out.println("  4. clip.loop(Clip.LOOP_CONTINUOUSLY); // 循环播放");
        System.out.println("     clip.start();                       // 单次播放");
        System.out.println("  5. clip.stop();");
        System.out.println("  6. clip.close();");

        System.out.println();
        System.out.println("Clip 的核心方法:");
        System.out.println("  - open(AudioInputStream): 打开 Clip 并加载音频数据");
        System.out.println("  - start(): 开始播放");
        System.out.println("  - stop(): 停止播放");
        System.out.println("  - loop(int): 循环播放指定次数, LOOP_CONTINUOUSLY 表示无限循环");
        System.out.println("  - setMicrosecondPosition(long): 设置播放位置(微秒)");
        System.out.println("  - setFramePosition(int): 设置播放位置(帧)");
        System.out.println("  - getMicrosecondPosition(): 获取当前播放位置(微秒)");
        System.out.println("  - getMicrosecondLength(): 获取音频长度(微秒)");
        System.out.println("  - close(): 释放资源");
        System.out.println("  - addLineListener(LineListener): 添加行监听器");
        System.out.println("  - isRunning(): 是否正在播放");
        System.out.println("  - isActive(): 是否处于活动状态");

        // 展示 Clip 的暂停/恢复功能
        System.out.println();
        System.out.println("Clip 暂停/恢复:");
        System.out.println("  - 暂停: clip.stop(); // 保留当前位置");
        System.out.println("  - 恢复: clip.start(); // 从暂停位置继续播放");

        // 尝试获取 Clip 对象(可能因环境不支持而失败)
        try {
            Clip clip = AudioSystem.getClip();
            System.out.println();
            System.out.println("成功获取 Clip 对象: " + clip.getClass().getName());
            System.out.println("Clip 支持的格式: " + clip.getFormat());
            clip.close();
        } catch (LineUnavailableException e) {
            System.out.println("获取 Clip 失败(当前环境可能不支持音频): " + e.getMessage());
        }
    }

    /**
     * 测试 DataLine 接口
     *
     * DataLine 是音频数据线的基接口, 主要子接口包括:
     * - SourceDataLine: 源数据线, 用于播放音频
     * - TargetDataLine: 目标数据线, 用于录制音频
     * - Clip: 剪辑, 用于播放预加载的音频
     */
    @Test
    public void testDataLine() {
        System.out.println("========== DataLine 接口测试 ==========");

        // DataLine.Info 用于描述 DataLine 的能力
        // 包含: 支持的 AudioFormat、缓冲区大小等信息

        // 为 SourceDataLine 创建 DataLine.Info
        AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);
        DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);
        System.out.println("SourceDataLine Info: " + sourceInfo);

        // 为 Clip 创建 DataLine.Info
        DataLine.Info clipInfo = new DataLine.Info(Clip.class, format);
        System.out.println("Clip Info: " + clipInfo);

        // 为 TargetDataLine 创建 DataLine.Info
        DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
        System.out.println("TargetDataLine Info: " + targetInfo);

        // DataLine 的核心方法
        System.out.println();
        System.out.println("DataLine 核心方法:");
        System.out.println("  - open(AudioFormat): 打开数据线");
        System.out.println("  - start(): 启动数据传输");
        System.out.println("  - stop(): 停止数据传输");
        System.out.println("  - close(): 关闭数据线");
        System.out.println("  - isRunning(): 是否正在传输");
        System.out.println("  - isActive(): 是否处于活动状态");
        System.out.println("  - drain(): 排空缓冲区数据");
        System.out.println("  - flush(): 清空缓冲区数据");
        System.out.println("  - getFormat(): 获取当前格式");
        System.out.println("  - getBufferSize(): 获取缓冲区大小");

        // 检查系统是否支持 SourceDataLine
        boolean isSourceLineSupported = AudioSystem.isLineSupported(sourceInfo);
        System.out.println();
        System.out.println("系统是否支持 SourceDataLine(44100Hz, 16bit, 立体声): " + isSourceLineSupported);

        // 检查系统是否支持 TargetDataLine
        boolean isTargetLineSupported = AudioSystem.isLineSupported(targetInfo);
        System.out.println("系统是否支持 TargetDataLine(录制): " + isTargetLineSupported);
    }

    /**
     * 测试 SourceDataLine 播放音频
     *
     * SourceDataLine 用于实时流式播放音频数据。
     * 与 Clip 不同, SourceDataLine 不会将音频数据完全加载到内存中,
     * 而是通过 write() 方法持续写入数据, 适合播放长时间音频。
     *
     * 注意: 此方法仅展示代码结构, 不实际播放音频。
     */
    @Test
    public void testSourceDataLine() {
        System.out.println("========== SourceDataLine 播放音频展示 ==========");

        System.out.println("SourceDataLine 使用流程:");
        System.out.println("  1. 获取 SourceDataLine 对象");
        System.out.println("  2. 打开数据线并指定格式");
        System.out.println("  3. 启动数据线");
        System.out.println("  4. 通过 write() 方法写入音频数据");
        System.out.println("  5. 播放完毕后 drain() 排空缓冲区");
        System.out.println("  6. 关闭数据线");

        System.out.println();
        System.out.println("伪代码示例:");
        System.out.println("  AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);");
        System.out.println("  DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);");
        System.out.println("  SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);");
        System.out.println("  line.open(format);");
        System.out.println("  line.start();");
        System.out.println("  // 从 AudioInputStream 读取数据并写入 SourceDataLine");
        System.out.println("  byte[] buffer = new byte[4096];");
        System.out.println("  int bytesRead = 0;");
        System.out.println("  while ((bytesRead = audioInputStream.read(buffer)) != -1) {");
        System.out.println("      line.write(buffer, 0, bytesRead);");
        System.out.println("  }");
        System.out.println("  line.drain();");
        System.out.println("  line.close();");

        // 展示生成正弦波音频数据并播放的代码结构
        System.out.println();
        System.out.println("生成正弦波音频数据:");
        System.out.println("  AudioFormat format = new AudioFormat(8000.0f, 8, 1, true, false);");
        System.out.println("  SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);");
        System.out.println("  line.open(format);");
        System.out.println("  line.start();");
        System.out.println("  // 生成 440Hz 正弦波(标准 A 音)");
        System.out.println("  byte[] data = new byte[8000]; // 1秒数据");
        System.out.println("  for (int i = 0; i < data.length; i++) {");
        System.out.println("      double angle = 2.0 * Math.PI * 440.0 * i / 8000.0;");
        System.out.println("      data[i] = (byte) (Math.sin(angle) * 127.0);");
        System.out.println("  }");
        System.out.println("  line.write(data, 0, data.length);");
        System.out.println("  line.drain();");
        System.out.println("  line.close();");
    }

    /**
     * 测试 TargetDataLine 录制音频
     *
     * TargetDataLine 用于从麦克风等音频输入设备录制音频数据。
     * 它与 SourceDataLine 相反, 通过 read() 方法读取音频数据。
     *
     * 注意: 此方法仅展示代码结构, 不实际录制音频。
     */
    @Test
    public void testTargetDataLine() {
        System.out.println("========== TargetDataLine 录制音频展示 ==========");

        System.out.println("TargetDataLine 使用流程:");
        System.out.println("  1. 获取 TargetDataLine 对象");
        System.out.println("  2. 打开数据线并指定格式");
        System.out.println("  3. 启动数据线");
        System.out.println("  4. 通过 read() 方法读取音频数据");
        System.out.println("  5. 将读取的数据写入 AudioInputStream 或文件");
        System.out.println("  6. 停止并关闭数据线");

        System.out.println();
        System.out.println("伪代码示例:");
        System.out.println("  AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);");
        System.out.println("  DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);");
        System.out.println("  TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);");
        System.out.println("  line.open(format);");
        System.out.println("  line.start();");
        System.out.println("  // 从 TargetDataLine 读取音频数据");
        System.out.println("  byte[] buffer = new byte[4096];");
        System.out.println("  int bytesRead = line.read(buffer, 0, buffer.length);");
        System.out.println("  // 将录制数据保存到文件");
        System.out.println("  AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);");
        System.out.println("  line.stop();");
        System.out.println("  line.close();");
    }

    /**
     * 测试 AudioInputStream 和音频文件操作
     *
     * AudioInputStream 是 JavaSound API 中用于读取音频数据的输入流。
     * 它封装了音频格式信息和音频数据。
     */
    @Test
    public void testAudioInputStream() {
        System.out.println("========== AudioInputStream 音频流测试 ==========");

        // AudioInputStream 的获取方式:
        System.out.println("获取 AudioInputStream 的方式:");
        System.out.println("  1. AudioSystem.getAudioInputStream(File file): 从音频文件获取");
        System.out.println("  2. AudioSystem.getAudioInputStream(InputStream stream): 从输入流获取");
        System.out.println("  3. AudioSystem.getAudioInputStream(URL url): 从 URL 获取");
        System.out.println("  4. new AudioInputStream(TargetDataLine line, AudioFormat format, long length):");
        System.out.println("     从 TargetDataLine 创建(用于录制)");

        System.out.println();
        System.out.println("支持的音频文件格式:");
        AudioFileFormat.Type[] fileTypes = AudioSystem.getAudioFileTypes();
        for (AudioFileFormat.Type type : fileTypes) {
            System.out.println("  - ." + type.getExtension());
        }

        System.out.println();
        System.out.println("将音频数据写入文件:");
        System.out.println("  AudioSystem.write(AudioInputStream in, AudioFileFormat.Type type, File out)");
        System.out.println("  支持的输出格式:");
        for (AudioFileFormat.Type type : fileTypes) {
            System.out.println("    - " + type.getExtension());
        }

        // 展示从文件读取音频信息的代码结构
        System.out.println();
        System.out.println("读取音频文件信息伪代码:");
        System.out.println("  File audioFile = new File(\"music.wav\");");
        System.out.println("  try {");
        System.out.println("      AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);");
        System.out.println("      AudioFormat format = ais.getFormat();");
        System.out.println("      System.out.println(\"采样率: \" + format.getSampleRate());");
        System.out.println("      System.out.println(\"采样位数: \" + format.getSampleSizeInBits());");
        System.out.println("      System.out.println(\"声道数: \" + format.getChannels());");
        System.out.println("      System.out.println(\"音频长度: \" + ais.getFrameLength() + \" 帧\");");
        System.out.println("      ais.close();");
        System.out.println("  } catch (UnsupportedAudioFileException e) {");
        System.out.println("      System.err.println(\"不支持的音频格式\");");
        System.out.println("  } catch (IOException e) {");
        System.out.println("      System.err.println(\"读取文件失败\");");
        System.out.println("  }");
    }

    /**
     * 测试 LineListener 和 LineEvent
     *
     * LineListener 用于监听音频数据线的状态变化。
     * LineEvent 表示数据线状态变化的事件。
     */
    @Test
    public void testLineListener() {
        System.out.println("========== LineListener 监听器测试 ==========");

        System.out.println("LineEvent.Type 类型:");
        System.out.println("  1. OPEN: 数据线已打开");
        System.out.println("  2. CLOSE: 数据线已关闭");
        System.out.println("  3. START: 数据线已启动");
        System.out.println("  4. STOP: 数据线已停止");

        System.out.println();
        System.out.println("LineListener 使用示例:");
        System.out.println("  clip.addLineListener(new LineListener() {");
        System.out.println("      @Override");
        System.out.println("      public void update(LineEvent event) {");
        System.out.println("          LineEvent.Type type = event.getType();");
        System.out.println("          if (type == LineEvent.Type.START) {");
        System.out.println("              System.out.println(\"开始播放\");");
        System.out.println("          } else if (type == LineEvent.Type.STOP) {");
        System.out.println("              System.out.println(\"停止播放\");");
        System.out.println("          } else if (type == LineEvent.Type.OPEN) {");
        System.out.println("              System.out.println(\"已打开\");");
        System.out.println("          } else if (type == LineEvent.Type.CLOSE) {");
        System.out.println("              System.out.println(\"已关闭\");");
        System.out.println("          }");
        System.out.println("      }");
        System.out.println("  });");

        System.out.println();
        System.out.println("应用场景:");
        System.out.println("  - 播放进度更新: 监听 STOP 事件更新播放进度");
        System.out.println("  - 资源管理: 监听 CLOSE 事件释放资源");
        System.out.println("  - 状态同步: 监听 START/STOP 事件同步 UI 状态");
    }

    /**
     * 测试 Mixer 混音器
     *
     * Mixer 是音频混音器, 管理多条音频数据线。
     * 每个音频设备(声卡)通常对应一个或多个 Mixer。
     */
    @Test
    public void testMixer() {
        System.out.println("========== Mixer 混音器测试 ==========");

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println("混音器总数: " + mixerInfos.length);

        for (Mixer.Info info : mixerInfos) {
            System.out.println();
            System.out.println("混音器: " + info.getName());
            try {
                Mixer mixer = AudioSystem.getMixer(info);

                // 获取该混音器支持的 SourceDataLine(播放)
                Line.Info[] sourceLineInfos = mixer.getSourceLineInfo();
                System.out.println("  支持的 SourceDataLine 数量: " + sourceLineInfos.length);

                // 获取该混音器支持的 TargetDataLine(录制)
                Line.Info[] targetLineInfos = mixer.getTargetLineInfo();
                System.out.println("  支持的 TargetDataLine 数量: " + targetLineInfos.length);

                // 获取支持的音频格式
                if (sourceLineInfos.length > 0) {
                    Line.Info lineInfo = sourceLineInfos[0];
                    if (lineInfo instanceof DataLine.Info) {
                        AudioFormat[] formats = ((DataLine.Info) lineInfo).getFormats();
                        System.out.println("  支持的音频格式数量: " + formats.length);
                        for (int i = 0; i < Math.min(formats.length, 3); i++) {
                            System.out.println("    - " + formats[i].getSampleRate() + "Hz, "
                                    + formats[i].getSampleSizeInBits() + "bit, "
                                    + formats[i].getChannels() + "ch");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("  获取混音器信息失败: " + e.getMessage());
            }
        }
    }

    /**
     * 测试异常处理: UnsupportedAudioFileException 和 LineUnavailableException
     *
     * JavaSound API 中常见的异常:
     * - UnsupportedAudioFileException: 不支持的音频文件格式
     * - LineUnavailableException: 音频数据线不可用
     * - IOException: IO 操作异常
     */
    @Test
    public void testSoundException() {
        System.out.println("========== JavaSound 异常处理 ==========");

        System.out.println("JavaSound API 常见异常:");

        System.out.println("  1. UnsupportedAudioFileException");
        System.out.println("     原因: 尝试读取不支持的音频文件格式");
        System.out.println("     处理: 检查文件格式, 提供格式转换功能");

        System.out.println("  2. LineUnavailableException");
        System.out.println("     原因: 音频设备不可用(被其他程序占用)或系统不支持");
        System.out.println("     处理: 提示用户关闭其他音频程序, 或使用备用播放方式");

        System.out.println("  3. IOException");
        System.out.println("     原因: 读取音频文件时发生 I/O 错误");
        System.out.println("     处理: 检查文件路径和权限");

        System.out.println();
        System.out.println("异常处理代码结构:");
        System.out.println("  try {");
        System.out.println("      AudioInputStream ais = AudioSystem.getAudioInputStream(file);");
        System.out.println("      Clip clip = AudioSystem.getClip();");
        System.out.println("      clip.open(ais);");
        System.out.println("      clip.start();");
        System.out.println("  } catch (UnsupportedAudioFileException e) {");
        System.out.println("      System.err.println(\"不支持的音频格式: \" + e.getMessage());");
        System.out.println("  } catch (LineUnavailableException e) {");
        System.out.println("      System.err.println(\"音频设备不可用: \" + e.getMessage());");
        System.out.println("  } catch (IOException e) {");
        System.out.println("      System.err.println(\"IO 错误: \" + e.getMessage());");
        System.out.println("  }");
    }

    /**
     * 综合展示: 完整的音频播放代码结构
     *
     * 展示一个完整的音频播放方法的代码结构(不实际执行)。
     */
    @Test
    public void testCompletePlaybackStructure() {
        System.out.println("========== 完整音频播放代码结构 ==========");

        System.out.println("// 播放 WAV 音频文件的完整方法结构");
        System.out.println("public void playWav(String filePath) {");
        System.out.println("    File audioFile = new File(filePath);");
        System.out.println("    try (AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);");
        System.out.println("         Clip clip = AudioSystem.getClip()) {");
        System.out.println("        ");
        System.out.println("        // 打开 Clip 并加载音频数据");
        System.out.println("        clip.open(ais);");
        System.out.println("        ");
        System.out.println("        // 添加监听器");
        System.out.println("        clip.addLineListener(event -> {");
        System.out.println("            if (event.getType() == LineEvent.Type.STOP) {");
        System.out.println("                System.out.println(\"播放完成\");");
        System.out.println("            }");
        System.out.println("        });");
        System.out.println("        ");
        System.out.println("        // 开始播放");
        System.out.println("        clip.start();");
        System.out.println("        ");
        System.out.println("        // 等待播放完成");
        System.out.println("        while (clip.isRunning()) {");
        System.out.println("            Thread.sleep(100);");
        System.out.println("        }");
        System.out.println("        ");
        System.out.println("    } catch (UnsupportedAudioFileException | LineUnavailableException");
        System.out.println("             | IOException | InterruptedException e) {");
        System.out.println("        System.err.println(\"播放失败: \" + e.getMessage());");
        System.out.println("    }");
        System.out.println("}");
    }
}