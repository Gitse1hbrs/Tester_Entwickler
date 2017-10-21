package se_01;

import java.util.HashMap;
import java.util.ArrayList;

public class Project {
  private HashMap<String, ArrayList<String>> nodes;

  public Project(String[][] nodes) {
    this.nodes = convertToMap(nodes);
  }

  private HashMap<String, ArrayList<String>> convertToMap(String[][] nodes) {
    HashMap<String, ArrayList<String>> map = new HashMap<>();

    for(String[] node : nodes) {
      if(map.containsKey(node[1])) {
        map.get(node[1]).add(node[0]);
      } else {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(node[0]);

        map.put(node[1], arrayList);
      }
    }

    return map;
  }

  public boolean isWellSorted(String[] sequence) {
    if(sequence.length <= 1) {
      return false;
    }
    ArrayList<String> completedTasks = new ArrayList<>();
    for(String node : sequence) {
      if(completedTasks.contains(node)) {
        return false;
      }
      completedTasks.add(node);
      if(this.nodes.containsKey(node)) {
        for(String value : this.nodes.get(node)) {
          if(!completedTasks.contains(value)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public String toString() {
    String toString = "";
    toString += "-----\n";
    for(String key : this.nodes.keySet()) {
      toString += key + " depends on: ";
      for(String value : this.nodes.get(key)) {
        toString += value + " ";
      }
      toString += "\n";
    }
    toString += "-----\n";

    return toString;
  }
}
