package com.problem.listing;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.problem.listing.utils.Utility;

public class DealsFetcherService extends IntentService {
    public static final String ACTION_DEAL_DATA = "com.problem.listing.action.DEAL_DATA";
    private static final String ACTION_GET_DEAL_DATA = "com.problem.listing.action.GET_DEAL_DATA";

    private static final String EXTRA_URL = "com.problem.listing.extra.URL";

    public DealsFetcherService() {
        super("DealsFetcherService");
    }

    /**
     * Starts this service to get deal data with the given parameters.
     */
    public static void startActionGetDealData(Context context, String url) {
        Intent intent = new Intent(context, DealsFetcherService.class);
        intent.setAction(ACTION_GET_DEAL_DATA);
        intent.putExtra(EXTRA_URL, url);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_DEAL_DATA.equals(action)) {
                String url = intent.getStringExtra(EXTRA_URL);
                String content = getDealsData(url);
                Intent mainActivityIntent = new Intent();
                mainActivityIntent.setAction(ACTION_DEAL_DATA);
                mainActivityIntent.putExtra("data", content);
                mainActivityIntent.putExtra("status", content != null);
                sendBroadcast(mainActivityIntent);
            }
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private String getDealsData(String urlString) {
        //This should be the implementation to get the data from the server,
        //but since url is not availble , we are going to use a local file
//        HttpURLConnection mHttpsURLConnection;
//        urlString = "";
//        try {
//            URL url = new URL(urlString);
//            mHttpsURLConnection = (HttpURLConnection) url.openConnection();
//
//            mHttpsURLConnection.setReadTimeout(7000);
//            mHttpsURLConnection.setConnectTimeout(7000);
//            mHttpsURLConnection.setRequestMethod("POST");
//            mHttpsURLConnection.setDoOutput(true);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(mHttpsURLConnection.getInputStream()));
//            String line = "";
//            StringBuilder responseOutput = new StringBuilder();
//            while((line = br.readLine()) != null ) {
//                responseOutput.append(line);
//            }
//            br.close();
//
//            return responseOutput.toString();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

        String content = Utility.getJSONFromFile(getApplicationContext());

        return content;
    }
}
