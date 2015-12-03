package es.framework.es.framework.entities;

import com.orm.SugarRecord;

/**
 * Created by 02481552 on 29.11.2015.
 */
public class TodoItem extends SugarRecord{

    private String title;
    private boolean checked;
    private Long dateCreated;
    private Long dateModified;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }


}
