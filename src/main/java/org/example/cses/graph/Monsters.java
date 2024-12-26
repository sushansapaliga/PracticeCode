package org.example.cses.graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Monsters {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/cses/graph/Monsters/test_input.txt"));
    String[] input = br.readLine().split(" ");
    int rows = Integer.parseInt(input[0]), cols = Integer.parseInt(input[1]);
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    char[] routeDir = new char[]{'D', 'R', 'U', 'L'};

    int[][] monsterGrid = new int[rows][cols];
    char[][] playerGrid = new char[rows][cols];
    boolean[][] grid = new boolean[rows][cols];
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        monsterGrid[i][j] = -1;
        playerGrid[i][j] = '.';
      }
    }

    Queue<int[]> monsterQ = new LinkedList<>();
    Queue<int[]> playerQ = new LinkedList<>();
    for(int i = 0; i < rows; i++) {
      char[] columnInput = br.readLine().toCharArray();
      for(int j = 0; j < cols; j++) {
        if(columnInput[j] == '.'){
          grid[i][j] = true;
        } else if(columnInput[j] == 'M') {
          grid[i][j] = true;
          monsterQ.add(new int[]{i, j, 1});
          monsterGrid[i][j] = 0;
        } else if(columnInput[j] == 'A') {
          grid[i][j] = true;
          playerQ.add(new int[]{i, j, 1});
          playerGrid[i][j] = 'A';
          if(i == rows-1 || i == 0 || j == cols-1 || j == 0) {
            System.out.println("YES");
            System.out.println(0);
            return;
          }
        } else {
          grid[i][j] = false;
        }
      }
    }

    // monsters run
    while(!monsterQ.isEmpty()) {
      int[] curr = monsterQ.poll();
      for(int[] dir : dirs) {
        int nextI = curr[0] + dir[0], nextJ = curr[1] + dir[1];
        if(nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols && grid[nextI][nextJ] && monsterGrid[nextI][nextJ] == -1) {
          monsterQ.add(new int[]{nextI, nextJ, curr[2]+1});
          monsterGrid[nextI][nextJ] = curr[2];
        }
      }
    }

    // player run
    while(!playerQ.isEmpty()) {
      int[] curr = playerQ.poll();
      for(int k = 0; k < dirs.length; k++) {
        int nextI = curr[0] + dirs[k][0], nextJ = curr[1] + dirs[k][1];
        if(
          nextI >= 0 && nextI < rows && nextJ >= 0 && nextJ < cols &&
            grid[nextI][nextJ] && (monsterGrid[nextI][nextJ] > curr[2] || monsterGrid[nextI][nextJ] == -1) &&
            playerGrid[nextI][nextJ] == '.'
        ) {
          playerGrid[nextI][nextJ] = routeDir[k];
          if(nextI == 0 || nextI == rows-1 || nextJ == 0 || nextJ == cols-1) {
            StringBuilder sb = new StringBuilder();
            while(playerGrid[nextI][nextJ] != 'A') {
              sb.append(playerGrid[nextI][nextJ]);
              for(int i = 0; i < dirs.length; i++) {
                if(playerGrid[nextI][nextJ] == routeDir[i]) {
                  nextI -= dirs[i][0];
                  nextJ -= dirs[i][1];
                  break;
                }
              }
            }
            sb.reverse();
            System.out.println("YES");
            System.out.println(sb.length());
            System.out.println(sb);
            return;
          }
          playerQ.add(new int[]{nextI, nextJ, curr[2]+1});
        }
      }
    }
    System.out.println("NO");
  }
}
