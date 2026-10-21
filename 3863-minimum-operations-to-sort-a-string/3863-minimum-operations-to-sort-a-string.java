class Solution {
    public int minOperations(String s) {
        if (isSorted(s)) {
            return 0;
        }

        int n = s.length();
        if (n == 2) {
            return -1;
        }

        int freq[] = new int[26];
        char min = 'z';
        char max = 'a';

        for (char ch : s.toCharArray()) {
            min = (char) Math.min(min, ch);
            max = (char) Math.max(max, ch);
            freq[ch-'a']++;
        }

        if (s.charAt(0) == min || s.charAt(n-1) == max) {
            return 1;
        }

        if (s.charAt(0) == max && s.charAt(n-1) == min) {
            if (freq[max-'a'] == 1 && freq[min-'a'] == 1) {
                return 3;
            } else {
                return 2;
            }
        }
    return 2;
    }
    boolean isSorted (String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) > s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}