package com.example.pelt.geoguessswipe;

public class GeoObject {

    private String mGeoName;
    private int mGeoImageName;
    private boolean mGeoAnswer;

    public GeoObject(String mGeoName, int mGeoImageName, boolean mGeoAnswer) {
        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
        this.mGeoAnswer = mGeoAnswer;
    }

    public String getmGeoName() {
        return mGeoName;
    }

    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }

    public int getmGeoImageName() {
        return mGeoImageName;
    }

    public void setmGeoImageName(int mGeoImageName) {
        this.mGeoImageName = mGeoImageName;
    }

    public boolean getmGeoAnswer() {
        return mGeoAnswer;
    }

    public void setmGeoAnswer(int mGeoImageName) {
        this.mGeoAnswer = mGeoAnswer;
    }

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "Denmark",
            "Canada",
            "Bang Ladesh",
            "Kazachstan",
            "Colombia",
            "Poland",
            "Malta",
            "Thailand"
    };

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };

    public static final boolean[] PRE_DEFINED_GEO_OBJECT_ANSWERS = {
            true,
            false,
            false,
            true,
            false,
            true,
            true,
            false,
    };
}
