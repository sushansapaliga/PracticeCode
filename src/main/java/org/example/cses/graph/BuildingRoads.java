package org.example.cses.graph;

import java.io.*;
import java.util.*;

public class BuildingRoads {
  public static void main(String[] args) throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/cses/graph/BuildingRoads/test_input.txt"));

    String[] input = br.readLine().split(" ");
    int verticesCount = Integer.parseInt(input[0])+1;
    int edgeCount = Integer.parseInt(input[1]);

    List<List<Integer>> vertices = new ArrayList<>();
    for(int i = 0; i < verticesCount; i++) {
      vertices.add(new ArrayList<>());
    }
    boolean[] visited = new boolean[verticesCount];

    for(int i = 0; i < edgeCount; i++) {
      input = br.readLine().split(" ");
      vertices.get(Integer.parseInt(input[0])).add(Integer.parseInt(input[1]));
      vertices.get(Integer.parseInt(input[1])).add(Integer.parseInt(input[0]));
    }
    LinkedList<Integer> result = new LinkedList<>();
    Queue<Integer> q = new LinkedList<>();
    int curr;
    for(int i = 1; i < verticesCount; i++) {
      if(!visited[i]) {
        q.add(i);
        visited[i] = true;
        while(!q.isEmpty()) {
          curr = q.poll();
          for(int next : vertices.get(curr)) {
            if(!visited[next]) {
              visited[next] = true;
              q.add(next);
            }
          }
        }
        result.add(i);
      }
    }

    PrintWriter out = new PrintWriter(System.out);
    out.println(result.size()-1);
    int count = 0;
    for(int res : result) {
      if(count != 0) {
        out.print(' ');
        out.println(res);
      }
      if(count != result.size()-1) out.print(res);
      count++;
    }
    out.close();
  }
}
