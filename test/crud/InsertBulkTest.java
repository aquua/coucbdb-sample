package crud;

import java.util.ArrayList;
import java.util.List;

public class InsertBulkTest extends DBClient {

	public InsertBulkTest(int bulkSize,String dbName) {
		this(bulkSize,dbName, "http://192.168.0.9:5984");
	}

	
	public InsertBulkTest(int bulkSize,String dbName, String url){
		super(dbName);

		List<CrudBean> list = new ArrayList<>();
		for ( int i = 0; i < bulkSize; i++){
			String docId = makeDocId();
			CrudBean crudBean = CrudBean.makeCrudBean(docId);
			crudBean.setId(docId);
			list.add(crudBean);
		}
		db.executeBulk(list);

	}

}
