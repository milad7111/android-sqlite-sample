package room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import room.classes.Person;

@Dao
public interface PersonDAO {
    @Insert
    public void insert(Person... persons);

    @Update
    public void update(Person... persons);

    @Delete
    public void delete(Person persons);

    @Query("SELECT * FROM Person")
    public List<Person> getContacts();

    @Query("SELECT * FROM Person WHERE _id = :id")
    public Person getPersonWithId(String id);
}
