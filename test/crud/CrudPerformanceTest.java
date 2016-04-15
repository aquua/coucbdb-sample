package crud;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args) throws MalformedURLException {
		Executor executor = Executors.newFixedThreadPool(400);
		for ( int i = 0; i <200; i++){
			executor.execute(new CrudPerformanceTest());
		}
		Timer t = new Timer();
		t.scheduleAtFixedRate(new GetCount(), 0, 1000);
		
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
