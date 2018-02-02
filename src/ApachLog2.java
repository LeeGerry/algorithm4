import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.TreeMap;

public class ApachLog2 {
	/**
	 * Format the date according to the input string date
	 * 
	 * @param string
	 *            date
	 * @return the date with target format
	 */
	public static String dateParse(String s) {
		SimpleDateFormat ForMatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		ForMatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
		Date date = new Date();
		String result = new String();
		try {
			date = formatter.parse(s);
			result = ForMatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TreeMap<String, List<Integer>> map = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] arr1 = s1.split(",");
				String[] arr2 = s2.split(",");
				if (arr1[0].equals(arr2[0])) {
					return arr1[1].compareTo(arr2[1]);
				}
				return arr1[0].compareTo(arr2[0]);
			}
		});
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			int index1 = s.indexOf("[");
			int index2 = s.indexOf("]");
			String time = s.substring(index1 + 1, index2);
			String parseTime = dateParse(time);
			int index3 = s.indexOf("\"");
			int index4 = s.indexOf("\"", index3 + 1);
			String path = s.substring(index3 + 1, index4);
			String[] arr = path.split(" ");
			String endPoint = "";
			int index5 = arr[1].indexOf("?");
			if (index5 == -1) {
				endPoint = arr[1];
			} else {
				endPoint = arr[1].substring(0, index5);
			}
			String key = parseTime + "," + endPoint;
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			int index6 = index4 + 2;
			String valueString = s.substring(index6, index6 + 3);
			int value = Integer.parseInt(valueString);
			map.get(key).add(value);
		}

		for (String key : map.keySet()) {
			String[] arr = key.split(",");
			List<Integer> values = map.get(key);
			int count = 0;
			for (int value : values) {
				if (value / 100 != 5) {
					count++;
				}
			}
			String result = String.format("%.2f", count * 1.0 / values.size() * 100);
			System.out.println(arr[0] + " " + arr[1] + " " + result);
		}
	}
}
