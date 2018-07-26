package com.yalematta.podable.ui.podcasts.episodes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.internal.Utils;

/**
 * Created by yalematta on 7/26/18.
 */

public class DownloadEpisode {

    private Context context;
    private static final String TAG = "Download Episode";
    private String downloadUrl = "", downloadFileName = "";

    public DownloadEpisode(Context context, Episode episode, String podcastSlug, String downloadUrl) {
        this.context = context;
        this.downloadUrl = downloadUrl;

        downloadFileName = podcastSlug + "_" + episode.getId().toString(); // Create file name
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingEpisode().execute();
    }

    private class DownloadingEpisode extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "Download Started");
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    Log.e(TAG, "Download Completed"); // If Download completed then change button text
                } else {
                    Log.e(TAG, "Download Failed"); // If download failed change button text
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "Download Again"); // Change button text again after 3sec
                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();
                // Change button text if exception occurs
                Log.e(TAG, "Download Failed");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "Download Again");
                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());
            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection(); // Open Url Connection
                c.setRequestMethod("GET"); // Set Request Method to "GET" since we are grtting data
                c.connect(); // Connect the URL Connection

                // If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());
                }

                // Get File if SD card is present
                if (new CheckForSDCard().isSDCardPresent()) {
                    apkStorage = new File(Environment.getExternalStorageDirectory() + "/" + context.getString(R.string.download_directory));
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                // If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName); // Create Output file in Main File

                // Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile); // Get OutputStream for NewFile Location

                InputStream is = c.getInputStream(); // Get InputStream for connection

                byte[] buffer = new byte[1024]; // Set buffer type
                int len1 = 0; // init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1); // Write new file
                }

                // Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                // Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }
    }

    public class CheckForSDCard {
        // Check If SD Card is present or not method
        public boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }
    }
}