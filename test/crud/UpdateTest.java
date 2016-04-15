package crud;

import org.ektorp.Options;

public class UpdateTest extends DBClient{

	private static int cnt = 0;
	/**
	 * testbean에서 name을 계속 추가만한다. 
	 */
	public UpdateTest(String dbName, String docId) {
		this(dbName, "http://192.168.0.9:5984", docId);
	}
	
	public UpdateTest(String dbName, String url, String docId){
		super(dbName);
		
		CrudRepository repo = new CrudRepository(db);
		if ( repo.contains(docId)){
			CrudBean bean = repo.get(docId, new Options().includeRevisions());
			bean.setName(bean.getName()+ (cnt++));
			repo.update(bean);
		}
	}
	
	public static void main(String args[]){
		InsertTest insert = new InsertTest("ektorp");
		String docId = insert.docId;
		new UpdateTest("ektorp", docId);
		CrudBean bean = new GetTest("ektorp").get(docId);
		System.out.println(bean.getRevision());
	}
}
