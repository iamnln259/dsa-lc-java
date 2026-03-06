"""
  "Can the abbreviation contain leading zeros?" (Crucial. The problem usually specifies that something like "a01b" is invalid. 
  You must explicitly check for 0 at the start of a number).
"Are adjacent numbers allowed?" (e.g., is "a12b" the same as "a" + 1 + 2 + "b" or "a" + 12 + "b"? 
  The standard rule is that adjacent digits form a single number. Clarifying this shows you understand tokenization).
"What happens if the number in the abbreviation is larger than the remaining word length?" 
  Use one pointer i for word and one pointer j for abbr. Iterate through abbr.
If abbr[j] is a letter, check if it matches word[i]. If not, return false.
If abbr[j] is a digit, parse the entire number (e.g., "12"). Move pointer i forward by that amount.
Critical Check: Ensure the number doesn't start with '0'.
Trade-off: Time is O(N). Space is O(1). This is the only acceptable solution for a phone screen.
  """
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0 , j = 0;
        while(i < word.length() && j < abbr.length()){
            if(Character.isDigit(abbr.charAt(j))){
                if(abbr.charAt(j) == '0' ) return false;
                int num = 0;
                while(j < abbr.length() && Character.isDigit(abbr.charAt(j))){
                    num = num * 10 + (abbr.charAt(j++) - '0');
                }
                i += num ;
            }else{
                if(i > word.length() || word.charAt(i) != abbr.charAt(j)) return false;
                i++;
                j++;
            }
        }
        return i == word.length() && j ==abbr.length();
    }
}
