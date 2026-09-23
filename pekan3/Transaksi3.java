package pekan3;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi3 {
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	String idTransaksi;
	String jenis;
	double nominal;
	
	//Constructor
	public Transaksi3 (String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	public String getIdTransaksi() { return idTransaksi; }
	public String getJenis() { return jenis; }
	public double getNominal() { return nominal; }
	
	public void cetakDetail() {
		System.out.println("ID: "+ idTransaksi+ " | Jenis: "+ jenis + " | Nominal: "+ rupiah.format(nominal));
	}
}
