package com.gifted.app.giftededucation.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.gifted.app.giftededucation.R;
import com.gifted.app.giftededucation.utils.Config;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class FullImageActivity extends AppCompatActivity {
    SubsamplingScaleImageView full_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        full_image = (SubsamplingScaleImageView) findViewById(R.id.full_image);
        String image = getIntent().getExtras().getString("image");

        Picasso.with(this)
                .load(Config.SOURCE_URL_IMAGE + image)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        full_image.setImage(ImageSource.bitmap(bitmap));

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });


    }

}
