class Solution {
    public boolean isValid(String s) {
        Deque<Character> openPar = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);

            if (bracket == '(' || bracket == '[' || bracket == '{') {
                openPar.push(bracket);
            } else if (openPar.isEmpty()) {
                return false;
            } else if (bracket == ')' && openPar.peek() == '(') {
                openPar.pop();
            } else if (bracket == ']' && openPar.peek() == '[') {
                openPar.pop();
            } else if (bracket == '}' && openPar.peek() == '{') {
                openPar.pop();
            } else {
                return false;
            }
        }

        return openPar.isEmpty() ? true : false;
    }
}