package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tacengineer")
public class Tacengineer implements Serializable {
	@Id
	private int id;

	private String engineerid;

	private static final long serialVersionUID = 1L;

	public Tacengineer() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEngineerid() {
		return this.engineerid;
	}

	public void setEngineerid(String engineerid) {
		this.engineerid = engineerid;
	}

}
