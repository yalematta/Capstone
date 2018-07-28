package com.yalematta.podable.ui.podcasts.episodes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.timqi.sectorprogressview.SectorProgressView;
import com.yalematta.podable.R;
import com.yalematta.podable.data.models.podcast.Episode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yalematta on 7/26/18.
 */

public class DownloadEpisode {

    private Context context;
    private TextView tvSize;
    private ImageView ivDownload;
    private SectorProgressView progressBar;
    private static final String TAG = "Download Episode";
    private String downloadUrl = "", downloadFileName = "";

    public DownloadEpisode(Context context, ImageView ivDownload, TextView tvSize, SectorProgressView progressBar, Episode episode, String podcastSlug, String downloadUrl) {
        this.tvSize = tvSize;
        this.context = context;
        this.ivDownload = ivDownload;
        this.progressBar = progressBar;
        this.downloadUrl = downloadUrl;

        downloadFileName = podcastSlug + "_" + episode.getId().toString(); // Create file name
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingEpisode().execute();
    }

    private class DownloadingEpisode extends AsyncTask<Void, Integer, Void> implements View.OnClickListener {

        int lenghtOfFile;
        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "Download Started");
            progressBar.setOnClickListener(this);
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    tvSize.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    ivDownload.setVisibility(View.VISIBLE);
                    ivDownload.setImageResource(R.drawable.ic_play);
                    Log.e(TAG, "Download Completed"); // If Download completed
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

                lenghtOfFile = c.getContentLength();

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
                long total = 0;
                int len1 = 0;

                while ((len1 = is.read(buffer)) > 0) {
                    total += len1; // total = total + len1
                    publishProgress((int)((total*100)/lenghtOfFile));
                    fos.write(buffer, 0, len1); // Write new file

                    if (isCancelled()) {
                        if (outputFile.exists()) {
                            outputFile.delete();
                            Log.e(TAG, "File Deleted");
                        }
                        fos.close();
                        is.close();
                        return null;
                    }
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

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // Making progress bar visible
            tvSize.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            // Updating progress bar value
            progressBar.setPercent(values[0]);

            // Updating percentage value
            tvSize.setText(String.valueOf(values[0]) + "%");
        }

        @Override
        public void onClick(View view) {
            tvSize.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            ivDownload.setVisibility(View.VISIBLE);
            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(TAG, "Download Cancelled");
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