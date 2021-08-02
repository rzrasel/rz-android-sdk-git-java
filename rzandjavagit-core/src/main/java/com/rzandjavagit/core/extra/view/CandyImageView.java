package com.rzandjavagit.core.extra.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.LruCache;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.rzandjavagit.core.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CandyImageView extends AppCompatImageView {
    private Context context;
    private ImageView imageView;

    public CandyImageView(Context argContext) {
        super(argContext);
        context = argContext;
        imageView = this;
    }

    public CandyImageView(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        context = argContext;
        imageView = this;
    }

    public CandyImageView(Context argContext, AttributeSet argAttrs, int argDefStyle) {
        super(argContext, argAttrs, argDefStyle);
        context = argContext;
        imageView = this;
    }

    public CandyImageView setImageURL(String argURLString) {
        /*new ImageAsyncTask().setEventListener(new AsyncTaskEventListener() {
            @Override
            public void onPostExecute(Bitmap argResult) {
                imageView.setImageBitmap(argResult);
            }
        })
                .execute();*/
        new ImageAsyncTask().execute(argURLString);
        return this;
    }

    private class ImageAsyncTask extends AsyncTask<String, String, Bitmap> {
        private AsyncTaskEventListener asyncTaskEventListener;
        private HttpURLConnection httpURLConnection = null;
        private InputStream inputStream = null;
        private BufferedReader bufferedReader = null;
        private URL url = null;
        private StringBuffer stringBuffer = null;
        private LruCache lruCache = null;
        private String imageCacheKey = "";

        public ImageAsyncTask() {
            //int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            // Use 1/8th of the available memory for this memory cache.
            int cacheSize = maxMemory / 8;
            lruCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String argKey, Bitmap argBitmap) {
                    // The cache size will be measured in kilobytes
                    return argBitmap.getByteCount() / 1024;
                }
            };
        }

        public ImageAsyncTask setEventListener(AsyncTaskEventListener argAsyncTaskEventListener) {
            asyncTaskEventListener = argAsyncTaskEventListener;
            return this;
        }

        @Override
        protected Bitmap doInBackground(String... argURLs) {
            Bitmap bitmap = null;
            try {
                //ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                String urlString = argURLs[0];
                imageCacheKey = Utils.getBase64Encode(urlString);
                /*int maxKb = activityManager.getMemoryClass() * 1024;
                int limitKb = maxKb / 8;
                limitKb = maxKb;
                lruCache = new LruCache<>(limitKb);*/
                onLogPrint("(doInBackground) url " + urlString);
                bitmap = getBitmapFromMemCache(imageCacheKey);
                if (bitmap != null) {
                    onLogPrint("(doInBackground) from LruCache");
                    return bitmap;
                }
                url = new URL(urlString);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                /*bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                String imagString = stringBuffer.toString();
                byte[] byteImageArray = Base64.decode(imagString, Base64.DEFAULT);
                try {
                    bitmap = BitmapFactory.decodeByteArray(byteImageArray, 0, byteImageArray.length);
                } catch (Exception e) {
                    //Log.d("tag", e.toString());
                }*/
                bitmap = BitmapFactory.decodeStream(inputStream);
                onLogPrint(bitmap.toString());
                //lruCache.put(imageCacheKey, bitmap);
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap argResult) {
            onLogPrint("(onPostExecute)");
            onLogPrint("(onPostExecute) getByteCount " + argResult.getByteCount() / 1024);
            addBitmapToMemoryCache(imageCacheKey, argResult);
            //imageView.setImageBitmap(argResult);
            if (asyncTaskEventListener != null) {
                asyncTaskEventListener.onPostExecute(argResult);
            }
        }

        public void addBitmapToMemoryCache(String argKey, Bitmap argBitmap) {
            if (getBitmapFromMemCache(argKey) == null) {
                lruCache.put(argKey, argBitmap);
            }
        }

        public Bitmap getBitmapFromMemCache(String argKey) {
            return (Bitmap) lruCache.get(argKey);
        }
    }

    public interface AsyncTaskEventListener {
        public void onPostExecute(Bitmap argResult);
    }

    private void onLogPrint(String argMessage) {
        System.out.println(this.getClass().getSimpleName() + " DEBUG_LOG_PRINT: " + " " + argMessage);
    }
    //cache
}