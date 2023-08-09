package ngordnet.main;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class testwordnet {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(Set.of("antihistamine", "actifed"), wn.hyponyms("antihistamine"));
    }
}
