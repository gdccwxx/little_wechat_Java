package com.lendbook.wechat_program.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String isbn10;
    private String isbn13;
    private String publisher;  // 出版社
    private String imgUrl; // 封面图片url
    private int storeNum;  // 库存
    private int lendNum;   // 已经借出
    private float rating;  // 评分
    private int numRater;  // 评分人数
    private String publishDate;    // 出版日期
    private String price;      // 价格
    @Column(columnDefinition = "TEXT")
    private String catalog;    // 分类名称
    @Column(columnDefinition = "TEXT")
    private String summary;    // 简介
    private Boolean distincOldOrNew;    // new or old book, 3 years to distinc;
    private ArrayList<BookTag>tags = new ArrayList<>();
    private ArrayList<String> author=new ArrayList<>();        // 作者，列表["",""]

    public Book(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDistincOldOrNew() {
        return distincOldOrNew;
    }

    public void setDistincOldOrNew(Boolean distincOldOrNew) {
        this.distincOldOrNew = distincOldOrNew;
    }

    public ArrayList<BookTag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<BookTag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(int storeNum) {
        this.storeNum = storeNum;
    }

    public int getLendNum() {
        return lendNum;
    }

    public void setLendNum(int lendNum) {
        this.lendNum = lendNum;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumRater() {
        return numRater;
    }

    public void setNumRater(int numRater) {
        this.numRater = numRater;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }
}

