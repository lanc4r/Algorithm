package Sort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 归并排序简单实现:<br>
 * <br>
 * 总体思想: <br>
 * 将两个有序的数组合并为一个数组<br>
 * <br>
 * 细节:<br>
 * 假设一个长度为 4 的数组 : [3, 1, -2, -3]，第一次分隔为 [3, 1], [-2, -3]<br>
 * 第二次分隔为 [[3], [1]] , [[-2], [-3]]<br>
 * [3], [1] 只有一个数，一定是有序的，于是开始合并得到 [1, 3]，同理得到 [-3, -2]<br>
 * 接着 合并 [1, 3]，[-3, -2] 得到 [-3, -2, 1, 3]
 */

public class MergeSort {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // int[] arr1 = { 2, 3, 7, 10 };
        // int[] arr2 = { 7, 7, 10 };
        // int[] resArr = new int[arr1.length + arr2.length];
        //
        // mergeGroup1(arr1, arr2, resArr);

        // int[] arr = { -4, 5, 9, 11, -6, 0, 5, 7, 9, 10, 10, 19 };
        // int pos = 3;
        // int[] resArr = new int[arr.length];
        //
        // mergeGroup2(arr, pos, resArr);

        int[] arr = { -5, 7, 1, -9, 0, 6, -19 };
        int[] tempArr = new int[arr.length];

        mergeSort(arr, 0, arr.length - 1, tempArr);

        System.out.println("After Sort: " + mapper.writeValueAsString(arr));

    }

    /**
     * 先尝试合并两个有序数列:<br>
     * 循环两个数组，把当前指针最小的值赋值到结果数组 resArr 中，然后把赋值的数组 和 结果数组 指针向后移动一位<br>
     * 如果某个数组赋值完了，然后把剩余数组的数字放到 resArr 之后就好了
     */
    public static void mergeGroup1(int[] arr1, int[] arr2, int[] resArr) {

        int len1, len2, i, j, k;
        i = 0;
        j = 0;
        k = 0;
        len1 = arr1.length;
        len2 = arr2.length;

        while (i < len1 && j < len2) {
            if (arr1[i] < arr2[j]) {
                resArr[k++] = arr1[i++];
            } else {
                resArr[k++] = arr2[j++];
            }
        }

        // 最后判断哪个数组指针没有指到最后，再进行赋值
        while (i < len1) {
            resArr[k++] = arr1[i++];
        }

        while (j < len2) {
            resArr[k++] = arr2[j++];
        }
    }

    /**
     * 合并数列进阶:<br>
     * 上面的方法是将两个分开的数组合并，我们可以理解为一个数组的前半段 和 后半段<br>
     * 基于这样的想法，我们试试把 排序一个 前后半段都有序的 数组<br>
     * <br>
     * pos 表示 前后半段的分界点(第一组的最后数字的位置)
     */
    public static void mergeGroup2(int[] arr, int pos, int[] resArr) {

        int end1, end2, i, j, k;
        i = 0;
        end1 = pos + 1;
        j = pos + 1;
        end2 = arr.length;
        k = 0;

        while (i < end1 && j < end2) {
            if (arr[i] < arr[j]) {
                resArr[k++] = arr[i++];
            } else {
                resArr[k++] = arr[j++];
            }
        }

        while (i < end1) {
            resArr[k++] = arr[i++];
        }

        while (j < end2) {
            resArr[k++] = arr[j++];
        }

        // 最后这里将 排好序的 resArr 赋值给 arr
        for (i = 0; i < arr.length; ++i) {
            arr[i] = resArr[i];
        }

    }

    /**
     * 然后谈谈归并排序的思想:<br>
     * <br>
     * 1. 将提供的数列分为 前半段 和 后半段，只要 前半段 和 后半段都有序，进行合并<br>
     * 2. 如果前半段无序，进行拆分，分为，前半段 1 和 前半段2，如 前半段 1 和 前半段2 都有序，进行和并<br>
     * 3. 如果 前半段 1 无序，继续拆分.... 直到 两段分别只剩下 一个数为止，然后开始合并，形成有序数列<br>
     * <br>
     * 递<b>归</b>拆解数列，合<b>并</b>数列<br>
     * <br>
     * 
     * 再结合上面我们的 mergeGroup2()，运用递归思想就能写出代码啦
     * 
     */
    public static void mergeSort(int[] arr, int start, int end, int[] temp) {

        // 递归终止条件，只有拆分到只剩下一个数字的时候才开始合并
        if (start < end) {

            // 我们按中间的位置分为两个数列
            int pos = (start + end) / 2;

            // 前半段 排序
            mergeSort(arr, start, pos, temp);

            // 后半段排序
            mergeSort(arr, pos + 1, end, temp);

            // 上面两个执行完后，表示前半段 和 后半段 都有序了，此时执行合并
            mergeGroup(arr, start, pos, end, temp);
        }

    }

    /**
     * 上面的 mergeGroup2() 方法其实是分好了组的，第一组就是从 [0...pos] 第二组从 [pos+1...n]<br>
     * 但实际递归中可这样的，所以我们需要简单的修改一下
     */
    public static void mergeGroup(int[] arr, int start, int pos, int end, int[] tempArr) {

        int end1, end2, i, j, k;
        i = start;
        end1 = pos;
        j = pos + 1;
        end2 = end;
        k = 0;

        while (i <= end1 && j <= end2) {
            if (arr[i] < arr[j]) {
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }

        while (i <= end1) {
            tempArr[k++] = arr[i++];
        }

        while (j <= end2) {
            tempArr[k++] = arr[j++];
        }

        /**
         * 这里赋值需要注意一下:<br>
         * 我们这里只合并了 [start...end] 这个区间，所以我们赋值也只能赋值 [start...end] 区间<br>
         * 
         * 这里 (end - start) + 1 其实值就是 k....， 因为之前 end 传过来的值 就是 就是 len-1，所以这里需要
         * (end - start) + 1
         */
        for (i = 0; i < end - start + 1; ++i) {
            arr[i + start] = tempArr[i];
        }

    }
}
