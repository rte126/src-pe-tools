package wftac.ckim.ipm.patchrequest.data;

import java.io.Serializable;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patchrequest")
public class Patchrequest implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String branch;

	private Date commitmentdate;

	private String commitmentpatch;

	private String customer;

	private int defectpr;

	private int defecttic;

	private String developer;

	private int kaid;

	private String reasonforrequest;

	private String remark;

	private String reproductionstatus;

	private String tacengineer;

	private int targetpatch;

	private String urgency;

	private String validationstatus;

	private static final long serialVersionUID = 1L;

	public Patchrequest() {
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

	public Date getCommitmentdate() {
		return this.commitmentdate;
	}

	public void setCommitmentdate(Date commitmentdate) {
		this.commitmentdate = commitmentdate;
	}

	public String getCommitmentpatch() {
		return this.commitmentpatch;
	}

	public void setCommitmentpatch(String commitmentpatch) {
		this.commitmentpatch = commitmentpatch;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getDefectpr() {
		return this.defectpr;
	}

	public void setDefectpr(int defectpr) {
		this.defectpr = defectpr;
	}

	public int getDefecttic() {
		return this.defecttic;
	}

	public void setDefecttic(int defecttic) {
		this.defecttic = defecttic;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getKaid() {
		return this.kaid;
	}

	public void setKaid(int kaid) {
		this.kaid = kaid;
	}

	public String getReasonforrequest() {
		return this.reasonforrequest;
	}

	public void setReasonforrequest(String reasonforrequest) {
		this.reasonforrequest = reasonforrequest;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReproductionstatus() {
		return this.reproductionstatus;
	}

	public void setReproductionstatus(String reproductionstatus) {
		this.reproductionstatus = reproductionstatus;
	}

	public String getTacengineer() {
		return this.tacengineer;
	}

	public void setTacengineer(String tacengineer) {
		this.tacengineer = tacengineer;
	}

	public int getTargetpatch() {
		return this.targetpatch;
	}

	public void setTargetpatch(int targetpatch) {
		this.targetpatch = targetpatch;
	}

	public String getUrgency() {
		return this.urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getValidationstatus() {
		return this.validationstatus;
	}

	public void setValidationstatus(String validationstatus) {
		this.validationstatus = validationstatus;
	}

}
