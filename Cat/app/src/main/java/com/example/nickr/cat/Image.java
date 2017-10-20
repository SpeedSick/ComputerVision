package com.example.nickr.cat;

/**
 * Created by nickr on 18.10.2017.
 */


import android.os.Parcel;
import android.os.Parcel.*;
import android.os.Parcelable;
import android.os.Parcelable.*;

public class Image implements Parcelable{

    private String url;
    private String id;
    private String sourceUrl;

    Image()
    {
    }

    Image(Parcel parcel)
    {
        this.url = parcel.readString();
        this.id = parcel.readString();
        this.sourceUrl = parcel.readString();
    }

    Image(String url, String id, String sourceUrl)
    {
        this.url = url;
        this.id = id;
        this.sourceUrl = sourceUrl;
    }
    public static final Creator<Image> CREATOR = new Creator<Image>() {

        @Override
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override
        public Image[] newArray(int sz) {
            return new Image[sz];
        }
    };

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(sourceUrl);
    }


}
