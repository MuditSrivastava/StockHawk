package com.sam_chordas.android.stockhawk.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.Constants;
import com.sam_chordas.android.stockhawk.ui.graph.MyGraphActivity;

/**
 * Created by DELL on 7/12/2016.
 */
public class StockWidgetProvider extends AppWidgetProvider {
    private static final String LOG_TAG = StockWidgetProvider.class.getSimpleName();
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // update all StockWidgets
        for (int widgetId: appWidgetIds){
            Intent intent = new Intent(context,StockWidgetRemoteViewService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);

            // create Widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setRemoteAdapter(R.id.widget_list_view,intent);

            // Open Graph on List Item click
            Intent intentStockGraph = new Intent(context,MyGraphActivity.class);
            PendingIntent pendingIntent = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(intentStockGraph)
                    .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.widget_list_view,pendingIntent);

            // Update Widget on HomeScreen
            appWidgetManager.updateAppWidget(widgetId,views);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Receive Broadcast About Stock Data Update
        super.onReceive(context, intent);
        if (intent.getAction().equals(Constants.ACTION_STOCK_UPDATE)){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,getClass()));
            // update All Widgets
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.widget_list_view);
        }
    }
}
