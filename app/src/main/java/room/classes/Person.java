package room.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "Person")
public class Person {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    private String mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "gender")
    private String mGender;

    @ColumnInfo(name = "age")
    private int mAge;

    public Person() {
    }

    @Ignore
    public Person(String name, String gender, int age) {
        mId = UUID.randomUUID().toString();
        mName = name;
        mGender = gender;
        mAge = age;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public void setId(@NonNull String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }
}
