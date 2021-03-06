package com.example.user.mvvpapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Bencoleng on 03/01/2019.
 */

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM not_table")
    void deleteAllNotes();

    @Query("SELECT * FROM not_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
