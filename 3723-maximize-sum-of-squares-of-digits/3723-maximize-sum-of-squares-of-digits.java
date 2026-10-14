class Solution {
    public String maxSumOfSquares(int num, int sum) {

        // no such integer exists
        if(sum > 9 * num){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int nines = sum / 9;
        int remainder = sum % 9;

        // add as much 9s as possible
        for(int i = 0; i < nines; i++){
            sb.append('9');
        }

        if(remainder > 0){
            sb.append(remainder);
        }

        // padding
        while(sb.length() < num){
            sb.append('0');
        }

        return sb.toString();
    }
}