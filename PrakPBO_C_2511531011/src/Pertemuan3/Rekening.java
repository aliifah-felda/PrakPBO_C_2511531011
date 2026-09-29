package Pertemuan3;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Rekening {		// Membuat class rekening dengan rekening.java
	private String nomorRekening;	// Membuat state/atribut
	private String namaPemilik;
	private double saldo;
	private String pin; //data sensitif
	
	//challenge
	private int salahPin;
	private boolean terkunci;
	
	NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);		// Challenge Number format untuk mengubah 50000 menjadi 50.000
	
	private ArrayList<Transaksi> riwayatTransaksi; //membuat array untuk menyimpan data
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {		// Membuat konstruktor rekening
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		this.salahPin = 0;
		this.terkunci = false;
		
		if (pinAwal.length()==6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		this.riwayatTransaksi = new ArrayList<>(); //menginstall arraylist dalam constructor agar tidak NullPointerException
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + nf.format(saldo));
	}
	
	// getter untuk atribut yang diizinkan dibaca publik
	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }
	
	// method otentikasi internal (validasi enkapsulasi)
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	public void setorTunai(double nominal) {		// Membuat method setor tunai
		if (nominal > 0 && nominal <= 500000) {
			saldo += nominal;
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nf.format(nominal) + " berhasil. Saldo saat ini: Rp"+ nf.format(saldo));
		} else if (nominal <= 0) {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		} else {
			System.out.println("Gagal: Nominal setor harus kurang dari Rp500.000!");
		}
	}
	
	public void tarikTunai(double nominal) {		// Membuat method tarik tunai disini adalah tugas pertama
		if (nominal > 0) {
			if (nominal > saldo) {		// Agar saldo tidak bisa minus
				System.out.println("Transaksi gagal: Saldo tidak mencukupi. Saldo anda Rp" + nf.format(saldo));
			} else {
				saldo -= nominal;
				String idTrx = "TRX-T-" + System.currentTimeMillis();
				Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
				riwayatTransaksi.add(trxBaru);
				System.out.println("Tarik tunai Rp" + nf.format(nominal) + " berhasil. Saldo saat ini: Rp"+ nf.format(saldo));
			}
		} else {
			System.out.println("Gagal: Nominal tarik tunai harus lebih dari 0!");
		}
	}
	
	public void cekInformasi() {		// Membuat method cek informasi
		System.out.println("--- INFO PENTING ---");
		System.out.println("No. Rekening	: " + nomorRekening);
		System.out.println("Nama Pemilik	: " + namaPemilik);
		System.out.println("Saldo Akhir	: Rp" + nf.format(saldo));
		System.out.println("------------------------");
	}
	
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
		} else {
			System.out.println("--- MUTASI REKENING ---");
			for (Transaksi transaksi : riwayatTransaksi) {
				transaksi.cetakDetail();
			}
		}
	}
	
	public void cetakMutasiTiga() { //bagiann dari challenge
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Tidak ada transaksi pada rekening ini");
		}
		
		System.out.println("--- 3 Transaksi Terakhir");
		int mulai = Math.max(0, riwayatTransaksi.size()-3);
		
		for (int i=mulai; i < riwayatTransaksi.size(); i++ ) {
			riwayatTransaksi.get(i).cetakDetail();
		}
	}
	
	public boolean pinSalah3Kali(String inputPin) {
		if (this.pin.equals(inputPin)) {
			salahPin = 0;
			return true;
		}
		salahPin++;
		if (salahPin >= 3) {
				terkunci = true;
				System.out.println("Akun anda terblokir!");
		}
		return false;
	}
	
	public boolean isTerkunci() {
		return terkunci;
	}

}
