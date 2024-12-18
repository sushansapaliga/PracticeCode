package org.example.cses.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Labyrinth {
  static class Pair {
    int i, j;

    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    int row = Integer.parseInt(input[0]);
    int col = Integer.parseInt(input[1]);
    boolean[][] matrix = new boolean[row][col];
    Pair A = new Pair(-1, -1);
    Pair B = new Pair(-1, -1);

    for(int i = 0; i < row; i++) {
      char[] colArray = br.readLine().toCharArray();
      for(int j = 0; j < col; j++) {
        if(colArray[j] == '#') matrix[i][j] = false;
        else {
          matrix[i][j] = true;
          if(colArray[j] == 'A') A = new Pair(i, j);
          else if(colArray[j] == 'B') B = new Pair(i, j);
        }
      }
    }

    int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    char[] dirChar = new char[]{'D', 'R', 'U', 'L'};
    boolean[][] visited = new boolean[row][col];
    char[][] path = new char[row][col];
    int nI, nJ;
    Queue<Pair> q = new LinkedList<>();
    q.add(A);

    outer:
    while(!q.isEmpty()) {
      Pair p = q.poll();

      for(int k = 0; k < dir.length; k++) {
        nI = p.i + dir[k][0];
        nJ = p.j + dir[k][1];

        if(nI >= 0 && nI < row && nJ >= 0 && nJ < col && matrix[nI][nJ] && !visited[nI][nJ]) {
          visited[nI][nJ] = true;
          path[nI][nJ] = dirChar[k];
          if(B.i == nI && B.j == nJ) break outer;
          q.add(new Pair(nI, nJ));
        }
      }
    }

    if(!visited[B.i][B.j]) System.out.println("NO");
    else {
      StringBuilder sb = new StringBuilder();
      nI = B.i;
      nJ = B.j;
      while(nI != A.i || nJ != A.j) {
        sb.append(path[nI][nJ]);
        for(int k = 0; k < dir.length; k++) {
          if(dirChar[k] == path[nI][nJ]) {
            nI -= dir[k][0];
            nJ -= dir[k][1];
            break;
          }
        }
      }
      sb.reverse();
      System.out.println("YES");
      System.out.println(sb.length());
      System.out.println(sb);
    }
  }
}
