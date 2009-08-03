package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="targetpatch")
public class Targetpatch implements Serializable {
	@Id
	private int id;

	private String patch;

	private Date releasedate;

	private String status;

	private Date targetdate;

	private static final long serialVersionUID = 1L;

	public Targetpatch() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatch() {
		return this.patch;
	}

	public void setPatch(String patch) {
		this.patch = patch;
	}

	public Date getReleasedate() {
		return this.releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTargetdate() {
		return this.targetdate;
	}

	public void setTargetdate(Date targetdate) {
		this.targetdate = targetdate;
	}

}
