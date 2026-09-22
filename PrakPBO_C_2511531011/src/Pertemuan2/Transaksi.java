package Pertemuan2;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
	
	//construktor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public void cetakDetail() {
		System.out.println("ID: " + idTransaksi + " | Jenis: " + jenis + " | Nominal: Rp" + nf.format(nominal));
	}
	
	

}
