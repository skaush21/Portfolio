package ngordnet.graphs;
import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.WordNet;
import ngordnet.proj2b_testing.AutograderBuddy;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class GraphTest {
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";


    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        System.out.println(wn.hyponyms("change"));
    }

    @Test
    public void testHyponymsSimple2() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(List.of("antihistamine", "actifed"), wn.hyponyms("antihistamine"));
    }

    @Test
    public void testHyponyms() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("irritation"); // <-- replace with the appropriate list of words!

        NgordnetQuery nq = new NgordnetQuery(words, 1470, 2019, 7);
        String actual = studentHandler.handle(nq);
        String expected = "[annoyance, annoying, discomfort, excitation, impatience, irritation, temper]";
        assertEquals(expected, actual);

    }

}
