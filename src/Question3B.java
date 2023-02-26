/*you are provided certain string and pattern, return true if pattern entirely matches the
 string otherwise return false.
Note: if pattern contains char @ it matches entire sequence of characters
 and # matches any single character within string.
Input: String a=“tt”, pattern =”@”
Output: true
Input: String a=“ta”, pattern =”t”
Output: false
Input: String a=“ta”, pattern =”t#”
Output: true
*/

//creating the class
class Question3B {
    //defining a function checkPattern that takes two parameters i.e. aand pattern
    public static boolean checkPattern(String inputVal, String pattern) {
        //initializing i and j with 0
        int i = 0,j=0;
      /*   Start a while loop with conditions:
             i is less than the length of the astring and
              j is less than the length of the pattern string*/
        while (i < inputVal.length() && j < pattern.length()) {
           /*  If the current character in the pattern string is '@',
             then the entire input string matches the pattern, so return true*/
            if (pattern.charAt(j) == '@') {
                return true;
            }
             /*If the current character in the pattern string is '#' and
             there are still characters in the astring, then
            increment both i and j to move to the next characters*/
            else if (pattern.charAt(j) == '#' && i < inputVal.length()) {
                i++;
                j++;
            }
           /*  If the current characters in the aand pattern strings
             match, increment both i and j to move to the next characters*/
            else if (inputVal.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
       /*      If none of the above conditions are met, the pattern doesn't match
            the astring, so return false*/
            else {
                return false;
            }
        }
/* If the loop has completed and both i and j are equal to the length of
their respective strings, then the pattern matches the astring*/
        return i == inputVal.length() && j == pattern.length();

    }

    public static void main(String[] args) {
        //providing a and pattern as input
        String a= "tt";
        String pattern = "@";
        //get a boolean value which is returned by checkPattern function
        boolean isMatch = checkPattern(a, pattern);
        //display the value in console
        System.out.println("Pattern match result: " + isMatch);

        a= "ta";
        pattern = "t";
        isMatch = checkPattern(a, pattern);
        System.out.println("Pattern match result: " + isMatch);

        a= "ta";
        pattern = "t#";
        isMatch = checkPattern(a, pattern);
        System.out.println("Pattern match result: " + isMatch);
    }

}

