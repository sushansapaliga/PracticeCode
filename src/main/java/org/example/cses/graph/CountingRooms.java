package org.example.cses.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class CountingRooms {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input = br.readLine().split(" ");
    int row = Integer.parseInt(input[0]);
    int col = Integer.parseInt(input[1]);

    char[][] matrix = new char[row][];

    for(int i = 0; i < row; i++) {
      matrix[i] = br.readLine().toCharArray();
    }

    boolean[][] visited = new boolean[row][col];
    Stack<int[]> stack = new Stack<>();
    int count = 0;
    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        if(matrix[i][j] == '.' && !visited[i][j]) {
          stack.add(new int[]{i, j});

          while(!stack.isEmpty()) {
            int[] pair = stack.pop();
            int nI = pair[0], nJ = pair[1];
            if(nI < 0 || nJ < 0 || nI >= row || nJ >= col || matrix[nI][nJ] == '#' || visited[nI][nJ]) continue;

            visited[nI][nJ] = true;
            stack.add(new int[]{nI+1, nJ});
            stack.add(new int[]{nI, nJ+1});
            stack.add(new int[]{nI-1, nJ});
            stack.add(new int[]{nI, nJ-1});
          }
          count++;
        }
      }
    }

    System.out.println(count);
  }
}
