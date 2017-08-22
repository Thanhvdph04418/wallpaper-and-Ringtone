package model;

import java.io.Serializable;

/**
 * Created by Thanh-PC on 8/8/2017.
 */

public class Item implements Serializable {
    private String urlImages,title,download,bigimgeurl;

    public Item() {
    }

    public Item(String urlImages, String title, String download, String bigimgeurl) {
        this.urlImages = urlImages;
        this.title = title;
        this.download = download;
        this.bigimgeurl=bigimgeurl;

    }


    public String getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(String urlImages) {
        this.urlImages = urlImages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
    public String getBigimgeurl() {
        return bigimgeurl;
    }
    public void setBigimgeurl(String bigimgeurl) {
        this.bigimgeurl = bigimgeurl;
    }
}
