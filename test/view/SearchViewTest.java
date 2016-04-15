package view;

import java.util.List;

import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import crud.CrudBean;
import crud.DBClient;

public class SearchViewTest extends DBClient {

	
	public SearchViewTest() throws JsonProcessingException{
		super("ektorp");
		ViewQuery q = new ViewQuery()
				.designDocId("_design/test")
				.viewName("test1")
				.includeDocs(true);
		ViewResult result = db.queryView(q);
		List<Row> row = result.getRows();
		ObjectMapper mapper = new ObjectMapper();
		for ( Row r : row){
			CrudBean newJsonNode = mapper.treeToValue(r.getValueAsNode(), CrudBean.class);
			System.out.println(newJsonNode.toString());
		}
		
				
	}
	public static void main(String[] args) throws Exception {
		new SearchViewTest();
	}

}
