/**
 * @author youguoqiang
 * @date 2020/8/6
 */
public class LeetCode {
    public static void main(String[] args) {
        removeDuplicates(new int[]{1, 1, 2});
    }
    public static int removeDuplicates(int[] nums) {
        int index = 1;
        int result = 1;
        for(int i=0, len=nums.length; i<len;i++){
            if(i==len-1){
                continue;
            }
            if(nums[i]!=nums[i+1]){
                if(i+1!=index){
                    nums[index]=nums[i+1];
                    index++;
                    result++;
                }
            }
        }
        return result;
    }
}
