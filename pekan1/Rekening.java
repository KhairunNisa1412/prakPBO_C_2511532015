package pekan1;

import java.text.NumberFormat;
import java.util.Locale;


public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	
	public Rekening (String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println ("Rekening atas nama "+ namaPemilik +" berhasil dibuat dengan saldo Rp" + rupiah.format(saldo));
	}
	public void setorTunai (double nominal) {
		if (nominal > 10000) {
			saldo += nominal;
			System.out.println ("Setor tunai Rp"+ nominal +" berhasil. Saldo saat ini Rp"+ rupiah.format(saldo));
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 10000!");
		}
	}
	public void tarikTunai (double nominal) {
		if (nominal < 10000) {
			System.out.println ("Transaksi gagal, minimal nominal penarikan Rp10.000");
		} else if (nominal > saldo ){
			System.out.println ("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+ rupiah.format(saldo));
		} else {
			saldo -= nominal;
			System.out.println ("Tarik tunai Rp" + rupiah.format (nominal)+" berhasil. Saldo saat ini Rp" + rupiah.format(saldo));
		}

	}
	public void cekInformasi() {
		System.out.println ("--- INFO REKENING ---");
		System.out.println ("No. Rekening: "+ nomorRekening);
		System.out.println ("Nama Pemilik: "+ namaPemilik);
		System.out.println ("Saldo Akhir: "+ rupiah.format(saldo));
		
		System.out.println ("--------------------");
	}
}
