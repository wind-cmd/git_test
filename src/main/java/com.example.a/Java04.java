public class Java04 {
    public static void main(String[] args) {
        int j = 2;
        String str2 = String.valueOf(j);

        String str = "1";
        Integer i = Integer.parseInt(str);// 自动拆箱
        System.out.println(i);

        Double d = Double.parseDouble(str); // 自动拆箱
        System.out.println(d);

        // 集合和数组区别
        // 1.数组的长度是固定的，集合的长度是可变的
        // 2.数组只能存储同一种类型的元素，集合可以存储不同类型的元素
        // 3.数组可以存储基本数据类型和引用数据类型，集合只能存储引用数据类型

    }

}
