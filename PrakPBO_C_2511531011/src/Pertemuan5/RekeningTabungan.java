package Pertemuan5;

public class RekeningTabungan extends Rekening {
	
	// atribut spesifik yang hanya dimiliki oleh tabungan
	private double sukuBunga;
	
	//constructor subclass
	public RekeningTabungan (String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		//super() memanggil constructor klas induk (Rekening). WAJIB berada di baris pertama
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		//menghitung bunga
		//mengapa bisa mengakses saldo secara langsung dari class RekeningTabungan?
		double nominalBunga = saldo * (sukuBunga/100);
		saldo += nominalBunga;
		
		//mencatat riwayat transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}

}
