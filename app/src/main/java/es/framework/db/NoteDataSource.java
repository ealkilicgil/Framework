package es.framework.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import es.framework.entities.Note;

/**
 * Created by 02481552 on 02.12.2015.
 */
public class NoteDataSource {
    private Note _note;

    public NoteDataSource(Note note)
    {
        _note=note;
    }

    public NoteDataSource()
    {

    }
    public List<Note> SampleTestData()
    {
        List<Note> sampleNotes=new ArrayList<Note>();
        Note note1=new Note();
        note1.setTitle("DisnayLand Trip");
        note1.setContent("We ent to disnayland");

        Calendar calendar1= GregorianCalendar.getInstance();
        note1.setDateCreated(calendar1.getTimeInMillis());
        note1.setDateModified(calendar1.getTimeInMillis());
        note1.save();
        sampleNotes.add(note1);

        Note note2=new Note();
        note2.setTitle("ESkişehir Atatürk Stadı");
        note2.setContent("We ent to disnayland");

        Calendar calendar2= GregorianCalendar.getInstance();
        calendar2.add(Calendar.DAY_OF_WEEK, 1);
        calendar2.add(Calendar.MILLISECOND, 1098098);
        note2.setDateCreated(calendar1.getTimeInMillis());
        note2.setDateModified(calendar1.getTimeInMillis());
        note2.save();
        sampleNotes.add(note2);

        Note note3=new Note();
        note3.setTitle("Ataköy evimiz");
        note3.setContent("We ent to disnayland");

        Calendar calendar3= GregorianCalendar.getInstance();
        calendar3.add(Calendar.DAY_OF_WEEK,1);
        calendar3.add(Calendar.MILLISECOND,1098098);
        note3.setDateCreated(calendar1.getTimeInMillis());
        note3.setDateModified(calendar1.getTimeInMillis());
        note3.save();
        sampleNotes.add(note3);

        return sampleNotes;
    }
    public Note Save(Note note)
    {
        _note=note;
        _note.save();
        return _note;
    }

    public List<Note> GetNotes(){
        return Note.listAll(Note.class);
    }

}
