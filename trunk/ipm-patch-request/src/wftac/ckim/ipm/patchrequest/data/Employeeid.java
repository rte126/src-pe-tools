package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employeeid")
public class Employeeid implements Serializable {
	@Id
	private int id;

	private String employeeidstring;

	private static final long serialVersionUID = 1L;

	public Employeeid() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeidstring() {
		return this.employeeidstring;
	}

	public void setEmployeeidstring(String employeeidstring) {
		this.employeeidstring = employeeidstring;
	}

}
