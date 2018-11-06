package Sort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 希尔算法简单实现<br>
 * 简单的说就是:<br>
 * 将数组 不断的拆分 (按步长拆分，步长越来越小，所以分成的组越来越少，每个组中的数据越来越多)<br>
 * 将每个拆分后的数组，实行直接插入排序，等最后切得足够小(比如 1)，这时候每个数都是单独的 组<br>
 * 此时再对所有的数据进行直接插入排序<br>
 * 
 * 效率依据：<br>
 * 因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，所以比单独的 直接插入排序效率要高<br>
 * <br>
 * Ref : https://blog.csdn.net/morewindows/article/details/6668714
 */

public class ShellSort {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        int[] arr = { -5, 7, 1, -9, 0, 6, 19 };

        System.out.println("Before Sort: " + mapper.writeValueAsString(arr));

        shellSort3(arr);

        System.out.println("After Sort: " + mapper.writeValueAsString(arr));
    }

    /**
     * 这里已 上面 arr 数组来看 { -5, 7, 1, -9, 0, 6, 19, 2 }，数组长度为 8<br>
     * 所以第一次按 步长 8/2 来分组，然后 按 (8/2)/2 来分组，依次类推...<br>
     * 实际上效果就是：<br>
     * 第一次会分成 n/2 个组，每个组中有 n / (n/2) = 2 个元素<br>
     * 第二次会分成 (n/2) /2 个组，每组中有 n / ((n/2) / 2) = 4 个元素<br>
     * ....<br>
     * <br>
     * 所以，我们可以发现，其实总体的循环是按照步长来控制的。<br>
     * 因为 每次分组后，每个组中的数据都是 基本有序的，<b>所以每个单独的组进行 直接插入排序 的时候 效率都会高</b>
     * 
     */
    public static void shellSort1(int[] arr) {

        int len, i, j, k, tempVal, gap;

        len = arr.length;

        // 最外层的步长控制整体循环
        for (gap = len / 2; gap > 0; gap /= 2) {

            // 内层开始基于 增量(gap) 进行分组，每一次循环就相当于遍历每一个组的数据
            for (i = 0; i < gap; i++) {

                for (k = i + gap; k < len; k += gap) {

                    // 直接插入排序思想
                    if (arr[k] < arr[k - gap]) {

                        tempVal = arr[k];

                        // 找到需要插入的位置，只要当前的值大于 需要移动的值 tempVal，就将值向后移动
                        for (j = k - gap; j >= 0; j -= gap) {
                            if (arr[j] < tempVal) {
                                break;
                            }
                            arr[j + gap] = arr[j];
                        }

                        // 当找到位置之后进行插入
                        arr[j + gap] = tempVal;
                    }

                }

            }

        }

    }

    /**
     * 第一次代码简化:<br>
     * 注意上面第一种写法的 <b>第二层</b> 和 <b>第三层</b> 循环<br>
     * 它们的做法是这样: 一个组一个组的去遍历，也即是我们先 遍历完 组一，然后去看 组二，接着组三、组四<br>
     * <br>
     * 而现在我们改为这样:<br>
     * <br>
     * 从第一个组的数字开始，顺序遍历，因为 第一个组的数字加上 1，可能就会跳到第二个组，如果再加一就会跳到第三组<br>
     * <br>
     * 顺序就变成了这样:<br>
     * <br>
     * <b>之前</b>：先是第一个组的所有数进行直接插入排序，再是第二个组的所有数，直到操作完左后一组<br>
     * <b>现在</b>：先按第一个数来进行插入排序(比如它是第一组的)，然后数字加一(比如跳到了第二组)，然后进行直接插入排序<br>
     * 这时候就相当于给第二组进行插入排序，接着是第三组的几个数，然后....，循环一圈又回到第一组.... 知道数字遍历完毕<br>
     * 
     */
    public static void shellSort2(int[] arr) {

        int len, i, tempVal, gap, j;

        len = arr.length;

        for (gap = len / 2; gap > 0; gap /= 2) {

            // 这里我们从第一组的数字开始，即使从 下标为 gap 的数开始，因为 gap - gap = 0，就表示第一个数，第一个数在第一组
            for (i = gap; i < len; ++i) {

                // 循环内是每次都是比较同一组的数据
                if (arr[i] < arr[i - gap]) {

                    tempVal = arr[i];

                    // 找到需要插入的位置，只要当前的值大于 需要移动的值 tempVal，就将值向后移动
                    for (j = i - gap; j >= 0; j -= gap) {
                        if (arr[j] < tempVal) {
                            break;
                        }
                        arr[j + gap] = arr[j];
                    }

                    // 当找到位置之后进行插入
                    arr[j + gap] = tempVal;

                }

            }

        }

    }

    /**
     * 代码第三次简化:<br>
     * 这里简化的是 直接插入排序的步骤，详见 直接插入排序的第三种方法<br>
     */
    public static void shellSort3(int[] arr) {

        int len, i, tempVal, gap, k;

        len = arr.length;

        for (gap = len / 2; gap > 0; gap /= 2) {

            for (i = gap; i < len; ++i) {

                for (k = i - gap; k >= 0 && arr[k] > arr[k + gap]; k -= gap) {
                    // 只要 arr[k] > arr[k + gap] 就表示 前一个大于后一个嘛，此时就需要交换两个数的位置
                    tempVal = arr[k];
                    arr[k] = arr[k + gap];
                    arr[k + gap] = tempVal;
                }

            }

        }

    }
}
