package com.github.wuchao.webproject.util;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public abstract class CommandUtils {

    private CommandUtils() {
    }

    /**
     * 执行操作系统命令
     *
     * @param command
     */
    public static void execCommand(String command) {
        try {
            String osName = System.getProperty("os.name");
            ProcessBuilder builder;

            if (osName.contains("Windows")) {
                builder = new ProcessBuilder(
                        "cmd.exe", "/c", command);
            } else if (osName.contains("Linux")) {
                builder = new ProcessBuilder(
                        "sh", "-c", command);
            } else {
                throw new RuntimeException("暂时只测试过 Windows 和 Linux 服务器");
            }

            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            Process process = builder
                    // 方法告诉此进程生成器是否合并标准错误和标准输出。
                    // 如果此属性为 true，则通过子进程所产生的任何错误输出将与标准输出合并，
                    // 合并的数据可从 Process.getInputStream() 返回的流读取。
                    .redirectErrorStream(true)
                    .start();

            @Cleanup InputStream fis = process.getInputStream();
            @Cleanup InputStreamReader isr = new InputStreamReader(fis);
            @Cleanup BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // 调用 waitFor 方法，主进程会等待子进程的命令执行完成（成功会返回 0）
            process.waitFor(10, TimeUnit.SECONDS);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
