package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;
import java.util.ArrayList;

/** creates the graph */
public class HistoryHandler extends NgordnetQueryHandler {
    private final NGramMap ngm;
    public HistoryHandler(NGramMap ngm) {
        this.ngm = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<TimeSeries> ts = new ArrayList<>();
        ArrayList<String> label = new ArrayList<>(q.words());

        for (String word: q.words()) {
            TimeSeries ts2 = ngm.weightHistory(word, q.startYear(), q.endYear());
            ts.add(ts2);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(label, ts);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;

    }


}
