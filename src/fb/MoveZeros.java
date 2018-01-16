package fb;

import java.util.Arrays;

public class MoveZeros {
	public void moveZeroes(int[] nums) {
		int pos = 0; //记录完成压缩的当前位置
		for(int i=0;i<nums.length;i++){//遍历数组
			if(nums[i] != 0){//如果遍历到的当前值不为0，则需要向前压缩
				nums[pos] = nums[i];//放置到pos位置处
				pos++;//pos自增
			}
		}
		//for循环结束后数组中不为0的数都已经向前压缩，并且从0开始到pos位置之前的都是非0数，
		//我们需要做的就是把从pos开始到最后，全部置为0即可
		while(pos<nums.length){
			nums[pos++] = 0;
		}
		System.out.println(Arrays.toString(nums));
	}
	public static void main(String[] args) {
		int[] nums = {0, 1, 0, 3, 2};
		new MoveZeros().moveZeroes(nums);
	}
}
