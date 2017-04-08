package org.richardqiao.algorithm;

import java.util.*;

public class Strstr {

  public static void main(String[] args) {
    System.out.println(strstr("abcde", "ef"));
  }

  private static int strstr(String hay, String needle){
    char[] chHay = hay.toCharArray();
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

      if(length == 0 && more == 0 && !flag[i]){
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
