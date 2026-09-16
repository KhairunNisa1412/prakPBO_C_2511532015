package pekan;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi {
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	String idTransaksi;
	String jenis;
	double nominal;
	
	//Constructor
	public Transaksi (String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public void cetakDetail() {
		System.out.println("ID: "+ idTransaksi+ " | Jenis: "+ jenis + " | Nominal: "+ rupiah.format(nominal));
	}
}
