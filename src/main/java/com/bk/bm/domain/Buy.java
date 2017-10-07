package com.bk.bm.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by choi on 2017. 9. 25. PM 4:01.
 */

public class Buy implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_id;
    private int uid;
    private String isbn10;
    private String isbn13;
    private String title;
    private int price_min;
    private int price_max;
    private boolean status;
    private String comment;
    private Date created_at;
    private Date updated_at;

    private ArrayList<BuyArea> area = new ArrayList<>();
    private ArrayList<BuyImage> images = new ArrayList<>();

    public Buy() {
    }

    public Buy(int uid, String isbn10, String isbn13, String title, int price_min, int price_max, boolean status, String comment) {
        this.uid = uid;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.price_min = price_min;
        this.price_max = price_max;
        this.status = status;
        this.comment = comment;
    }

    public Buy(int buy_id, int uid, String isbn10, String isbn13, String title, int price_min, int price_max, boolean status, String comment, Date created_at, Date updated_at) {
        this.buy_id = buy_id;
        this.uid = uid;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.price_min = price_min;
        this.price_max = price_max;
        this.status = status;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice_min() {
        return price_min;
    }

    public void setPrice_min(int price_min) {
        this.price_min = price_min;
    }

    public int getPrice_max() {
        return price_max;
    }

    public void setPrice_max(int price_max) {
        this.price_max = price_max;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public ArrayList<BuyArea> getArea() {
        return area;
    }

    public void setArea(ArrayList<BuyArea> area) {
        this.area = area;
    }

    public ArrayList<BuyImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<BuyImage> images) {
        this.images = images;
    }
}
