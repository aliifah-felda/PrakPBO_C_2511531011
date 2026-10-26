package Pertemuan5;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi {
	// mengubah semua atribut menjadi private
	private String idTransaksi;
	private String jenis;
	private double nominal;
	NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
	
	//construktor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	// hanya menyediakan getter (read-only)
	public String getIdTransaksi() { return idTransaksi;}
	public String getJenis() { return jenis;}
	public double getNominal() { return nominal;}
	
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nf.format(nominal));
	}
	
	

}
