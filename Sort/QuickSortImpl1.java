package QuickSort;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 快排最简单实现
 */

public class QuickSortImpl1 {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        int[] unOrderArr = { -3, 1, -7, 9, 5, 19, -7 };
        System.out.println("Before Sort: " + mapper.writeValueAsString(unOrderArr));

        quickSort(unOrderArr, 0, unOrderArr.length - 1);
        System.out.println("After Sort: " + mapper.writeValueAsString(unOrderArr));
    }

    public static void quickSort(int[] unOrderArr, int start, int end) {

        // 递归死循环终结条件
        if (start >= end) {
            return;
        }

        // 获取中间位置 —— 同时就把当前位置已经排好序了
        int position = getPosition(unOrderArr, start, end);
        quickSort(unOrderArr, start, position - 1);
        quickSort(unOrderArr, position + 1, end);
    }

    public static int getPosition(int[] unOrderArr, int start, int end) {

        int num = unOrderArr[start];

        // 循环，直到找到 num 值的位置
        while (start < end) {

            // 首先移动右边的指针，只要 end 指针指向的值比 num 小就移动
            while (start < end && num <= unOrderArr[end])
                end--;

            unOrderArr[start] = unOrderArr[end];

            // 然后移动左边的指针，只要 start 指针指向值 小于等于 num 就移动
            while (start < end && unOrderArr[start] <= num)
                start++;

            unOrderArr[end] = unOrderArr[start];
        }

        // 最后退出 外圈循环的时候，此时表示找到 num 的位置了，进行赋值
        unOrderArr[start] = num;
        System.out.println("数值为: " + num + " 排序完成，当前位置为: " + start);
        return start;
    }

    // -2
    // -2 1 -2 3 7 -2 8

}
