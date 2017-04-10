# Strstr O(n)

This is an algorithm to take advantage of anagram and valid suffix character.

There are 2 factors we are considering to ignore the characters comparison:
* If a substring in hay is not an anagram of needle, it must not be needle, and we will ignore the related characters' comparison.
* If the sequence of any 2 characters in hay is not existed in any sequence of 2 characters in needle, then in the hay,
  the characters before the 2 "invalid" sequence can be ignored from comparison.
  For example, we have hay = "abadef", and needle = "ade", and we can see all needle's sequences are [ad, de], and the sequence of
  "ba" will be used to mark that the substring of "aba" will not be possible to be needle, because "ba" is not any sequence of
  [ad, de]

### Class Strstr
By using above 2 features, we will build our algorithm in this way.
* 1. Scan needle and build sequence map
* 2. Create a boolean array with the same length of hay, and scan all hay's sequence, to mark the substring's characters
     which is not possible to be the starting point of needle substring in hay.
* 3. Mark all the positions after starting point with length of needle to "invalid" starting point.
* 4. Use O(n) to analyze possible anagram of needle in hay, once there is a anagram substring as well as the starting point is
     valid(which was NOT marked in step 2&3), compare the needle characters one by one to check if it's a substring of hay.
* 5. When hay's length is much much larger than needle's length, the time complexity is O(n)
* 6. Easier than KMP & Suffix Tree, right?

### Class StrstrAnagramOnly
Class StrstrAnagramOnly is impelementing the algorithm without using sequences I mentioned in step 1-4, as makes this algorithm
Space O(1).