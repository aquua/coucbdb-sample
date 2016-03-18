package crud;

public class InsertTest extends DBClient{

	
	public InsertTest(String dbName) {
		this(dbName, "http://192.168.0.9:5984");
	}
	
	public InsertTest(String dbName, String url){
		super(dbName);
		
		String docId = makeDocId(); 
		CrudBean crudBean = CrudBean.makeCrudBean("test-0");
		crudBean.setId(docId);
		db.create(crudBean);
		
		System.out.println(crudBean.getId()+" : " + crudBean.getRevision());
	}
}
