package org.example.cses.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BuildingTeams {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/cses/graph/BuildingTeams/test_input.txt"));
    String[] input = br.readLine().split(" ");

    int vertexCount = Integer.parseInt(input[0]) + 1;
    int edgeCount = Integer.parseInt(input[1]);

    List<List<Integer>> edges = new ArrayList<>(vertexCount);
    for(int i = 0; i < vertexCount; i++) edges.add(new LinkedList<>());

    int[] vertices = new int[vertexCount];

    for(int i = 0; i < edgeCount; i++) {
      input = br.readLine().split(" ");
      edges.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
      edges.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
    }

    Queue<Integer> q = new LinkedList<>();

    for(int i = 1; i < vertexCount; i++) {
      if(vertices[i] == 0) vertices[i] = 1;
      q.add(i);
      while(!q.isEmpty()) {
        int curr = q.poll();
        for(int next : edges.get(curr)) {
          if(vertices[next] == 0) {
            vertices[next] = vertices[curr] == 1 ? 2 : 1;
            q.add(next);
          } else {
            if(vertices[next] == vertices[curr]) {
              System.out.println("IMPOSSIBLE");
              return;
            }
          }
        }
      }
    }

    /**
    StringBuilder sb = new StringBuilder();
    for(int i = 1; i < vertexCount; i++) {
      sb.append(vertices[i]);
      sb.append(' ');
    }
    sb.setLength(sb.length() - 1);
    System.out.println(sb);
     */
    PrintWriter out = new PrintWriter(System.out);
    for(int i = 1; i < vertexCount; i++) {
      out.print(vertices[i]);
      out.print(' ');
    }
    out.close();
  }
}
