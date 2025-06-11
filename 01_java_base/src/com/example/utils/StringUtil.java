public class StringUtil {
    public static void main(String[] args) {
        //通过new关键字创建的对象，地址不同
        Str str1 = new Str("Hello");
        Str str2 = new Str("Hello");
        System.out.println(str1==str2); //false

        //通过字面量创建的对象，地址相同
        String str3 = "Hello";
        String str4 = "Hello";
        System.out.println(str3==str4); //true


        //equals方法比较的是字符串的内容是否相同
        System.out.println(str1.equals(str2)); //true
        System.out.println(str1.equals(str3)); //true
        System.out.println(str1.equals(str4)); //true
        System.out.println(str1.equals("Hello")); //true

        //charAt方法返回指定索引位置的字符
        System.out.println(str1.charAt(0)); //H

        //length方法返回字符串的长度
        System.out.println(str1.length()); //5

        //substring方法截取字符串的一部分
        System.out.println(str1.substring(0, 2)); //He

        //indexOf方法返回指定字符或子字符串的索引位置
        System.out.println(str1.indexOf("l")); //2

        //lastIndexOf方法返回指定字符或子字符串最后一次出现的索引位置
        System.out.println(str1.lastIndexOf("l")); //3
        //replace方法替换字符串中的字符或子字符串
        System.out.println(str1.replace("l", "L")); //HeLLo

        //toLowerCase方法将字符串转换为小写
        System.out.println(str1.toLowerCase()); //hello

        //toUpperCase方法将字符串转换为大写
        System.out.println(str1.toUpperCase()); //HELLO

        //trim方法去除字符串两端的空白字符
        System.out.println("  Hello  ".trim()); //Hello

        //split方法将字符串分割成字符串数组
        String[] strArray = str1.split("l");
        for (String str : strArray) {
            System.out.println(str); //He o
        }

        //join方法将字符串数组连接成一个字符串
        String[] strArray2 = {"Hello", "World"};
        System.out.println(String.join(",", strArray2)); //Hello,World

        //valueOf方法将其他类型转换为字符串
        System.out.println(String.valueOf(123)); //123
        
    }
}
