package com.test.contact_jenius.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

public class Functionality extends AppCompatActivity {
    public static void storeObjectToSharedPrefObj(final Context context, Object object, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.test.contact_jenius", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(key, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getObjectFromSharedPrefObj(Context context, Class<GenericClass> classType, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.test.contact_jenius", 0);
        if (sharedPreferences.contains(key)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(key, ""), classType);
        }

        return null;
    }
}
