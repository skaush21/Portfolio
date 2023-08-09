package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import java.util.List;
import java.util.*;
/** creates the UI. */
public class HyponymsHandler extends NgordnetQueryHandler {
    private final NGramMap ngm;
    private final WordNet wordnet;
    /** creates the map and word. */
    public HyponymsHandler(NGramMap map, WordNet net) {
        wordnet = net;
        ngm = map;
    }

    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();


        TreeSet<String> notK = new TreeSet<>();
        TreeSet<String> K = new TreeSet<>();
        TreeMap<Double, TreeSet<String>> mapWords = new TreeMap<>();

        if (k > 0) {
            TreeSet<String> multi = wordnet.multhyp(words);
            for (String word : multi) {
                double sum = 0;
                Collection<Double> ts = ngm.countHistory(word, startYear, endYear).values();
                for (double i : ts) {
                    sum += i;
                }
                if (mapWords.containsKey(sum)) {
                    mapWords.get(sum).add(word);
                } else {
                    TreeSet<String> x = new TreeSet<>();
                    x.add(word);
                    if (sum > 0.0) {
                        mapWords.put(sum, x);
                    }
                }
            }

            for (int i = 0; i < k; i++) {
                if (mapWords.size() > 0) {
                    Double key2 = mapWords.lastKey();
                    if (key2 == 0.0) {
                        continue;
                    }
                    TreeSet<String> a = mapWords.get(key2);
                    String key1 = a.pollFirst();
                    if (a.size() == 0) {
                        mapWords.remove(key2);
                    }
                    K.add(key1);
                }
            }
            return K.toString();
        } else {
            TreeSet<String> m = wordnet.multhyp(words);
            for (String x : m) {
                notK.add(x);
            }
            return notK.toString();
        }
    }
}
