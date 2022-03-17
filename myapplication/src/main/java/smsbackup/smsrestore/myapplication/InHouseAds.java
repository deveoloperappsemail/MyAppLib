package smsbackup.smsrestore.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class InHouseAds {

    public static void getInHouseAds(Context context){
        getData(context);
    }

    private static void getData(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context); // this = context

        StringRequest getRequest = new StringRequest(Request.Method.GET, context.getString(R.string.base_url) + "adsinhousefetch.php",
                response -> {
                    // display response
                    Log.d("Response1", response.toString());
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            SharedPrefUtils.saveData(context, Constants.ADS_TITLE, jsonObject.getString("adstitle"));
                            SharedPrefUtils.saveData(context, Constants.ADS_SUB_TEXT, jsonObject.getString("adssubtext"));
                            SharedPrefUtils.saveData(context, Constants.ADS_RATING, jsonObject.getString("adsrating"));
                            SharedPrefUtils.saveData(context, Constants.ADS_IMAGE, context.getString(R.string.base_url)+"uploads/"+jsonObject.getString("adsimage"));
                            SharedPrefUtils.saveData(context, Constants.ADS_ICON, context.getString(R.string.base_url)+"uploads/"+jsonObject.getString("adsicon"));
                            SharedPrefUtils.saveData(context, Constants.ADS_VIDEO, context.getString(R.string.base_url)+"uploads/"+jsonObject.getString("adsvideo"));
                            SharedPrefUtils.saveData(context, Constants.IS_VIDEO, jsonObject.getBoolean("isVideo"));
                            SharedPrefUtils.saveData(context, Constants.PACKAGE_NAME, jsonObject.getBoolean("packagename"));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    try {
                        Log.d("Response1","Error.Response"+ error.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }
}
