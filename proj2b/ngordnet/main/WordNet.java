package ngordnet.main;

import java.util.*;
import java.util.List;
import edu.princeton.cs.algs4.In;

/** Implementation of the wordnet. */
public class WordNet {
    private final Graph graph;
    private final HashMap<String, String> synsets;
    private final HashMap<String, HashSet<String>> hashwords;
    private Set<String> w;

    /* takes files anc converts them to words. */
    public WordNet(String synsetsFileName, String hyponymFileName) {
        graph = new Graph();
        In synsetIn = new In(synsetsFileName);
        In hyponymIn = new In(hyponymFileName);

        synsets = new HashMap<>();
        hashwords = new HashMap<>();
        /** while file is not empty, iterate until there is no words left. */
        while (!(synsetIn.isEmpty())) {
            String[] row = synsetIn.readLine().split(",");
            String id = row[0];
            String words = row[1];
            synsets.put(id, words);
            //all keys are words and all values are ids
            String[] addwords = row[1].split("\s");
            for (String word : addwords) {
                if (hashwords.containsKey(word)) {
                    hashwords.get(word).add(id);
                } else {
                    HashSet<String> ids = new HashSet<>();
                    ids.add(id);
                    hashwords.put(word, ids);
                }
            }
        }
        /** while file is not empty, iterate until there is no words left. */
        while (!hyponymIn.isEmpty()) {
            String[] row2 = hyponymIn.readLine().split(",");
            for (String id : row2) {
                if (!id.equals(row2[0])) {
                    graph.addEdge(row2[0], id);
                }
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return w.contains(noun);
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return w;
    }

    /** returns a list of all hyponyms.  */
    public TreeSet<String> hyponyms(String word) {
        if (hashwords.containsKey(word)) {
            HashSet<String> ids = hashwords.get(word);
            ArrayList<String> idlst = new ArrayList<>();
            TreeSet<String> wordslst = new TreeSet<>();
            for (String id : ids) {
                List<String> x = graph.path(id);

                for (String i : x) {
                    idlst.add(i);
                }
            }
            for (String id : idlst) {
                String[] synsetid = synsets.get(id).split(" ");
                for (String x : synsetid) {
                    wordslst.add(x);
                }
            }
            return wordslst;
        } else {
            return new TreeSet<String>();
        }
    }
    /** returns a list of multiple hyponyms. */
    public TreeSet<String> multhyp(List<String> words) {
        if (!words.isEmpty()) {
            TreeSet<String> list1 = hyponyms(words.get(0));
            for (String word : words) {
                TreeSet<String> list2 = hyponyms(word);
                list1.retainAll(list2);
            }
            return list1;
        }
        return new TreeSet<>();
    }
}

