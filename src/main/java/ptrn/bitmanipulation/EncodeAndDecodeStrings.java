package ptrn.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/encode-and-decode-strings/
//      - https://neetcode.io/problems/string-encode-and-decode
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5246877035134976
public class EncodeAndDecodeStrings {

    //////////OSI///////////////////////
    /*
        Intuition by nc.
     */
    static class Solution0 {
        //T: O(N), S: O(1)
        public String encode(List<String> strs) {
            StringBuffer sb = new StringBuffer();
            for (String st: strs) {
                sb.append(String.valueOf(st.length())+"#"+st);
            }

            return sb.toString();
        }

        //T: O(N), S: O(1)
        public List<String> decode(String str) {
            int n = str.length();

            List<String> ans = new ArrayList<>();
            int i = 0;
            while (i < n) {
                int j = i;
                while (j < n && str.charAt(j) != '#') {
                    j += 1;
                }
                int len = Integer.parseInt(str.substring(i, j));
                ans.add(str.substring(j+1, j+1+len));
                i=j+1+len;
            }

            return ans;
        }
    }
    ///////////////////////////////////


    //Edctv soln.
    //straightforward and cleaner but confusing on the byte shift for length.
    //redo @check.
    static class Solution {
        public String lengthToBytes(String x) {
            // Express the string length as a string of bytes
            // If there are 4 characters in the string, the function will return "0004"
            int length = x.length(); // Compute the length of the string
            StringBuilder bytes = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                // Apply right shift operator
                bytes.append((char) (length >> (i * 8)));
            }

            return bytes.reverse().toString();
        }

        public int bytesToLength(String bytesString) {
            // Convert a 4-byte string to an integer
            // If bytesString is "0040", the function will return 40
            int result = 0;

            for (char c : bytesString.toCharArray()) {
                result = result * 256 + c;
            }

            return result;
        }

        public String encode(List<String> strings) {
            StringBuilder encodedString = new StringBuilder();

            for (String x : strings) {
                // Add appended 4-byte string length to string in encoded string
                encodedString.append(lengthToBytes(x)).append(x);
            }

            return encodedString.toString();
        }

        public List<String> decode(String str) {
            // Initialize a pointer
            int i = 0;
            List<String> decodedString = new ArrayList<>();

            while (i < str.length()) {
                // Get length in integer from 4-byte string
                int length = bytesToLength(str.substring(i, i + 4));
                i += 4;
                // Add string of the computed length
                decodedString.add(str.substring(i, i + length));
                // Move the pointer
                i += length;
            }

            return decodedString;
        }
    }

    //my soln.
    //serialize the byte array.
    //kinda nasty.
    static class Solution2 {

        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();

            for (String str: strs) {
                byte[] brr = str.getBytes();
                StringBuilder sb2 = new StringBuilder();
                for (byte b: brr) sb2.append(b).append("|");
                sb.append(sb2.toString()).append("$");
            }
            return sb.toString();
        }

        public List<String> decode(String str) {
            List<String> ans = new ArrayList<>();
            System.out.println(str);
            if (str.length() == 0) {
                return ans;
            }
            String[] brr = str.split("\\$");

            for (int i=0; i<brr.length; i++) {
                List<Byte> bl = new ArrayList<>();
                String[] sarr = brr[i].split("\\|");

                byte[] byrr = new byte[sarr.length];
                if (sarr.length > 1 || !"".equals(sarr[0])) {
                    for (int j=0; j<sarr.length; j++) {
                        byrr[j] = Byte.parseByte(sarr[j]);
                    }
                    ans.add(new String(byrr));
                } else {
                    ans.add("");
                }


            }
            if (ans.size() == 0) {
                ans.add("");
                return ans;
            }
            return ans;
        }
    }

}
