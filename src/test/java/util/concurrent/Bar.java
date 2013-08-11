package test.java.util.concurrent;


/**
 * @Project: test.commons
 * <p>Description:  </p>
 * <p>此模块分功能：</p>		
 * <p> 2013-9-11 </p>
 * @author Zhoutao
 * @version 2.x
 */
public class Bar {
	
	private String id;
	public Bar(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public Bar() {
	}
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
