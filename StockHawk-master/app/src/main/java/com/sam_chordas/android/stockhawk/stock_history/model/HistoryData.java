package com.sam_chordas.android.stockhawk.stock_history.model;

import java.util.List;

/**
 * Created by DELL on 7/11/2016.
 * This Class is used for Gson library
 */
public class HistoryData {
    public HistoryData() {
    }

    Query query;
    public List<HistoryQuote> getQuotes(){
        return this.query.results.quote;
    }
}
