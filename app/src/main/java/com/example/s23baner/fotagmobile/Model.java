package com.example.s23baner.fotagmobile;

import android.content.Context;
import android.content.Intent;

import  java.util.*;
import java.util.List;


public class Model implements java.io.Serializable {
    private List<Observer> observers;
    private List<ImageModel> imageCollection;
    private boolean isGrid;
    private int filterSelection;
    private int columnsRequired;
    private boolean imageLoaded;
    private List<ImageModel> displayables;

    public Model()
    {
        this.observers = new ArrayList<>();
        this.imageCollection = new ArrayList<>();
        isGrid = true;
        filterSelection = 0;
        imageLoaded = false;
    }

    public void loadImages(Context context)
    {
        // handle for android
            ImageModel image1 = new ImageModel(R.drawable.galary_image1, this, context);
            ImageModel image2 = new ImageModel(R.drawable.galary_image2, this, context);
            ImageModel image3 = new ImageModel(R.drawable.galary_image3, this, context);
            ImageModel image4 = new ImageModel(R.drawable.galary_image4, this, context);
            ImageModel image5 = new ImageModel(R.drawable.galary_image5, this, context);
            ImageModel image6 = new ImageModel(R.drawable.galary_image6, this, context);
            ImageModel image7 = new ImageModel(R.drawable.galary_image7, this, context);
            ImageModel image8 = new ImageModel(R.drawable.galary_image8, this, context);
            ImageModel image9 = new ImageModel(R.drawable.galary_image9, this, context);
            ImageModel image10 = new ImageModel(R.drawable.galary_image10, this, context);

            this.addImage(image1);
            this.addImage(image2);
            this.addImage(image3);
            this.addImage(image4);
            this.addImage(image5);
            this.addImage(image6);
            this.addImage(image7);
            this.addImage(image8);
            this.addImage(image9);
            this.addImage(image10);

            imageLoaded = true;

            Intent intent = new Intent();
            intent.setAction("loadImage");
            this.notifyObservers(intent);
    }

    public void clearImages()
    {
        imageCollection = new ArrayList<>();
        imageLoaded = false;
        Intent intent = new Intent();
        intent.setAction("clearImage");
        this.notifyObservers(intent);
    }

    public void saveImages()
    {
        // handle for android
    }

    public List<ImageModel> getLsitOfImages()
    {
        return imageCollection;
    }

    public void addImage(ImageModel imgModel)
    {
        this.imageCollection.add(imgModel);
        Intent intent = new Intent();
        intent.setAction("addImage");
        this.notifyObservers(intent);
    }

    public void setFilter(int desiredVal)
    {
        filterSelection = desiredVal;
        Intent intent = new Intent();
        intent.setAction("setFilter");
        this.notifyObservers(intent);
    }

    public void imageRatingChange()
    {
        Intent intent = new Intent();
        intent.setAction("imgFilter");
        this.notifyObservers(intent);
    }

    public List<ImageModel> getDisplayableImages()
    {
        displayables = new ArrayList<>();
        for (int i = 0; i < imageCollection.size(); ++i)
        {
            if (imageCollection.get(i).getRating() >= filterSelection)
            {
                displayables.add(imageCollection.get(i));
            }
        }
        return displayables;
    }

    public int getFilter()
    {
        return filterSelection;
    }

    public void setGrid(boolean desiredVal)
    {
        isGrid = desiredVal;
        Intent intent = new Intent();
        intent.setAction("setGrid");
        this.notifyObservers(intent);
    }

    public  void setImageRating(ImageModel img, int rating)
    {
        img.setRating(rating);
    }

    public boolean isGridLayout()
    {
        return isGrid;
    }

    public void addObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer)
    {
        this.observers.remove(observer);
    }

    public int getColumns()
    {
        if (!isGrid)
        {
            return 1;
        }
        else {
            // handle if in grid mode
            return 3;
        }
    }

    public void notifyObservers(Intent intent)
    {
        for (Observer observer : this.observers)
        {
            observer.update(this, intent);
        }
    }

}
