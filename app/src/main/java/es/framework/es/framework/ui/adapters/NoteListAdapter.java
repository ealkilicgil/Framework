package es.framework.es.framework.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import es.framework.R;
import es.framework.es.framework.entities.Note;

/**
 * Created by 02481552 on 05.12.2015.
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    private List<Note> mNotes;
    private Context mContext;
    private OnStartDragListener mDragListener;

    public NoteListAdapter(Context context,List<Note> mNotes,OnStartDragListener dragListener) {
        this.mNotes = mNotes;
        mContext=context;
        mDragListener=dragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note selectedNote=mNotes.get(position);
        holder.noteTitle.setText(selectedNote.getTitle());
        holder.noteCreateDate.setText(selectedNote.getReadableModifiedDate());
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEventCompat.getActionMasked(event)==MotionEvent.ACTION_DOWN){
                    mDragListener.OnStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mNotes,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismess(int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
        public TextView noteTitle,noteCreateDate;
        public ImageView handleView;
        public ViewHolder(View itemView) {
            super(itemView);

            noteTitle= (TextView) itemView.findViewById(R.id.text_view_note_title);
            noteCreateDate= (TextView) itemView.findViewById(R.id.text_view_note_date_created);
            handleView= (ImageView) itemView.findViewById(R.id.handle);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.GRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

}
