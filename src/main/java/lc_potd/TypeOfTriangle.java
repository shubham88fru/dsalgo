package lc_potd;

//@link - https://leetcode.com/problems/type-of-triangle/
public class TypeOfTriangle {
    public String triangleType(int[] nums) {
        return pass1(nums);
    }

    private String pass1(int[] nums) {
        if (nums[0] + nums[1] <= nums[2] ||
                nums[0] + nums[2] <= nums[1] ||
                nums[1] + nums[2] <= nums[0]
        ) return "none";

        if (nums[0] == nums[1] && nums[1] == nums[2]) return "equilateral";

        if (nums[0] == nums[1] || nums[0] == nums[2] || nums[1] == nums[2]) return "isosceles";

        return "scalene";
    }
}
