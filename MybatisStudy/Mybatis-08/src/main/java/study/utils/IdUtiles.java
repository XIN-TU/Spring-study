package study.utils;

import org.junit.Test;

import java.util.UUID;


//éæºçæID
public class IdUtiles {

    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IdUtiles.getId());
    }
}
