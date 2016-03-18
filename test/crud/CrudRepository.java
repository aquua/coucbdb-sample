package crud;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class CrudRepository  extends CouchDbRepositorySupport<CrudBean> {

    public CrudRepository(CouchDbConnector db) {
        super(CrudBean.class, db);
    }
}
