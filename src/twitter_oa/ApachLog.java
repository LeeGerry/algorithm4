package twitter_oa;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

/**
Given an Apache-style web server log, calculate and report the success rate per endpoint per minute.
The "endpoint" is the path component of the URI. Example: /1.1/users/show.json
The "success rate" is the ratio of non-500-level response codes to total requests for a given endpoint, expressed as a percentage with two digits after the decimal.

Note that the results should be sorted first by minute, and then lexicographically by endpoint. Times should be output in GMT.

Your solution should work for any log file that uses the same formatting as the example provided below, including very large files.



Sample input:

10.10.10.10 - - [27/Sep/2016:05:22:00 +0000] "GET /1.1/friendships/list.json?user_id=123 HTTP/1.1" 500 563 19 "Twitter-iPhone/6.63 iOS/10.0.2 (Apple;iPhone7,2;;;;;1)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:05:22:08 +0000] "GET /1.1/friendships/list.json?user_id=123 HTTP/1.1" 200 563 19 "Twitter-iPhone/6.63 iOS/10.0.2 (Apple;iPhone7,2;;;;;1)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:05:22:31 +0000] "GET /1.1/friendships/list.json HTTP/1.1" 200 563 19 "Twitter-iPhone/6.63 iOS/10.0.2 (Apple;iPhone7,2;;;;;1)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:05:22:59 +0000] "GET /1.1/friendships/list.json HTTP/1.1" 200 94 6 "Twitter-iPhone/6.63 iOS/10.0.1 (Apple;iPhone7,2;;;;;1)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:05:23:01 +0000] "GET /1.1/users/show.json?include_entities=1&user_id=321 HTTP/1.1" 200 4160 51 "Twitter-iPhone/6.63 iOS/9.3.5 (Apple;iPhone7,2;;;;;0)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:22:45:33 +0000] "GET /1.1/friendships/list.json?user_id=234 HTTP/1.1" 200 563 19 "Twitter-iPhone/6.63 iOS/10.0.2 (Apple;iPhone7,2;;;;;1)" 177.177.177.177
10.10.10.10 - - [27/Sep/2016:22:45:51 +0000] "POST /1.1/friendships/create.json HTTP/1.1" 200 4193 120 "Twitter-iPhone/6.62.1 iOS/9.3.5 (Apple;iPhone7,2;;;;;0)" 177.177.177.177

Sample Output:

2016-09-27T05:22 /1.1/friendships/list.json 75.00
2016-09-27T05:23 /1.1/users/show.json 100.00
2016-09-27T22:45 /1.1/friendships/create.json 100.00
2016-09-27T22:45 /1.1/friendships/list.json 100.00
*/
class LogItem implements Comparable<LogItem>{
	private String date;
	private String endPoint;
	private int successCount;
	private int failedCount;
	
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	public String getRate(){
		float fRate = (float) (100 * successCount / ((successCount + failedCount) + 0.0));
		return String.format("%.2f", fRate);
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	@Override
	public int hashCode() {
		return this.date.hashCode() + this.endPoint.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this)	return true;
		if(obj.getClass() == this.getClass()){
			LogItem li = (LogItem)obj;
			return li.getDate().equals(this.getDate()) && li.getEndPoint().equals(this.getEndPoint());
		}
		return false;
	}
	@Override
	public String toString() {
		return getDate() + "," + getEndPoint() + ","+getRate();
	}
	@Override
	public int compareTo(LogItem o) {
		if(!o.getDate().equals(this.getDate())){
			return this.getDate().compareTo(o.getDate());
		}else{
			return this.getEndPoint().compareTo(o.getEndPoint());
		}
	}
}
public class ApachLog {
	/**
	 * Format the date according to the input string date
	 * @param string date 
	 * @return the date with target format
	 */
	public static String formatDate(String date){
		SimpleDateFormat sdfOfTarget = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		sdfOfTarget.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdfOfSource = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.US);
		Date dResult = null;
		try {
			dResult = sdfOfSource.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdfOfTarget.format(dResult);
	}
	public static void main(String[] args) {
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<LogItem> items = new ArrayList<>();
		try {
			while((line = reader.readLine()) != null){
				if(line.equals("")) break;
				String[] strs = line.trim().split(" ");
				String time = (strs[3]+" "+strs[4]);
				time = time.substring(1,time.length()-1);
				String endPoint = strs[6].substring(0, strs[6].lastIndexOf(".json") + 5);
				String status = strs[8].trim();
				LogItem item = new LogItem();
				item.setDate(formatDate(time));
				item.setEndPoint(endPoint);
				int code = Integer.parseInt(status);
				boolean failed = code >= 500? true:false;
				if(items.contains(item)){
					item = items.get(items.lastIndexOf(item));
				}
				
				if(failed) 	item.setFailedCount(item.getFailedCount()+1);
				else		item.setSuccessCount(item.getSuccessCount()+1);
				if(!items.contains(item))
					items.add(item);
			}
		} catch (Exception e) {
		}
		Collections.sort(items, new Comparator<LogItem>() {
			@Override
			public int compare(LogItem item1, LogItem item2) {
				if(!item1.getDate().equals(item2.getDate())){
					return item1.getDate().compareTo(item2.getDate());
				}else{
					return item1.getEndPoint().compareTo(item2.getEndPoint());
				}
			}
		});
		for(LogItem log: items)System.out.println(log);
	}
}
//2016-09-27T05:22 /1.1/friendships/list.json 75.00
//2016-09-27T22:45 /1.1/friendships/list.json 75.00
//2016-09-27T22:45 /1.1/friendships/list.json 0.75