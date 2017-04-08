package org.richardqiao.algorithm;

import java.util.*;

public class Strstr {

  public static void main(String[] args) {
    System.out.println(strstr("abadef", "ade"));
  }

  private static int strstr(String haystack, String needle){
    if(haystack.length() < needle.length()) return -1;
    if(haystack.length() == needle.length()){
      if(haystack.equals(needle)) return 0;
      return -1;
    }
    char[] chHay = haystack.toCharArray();
    char[] chNeedle = needle.toCharArray();
    int len = chNeedle.length;
    boolean[] flag = new boolean[chHay.length];
    Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
    for(int i = 0; i < chNeedle.length - 1; i++){
      if(!map.containsKey(chNeedle[i])){
        map.put(chNeedle[i], new HashSet<Character>());
      }
      map.get(chNeedle[i]).add(chNeedle[i + 1]);
    }
    for(int i = chNeedle.length - 1; i > 0 && i < chHay.length; i++){
      char chPre = chHay[i - 1], chSuf = chHay[i];
      if(!map.containsKey(chPre) || !map.get(chPre).contains(chSuf)){
        flag[i - len + 1] = true;
      }
    }
    
    
    for(int i = 0, count = 0; i < flag.length; i++){
      if(flag[i]){
        count = len - 1;
      }
      if(count > 0){
        flag[i] = true;
        count--;
      }
    }
    
    int[] anagram = new int[128];
    for(char ch: chNeedle){
      anagram[ch]++;
    }

    int length = len, more = 0;
    for(int i = 0; i < chHay.length; i++){
      if(i < chNeedle.length){
        anagram[chHay[i]]--;
        if(anagram[chHay[i]] >= 0){
          length--;
        }else{
          more++;
        }
      }else{
        int j = i - len;
        char chStart = chHay[j];
        char chEnd = chHay[i];
        
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
      }
      if(length == 0 && more == 0){
        if(compare(chNeedle, chHay, i - len + 1)){
          return i - len + 1;
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
