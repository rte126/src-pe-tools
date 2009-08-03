package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reproductionstatus")
public class Reproductionstatus implements Serializable {
	@Id
	private int id;

	private String reproductionstatusstring;

	private static final long serialVersionUID = 1L;

	public Reproductionstatus() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReproductionstatusstring() {
		return this.reproductionstatusstring;
	}

	public void setReproductionstatusstring(String reproductionstatusstring) {
		this.reproductionstatusstring = reproductionstatusstring;
	}

}
