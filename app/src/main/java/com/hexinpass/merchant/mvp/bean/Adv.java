package com.hexinpass.merchant.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whl on 2018/2/26.
 */

public class Adv implements Parcelable {
    /**
     * img : http://192.168.2.240:9138/uploads/20180222151901_2418000743.jpg
     * go_type : 1
     * url : www.baidu.com
     */

    String img;
    @SerializedName("typ")
    int go_type;
    private String url;
    private int status;
    private int id;
    private String title;
    private String location;
    private String content;

    protected Adv(Parcel in) {
        img = in.readString();
        go_type = in.readInt();
        url = in.readString();
        status = in.readInt();
        id = in.readInt();
        title = in.readString();
        location = in.readString();
        content = in.readString();
    }

    public static final Creator<Adv> CREATOR = new Creator<Adv>() {
        @Override
        public Adv createFromParcel(Parcel in) {
            return new Adv(in);
        }

        @Override
        public Adv[] newArray(int size) {
            return new Adv[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getGo_type() {
        return go_type;
    }

    public void setGo_type(int go_type) {
        this.go_type = go_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img);
        dest.writeInt(go_type);
        dest.writeString(url);
        dest.writeInt(status);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(location);
        dest.writeString(content);
    }
}
