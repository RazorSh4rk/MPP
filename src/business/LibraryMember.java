package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private List<CheckoutRecord> cRec;
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
		this.cRec = new ArrayList<>();
	}
	
	public void addCheckout(CheckoutRecord cR) {
		this.cRec.add(cR);
	}
	public void removeCheckout(CheckoutRecord cr) {
		this.cRec.remove(cr);
	}
	public List<CheckoutRecord> getCheckouts() {
		return this.cRec;
	}
	
	public String getMemberId() {
		return memberId;
	}

	
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
