package Sort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 冒泡排序简单实现<br>
 * 
 * 思路如下(这里以从小到大举栗):<br>
 * 1. 从第一个开始比较 相邻的两个数字的大小，如果前一个大于后一个，发生交换，<br>
 * 第一次将会从 第一个 比较到 最后一个，这时候 最后一个数字就会成为最大的，它是有序的<br>
 * 2. 第一次再次从第一个开始，一直比较到 倒数第二个 <br>
 * 3. .....
 */

public class BubbleSort {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        int[] arr = { -5, 7, 1, -9, 0, 6, 19 };

        System.out.println("Before Sort: " + mapper.writeValueAsString(arr));

        improveBubbleSort_2(arr);

        System.out.println("After Sort: " + mapper.writeValueAsString(arr));

    }

    // Swap()
    public static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    // 最基础的实现，直接根据上面的伪代码写就好了
    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; ++i)
            for (int j = 1; j < arr.length - i; ++j)
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
    }

    /**
     * 第一次改进，增加一个排序标志位，判断当前循环是否发生交换<br>
     * 
     * 因为冒泡排序的规则是：每次都从第一位开始向后比较，把 最大 或是 最小 的向下“沉”<br>
     * 所以当某一次循环时候，没有发生交换，就可以判断整个数组已经排序完成了<br>
     * 
     * 举个栗子：<br>
     * 当 循环一次为 0 3 5 7 9 [11 13] 是有序的，这次比较了 0 3 5 7 9 这五个数字，没有发生交换<br>
     * 那么下次比较 0 3 5 7 就一定是有序，同理下一次比较 0 3 5 也是有序的...
     */
    public static void improveBubbleSort_1(int[] arr) {

        for (int i = 0; i < arr.length; ++i) {

            // 增加标志位
            boolean flag = false;

            for (int j = 1; j < arr.length - i; ++j) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    flag = true;
                }
            }

            if (!flag)
                return;

        }
    }

    /**
     * 其实通过上面来看，外层循环的控制条件并不是 i < arr.length，二变成了 if (!flag) return;<br>
     * 因为 最外层循环控制，会把所有的次数跑满，而 if 判断 跑的次数 总是会小于等于 for 循环的次数<br>
     * 依据这个思想 我们来简化一下一下代码
     */
    public static void improveBubbleSort_1_v2(int[] arr) {

        int n = arr.length;
        boolean flag = true;

        while (flag) {

            flag = false;

            for (int j = 1; j < n; ++j) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    flag = true;
                }
            }

            n--;
        }

    }

    /**
     * 第二次改进，依然是利用 冒泡排序的机制，我们增加一个标志位，记录最后一次发生交换的位置<br>
     * 
     * 这里依然以从小到大排序为栗，比如总长度为 10，第一次循环的时候发生交换的位置在 5，这就表明在 5 之后数据都是有序的<br>
     * 那么在第二次循环的时候，终点就可以设置为5。
     */
    public static void improveBubbleSort_2(int[] arr) {

        int flag = arr.length; // 定义交换的标志位，初始状态是循环到 数组的最后一位
        int k;

        while (flag > 0) {

            k = flag; // flag 用于外层循环，使用 k 作为内层循环判断条件
            flag = 0;

            for (int j = 1; j < k; ++j)
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    flag = j;
                }
        }

    }
}
