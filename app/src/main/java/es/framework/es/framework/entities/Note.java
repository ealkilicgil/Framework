package es.framework.es.framework.entities;

import com.orm.SugarRecord;

/**
 * Created by 02481552 on 29.11.2015.
 */
public class Note extends SugarRecord{

    private String title;
    private String content;
    private int color;
    private Long dateCreated;
    private Long dateModified;

    public Note(){}
    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }


}
