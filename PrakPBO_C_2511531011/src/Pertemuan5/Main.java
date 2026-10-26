package Pertemuan5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; 
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {		
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Rekening");
			System.out.println("6. Cek Mutasi");
			System.out.println("7. Simulasi Akhir Bulan (khusus tabungan)");
			System.out.println("8. Transfer ke Rekening Lain");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); 
			
			switch (pilihan) {
				case 1 :
					System.out.print("Masukkan No Rekening: ");
					String no = input.nextLine();
					
					boolean nomorSudahAda = false;
					
					for (Rekening rekening : daftarRekening) {		
						if (rekening.getNomorRekening().equals(no)) {
							nomorSudahAda = true;
							break;
						}
					}
					
					if (nomorSudahAda) {
						System.out.println("Error: Rekening dengan nomor " + no + " sudah ada!");
					} else {
						System.out.print("Masukkan Nama Pemilik: ");
						String nama = input.nextLine();
						System.out.print("Masukkan Saldo Awal: ");
						double saldo = input.nextDouble();
						input.nextLine();
						System.out.print("Masukkan PIN awal: ");
						String pin = input.nextLine();
						
						
						if (saldo >= 50000) {
							System.out.println("Pilihan Produk");
							System.out.println("1. Tabungan Umum");
							System.out.println("2. Giro Bisnis");
							System.out.println("3. Rekening VIP");
							System.out.print("Pilih produk: ");
							int pilihRek = input.nextInt();
							input.nextLine();
							
							switch (pilihRek) {
							case 1:
								System.out.print("Masukkan suku bunga dalam persen: ");
								double sukuBunga = input.nextDouble();
								RekeningTabungan rekTabungan = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
								daftarRekening.add(rekTabungan);
								akunAktif = rekTabungan;
								break;
							
							case 2:
								System.out.print("Masukkan batas Overdraft (limit pinjaman): ");
								double batasOverdraft = input.nextDouble();
								RekeningGiro rekGiro = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);
								daftarRekening.add(rekGiro);
								akunAktif = rekGiro;
								break;
								
							case 3:
								double bonus = 0;
								RekeningVIP rekVIP = new RekeningVIP(no, nama, saldo, pin, bonus);
								daftarRekening.add(rekVIP);
								akunAktif = rekVIP;
								break;
							}	
						} else {
						System.out.println("Saldo anda minimal harus 50000!");
						}
					}					
					break;
					
				case 2 :
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else if (akunAktif.isTerkunci()) {
						System.out.println("Setor tunai tidak dapat dilakukan\nAkun anda Terblokir!");
						break;
					} else {
						System.out.print("Masukkan nominal setor: ");
						double setor = input.nextDouble();
						akunAktif.setorTunai(setor);
					}
					break;
					
				case 3 :	
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else if (akunAktif.isTerkunci()) {
						System.out.println("Akun anda terblokir!");
						break;
					} else {
						System.out.print("Masukkan PIN: ");
						String pinTarik = input.nextLine();
						if (akunAktif.pinSalah3Kali(pinTarik)) {
								System.out.print("Masukkan nominal tarik tunai: ");
								double tarik = input.nextDouble();
								akunAktif.tarikTunai(tarik);
							} else {
								System.out.println("Akses Ditolak: PIN yang anda masukkan salah!");
						}
					}
	
					break;
					
				case 4 :
					if (akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					} else {
						akunAktif.cekInformasi();
					}
					break;
					
				case 5:	
					if (daftarRekening.isEmpty()) {
						System.out.println("Belum ada rekening yang tersedia!");
					} else {
						System.out.print("Masukkan nomor rekening yang ingin digunakan: ");
						String noCari = input.nextLine();
						
						boolean ditemukan = false;
						
						for (Rekening rekening : daftarRekening) {
							if (rekening.getNomorRekening().equals(noCari)) {
								akunAktif = rekening;
								ditemukan = true;
								System.out.println("Berhasil mengganti akun ke "+noCari);
								break;
							}
						}
						if (!ditemukan) {
							System.out.println("Nomor rekening tidak ditemukan!");
						}
					}
					break;
					
				case 6:
					System.out.print("Masukkan PIN: ");
					String pin = input.nextLine();
					if (akunAktif.otentikasi(pin)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang anda masukkan salah!");
					}
					break;
					
				case 7:
					if (akunAktif instanceof RekeningTabungan) {
						((RekeningTabungan) akunAktif).tambahBungaAkhirBulan();
					} else {
						System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan");
					}
					break;
					
				case 8:
					if (akunAktif == null) {
						System.out.println("Anda belum punya akun aktif!");
						break;
					}
					System.out.print("Masukkan nomor rekening tujuan: ");
					String noTujuan = input.nextLine();
					
					Rekening akunTujuan = null;
					for (Rekening rekening : daftarRekening) {
						if (rekening.getNomorRekening().equals(noTujuan)) {
							akunTujuan = rekening;
							break;
						}
					}
					
					if (akunTujuan == null) {
						System.out.println("Nomor rekening tujuan tidak ditemukan!");
					} else {
						System.out.print("Masukkan nominal transfer: ");
						double nominal = input.nextDouble();
						input.nextLine();
						System.out.print("Masukkan PIN: ");
						String pinTransfer = input.nextLine();
						akunAktif.transfer(akunTujuan, nominal, pinTransfer);
					}
					break;
					
				case 0 :
					isRunning = false;
					System.out.println("Sistem ditutup. Terima kasih!");
					break;
					
				default :
					System.out.println("Pilihan tidak valid!");
					
			}
		}
		input.close();

	}

}
