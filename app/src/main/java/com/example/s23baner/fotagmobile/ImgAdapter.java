package com.example.s23baner.fotagmobile;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import java.util.zip.Inflater;

import java.util.List;

public class ImgAdapter extends BaseAdapter {
    private List<ImageModel> imageCollection;
    private Context context;
    private Model model;

    public ImgAdapter(Context cont, Model model)
    {
        context = cont;
        this.model = model;
        imageCollection = model.getLsitOfImages();
    }

    public List<ImageModel> updateImages()
    {
        imageCollection = model.getDisplayableImages();
        return imageCollection;
    }

    @Override
    public int getCount() {
        return imageCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return imageCollection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // we should create a thumbnail object and return here

        View thumbView = LayoutInflater.from(context).inflate(R.layout.thumbnail_layout, parent, false);
        final ImageView imageView = thumbView.findViewById(R.id.imgView);
        final ViewGroup parent2 = parent;
        final RatingBar ratingBar = thumbView.findViewById(R.id.ratingBar);
        ImageButton resetButton = (ImageButton)thumbView.findViewById(R.id.resButton);

        imageView.setImageBitmap(imageCollection.get(position).getImageBitmap());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog diag = new Dialog(context, R.style.FullScreen);

                View view = LayoutInflater.from(context).inflate(R.layout.image_popup_layout, parent2, false);
                ImageView imageView2 = view.findViewById(R.id.popupImage);
                imageView2.setImageBitmap(((BitmapDrawable)imageView.getDrawable()).getBitmap());
                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diag.cancel();
                    }
                });

                diag.setContentView(view);
                diag.show();
            }
        });

        final ImageModel imgModel = imageCollection.get(position);
        ratingBar.setRating(imgModel.getRating());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
            {
                imgModel.setRating((int)rating);
                model.imageRatingChange();
            }
        }
        );

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(0);
                imgModel.setRating(0);
            }
        });


        //imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
        return thumbView;
    }
}
