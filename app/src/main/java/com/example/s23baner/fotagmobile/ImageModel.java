package com.example.s23baner.fotagmobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.InputStream;


public class ImageModel implements java.io.Serializable {
    private int imageRating;
    private String imageLocation;
    private String imageName;
    private boolean isDisplayed;
    private transient Bitmap bitmap;
    private Model model;
    private Context context;
    private boolean didLoad;

    public ImageModel(int imageRequested, Model model, Context context)
    {
        this.model = model;
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), imageRequested);
    }

    public ImageModel(String url, Model model, Context context)
    {
        this.model = model;
        this.context = context;
        new obtainImageViaUrl(this).execute(url);
    }

    public class obtainImageViaUrl extends AsyncTask<String, Void, Bitmap> {
        ImageModel imgModel;
        public obtainImageViaUrl(ImageModel imgModel) {
            this.imgModel = imgModel;
        }
        @Override
        protected Bitmap doInBackground(String... url)
        {
            String urlValue = url[0];
            try
            {
                InputStream iStream = new java.net.URL(urlValue).openStream();
                bitmap = BitmapFactory.decodeStream(iStream);
                // now we can add this image to our images
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                Log.d("FAIL", "Unable to load image from URI");
                //model.notifyObservers(new Intent().setAction("failImage"));
            }
            return bitmap;
        }

        public void onPostExecute(Bitmap result)
        {
            model.addImage(imgModel);
        }
    }


    public String getLocation()
    {
        return imageLocation;
    }

    public void setRating(int rating)
    {
        imageRating = rating;
    }

    public int getRating()
    {
        return imageRating;
    }

    public void setPainted(boolean painted)
    {
        isDisplayed = painted;
    }

    public String getImageName()
    {
        return imageName;
    }

    public Bitmap getImageBitmap() {return bitmap;}

}
