import java.util.PriorityQueue;

public class KthLargest {
	public static int kthLargest(int[] arr, int k){
		if(arr.length == 1)	return arr[0];
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		pq.add(arr[0]);
		for(int i=1; i<arr.length;i++){
			if(pq.size() < k){
				pq.add(arr[i]);
			}else{
				int value = pq.peek();
				if(arr[i] > value){
					pq.poll();
					pq.add(arr[i]);
				}
			}
		}
		return pq.peek();
	}
	public static void main(String[] args) {
		int[] array = {1,4,1,3,1,2,1,1};
		System.out.println(kthLargest(array, 3));
	}
}
