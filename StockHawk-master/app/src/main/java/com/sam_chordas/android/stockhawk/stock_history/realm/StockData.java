package com.sam_chordas.android.stockhawk.stock_history.realm;

import com.sam_chordas.android.stockhawk.data.Constants;
import com.sam_chordas.android.stockhawk.stock_history.model.HistoryQuote;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DELL on 7/11/2016.
 * Thanks to Ravi Tamada for Awesome Blog about Realm database integration
 *http://www.androidhive.info/2016/05/android-working-with-realm-database-replacing-sqlite-core-data/
 */
public class StockData extends RealmObject {
    private static final String LOG_TAG = StockData.class.getSimpleName();
    @PrimaryKey
    private String stock_symbol;
    private RealmList<HistoryQuote> stockDataList = new RealmList<>();

    public StockData(String stock_symbol, List<HistoryQuote> quoteList) {
        try {
            this.stock_symbol = stock_symbol;
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_TEMPLATE, Locale.ENGLISH);
            for (HistoryQuote quote: quoteList ) {
                // set Actual Date for Graph sorting
                Date date = dateFormat.parse(quote.getDate());
                quote.setActualDate(date);
                this.stockDataList.add(quote);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public StockData() {
    }
    public int size(){
        return stockDataList.size();
    }

    public RealmList<HistoryQuote> getStockDataList() {
        return stockDataList;
    }
}
