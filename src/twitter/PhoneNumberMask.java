package twitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PhoneNumberMask {
    //mask the email
    private static String emailMask(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append("E:");
        sb.append(email.charAt(0));
        sb.append("*****");
        int start = email.lastIndexOf('@') - 1;
        sb.append(email.substring(start));
        return sb.toString();
    }

    //mask the phone number
    private static String phoneMask(String phone) {
        StringBuilder sb = new StringBuilder();
        sb.append("P:");
        char[] arr = phone.toCharArray();
        int num = 0;
        for (char c : arr) {
            if (Character.isDigit(c)) {
                num++;
            }
        }
        int countryCode = num - 10;
        boolean extra = false;
        if (countryCode > 0) {
            sb.append('+');
            extra = true;
        }
        int cur = 0;

        while (cur < arr.length && countryCode > 0) {
            if (Character.isDigit(arr[cur])) {
                sb.append('*');
                countryCode--;
            }
            cur++;
        }
        if (extra) {
            sb.append('-');
        }
        int cnt = 0;
        while (cur < arr.length && cnt < 3) {
            if (Character.isDigit(arr[cur])) {
                sb.append('*');
                cnt++;
            }
            cur++;
        }
        sb.append('-');
        cnt = 0;
        while (cur < arr.length && cnt < 3) {
            if (Character.isDigit(arr[cur])) {
                sb.append('*');
                cnt++;
            }
            cur++;
        }
        sb.append('-');
        cnt = 0;
        while (cur < arr.length && cnt < 4) {
            if (Character.isDigit(arr[cur])) {
                sb.append(arr[cur]);
                cnt++;
            }
            cur++;
        }
        return sb.toString();

    }

    public static String parsePhone(String number){
        StringBuilder result = new StringBuilder();
        result.append("P:");
        StringBuilder num = new StringBuilder();
        String[] strs = number.trim().split(":");
        for (char c: strs[1].toCharArray())
            if (c>='0' && c<='9')
                num.append(c);
        String realNum = num.toString();
        String show = num.substring(realNum.length()-4);
        int codeCount = realNum.length() - 10;
        if (codeCount != 0) {
            result.append("+");
            for(int i = 0;i<codeCount;i++){
                result.append("*");
            }
            result.append("-");
        }
        result.append("***-***-");
        result.append(show);
        return result.toString();
    }
    public static String parseEmail(String email){
        StringBuilder sb = new StringBuilder();
        String[] strs = email.trim().split(":");
        strs = strs[1].split("@");
        String pre = strs[0];
        String post = strs[1];
        sb.append("E:").append(pre.charAt(0));
        for(int i=0;i<pre.length()-2;i++)
            sb.append("*");
        sb.append(pre.charAt(pre.length() -1));
        sb.append("@").append(post);
        return sb.toString();
    }
    public static void main(String args[]) {
        BufferedReader br = null;
        String line;
        ArrayList<String> result = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                char zeroPos = line.charAt(0);
                if (zeroPos == 'E'){
                    result.add(parseEmail(line));
                }else if(zeroPos == 'P'){
                    result.add(parsePhone(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {

                }
            }

        }
        System.out.println(result.toString());

    }
}
