package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reasonforrequest")
public class Reasonforrequest implements Serializable {
	@Id
	private int id;

	private String reasonforrequeststring;

	private static final long serialVersionUID = 1L;

	public Reasonforrequest() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReasonforrequeststring() {
		return this.reasonforrequeststring;
	}

	public void setReasonforrequeststring(String reasonforrequeststring) {
		this.reasonforrequeststring = reasonforrequeststring;
	}

}
