package pekan4;

import java.util.Scanner;
import java.util.ArrayList;

public class Main4 {
	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		ArrayList<Rekening4> daftarRekening = new ArrayList<>();
		Rekening4 akunAktif = null; //Objek belum diinisialisasikan (null)
		boolean isRunning = true;
		
		System.out.println ("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println ("\nMenu Utama: ");
			System.out.println ("1. Buku Rekening Baru");
			System.out.println ("2. Setor Tunai");
			System.out.println ("3. Tarik Tunai");
			System.out.println ("4. Cek Informasi Rekening");
			System.out.println ("5. Ganti akun");
			System.out.println ("6. Cetak Mutasi (Riwayat): ");
			System.out.println ("7. Challange 3: ");
			System.out.println ("8. Ringkasan dan Akumulasi: ");
			System.out.println ("9. Ganti PIN");
			System.out.println ("10. Simulasi Akhir Bulan (Khusus Tabungan)");
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
				String pin = "";
				boolean pinValid = false;
				
				while (!pinValid) {
                    System.out.print("Masukkan PIN anda (6 digit): ");
                    pin = input.nextLine();
                    
                    if (Rekening4.validasiPin(pin)) {
                        pinValid = true;
                    } else {
                        System.out.println("PIN tidak valid!");
                        System.out.println("PIN harus kombinasi acak");
                    }
                }
				System.out.println("\n--- Pilih Jenis Produk Rekening ---");
			    System.out.println("Pilih Produk: 1. \r\n"
			    		+ "Tabungan Umum | 2. Giro Bisnis");
			    System.out.print("Pilihan Anda (1/2): ");
			    int pilihanProduk = input.nextInt();
			    input.nextLine();
			    
			    Rekening4 akunBaru = null; 
			    
			    if (pilihanProduk == 1) {
			        System.out.print("Masukkan Suku Bunga per tahun (%): ");
			        double sukuBunga = input.nextDouble();
			        input.nextLine(); // Bersihkan buffer
			        
			        // Instansiasi objek Tabungan (Subclass)
			        akunBaru = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
			        System.out.println("Rekening Tabungan berhasil dibuat!");
			        
			    } else if (pilihanProduk == 2) {
			        System.out.print("Masukkan Batas Overdraft (Limit Pinjaman): ");
			        double batasOverdraft = input.nextDouble();
			        input.nextLine(); // Bersihkan buffer
			        
			        // Instansiasi objek Giro (Subclass)
			        akunBaru = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);
			        System.out.println("Rekening Giro berhasil dibuat!");
			        
			    } else {
			        System.out.println("Pilihan tidak valid. Sistem membatalkan pembuatan akun.");
			        break; // Keluar dari case jika pilihan salah
			    }
				
			    daftarRekening.add(akunBaru);
			    akunAktif = akunBaru;
			    System.out.println("Akun berhasil ditambahkan dan otomatis menjadi akun aktif.");
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
					System.out.print ("Masukkan PIN untuk verifikasi: ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						System.out.print ("Masukkan nominal tarik tunai: ");
						double tarik = input.nextDouble();
						input.nextLine();
						akunAktif.tarikTunai(tarik);
					} else {
						System.out.println("Akses ditolak: Pin yang anda masukkan salah!");
					} 
					
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
					for (Rekening4 r : daftarRekening) {
						if (r.getNomorRekening().equals(cariNo)) {
						akunAktif = r;
						ditemukan = true;
						System.out.println("Berhasil beralih ke rekening atas nama: "+ akunAktif.getNamaPemilik());
						break;
						}
					}
					if (!ditemukan) {
						System.out.println("Nomor rekening tidak ditemukan");
					
					}
				}
				break;
				
			
			case 6: 
				if (akunAktif == null) {
					System.out.println ("Belum ada transaksi pada rekening ini");
				} else {
					System.out.print ("Masukkan PIN untuk verifikassi: ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses ditolak: Pin yang dimasukkan salah");
					}
				}
				break;
					
			case 0:
				isRunning = false;
				System.out.println ("Sistem ditutup. Terima kasih!");
				break;
				
			case 7: 
				if (akunAktif == null) {
					System.out.println ("Belum ada transaksi pada rekening ini");
				} else {
					akunAktif.cetakRiwayat();
				}
				break;
			
			case 8:
				if (akunAktif == null) {
					System.out.println ("Belum ada transaksi pada rekening ini");
				} else {
					akunAktif.cetakRingkasan();
				}
				break;
				
			case 10:
				if (akunAktif == null) {
					System.out.println ("Anda belum membuka tabungan");
				} else {
					if (akunAktif instanceof RekeningTabungan){
						RekeningTabungan akunTabungan = (RekeningTabungan) akunAktif;
						akunTabungan.tambahBungaAkhirBulan();
					} else {
						System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan");
					}
				}
				break;
				
			default:
				System.out.println ("Pilihan tidak valid");
			}
			
		}
		input.close();
	}
}
