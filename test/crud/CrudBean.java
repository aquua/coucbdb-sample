package crud;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.ektorp.support.CouchDbDocument;


public class CrudBean extends CouchDbDocument{

	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Date createDt;


	/**
	 * ObjectMappter 에서 반드시 필요하다. (기본생성자)
	 */
	public CrudBean(){
	}
	
	public static CrudBean makeCrudBean( ){
		return makeCrudBean(UUID.randomUUID().toString().replace("-", ""));
	}
	public static CrudBean makeCrudBean(String name ){
		return  new CrudBean(name);
	}

	private CrudBean(String name){
		this.name = name;
		this.createDt = new Date(System.currentTimeMillis());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
