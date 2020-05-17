package com.pramu.mymovies.TV;

import android.preference.PreferenceActivity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ModelTV extends ViewModel {
    private static final String API_KEY = "b67c000b9ab21b8a8cd1ee976dcc11af";
    private MutableLiveData<ArrayList<MyTV>> listTV = new MutableLiveData<>();

    void setTv() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MyTV> listItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        MyTV TVItem = new MyTV(tv);
                        listItem.add(TVItem);
                    }
                    listTV.postValue(listItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }

        });
    }

    LiveData<ArrayList<MyTV>> getTV() {
        return listTV;
    }

}
