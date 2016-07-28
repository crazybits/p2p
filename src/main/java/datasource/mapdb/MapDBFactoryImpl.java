package datasource.mapdb;

import java.io.File;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import datasource.KeyValueDataSource;

public class MapDBFactoryImpl implements MapDBFactory {


    @Override
    public KeyValueDataSource createDataSource() {
        return new MapDBDataSource();
    }

    @Override
    public DB createDB(final String name) {
        return createDB(name, false);
    }

    @Override
    public DB createTransactionalDB(final String name) {
        return createDB(name, true);
    }

    private DB createDB(final String name, final boolean transactional) {
        File dbFile = new File("/" + name);
        if (!dbFile.getParentFile().exists()) {
            dbFile.getParentFile().mkdirs();
        }
        DBMaker.Maker dbMaker = DBMaker.fileDB(dbFile).closeOnJvmShutdown();
        if (!transactional) {
            dbMaker.transactionDisable();
        }
        return dbMaker.make();
    }
}
