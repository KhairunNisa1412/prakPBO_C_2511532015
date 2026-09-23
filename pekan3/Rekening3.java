package pekan3;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;


public class Rekening3 {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	ArrayList<Transaksi3> riwayatTransaksi;
	
	int percobaanPinGagal = 0;
	boolean statusBlokir = false;
	static final int maks_percobaan= 3;
	
	NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
	
	public Rekening3 (String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		//Validasi PIN di dalan Constructor
		if (pinAwal.length()== 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		// wajib menginisialisasi ArrayList di dalam konstruktor agar tidak NullPointerException
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println ("Rekening atas nama "+ namaPemilik +" berhasil dibuat dengan saldo " + rupiah.format(saldo));
	}
	//Getter untuk atribut ynag diizinkan dibaca publik
	public String getNomorRekening() { return nomorRekening;}
	public String getNamaPemilik() { return namaPemilik;}
	
	//4. Method Otentikasi Internal (Validasi Enkapsulasi)
	public boolean otentikasi (String inputPin) {
		return this.pin.equals(inputPin);
	}
	public void setorTunai (double nominal) {
		if (nominal >= 10000 && nominal <= 500000 ) {
			saldo += nominal;
			//merekam riwayat (pembuatan objek transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi3 trxBaru = new Transaksi3 (idTrx, "Kredit", nominal);
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
			Transaksi3 trxBaru = new Transaksi3 (idTrx, "Debit", nominal);
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
	        for (Transaksi3 trx : riwayatTransaksi) {
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
	    for (Transaksi3 trx : riwayatTransaksi) {
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
	public static boolean validasiPin(String pin) {
        boolean semuaSama = true;
        boolean berurutanNaik = true;
        boolean berurutanTurun = true;

        for (int i = 1; i < 6; i++) {
            if (pin.charAt(i) != pin.charAt(0)) semuaSama = false;
            if (pin.charAt(i) != pin.charAt(i - 1) + 1) berurutanNaik = false;
            if (pin.charAt(i) != pin.charAt(i - 1) - 1) berurutanTurun = false;
        }

        if (semuaSama || berurutanNaik || berurutanTurun) {
            return false; 
        }
        return true; 
    }
	
	
	public void cekInformasi() {
		System.out.println ("--- INFO REKENING ---");
		System.out.println ("No. Rekening: "+ nomorRekening);
		System.out.println ("Nama Pemilik: "+ namaPemilik);
		System.out.println ("Saldo Akhir: "+ rupiah.format(saldo));
		
		System.out.println ("--------------------");
	}
}
