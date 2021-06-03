package com.shao.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class FileInsert{


    public  void insert(String filePath,int position,String contents) throws IOException {
        File file = inputValidation(filePath, position);
        if (file != null){
            insertByRandomAccessFile(file, position, contents);
        }
    }

    //验证输入参数
    public File inputValidation(String filePath,int position){

        File file = new File(filePath);
        System.out.println(file);
        if (!(file.exists() && file.isFile())) {
            log.info("文件不存在");
            return null;
        }

        if ((position < 0) || (position > file.length())) {
            log.info("position超出");
            return null;
        }
        return file;
    }

    //通过RandomAccessFile方式插入
    public void insertByRandomAccessFile(File file,int position,String contents) throws IOException {

        File tempFile = File.createTempFile("temporary", ".temp", new File("D:\\Project"));

        FileOutputStream outputStream = new FileOutputStream(tempFile);
        FileInputStream inputStream = new FileInputStream(tempFile);
        //在退出JVM退出时自动删除
        tempFile.deleteOnExit();

        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        rw.seek(position);

        //读出position后的内容
        int tmp;
        while ((tmp = rw.read()) != -1) {
            outputStream.write(tmp);
        }

        //在position后插入
        rw.seek(position);
        rw.write(contents.getBytes());

        //写回内容
        while ((tmp = inputStream.read()) != -1) {
            rw.write(tmp);
        }

        rw.close();
        outputStream.close();
        inputStream.close();
    }
}

