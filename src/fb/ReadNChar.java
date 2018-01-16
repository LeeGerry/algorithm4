package fb;

import java.util.Arrays;

public class ReadNChar {
	public int read(char[] buf, int n){
		for(int i=0; i<n; i+=4){
			//将数据读入临时数组
			char[] temp = new char[4];
			int len = read4(temp);
			//将临时数组拷贝至buf数组，拷贝的长度是本次读到的个数和所剩余所需个数中较小的
			System.arraycopy(temp, 0, buf, i, Math.min(len, n-i));
			//如果读不满4个说明已经读完了，返回总共所需长度和目前已经读到的长度的较小的
			if(len < 4)	
				return Math.min(i + len, n);
		}
		return n;
	}

	private int read4(char[] temp) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9};
		int[] b = new int[6];
		System.arraycopy(a, 2, b, 2, 3);
		System.out.println(Arrays.toString(b));
	}
}
