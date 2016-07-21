package io.github.keshuangjie.androidutil.exception;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 输入流异常模板，对流的关闭统一处理，增强代码的可读性
 *
 * @author keshuangjie
 * @date 2014-12-23 下午1:30:47
 */
public class InputStreamProcessingTemplate {

    public void process(String fileName, InputStreamProcessor processor) {
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            processor.process(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
