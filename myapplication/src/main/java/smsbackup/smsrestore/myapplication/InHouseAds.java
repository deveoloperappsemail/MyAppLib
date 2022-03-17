package smsbackup.smsrestore.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InHouseAds {

    private static ArrayList<InHouseModel> modelArrayList = new ArrayList<>();

    public static void getInHouseAds(Context context) {
        getData(context);
    }

    private static void getData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context); // this = context

        StringRequest getRequest = new StringRequest(Request.Method.GET, context.getString(R.string.base_url) + "adsinhousefetch.php",
                response -> {
                    // display response
                    Log.d("Response1", response.toString());
                    try {
                        modelArrayList.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            modelArrayList.add(new InHouseModel(jsonObject.getString("adstitle"), jsonObject.getString("adssubtext"),
                                    jsonObject.getString("adsrating"), context.getString(R.string.base_url) + "uploads/" + jsonObject.getString("adsicon")
                                    , context.getString(R.string.base_url) + "uploads/" + jsonObject.getString("adsimage"),
                                    context.getString(R.string.base_url) + "uploads/" + jsonObject.getString("adsvideo"),
                                    jsonObject.getBoolean("isVideo"), jsonObject.getString("packagename")));


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    try {
                        Log.d("Response1", "Error.Response" + error.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }

    public static ArrayList<InHouseModel> getModelAdsList() {
        return modelArrayList;
    }
}
