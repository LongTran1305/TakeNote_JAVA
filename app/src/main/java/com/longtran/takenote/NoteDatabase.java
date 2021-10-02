package com.longtran.takenote;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDAO noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
         if(instance == null){
             instance = Room.databaseBuilder(context.getApplicationContext(),
                     NoteDatabase.class,"note_database").fallbackToDestructiveMigration()
                     .addCallback(roomCallBack)
                     .build();
         }
         return instance;
    }

    private static  RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDAO noteDAO;
        private PopulateDbAsyncTask(NoteDatabase database){
            noteDAO = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.Insert(new Note("Title 1","Des 1"));
            noteDAO.Insert(new Note("Title 2","Des 2"));
            noteDAO.Insert(new Note("Title 3","Des 3"));
            noteDAO.Insert(new Note("Title 4","Des 4"));
            return null;
        }
    }
}
