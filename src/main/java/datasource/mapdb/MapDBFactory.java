package datasource.mapdb;

import org.mapdb.DB;

import datasource.KeyValueDataSource;

public interface MapDBFactory {

    KeyValueDataSource createDataSource();

    DB createDB(String name);

    DB createTransactionalDB(String name);
}
