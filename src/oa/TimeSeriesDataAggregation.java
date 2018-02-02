package oa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

public class TimeSeriesDataAggregation {
	static String startMonth;
	static String endMonth;

	static String removeDay(String date) {
		Date initialDate = null;
		try {
			initialDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String parsedDate = formatter.format(initialDate);
		return parsedDate;
	}

	static boolean isBetween(String date) {
		return date.compareTo(startMonth) >= 0 && date.compareTo(endMonth) < 0;
	}

	public static void main(String[] args) {
		String line = "";
		int num = 0;
		TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while ((line = br.readLine()) != null) {
				if (num == 0) {
					String[] months = line.trim().split(",");
					if (months.length != 2) {
						throw new Exception("invalid input!");
					}
					startMonth = months[0].trim();
					endMonth = months[1].trim();
					num++;
				} else if (num == 1) {
					num++;
					continue;
				}else if(line.isEmpty()){
					break;
				}else {
					String[] lines = line.trim().split(",");
					if (lines.length != 3) {
						throw new Exception("invalid input!");
					}
					String date = lines[0].trim();
					String parseDate = removeDay(date);
					String category = lines[1].trim();
					String countString = lines[2].trim();
					int count = Integer.parseInt(countString);
					if (!map.containsKey(parseDate)) {
						map.put(parseDate, new TreeMap<String, Integer>());
					}
					TreeMap<String, Integer> treeMap = map.get(parseDate);
					if (!treeMap.containsKey(category)) {
						treeMap.put(category, count);
					} else {
						treeMap.put(category, treeMap.get(category) + count);
					}
					num++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		System.out.println(map.toString());
		for (String key : map.keySet()) {
			StringBuilder sb = new StringBuilder();
			if (isBetween(key)) {
				sb.append(key);
				sb.append(", ");
				for (String kind : map.get(key).keySet()) {
					sb.append(kind);
					sb.append(", ");
					sb.append(map.get(key).get(kind));
					sb.append(", ");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
				System.out.println(sb.toString());
			}
		}
	}
}
