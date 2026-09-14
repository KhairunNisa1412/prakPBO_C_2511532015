package pekan1;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; //Objek belum diinisialisasikan (null)
		boolean isRunning = true;
		
		System.out.println ("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println ("\nMenu Utama: ");
			System.out.println ("1. Buku Rekening Baru");
			System.out.println ("2. Setor Tunai");
			System.out.println ("3. Tarik Tunai");
			System.out.println ("4. Cek Informasi Rekening");
			System.out.println ("5. Ganti akun");
			System.out.println ("0. Keluar");
			System.out.println ("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.print ("Masukkan No. Rekening: ");
				String no= input.nextLine();
				System.out.print ("Masukkan Nama Pemilik: ");
				String nama= input.nextLine();
				System.out.print ("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				
				Rekening akunBaru = new Rekening (no, nama, saldo);
				daftarRekening.add(akunBaru);
				akunAktif = akunBaru;
				System.out.println("Akun behasil ditambahkan dan otomatis mrnjadi akun aktif");
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomoer rekening!");
				} else {
					System.out.print ("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					System.out.print ("Masukkan nominal tarik tunai: ");
					double tarik = input.nextDouble();
					akunAktif.tarikTunai(tarik);
				}
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println ("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (daftarRekening.isEmpty()) {
					System.out.println("Belum ada akun yang terdaftar");
				} else {
					System.out.println("Masukkan no.Rekening yang ingin diakses: ");
					String cariNo = input.nextLine();
					
					boolean ditemukan = false;
					for (Rekening r : daftarRekening) {
						if (r.nomorRekening.equals(cariNo)) {
						akunAktif = r;
						ditemukan = true;
						System.out.println("Berhasil beralih ke rekening atas nama: "+ akunAktif.namaPemilik);
						break;
						}
					}
					if (!ditemukan) {
						System.out.println("Nomor rekening tidak ditemukan");
					
					}
				}
				break;
				
			case 0:
				isRunning = false;
				System.out.println ("Sistem ditutup. Terima kasih!");
				break;
				
			default:
				System.out.println ("Pilihan tidak valid");
			}
			
		}
		input.close();
	}
}
