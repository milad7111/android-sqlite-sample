package room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import room.classes.Person;
import room.dao.PersonDAO;

@Database(entities = {Person.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract PersonDAO getPersonDAO();

    private static final Object myDB = new Object();

    /**
     * Migrate from:
     * version 2 - using the SQLiteDatabase API
     * to
     * version 3 - using Room
     */
    @VisibleForTesting
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Room uses an own database hash to uniquely identify the database
            // Since version 1 does not use Room, it doesn't have the database hash associated.
            // By implementing a Migration class, we're telling Room that it should use the data
            // from version 1 to version 2.
            // If no migration is provided, then the tables will be dropped and recreated.
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static AppDatabase getInstance(Context context) {
        synchronized (myDB) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "RoomAppDatabase.db")
                        .addMigrations(MIGRATION_2_3)
                        .build();
            }
            return INSTANCE;
        }
    }
}
