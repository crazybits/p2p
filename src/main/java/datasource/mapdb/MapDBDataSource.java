package datasource.mapdb;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import datasource.KeyValueDataSource;

public class MapDBDataSource implements KeyValueDataSource {

    private static final int BATCH_SIZE = 1024 * 1000 * 10;

    Config config = ConfigFactory.defaultApplication();

    private DB db;
    private Map<byte[], byte[]> map;
    private String name;
    private boolean alive;


    @Override
    public void init() {

        File dbFile = new File(this.config.getString("database.dir") + this.name);
        if (!dbFile.getParentFile().exists()) {
            dbFile.getParentFile().mkdirs();
        }


        this.db = DBMaker.fileDB(dbFile).transactionDisable().closeOnJvmShutdown().make();

        this.map = this.db.hashMapCreate(this.name).keySerializer(Serializer.BYTE_ARRAY).valueSerializer(Serializer.BYTE_ARRAY)
            .makeOrGet();

        this.alive = true;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public byte[] get(final byte[] key) {
        return this.map.get(key);
    }

    @Override
    public byte[] put(final byte[] key, final byte[] value) {
        try {
            return this.map.put(key, value);
        } finally {
            this.db.commit();
        }
    }

    @Override
    public void delete(final byte[] key) {
        try {
            this.map.remove(key);
        } finally {
            this.db.commit();
        }
    }

    @Override
    public Set<byte[]> keys() {
        return this.map.keySet();
    }

    @Override
    public void updateBatch(final Map<byte[], byte[]> rows) {
        int savedSize = 0;
        try {
            for (byte[] key : rows.keySet()) {
                byte[] value = rows.get(key);
                savedSize += value.length;

                this.map.put(key, value);
                if (savedSize > MapDBDataSource.BATCH_SIZE) {
                    this.db.commit();
                    savedSize = 0;
                }
            }
        } finally {
            this.db.commit();
        }
    }

    @Override
    public void close() {
        this.db.close();
        this.alive = false;
    }
}
