package wftac.jsf001;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigator {
	private String[] resultPages =
		{ "page1", "page2", "page3" };
	
	public String choosePage() {
		return resultPages[2];
		}
}
