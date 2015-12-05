package es.framework.es.framework.db;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import es.framework.es.framework.entities.Note;

/**
 * Created by 02481552 on 02.12.2015.
 */
public class NoteDataSource {
    private Note _note;

    public NoteDataSource(Note note)
    {
        _note=note;
    }

    public Note Save()
    {
        _note.setTitle("test");
        _note.setContent("testin testin testin");

        Calendar calendar= GregorianCalendar.getInstance();
        _note.setDateCreated(calendar.getTimeInMillis());
        _note.setDateModified(calendar.getTimeInMillis());
        _note.save();
        return _note;
    }

    public List<Note> GetNotes(){
        return Note.listAll(Note.class);
    }

}
