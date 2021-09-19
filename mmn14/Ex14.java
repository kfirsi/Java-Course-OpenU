
/**
 * @version 2020a
 * @author Kfir Sibirsky
 */
public class Ex14 {
    /**
     * counts how many possible valid paths are in a given 2D array of integers.<br>
     * valid path = a set of cells in the array, beginning with the first cell [0][0] and advance to the 
     * last cell [mat.length-1][mat[0].length-1] by the ones and tens digits of the number in the cell.<br>
     * in example: if 15 is in cell [2][3], than from that cell it is only possible to advance:<br>
     * or +1 in rows and +5 in columns, meaning to cell [3][8]<br>
     * or +5 in columns and +1 in rows, meaning to cell [7][4]<br>
     * Note: it is impossible to move beyond the boundaries of the array.
     * @param mat the 2D array to count valid paths in.
     * @return the number of possible valid paths in the 2D array (mat);
     */
    public static int countPaths(int[][] mat) {

        return countPaths(mat, 0, 0);
    }
    //overloading method countPaths, this method counts recursively how many possibilities for valid routes are there.
    private static int countPaths(int[][] mat, int row, int col)
    {
        //if we are at the last cell of the array, that means we found a path.
        if (row == mat.length-1 && col == mat[row].length-1)
            return 1;

        //avoid moving out of bounds and to get stuck in cell that contains 0.
        if (row > mat.length-1 || col > mat[row].length-1 || mat[row][col] == 0)
            return 0;

        //count only one path if the cell contains a numbers that it's ones and tens digits are the same.
        if (mat[row][col] % 10 == mat[row][col] / 10)
            return countPaths(mat,row+mat[row][col]/10,col+mat[row][col]/10);

        //Count the number of valid paths for each step.
        else return countPaths(mat, row+mat[row][col]/10, col+mat[row][col]%10) +
            countPaths(mat, row+mat[row][col]%10, col+mat[row][col]/10);

    }

    /**
     * Checks whether or not a given string t is a transformation of a given string s.<br>
     * Note: t is a transformation of s if every character in s is in t (in the order they appear in s) at least once.
     * @param s the original String.
     * @param t the String to check whether or not is a transformation of s.
     * @return true if t is a transformation of s.
     */
    public static boolean isTrans (String s, String t) {
        return isTrans (s, t, 0, 0); 
    }
    //overloading method isTrans, this method checks recursively if t is trans of s.
    private static boolean isTrans (String s, String t, int i, int j) {	

        //if t start with a different character than s or t is shorter than s, and so t can't be trans
        if (t.equals("") || s.charAt(0) != t.charAt(0) || t.length() < s.length())
            return false;

        if (s.charAt(i) == t.charAt(j)) {
            if (j == t.length()-1 && i == s.length()-1)//at the end of both strings
                return true;
            if (i == s.length()-1)//at the end of s
                return (isTrans (s, t, i, ++j));
            return (isTrans (s, t, ++i, ++j));//not at the end of either of the strings
        }
        if (s.charAt(i) != t.charAt(j)) {
            //last character in t is different from s[i], and so t can't be trans
            if (j == t.length()-1 || t.charAt(j-1) != t.charAt(j))
                return false;
            return (isTrans (s, t, i, ++j));
        }
        return true;
    }

    /**
     * Checks how many Sub-Strings are within a given String that starts and ends with a
     * given character and also has that character inside the Sub-String exactly once.
     * @param s the String to check for the Sub-String within.
     * @param c the character to check the Sub-String according to.
     * @return the number of the valid Sub-Strings.
     * @timeComplexity O(n). n = the array's (s) length. we go over the String's (s) from left to right once. 
     * @SpaceComplexity O(1).
     */
    public static int subStrC(String s, char c)	{
        int count = 0;//counts the number of c's in the given string
        if( s == null ||  s.length()<3|| s.equals(null))//s length is smaller than needed for c...c...c
            return 0;
        for (int i = 0; i < s.length(); i++) {//go over the string and counting c's
            if(s.charAt(i)==c)
                count++;
        }
        if( count < 3)//there were not enough c's in the given string (0 / 1 / 2) 
            return 0;

        return count-2;
    }

    /**
     * Checks how many Sub-Strings are within a given String that starts and ends with a
     * given character and also has that character inside the Sub-String maximum a given amount of times.
     * @param s the String to check for the Sub-String within.
     * @param c the character to check the Sub-String according to.
     * @param k the maximum amount of time that the given character has to be within every Sub-String.
     * @return the number of the valid Sub-Strings.
     * @timeComplexity O(n). n = the array's (s) length. we go over the String's (s) from left to right once. 
     * @SpaceComplexity O(1).
     */
    public static int subStrMaxC(String s, char c, int k){
        int subStrs = 0;//counts the number of valid substrings
        int count = 0;// counts the number of c's in the string
        if(s == null)
            return 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == c){

                //valid substring looks like this: c... k times c ...c
                //k+1 is the part from the substring that contains only k and the last c 
                subStrs += ((k+1) <= count ? (k+1) : count);
                count++;
            }
        }	
        return subStrs;
    }

    /**
     * replaces each value in the given array that is not zero with the distance from it to the closest zero value.<br>
     * note: the distance is the number of steps in the array needed in order to get to the closest zero value;
     * @param a the array to work on.
     * @timeComplexity O(n). n = the array's (a) length. 2n = first loop from left to right + second loop from right to left.
     * @SpaceComplexity O(1).
     */
    public static void zeroDistance (int [] a) {
        int closestZero = -1;// closest == -1 means no zero was found yet
        for (int i=0; i<a.length; i++) { //go from left to right
            if(a[i] == 0) 
                closestZero = 0;
            else
            //if a[i]=1, and no zero was found yet, a[i] is indicated by the biggest int value
            //if a[i]=1, and a zero was found, the index of closest zero is incremented by 1 and assigned to a[i]
                a[i] = (closestZero == -1 ? Integer.MAX_VALUE : ++closestZero);
        }
        closestZero = -1;
        for (int i=a.length-1 ; i>=0 ; i--) {//go from right to left 
            if (a[i] == 0)                            
                closestZero = 0;
            //in case a closer zero from the right was found, a[i] is assigned that index+1
            else if (closestZero != -1 && a[i] > ++closestZero) 
                a[i] = closestZero;       
        }
    }

}
