package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urgency")
public class Urgency implements Serializable {
	@Id
	private byte id;

	private String urgencystring;

	private static final long serialVersionUID = 1L;

	public Urgency() {
		super();
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getUrgencystring() {
		return this.urgencystring;
	}

	public void setUrgencystring(String urgencystring) {
		this.urgencystring = urgencystring;
	}

}
