package Sort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 直接选择排序:<br>
 * 思想(以从小到大排列)：<br>
 * 假设一个长度为 n 的数列，首先遍历 0 - n-1 的数，将最小的值放到 0 的位置，此时序列 [0] 就有序了<br>
 * 然后再从 1- n-1 中找到 最小的值，放到 1 的位置，此时序列 [0...1] 就有序了<br>
 * 依次类推直到遍历到最后一位数为止
 */

public class SelectSort {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        int[] arr = { -5, 7, 1, -9, 0, 6, 19 };

        System.out.println("Before Sort: " + mapper.writeValueAsString(arr));

        selectSort2(arr);

        System.out.println("After Sort: " + mapper.writeValueAsString(arr));

    }

    /**
     * 找到最小数的决策是，只要遇到比当前小的数字，就将两个数交换~
     */
    public static void selectSort1(int[] arr) {

        int len, i, j, tempVal;
        len = arr.length;

        for (i = 0; i < len; ++i) {
            for (j = i + 1; j < len; ++j) {
                if (arr[i] > arr[j]) {
                    tempVal = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tempVal;
                }
            }
        }

    }

    /**
     * 小优化：<br>
     * 上面的例子我们每次交换的都是数组的值，换句话说，我们在循环中不断的在操作数组<br>
     * 虽然这种有序数列，访问还是比较快的，但是当数据量大的时候这样不断的操作数组也是一件耗时的事情<br>
     * 所以我们做出如下改进<br>
     * 每次只找到最小数的下标，最后再开始交换数据
     */
    public static void selectSort2(int[] arr) {

        int len, i, j, tempVal, minPos;
        len = arr.length;

        for (i = 0; i < len; ++i) {

            // 初始化当前位置为最小值位置
            minPos = i;

            for (j = i + 1; j < len; ++j) {
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
            }

            tempVal = arr[i];
            arr[i] = arr[minPos];
            arr[minPos] = tempVal;
        }

    }
}
