package Pertemuan1;

import java.text.NumberFormat;
import java.util.Locale;

public class Rekening {		// Membuat class rekening dengan rekening.java
	String nomorRekening;	// Membuat state/atribut
	String namaPemilik;
	double saldo;
	NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);		// Challenge Number format untuk mengubah 50000 menjadi 50.000
	
	public Rekening(String nomor, String nama, double saldoAwal) {		// Membuat konstruktor rekening
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat dengan saldo Rp" + nf.format(saldo));
	}
	
	public void setorTunai(double nominal) {		// Membuat method setor tunai
		if (nominal > 0) {
			saldo += nominal;
			System.out.println("Setor tunai Rp" + nf.format(nominal) + " berhasil. Saldo saat ini: Rp"+ nf.format(saldo));
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
	public void tarikTunai(double nominal) {		// Membuat method tarik tunai disini adalah tugas pertama
		if (nominal > 0) {
			if (nominal > saldo) {		// Agar saldo tidak bisa minus
				System.out.println("Transaksi gagal: Saldo tidak mencukupi. Saldo anda Rp" + nf.format(saldo));
			} else {
				saldo -= nominal;
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
	
	public String getNomorRekening() {		// Method tambahan untuk tugas menyimpan nomor rekening menggunakan array
		return nomorRekening;
	}

}
