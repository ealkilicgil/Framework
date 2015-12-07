package es.framework.entities;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    public String getReadableModifiedDate(){
        Calendar calendar=new GregorianCalendar().getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM dd,yyyy - h:mm a");
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        calendar.setTimeInMillis(this.getDateModified());
        Date modifiedDate=calendar.getTime();
        return simpleDateFormat.format(modifiedDate);
    };

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
