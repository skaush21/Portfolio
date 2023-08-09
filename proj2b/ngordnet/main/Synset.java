package ngordnet.main;

import java.util.Set;

/** Initializes the synset and creates an empty synset. */
public class Synset {
    public int id;
    public Set<String> synonym;
    public String definition;

    public Synset(int id, Set<String> synonym, String definition) {
        this.id = id;
        this.synonym = synonym;
        this.definition = definition;
    }


}


