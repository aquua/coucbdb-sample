package crud;

import org.ektorp.Options;

public class GetTest extends DBClient{


	public GetTest(String dbName) {
		this(dbName, "http://192.168.0.9:5984");
	}
	
	public GetTest(String dbName, String url){
		super(dbName);

	}
	
	public CrudBean get(String docId){
		CrudRepository repo = new CrudRepository(db);
		return repo.get(docId, new Options().includeRevisions());		
	}
}
