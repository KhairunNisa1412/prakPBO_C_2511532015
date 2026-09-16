package pekan;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;


public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	ArrayList<Transaksi> riwayatTransaksi;
	
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	
	public Rekening (String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		// wajib menginisialisasi ArrayList di dalam konstruktor agar tidak NullPointerException
		this.riwayatTransaksi = new ArrayList<>();
				
		System.out.println ("Rekening atas nama "+ namaPemilik +" berhasil dibuat dengan saldo " + rupiah.format(saldo));
	}
	public void setorTunai (double nominal) {
		if (nominal >= 10000 && nominal <= 500000 ) {
			saldo += nominal;
			//merekam riwayat (pembuatan objek transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println ("Setor tunai "+ nominal +" berhasil. Saldo saat ini "+ rupiah.format(saldo));
		} else if (nominal < 10000) {
			System.out.println("Gagal: Nominal setor minimal 10000");
		} else {
			System.out.println("Gagal: Nominal setor maksimal 50000");
		}
	}
	public void tarikTunai (double nominal) {
		if (nominal < 10000) {
			System.out.println ("Transaksi gagal, minimal nominal penarikan Rp10.000");
		} else if (nominal > saldo ){
			System.out.println ("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: "+ rupiah.format(saldo));
		} else {
			saldo -= nominal;
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi (idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println ("Tarik tunai Rp" + rupiah.format (nominal)+" berhasil. Saldo saat ini " + rupiah.format(saldo));
		}

	}
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini");
	    } else {
	    	System.out.println("----- Mutasi Rekening -----");
	        // Menggunakan perulangan for-each untuk menelusuri riwayat
	        for (Transaksi trx : riwayatTransaksi) {
	            trx.cetakDetail();
	        }
	        System.out.println("----------------------------");
	    }
	}
	
	public void cetakRiwayat() {
		if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini");
	    } else {
	    	System.out.println("----- 3 Transaksi Terbaru -----");
	        int jumlah = Math.min(3,  riwayatTransaksi.size());
	        for (int i= riwayatTransaksi.size() - 1; i >= riwayatTransaksi.size() - jumlah; i-- ) {
	        	riwayatTransaksi.get(i).cetakDetail();
	        }
	        System.out.println("----------------------------");
	    }
	       	}
	public void cetakRingkasan() {
		if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini.");
	        return;
	    }

	    double totalSetor = 0;
	    double totalTarik = 0;
	    for (Transaksi trx : riwayatTransaksi) {
	        if (trx.jenis.equalsIgnoreCase("Kredit")) {
	            totalSetor += trx.nominal;
	        } else if (trx.jenis.equalsIgnoreCase("Debit")) {
	            totalTarik += trx.nominal;
	        }
	    }

	    double akumulasiMutasi = totalSetor + totalTarik;

	    System.out.println("No. Rekening   : " + nomorRekening);
	    System.out.println("Nama Pemilik   : " + namaPemilik);
	    System.out.println("Total Setor    : " + rupiah.format(totalSetor));
	    System.out.println("Total Tarik    : " + rupiah.format(totalTarik));
	    System.out.println("Akumulasi Trx  : " + rupiah.format(akumulasiMutasi));
	    System.out.println("Saldo Saat Ini : " + rupiah.format(saldo));
	}
	
	public void cekInformasi() {
		System.out.println ("--- INFO REKENING ---");
		System.out.println ("No. Rekening: "+ nomorRekening);
		System.out.println ("Nama Pemilik: "+ namaPemilik);
		System.out.println ("Saldo Akhir: "+ rupiah.format(saldo));
		
		System.out.println ("--------------------");
	}
}
