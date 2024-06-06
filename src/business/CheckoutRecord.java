package business;

import java.time.LocalDate;

public class CheckoutRecord {
	private LocalDate cDate, dDate;
	private BookCopy b;
	
	public CheckoutRecord(LocalDate c, LocalDate d, BookCopy b) {
		b.changeAvailability();
		cDate = c; dDate = d; this.b = b;
	}
}
