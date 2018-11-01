package Sort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 直接插入排序<br>
 * <br>
 * 理解：<br>
 * 一个 长度为 n 的无序数组<br>
 * 第一次 a[0] 有序，第二次 将 a[1] 插入到 a[0] 这个序列中，形成有序数列 a[0, 1]<br>
 * 然后 将 a[2] 插入到 a[0, 1] 中形成 a[0, 1, 2]<br>
 * 一直循环，直到 a[n-1] 插入到 a[0...n-2] 中<br>
 * 
 * Ref: https://blog.csdn.net/MoreWindows/article/details/6665714
 */

public class InsertionSort {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        int[] arr = { -5, 7, 1, -9, 0, 6, 19 };

        System.out.println("Before Sort: " + mapper.writeValueAsString(arr));

        insertionSort3(arr);

        System.out.println("After Sort: " + mapper.writeValueAsString(arr));

    }

    public static void insertionSort1(int[] arr) {

        // i 表示当前需要排序的位置，这里初始化为 1 表示 第 0 个本来就是有序的
        for (int i = 1; i < arr.length; ++i) {

            // 这里 j = i - 1 表示，第一跟 i 比较的位置是 i - 1 嘛，然后 一次往前比较
            int j = i - 1;
            // 1. 先找到需要插入的位置，每次都从 i - 1 位置向前找
            for (; j >= 0; j--) {
                // 从小到大排列，如果 当前 i 对应的值 小于 j 对应的值，表示需要交换，找到位置了
                if (arr[i] >= arr[j]) {
                    break;
                }
            }

            // 这里判断是否需要经进行插入排序，如果 j = i - 1 ，说明上面指针没有移动过，此时本来就是有序的
            if (j != i - 1) {

                // 说明移动过，此时需要进行插入排序
                // j 后面的值依次向后移动
                int tempVal = arr[i];
                for (int k = i - 1; k > j; k--)
                    arr[k + 1] = arr[k];

                // 然后 把 i 的值放到 j 指向的后面
                arr[j + 1] = tempVal;
            }

        }

    }

    /**
     * 第一次代码简化：<br>
     * 
     * 在上面的程序中 我们的第一个 for 循环其实做了两件事：<br>
     * 1. 它判断了当前 i 值，是否需要向前插入(通过 j = i - 1 来判断)<br>
     * 2. 如果需要插入，它 找到了需要插入的位置 也就是我们的 j + 1<br>
     * <br>
     * 这里我们进行如下简化:<br>
     * 1. 第一个 for 循环中 不再查找 需要插入的 位置 j<br>
     * 2. 由第一点，我们也不再需要通过 j = i - 1 来判断是否进行插入排序，而是直接通过 arr[i] >= arr[j] 来判断<br>
     * 只要 后一个数(arr[i]) 小于 前一个数(arr[j]) 就需要进行插入排序<br>
     * 至于应该插入到哪个位置，我们直接 一边判断 i - 1 是否 大于 i，一边移动<br>
     * 也即 如果 i - 1 > i，那么 就把 i - 1 的值 赋值给 i，然后 判断 i - 2 是否 大于 i<br>
     * 如果 i - 2 小于等于 i，那么就把 i 的值 插入到 (i - 2) + 1 的位置
     */
    public static void insertionSort2(int[] arr) {

        int i, k;

        for (i = 1; i < arr.length; ++i) {

            // 直接判断，只有 后一个数(arr[i]) 小于 前一个数(arr[j]) 的情况，我们才开始 插入排序
            if (arr[i] < arr[i - 1]) {

                // 插入排序开始

                // 只要 arr[i - 1] 大于 arr[i] 值 并且 (i - 1) > 0，此时就需要向后移动
                // 当退出循环的时候
                int tempVal = arr[i];
                for (k = i - 1; k >= 0 && arr[k] > tempVal; k--)
                    arr[k + 1] = arr[k];

                // 退出循环的时候，需要把 arr[i] 的值 赋值个 arr[k+1]
                arr[k + 1] = tempVal;
            }

        }

    }

    /**
     * 第二次代码简化：<br>
     * 
     * 在第一次程序简化中，我们的 内层 for 所做的事情就是:<br>
     * 每次都比较 a[k-1] 和 a[k] 的大小，如果 a[k-1] > a[k]，我们是将 a[k-1] 向后移动一位，到 a[k] 这个位置<br>
     * <br>
     * 现在，我们换一种思路:<br>
     * 将 移动变为交换，当 a[k-1] > a[k]，我们交换 a[k-1] 和 a[k]，然后再比较 a[k-1] 和 a[k-2]
     */
    public static void insertionSort3(int[] arr) {

        int i, k;

        for (i = 1; i < arr.length; ++i) {

            // 直接判断，只有 后一个数(arr[i]) 小于 前一个数(arr[j]) 的情况，我们才开始 插入排序
            if (arr[i] < arr[i - 1]) {

                // 插入排序开始

                // 只要 arr[i - 1] 大于 arr[i] 值 并且 (i - 1) > 0，此时交换两个数据的位置
                for (k = i - 1; k >= 0 && arr[k] > arr[k + 1]; k--) {
                    int tempVal = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = tempVal;
                }
            }

        }

    }

}
