package com.example.s23baner.fotagmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements Observer {

    private android.support.v7.widget.Toolbar toolbar;
    private Model model;
    private Context context;
    ImageButton starButton1;
    ImageButton starButton2;
    ImageButton starButton3;
    ImageButton starButton4;
    ImageButton starButton5;
    ImageButton refreshButton;
    ImgAdapter imgAdapter;
    private List<ImageModel> images;
    GridView gridView;
    FloatingActionButton fabButton;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("currModel", model);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
        {
            Log.d("Shayon", "LOL");
            model = (Model) savedInstanceState.getSerializable("currModel");
        }
        else
        {
            Log.d("Shayon", "LOLOL");
            model = new Model();
            model.addObserver(this);
            context = this;
        }

        setTitle("Fotag (s23baner)");
        // initialize the views and have them observe the model

        // get the images to load
        imgAdapter = new ImgAdapter(this, model);


        setContentView(R.layout.activity_main);

        ImageButton loadButton = (ImageButton) findViewById(R.id.loadButton);
        ImageButton clearButton = (ImageButton) findViewById(R.id.clearButton);
        starButton1 = (ImageButton) findViewById(R.id.starButton1);
        starButton2 = (ImageButton) findViewById(R.id.starButton2);
        starButton3 = (ImageButton) findViewById(R.id.starButton3);
        starButton4 = (ImageButton) findViewById(R.id.starButton4);
        starButton5 = (ImageButton) findViewById(R.id.starButton5);
        refreshButton = (ImageButton) findViewById(R.id.refreshButton);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.loadImages(context);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.clearImages();
            }
        });

        starButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton2.setImageResource(R.drawable.ic_star_icon);
                starButton3.setImageResource(R.drawable.ic_star_icon);
                starButton4.setImageResource(R.drawable.ic_star_icon);
                starButton5.setImageResource(R.drawable.ic_star_icon);
                model.setFilter(1);
            }
        });

        starButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton2.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton3.setImageResource(R.drawable.ic_star_icon);
                starButton4.setImageResource(R.drawable.ic_star_icon);
                starButton5.setImageResource(R.drawable.ic_star_icon);
                model.setFilter(2);
            }
        });

        starButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton2.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton3.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton4.setImageResource(R.drawable.ic_star_icon);
                starButton5.setImageResource(R.drawable.ic_star_icon);
                model.setFilter(3);
            }
        });

        starButton4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton2.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton3.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton4.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton5.setImageResource(R.drawable.ic_star_icon);
                model.setFilter(4);
            }
        });

        starButton5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton2.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton3.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton4.setImageResource(R.drawable.ic_goldenstar_icon);
                starButton5.setImageResource(R.drawable.ic_goldenstar_icon);
                model.setFilter(5);
            }
        });


        refreshButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                starButton1.setImageResource(R.drawable.ic_star_icon);
                starButton2.setImageResource(R.drawable.ic_star_icon);
                starButton3.setImageResource(R.drawable.ic_star_icon);
                starButton4.setImageResource(R.drawable.ic_star_icon);
                starButton5.setImageResource(R.drawable.ic_star_icon);
                model.setFilter(0);
            }
        });



        fabButton = (FloatingActionButton) findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                View view = getLayoutInflater().inflate(R.layout.search_dialog_layout, null);
                final EditText uriEntered =  (EditText) view.findViewById(R.id.imageURI);

                builder.setNeutralButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!uriEntered.getText().toString().isEmpty())
                        {
                            new ImageModel(uriEntered.getText().toString(), model, MainActivity.this);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please fill search field", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                builder.setView(view);
                builder.show();

            }
        });
        gridView = (GridView) findViewById(R.id.galaryView);
        imgAdapter.updateImages();
        gridView.setAdapter(imgAdapter);
        gridView.setNumColumns(1);

        Log.d("Shayon", "HEREHERE");

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("Shayon", "H234");
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("Shayon", "HEREHERE3");
            gridView.setNumColumns(2);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("Shayon", "HEREHERE4");
            gridView.setNumColumns(1);
        }
    }



    public void update(Object Observable, Intent intent)
    {
        Log.d("Shayon", intent.getAction());

        if (intent.getAction() == "clearImage")
        {
            images = imgAdapter.updateImages();
            Log.d("Debug1", "ClearImage");
            gridView.setAdapter(imgAdapter);
        }
        else if (intent.getAction() == "addImage")
        {
            images = imgAdapter.updateImages();
            Log.d("Debug1", "addImage");
            gridView.setAdapter(imgAdapter);
        }
        else if (intent.getAction() == "setFilter")
        {
            images = imgAdapter.updateImages();
            Log.d("Debug1", "setFilter");
            gridView.setAdapter(imgAdapter);
        }
        else if (intent.getAction() == "failImage")
        {
            //Toast.makeText(MainActivity.this, "Failed to add image!", Toast.LENGTH_SHORT).show();
            //Log.d("Debug1", "failImage");
        }
        else if (intent.getAction() == "loadImage")
        {
            images = imgAdapter.updateImages();
            Log.d("size", Integer.toString(model.getLsitOfImages().size()));
            gridView.setAdapter(imgAdapter);
        }
        else if (intent.getAction() ==  "imgFilter")
        {

            List<ImageModel> images2 = new ArrayList<>();
            images2 = imgAdapter.updateImages();
            if (images2.size() != images.size())
            {
                images = images2;
                gridView.setAdapter(imgAdapter);
            }
        }

    }
}
