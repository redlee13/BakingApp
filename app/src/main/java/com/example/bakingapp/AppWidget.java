package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.bakingapp.UI.MainActivity;

import static com.example.bakingapp.UI.ListDetailActivity.PREF_WIDGET;
import static com.example.bakingapp.UI.ListDetailActivity.PREF_WIDGET_BODY;
import static com.example.bakingapp.UI.ListDetailActivity.PREF_WIDGET_NAME;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intent = new Intent(context, MainActivity.class);


        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_WIDGET, 0);
        String name = sharedPreferences.getString(PREF_WIDGET_NAME, context.getString(R.string.add_data));
        String ingredients = sharedPreferences.getString(PREF_WIDGET_BODY, "FFFFF");


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

        views.setTextViewText(R.id.widget_title, name);
        views.setTextViewText(R.id.widget_ing_list, ingredients);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_ing_list);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }

    }
    public static void update(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, AppWidget.class));
        for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

