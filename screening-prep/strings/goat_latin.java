Questions:
How are words separated?
Is the Input strictly input letters? any additional punctuation marks etc?
Do we need to handle the case sensitivity of the vowels? YES 
Any leading or Trainling spaces?

Appraoch
Splling each word, process, add each word back - takes lot of space.
Use a mutable string builder to construct the final result.
Identify the Vowels. 
For consonants, rotate the string 
Append ma
Maintaing a running suffix of 'a' s 

Edge Cases 

Single Letter Words:
"A" (Vowel) -> "Ama" + "a" -> "Amaa". Correct.
"B" (Consonant) -> "" (substring) + "B" + "ma" + "a" -> "Bmaa". Correct.
All Vowels: Handled correctly by the Set check.
Mixed Case: "Apple" vs "apple". Both 'A' and 'a' are in the Set.

Code 
class Solution {
    public String toGoatLatin(String sentence) {
        // 1. Guard Clause: Handle empty input
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        // 2. Define vowels for O(1) lookup
        // Using a Set is cleaner than a massive if-statement
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        
        // 3. Current suffix tracker ("a", "aa", "aaa"...)
        // Using StringBuilder for the suffix prevents creating new String objects for "aa", "aaa", etc.
        StringBuilder currentSuffix = new StringBuilder("a");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char firstChar = word.charAt(0);

            // 4. Append space before words (except the first one)
            if (i > 0) {
                result.append(" ");
            }

            // 5. Apply Logic
            if (vowels.contains(firstChar)) {
                // Rule 1: Starts with vowel -> Append "ma"
                result.append(word).append("ma");
            } else {
                // Rule 2: Starts with consonant -> Move first char to end + "ma"
                // substring(1) gets everything from index 1 to end
                result.append(word.substring(1)).append(firstChar).append("ma");
            }

            // 6. Rule 3: Add index-based 'a's
            result.append(currentSuffix);
            
            // Increment suffix for the next word
            currentSuffix.append("a");
        }

        return result.toString();
    }
}


Dry Run
Test Case 1: "I speak"
Init: suffix = "a", result = ""
Word 1 ("I"):
Starts with 'I' (Vowel).
Append word + "ma" -> "Ima".
Append suffix ("a") -> "Imaa".
Update suffix -> "aa".
result = "Imaa".
Word 2 ("speak"):
Starts with 's' (Consonant).
Append space -> "Imaa ".
Move 's' to end -> "peak" + "s" + "ma" -> "peaksma".
Append suffix ("aa") -> "peaksmaaa".
Update suffix -> "aaa".
result = "Imaa peaksmaaa".
Return: "Imaa peaksmaaa".
Test Case 2: "The"
Init: suffix = "a"
Word 1 ("The"):
Starts with 'T' (Consonant).
Move 'T' -> "he" + "T" + "ma" -> "heTma".
Append suffix ("a") -> "heTmaa".
Return: "heTmaa".
