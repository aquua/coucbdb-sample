package crud;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.StringEntity;
import org.ektorp.http.HttpClient;
import org.ektorp.http.HttpResponse;
import org.json.simple.JSONObject;


public class OneFieldupdate extends DBClient {


	public OneFieldupdate() {
		super("mapping");
	}
	
	public void updateOneField(){
		HttpClient httpClient = db.getConnection();
		HttpResponse httpResponse = null;
		
		String url = "http://192.168.0.9:5984/mapping/_design/app/_update/modifyinplace/00a45674fe863563929dbcd8cc962ce5";
		Map<String, String> data = new HashMap<String, String>();
		data.put("a", "a1");
		data.put("aa", "a2");
		
		try {
			JSONObject json = new JSONObject(data);
			StringEntity body = new StringEntity(json.toString());
			httpResponse = httpClient.post(url, body);
			String response = IOUtils.toString(httpResponse.getContent());
			System.out.println(response);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if ( httpResponse != null ) httpResponse.releaseConnection();
			httpClient.shutdown();
		}
	}
	
	public static void main(String[] args) {
		new OneFieldupdate().updateOneField();;
	}

}
