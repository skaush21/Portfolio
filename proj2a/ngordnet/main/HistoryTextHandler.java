package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import java.util.ArrayList;
import java.util.List;

/** Creates a UI that read a word given by the user and outputs its result. */
public class HistoryTextHandler extends NgordnetQueryHandler {
    private final NGramMap ngm;
    /* Constructor for the text handler map. */
    public HistoryTextHandler(NGramMap map) {

        this.ngm = map;
    }
    /* creates the UI for the history text. */
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<String> w = new ArrayList<String>();
        String response = "";
        for (String word : words) {
            TimeSeries t = ngm.weightHistory(word, startYear, endYear);
            response += word + ": " + t.toString() + "\n";
            w.add(response);
        }
        return response;
    }
}
