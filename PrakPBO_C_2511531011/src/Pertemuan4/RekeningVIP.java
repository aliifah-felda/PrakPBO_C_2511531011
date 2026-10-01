package Pertemuan4;

public class RekeningVIP extends Rekening {
	public double bonus;
	
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal, double bonus) {
		//memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.bonus = bonus;
		
		bonus = 100000;
		saldo += bonus;
		
		System.out.println("Anda mendapatkan bonus saldo. Saldo saat ini Rp" + saldo);
	}
	
	public void bonusVIP() {
		//mencatat riwayat transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bonus VIP", bonus));
		
		System.out.println("Bonus Rekening VIP senilai" + bonus + "berhasil ditambahkan");
	}
	

}
