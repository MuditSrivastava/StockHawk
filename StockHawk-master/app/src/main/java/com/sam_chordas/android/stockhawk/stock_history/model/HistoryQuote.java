package com.sam_chordas.android.stockhawk.stock_history.model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import io.realm.RealmObject;

/**
 * Created by DELL on 7/11/2016.
 */
public class HistoryQuote extends RealmObject {
    public HistoryQuote() {
    }

    public String Symbol;
    public String Date;
    public String Close;
    // this will ignore actualDate in Gson conversion
    @Expose(serialize=false,deserialize = false)
    java.util.Date actualDate;

    public String getSymbol() {
        return Symbol;
    }

    public String getDate() {
        return Date;
    }

    public String getClose() {
        return Close;
    }

    public java.util.Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(java.util.Date actualDate) {
        this.actualDate = actualDate;
    }
}
