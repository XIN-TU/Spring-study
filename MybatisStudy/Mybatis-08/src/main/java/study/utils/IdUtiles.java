package study.utils;

import org.junit.Test;

import java.util.UUID;


//随机生成ID
public class IdUtiles {

    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IdUtiles.getId());
    }
}
