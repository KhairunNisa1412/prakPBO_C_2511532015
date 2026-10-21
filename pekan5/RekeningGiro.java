package pekan5;

import pekan5.Rekening5;

public class RekeningGiro extends Rekening5{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari superClass
		super (nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	//implementasi plomorfisme (overriding)
	@Override
	public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
			return false;
		} else if (nominal > (saldo + batasOverdraft)) {
			System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo+batasOverdraft));
			return false;
		} else {
			saldo -= nominal; //saldo bernilai negatif (mamakan overdraft)
			String idTrx = "TRX-G" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi5(idTrx, "Debit Giro", nominal));
			
			System.out.println("Tarik tunak Giro Rp"+ nominal+ " berhasil. Saldo saat ini: Rp"+ saldo );
			return true;
		}
	}
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
}
