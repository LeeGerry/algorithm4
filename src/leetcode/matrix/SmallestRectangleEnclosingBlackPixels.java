package leetcode.matrix;

public class SmallestRectangleEnclosingBlackPixels {
	public int minArea(char[][] image, int x, int y) {
		int row = image.length;
		int col = image[0].length;
		int left = binarySearchLeft(image, 0, y, true);
		int right = binarySearchRight(image, y, col - 1, true);
		int top = binarySearchLeft(image, 0, x, false);
		int bottom = binarySearchRight(image, x, row - 1, false);
		return (right - left + 1) * (bottom - top + 1);
	}

	private int binarySearchRight(char[][] image, int left, int right, boolean isHo) {
		while(left + 1 < right){
			int mid = (right - left) / 2 + left;
			if(hasBlack(image, mid, isHo))	left = mid;
			else							right = mid;
		}
		if(hasBlack(image, right, isHo))	return right;
		return left;
	}

	private int binarySearchLeft(char[][] image, int left, int right, boolean isHo) {
		while(left + 1 < right){
			int mid = (right - left) / 2 + left;
			if(hasBlack(image, mid, isHo))	right = mid;
			else 							left = mid;
		}
		if(hasBlack(image, left, isHo))		return left;
		return right;
	}

	private boolean hasBlack(char[][] image, int x, boolean isHo) {
		if(isHo){
			for(int i = 0; i < image.length; i++){
				if(image[i][x] == '1')	return true;
			}
		}else{
			for(int i = 0; i<image[0].length; i++){
				if(image[x][i] == '1')	return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] image = {
				{'0','0','1','0'},
				{'0','1','1','0'},
				{'0','1','0','0'},
		};
		SmallestRectangleEnclosingBlackPixels s = new SmallestRectangleEnclosingBlackPixels();
		System.out.println(s.minArea(image, 0, 1));
	}
}
