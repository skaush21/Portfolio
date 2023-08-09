package ngordnet.main;

import java.util.*;
import java.util.ArrayList;

/**
 * Maps the words for hyp and syn.
 * @source https://www.geeksforgeeks.org/java-program-to-find-the-occurrence-of-words-in-a-string-using-hashmap/
 *         https://stackoverflow.com/questions/13627151/count-all-vertices-in-a-graph
 *         https://www.baeldung.com/java-depth-first-search
 *         https://stackoverflow.com/questions/12455737/
 *         https://www.geeksforgeeks.org/printing-pre-and-post-visited-times-in-dfs-of-a-graph/
 *         https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
 *         https://docs.google.com/presentation/d/11iacyiFt3QUrzo1yAU_xoXAjGTH4UzV7o6CR04HYRrI/edit#slide=id.g54593997ea_2_862
 */
public class Graph {
    private HashMap<String, HashSet<String>> idm;

    public Graph() {
        idm = new HashMap<>();
    }

    public void addEdge(String key, String value){
        if (idm.containsKey(key)) {
            HashSet<String> edge = idm.get(key);
            edge.add(value);
            idm.put(key, edge);
        }
        else {
            HashSet<String> edge = new HashSet<>();
            edge.add(value);
            idm.put(key, edge);
        }
    }
    public List<String> path(String x){
        List<String> hashlst = new ArrayList<>();
        Stack<String> s = new Stack<>();
        s.push(x);
        while (!s.isEmpty()) {
            String mostRecent = s.pop();
            hashlst.add(mostRecent);
            if (idm.containsKey(mostRecent)) {
                for (String hey: idm.get(mostRecent)) {
                    s.push(hey);
                }
            }
        }
        return hashlst;
    }
}