class Solution {
    public int calculate(String s) {
        int result = 0;
        int number = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                number = number * 10 + (c - '0');
            }
            if(c == '+'){
                result += number * sign;
                number = 0;
                sign = 1;
            }
            if(c == '-'){
                result += number * sign;
                number = 0;
                sign = -1;
            }
            if(c == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }
            if(c == ')'){
                result += number * sign;
                number = 0;

                int prevSign = stack.pop();
                result *= prevSign;

                int prevResult = stack.pop();
                result += prevResult; 
            }
        }
        result += number * sign;
        return result;
    }
}