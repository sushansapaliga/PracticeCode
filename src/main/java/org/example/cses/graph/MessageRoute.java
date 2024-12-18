package org.example.cses.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MessageRoute {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/cses/graph/MessageRoute/test_input.txt"));
    String[] input = br.readLine().split(" ");
    int verticesCount = Integer.parseInt(input[0]);
    int edgesCount = Integer.parseInt(input[1]);
    Queue<Integer> q = new LinkedList<>();
    int[] visited = new int[verticesCount+1];
    List<List<Integer>> edges = new ArrayList<>();

    for(int i = 0; i <= verticesCount; i++) {
      edges.add(new ArrayList<>());
      visited[i] = 0;
    }

    for(int i = 0; i < edgesCount; i++) {
      input = br.readLine().split(" ");
      edges.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
      edges.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
    }

    q.add(1);
    visited[0] = -1;
    visited[1] = 0;
    while(!q.isEmpty()) {
      int curr = q.poll();
      for(int next : edges.get(curr)) {
        if(visited[next] == 0 && next != 1) {
          visited[next] = curr;
          if(next == verticesCount) {
            int count = 1;
            List<Integer> resultList = new LinkedList<>();
            resultList.add(next);
            int prev = visited[next];
            while(visited[prev] != -1) {
              resultList.add(prev);
              prev = visited[prev];
              count++;
            }
            Collections.reverse(resultList);
            StringBuilder sb = new StringBuilder();
            for(int i : resultList) {
              sb.append(i);
              sb.append(' ');
            }
            sb.setLength(sb.length()-1);
            System.out.println(count);
            System.out.println(sb);
            return;
          }
          q.add(next);
        }
      }
    }
    System.out.println("IMPOSSIBLE");
  }
}
