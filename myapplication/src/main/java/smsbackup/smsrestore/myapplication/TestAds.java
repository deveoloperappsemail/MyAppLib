package smsbackup.smsrestore.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestAds {
    public static void getTestAds(Context context) {
        if (InternetConnection.checkConnection(context)) {
            fetchData(context);
        } else {
            storeAds(context);
        }
    }

    private static void storeAds(Context context) {
        String appid = SharedPrefUtils.getStringData(context, Constants.APP_ID);
        if (appid == null) {
            SharedPrefUtils.saveData(context, Constants.APP_ID, "no");

        }
        String inters = SharedPrefUtils.getStringData(context, Constants.INTERSTIAL);
        if (inters == null) {
            SharedPrefUtils.saveData(context, Constants.INTERSTIAL, "no");

        }
        String banner = SharedPrefUtils.getStringData(context, Constants.BANNER);
        if (banner == null) {
            SharedPrefUtils.saveData(context, Constants.BANNER, "no");

        }
        String native1 = SharedPrefUtils.getStringData(context, Constants.NATIVE_AD);
        if (native1 == null) {
            SharedPrefUtils.saveData(context, Constants.NATIVE_AD, "no");

        }
        String openad = SharedPrefUtils.getStringData(context, Constants.OPEN_AD);
        if (openad == null) {
            SharedPrefUtils.saveData(context, Constants.OPEN_AD, "no");

        }

    }

    private static void fetchData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context); // this = context

           StringRequest getRequest = new StringRequest(Request.Method.GET, "https://adstesting.toptrendingappstudio.com/fetchtestads.php",
                response -> {
                    // display response
                    Log.d("Response", response.toString());
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            SharedPrefUtils.saveData(context, Constants.APP_ID, jsonObject.getString("appid"));
                            SharedPrefUtils.saveData(context, Constants.INTERSTIAL, jsonObject.getString("inter"));
                            SharedPrefUtils.saveData(context, Constants.BANNER, jsonObject.getString("banner"));
                            SharedPrefUtils.saveData(context, Constants.NATIVE_AD, jsonObject.getString("native"));
                            SharedPrefUtils.saveData(context, Constants.OPEN_AD, jsonObject.getString("openad"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        storeAds(context);
                    }
                },
                error -> {
                    Log.d("Error.Response", error.getMessage());
                    storeAds(context);
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }
}
