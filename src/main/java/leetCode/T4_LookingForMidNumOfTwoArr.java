package leetCode;

/**
 * 寻找两个正序数组中的中位数
 *
 * @author: diaoyuhang
 * @date 2022/2/10
 */
public class T4_LookingForMidNumOfTwoArr {
    public static void main(String[] args) {
        System.out.println(1 / 2);
    }

    public double find(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        if ((length1 + length2) % 2 == 1) {
            return getMidNumOfTwoArr(nums1, nums2, (length1 + length2) / 2 + 1);
        } else {
            return (getMidNumOfTwoArr(nums1, nums2, (length1 + length2) / 2) + getMidNumOfTwoArr(nums1, nums2, (length1 + length2) / 2 + 1)) / 2;
        }
    }

    private double getMidNumOfTwoArr(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while (true) {
            if (length1 == index1) {
                return nums2[index2 + k - 1];
            }

            if (length2 == index2) {
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int mid = k / 2;

            int midValue1 = nums1[mid];
            int midValue2 = nums2[mid];

            if (midValue1 > midValue2) {
                
            }
        }

        return 0;
    }
}