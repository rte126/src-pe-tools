package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="branch")
public class Branch implements Serializable {
	@Id
	private int id;

	private String branch;

	private Date eoedate;

	private Date eosdate;

	private Date frsdate;

	private static final long serialVersionUID = 1L;

	public Branch() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getEoedate() {
		return this.eoedate;
	}

	public void setEoedate(Date eoedate) {
		this.eoedate = eoedate;
	}

	public Date getEosdate() {
		return this.eosdate;
	}

	public void setEosdate(Date eosdate) {
		this.eosdate = eosdate;
	}

	public Date getFrsdate() {
		return this.frsdate;
	}

	public void setFrsdate(Date frsdate) {
		this.frsdate = frsdate;
	}

}
