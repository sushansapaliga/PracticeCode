package org.example.cses.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoundTrip {

  static List<List<Integer>> edges;
  static boolean[] visited;
  static List<Integer> result;

  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/cses/graph/RoundTrip/test_input.txt"));
    String[] input = br.readLine().split(" ");

    int vertexCount = Integer.parseInt(input[0])+1;
    int edgeCount = Integer.parseInt(input[1]);

    visited = new boolean[vertexCount];
    edges = new ArrayList<>();

    for(int i = 0; i < vertexCount; i++) edges.add(new LinkedList<>());
    for(int i = 0; i < edgeCount; i++) {
      input = br.readLine().split(" ");
      edges.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
      edges.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
    }

    result = new LinkedList<>();

    for(int i = 1; i < vertexCount; i++) {
      if(!visited[i] && dfs(i, 0, 0)) {
        int count = 0, firstNum = 0;
        StringBuilder sb = new StringBuilder();
        for(int next : result) {
          sb.append(next);
          sb.append(' ');
          count++;

          if(firstNum == 0) firstNum = next;
          else if(firstNum == next) break;
        }

        System.out.println(count);
        System.out.println(sb);
        return;
      }
    }
    System.out.println("IMPOSSIBLE");
  }

  public static boolean dfs(int curr, int level, int parent) {
    visited[curr] = true;
    for(int next : edges.get(curr)) {
      if(next == parent) continue;
      if(visited[next] && level > 1) {
        result.add(next);
        result.add(curr);
        return true;
      } else if(!visited[next] && dfs(next, level+1, curr)) {
        result.add(curr);
        return true;
      }
    }
    return false;
  }
}
