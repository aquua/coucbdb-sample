package crud;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.TimerTask;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class CrudPerformanceTest implements Runnable{

	
	
	@Override
	public void run() {
		for ( int i = 0; i < 100000; i++){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) throws MalformedURLException {
//		Executor executor = Executors.newFixedThreadPool(400);
//		for ( int i = 0; i <200; i++){
//			executor.execute(new CrudTest());
//		}
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new GetCount(), 0, 1000);
		
		new InsertTest("ektorp");
		new UpdateTest("ektorp", "7cdb90dbce1b4a3b9ba5b9428bc280ed");
		CrudBean bean = new GetTest("ektorp").get("7cdb90dbce1b4a3b9ba5b9428bc280ed");
		System.out.println(bean.getName());
	}

}
class GetCount extends TimerTask{
	private long cnt = 0;
	@Override
	public void run() {
		try {
			HttpClient client = new StdHttpClient.Builder()
					.url("http://192.168.0.9:5984")
					.socketTimeout(5000)
					.connectionTimeout(5000)
					.build();

			CouchDbInstance dbInstance = new StdCouchDbInstance(client);
			CouchDbConnector mydb = dbInstance.createConnector("ektorp", true);
			long curCnt = mydb.getDbInfo().getDocCount(); 
			System.out.println(new Date().toLocaleString() 
					+"all Cnt : " + curCnt
					+" increment :" +(curCnt - cnt));
			cnt = curCnt;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
