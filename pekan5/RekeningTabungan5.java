package pekan5;

import pekan4.Rekening4;
import pekan4.Transaksi4;

public class RekeningTabungan5 extends Rekening5{
	//Atribut spesifik yang hanya dimiliki oleh Tabungan
	private double sukuBunga;
	
	//Constructor Subclass
	public RekeningTabungan5 (String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super (nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	public void tambahBungaAkhirBulan() {
		//Menghitung bunga
		//mengapa bisa mengakses saldo secara langsung dari class RekeningTabungan?
		double nominalBunga = saldo * (sukuBunga / 100);
		double bonus = 0;
		
		if (saldo > 10000000) {
            bonus = saldo * 0.01; // Bonus 1% untuk nasabah prioritas (bisa disesuaikan)
        }
		saldo += nominalBunga + bonus;
		
		//Mencatat riwaya transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi5 (idTrx, "Bunga", nominalBunga));
		
		if (bonus > 0) {
            String idTrxBonus = "TRX-BONUS-" + System.currentTimeMillis();
            riwayatTransaksi.add(new Transaksi5(idTrxBonus, "Bonus Bunga Prioritas", bonus));
        }
		 System.out.println("=== Simulasi Akhir Bulan ===");
	        System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: " + rupiah.format(nominalBunga));
	        
	        if (bonus > 0) {
	            System.out.println("[CHALLENGE] Bonus Nasabah Prioritas (Saldo > 10jt): " + rupiah.format(bonus));
	        }
	        
	        System.out.println("Saldo Akhir Anda: " + rupiah.format(saldo));
	        System.out.println("============================");
	    }
	}

