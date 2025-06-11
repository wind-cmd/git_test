import java.lang.Math;

public class MathUtil {
    /**
     * Math类中提供了一些常用的数学运算方法，
     * 如求最大值、最小值、绝对值、向上取整、向下取整、四舍五入等。
     * 这些方法可以直接通过类名调用，无需创建对象。
     * 
     * 示例代码中展示了如何使用Math类中的方法来进行数学运算。
     * 你可以根据需要调用不同的方法来完成各种数学运算。
     * 
     * 注意：Math类中的方法都是静态方法，可以直接通过类名调用，无需创建对象。
     * 另外，Math类中的方法返回的结果通常是double类型，
     * 如果需要得到整数类型的结果，可以进行类型转换。
     * 
     * 希望这个示例能够帮助你理解如何使用Math类进行数学运算。
     */
    public static double toDbRandom(int min, int max) {
        return (Math.random() * (max - min) + min);
    }

    public static int toIntRandom(int min, int max) {
        return ((int) (Math.random() * (max - min + 1) + min));
    }

    public static void main(String[] args) {
        // 数学类Math练习
        int a = 10;
        int b = 20;
        Math.max(a, b);// 求最大值
        System.out.println(Math.max(a, b));
        Math.min(a, b);// 求最小值
        Math.abs(-9);// 求绝对值
        Math.ceil(1.1);// 向上取整
        System.out.println(Math.ceil(1.1));
        Math.floor(1.7);// 向下取整
        System.out.println(Math.floor(1.7));
        Math.round(1.5);// 四舍五入
        System.out.println(Math.round(1.5));
        System.out.println(Math.random());
        // 生成1-10之间的随机数
        for (int i = 0; i < 100; i++) {
            System.out.println(toDbRandom(1, 10));
            // System.out.println(toIntRandom(1, 10));
        }
    }

}
