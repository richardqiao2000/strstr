package org.richardqiao.algorithm;

public class StrstrAnagramOnly {

  public static void main(String[] args) {
    System.out.println(strstr("abadef", "ade"));
  }

  private static int strstr(String hay, String needle){
    if(hay.length() < needle.length()) return -1;
    if(hay.length() == needle.length()){
      if(hay.equals(needle)) return 0;
      return -1;
    }
    char[] chHay = hay.toCharArray();
    char[] chNeedle = needle.toCharArray();
    int len = chNeedle.length;

    int[] anagram = new int[128];
    for(char ch: chNeedle){
      anagram[ch]++;
    }

    int length = len, more = 0;
    for(int i = 0; i < chNeedle.length; i++){
      anagram[chHay[i]]--;
      if(anagram[chHay[i]] >= 0){
        length--;
      }else{
        more++;
      }
    }
    
    if(length == 0 && more == 0){
      if(compare(chNeedle, chHay, 0)){
        return 0;
      }
    }
    
    for(int i = 1; i <= chHay.length - len; i++){
      char chStart = chHay[i - 1];
      char chEnd = chHay[i + len - 1];
      
      anagram[chStart]++;
      if(anagram[chStart] <= 0){
        more--;
      }else{
        length++;
      }
      
      anagram[chEnd]--;
      if(anagram[chEnd] >= 0){
        length--;
      }else{
        more++;
      }

      if(length == 0 && more == 0){
        if(compare(chNeedle, chHay, i)){
          return i;
        }
      }
    }

    return -1;
  }
  
  private static boolean compare(char[] needle, char[] hay, int start){
    for(int i = start; i < start + needle.length; i++){
      int j = i - start;
      if(needle[j] != hay[i]) return false;
    }
    return true;
  }

}
