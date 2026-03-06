//O(N) , O(N) for the char array
class Solution {
    public String minRemoveToMakeValid(String s) {
                char [] chars = s.toCharArray();
        int open = 0;
        for(int i = 0 ; i < chars.length; i++){
            if(chars[i] == '('){
                open ++;
            }else if (chars[i] == ')'){
                if(open == 0){
                    chars[i] = '*';
                }else{
                    open --;
                }
            }
        }

        for(int i = chars.length - 1 ; i >= 0 && open > 0 ; i--){
            if(chars[i] == '('){
                chars[i] = '*';
                open --;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(c != '*') sb.append(c);
        }
        return sb.toString();
    }
}
