package fb;

public class AddBinary {
	public static String addBinary(String a, String b){
		StringBuilder sb = new StringBuilder();
		/* init operation:
			101 0011
			    1011
		*/
		int longer,shorter;
		String longerString, shorterString;
		if(a.length() > b.length()){
			longer = a.length(); shorter = b.length();
			longerString = a; shorterString = b;
		}else{
			longer = b.length(); shorter = a.length();
			longerString = b; shorterString = a;
		}
		//split
		int offset = longer - shorter;
		String off = longerString.substring(0,offset);
		String common = longerString.substring(offset, longer);
		int plus = 0;
		int i = 0;
		//calculate the common part
		for(i = shorter-1;i>=0;i--){
			int c1 = shorterString.charAt(i) - '0';
			int c2 = common.charAt(i) - '0';
			int sum = c1 + c2 + plus;
			if(sum >= 2){
				plus = 1;
				sb.insert(0, sum % 2);
			}else{
				plus = 0;
				sb.insert(0, sum);
			}
		}
		System.out.println(sb.toString());
		//calculate the offset part
		for(int j = offset-1;j>=0;j--){
			int sum = off.charAt(j) -'0' + plus;
			if(sum >=2){
				plus = 1;
				sb.insert(0,  sum%2);
			}else{
				plus = 0;
				sb.insert(0, sum);
			}
		}
		// deals with plus = 1 finally
		if(plus == 1){
			sb.insert(0, plus);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		String result = new AddBinary().addBinary("1010", "1011");
		System.out.println(result);
	}
}
