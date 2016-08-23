package crud;

import java.net.MalformedURLException;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class DBClient {

	
	protected CouchDbConnector db;

	public DBClient(String dbName){
		makeDbConnector(dbName,"http://192.168.0.9:5984");
	}

	
	private void makeDbConnector(String dbName, String url){
		CouchDbInstance dbInstance = new StdCouchDbInstance(makeClient(url));
		db = dbInstance.createConnector(dbName, true);
	}
	
	private HttpClient makeClient(String url) {
		HttpClient client = null;
		try {
			client = new StdHttpClient.Builder()
					.url(url)
					.socketTimeout(10000)
					.connectionTimeout(10000)
					.maxConnections(50)
					.build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	protected String makeDocId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
