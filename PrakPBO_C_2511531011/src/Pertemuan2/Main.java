package Pertemuan2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; //objek blm di inisialisasi (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {		// Membuat menu untuk sistem perbankan mini
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Rekening");
			System.out.println("6. Cek Mutasi");
			System.out.println("7. Tampilkan 3 Transaksi Terbaru");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); //memberihkan buffer enter
			
			switch (pilihan) {
				case 1 :
					System.out.print("Masukkan No Rekening: ");
					String no = input.nextLine();
					
					boolean nomorSudahAda = false;
					
					for (Rekening rekening : daftarRekening) {		// Tambahan agar pembuatan rekening tidak menumpuk satu nomor
						if (rekening.getNomorRekening().equals(no)) {
							nomorSudahAda = true;
							break;
						}
					}
					
					if (nomorSudahAda) {
						System.out.println("Error: Rekening dengan nomor " + no + " sudah ada!");
					} else {
						System.out.print("Masukkan Nama Pemilik: ");		// Bagian dari praktikum
						String nama = input.nextLine();
						System.out.print("Masukkan Saldo Awal: ");
						double saldo = input.nextDouble();
						
						if (saldo >= 50000) {
							//Membuat objek rekening baru
							Rekening rekeningBaru = new Rekening(no, nama, saldo);
							
							//Bagian dari tugas kedua untuk menambahkan rekening baru ke dalam array
							daftarRekening.add(rekeningBaru);
							
							//Instalasi objek atau menjalankan konstruktor
							akunAktif = rekeningBaru;
						} else {
						System.out.println("Saldo anda minimal harus 50000!");		// Challenge agar nominal setor minimal 50000
						}
					}
					
					break;
					
				case 2 :
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else {
						System.out.print("Masukkan nominal setor: ");
						double setor = input.nextDouble();
						akunAktif.setorTunai(setor);
					}
					break;
					
				case 3 :		// Bagian dari tugas pertama untuk tarik tunai
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
					} else {
						System.out.print("Masukkan nominal tarik tunai: ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);
					}
					break;
					
				case 4 :
					if (akunAktif == null) {
						System.out.println("Error: Anda belum membuka rekening!");
					} else {
						akunAktif.cekInformasi();
					}
					break;
					
				case 5:		// Bagian dari tugas kedua untuk mengganti rekening aktif ke nomor rekening yang lain
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
					akunAktif.cetakMutasi();
					break;
					
				case 7:
					akunAktif.cetakMutasiTiga();
					
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
