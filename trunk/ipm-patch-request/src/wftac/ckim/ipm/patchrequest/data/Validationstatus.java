package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="validationstatus")
public class Validationstatus implements Serializable {
	@Id
	private int id;

	private String validationstatusstring;

	private static final long serialVersionUID = 1L;

	public Validationstatus() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValidationstatusstring() {
		return this.validationstatusstring;
	}

	public void setValidationstatusstring(String validationstatusstring) {
		this.validationstatusstring = validationstatusstring;
	}

}
