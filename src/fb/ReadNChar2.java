package fb;

import java.util.LinkedList;
import java.util.Queue;

public class ReadNChar2 {
	Queue<Character> cache = new LinkedList<>();
	public int read(char[] buf, int n){
		int i=0; 
		//队列不空，先读取队列中的暂存字符
		while(i<n && ! cache.isEmpty()){
			buf[i] = cache.poll();
			i++;
		}
		for(;i<n;i+=4){
			char[] temp = new char[4];
			int len = read4(temp);
			//如果读到字符多于我们需要的字符，需要暂存这些多余的字符
			if(len > n-i){
				System.arraycopy(temp, 0, buf, i, n-i);;
				for(int j=n-i; j<len; j++){
					cache.offer(temp[j]);
				}
			}else{//如果读到的字符少于我们需要的字符，直接拷贝即可
				System.arraycopy(temp, 0, buf, i, len);
			}
			//同样，如果读不满4个，说明数据已经读完，返回总共所需长度和目前已经读到的长度的较小值
			if(len < 4)	return Math.min(i+len, n);
		}
		//如果到这里，说明是完美读取，直接放回n
		return n;
	}
	private int read4(char[] buf42) {
		return 0;
	}

	public static void main(String[] args) {
	}
}
