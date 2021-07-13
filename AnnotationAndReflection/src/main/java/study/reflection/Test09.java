package study.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

// 通过反射获取泛型
public class Test09 {
    public void test01(Map<String,User>map, List<User> list){
        System.out.println("Test01");
    }

    public Map<String,User> test02(){
        System.out.println("Test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Test09.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#" + genericParameterType);
            if(genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("1:" +actualTypeArgument);
                }

            }
        }

        method = Test09.class.getMethod("test02");
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("genericReturnType:" + genericReturnType);
        if(genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println("2:" + actualTypeArgument);
            }
        }
    }
}
