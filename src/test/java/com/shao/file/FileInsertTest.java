package com.shao.file;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileInsertTest {
    @Resource
    private FileInsert fileInsert;

    @Test
    public void fileInsert() throws IOException {
        String path = "D:\\Project\\number.txt";
        int position = 10;
        String content = "hhhhhh";
        fileInsert.insert(path, position, content);
    }
    @Test
    public void testoo(){
        boolean flag = false;
        assert flag : System.out.printf("flag is false");
        System.out.println();
    }

}