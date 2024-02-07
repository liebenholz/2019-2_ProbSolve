import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

class Main {
  
  public static int max(Turtle[] a) {

    int[] prev = new int[a.length+1];
    int[] dp = new int[a.length+1];
    

    for (int i = 0; i< prev.length; i++) {
      prev[i] = Integer.MAX_VALUE;
    }
    
    prev[1] = a[0].w;
    dp[0]= Integer.MAX_VALUE;
    int max = 1;

    for (int i = 1; i < a.length; i++) {
      for (int j = 1; j < dp.length; j++){
        int dontuse =  prev[j];
        int use = (a[i].r >= prev[j-1]) ? prev[j-1] + a[i].w : Integer.MAX_VALUE; 
        dp[j] = Math.min (use, dontuse);
        if (dp[j] != Integer.MAX_VALUE){
          max = Math.max(max, j);
        }
      }
      int[] tmp = prev;
      prev = dp;
      dp = tmp;
    }
    return max;
  }

  private static class Turtle implements Comparable<Turtle> {
    private int w;
    private int s;
    private int r;

    @Override
    public int compareTo(Turtle t) {
      if (s < t.s) return -1;
      if (s > t.s) return 1;
      else return 0;
    }
  } 

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Turtle> turtles = new ArrayList<>();
    int i = 0;
    while (sc.hasNextInt()) {
      Turtle t = new Turtle();
      t.w = sc.nextInt();
      t.s = sc.nextInt();
      t.r = t.s - t.w;
      turtles.add(t);
      i++;
    }
    Collections.sort(turtles);
    Turtle[] a = new Turtle[turtles.size()];
    turtles.toArray(a);
    System.out.println(max(a));
  }
}