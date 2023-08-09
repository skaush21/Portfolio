package ngordnet.proj2b_testing;

import ngordnet.hugbrowsermagic.HugNgordnetServer;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.HistoryHandler;
import ngordnet.main.HistoryTextHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.main.WordNet;
import ngordnet.ngrams.NGramMap;

public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(String wordFile, String countFile, String synsetFile, String hyponymFile) {
        HugNgordnetServer hns = new HugNgordnetServer();
        // wordFile = "./data/ngrams/top_49887_words.csv";
        // countFile = "./data/ngrams/total_counts.csv";

        //synsetFile = "./data/wordnet/synsets.txt";
        //hyponymFile = "./data/wordnet/hyponyms.txt";

        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wm = new WordNet(synsetFile, hyponymFile);

        hns.startUp();
        hns.register("history", new HistoryHandler(ngm));
        hns.register("historytext", new HistoryTextHandler(ngm));
        hns.register("hyponyms", new HyponymsHandler(ngm, wm));
        return new HyponymsHandler(ngm, wm);

    }
}