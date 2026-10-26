package Pertemuan5;

public class RekeningGiro extends Rekening {
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	//implementasi polymorphism
	public boolean tarikTunai(double nominal) {	
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik giro harus lebih dari 0!");
			return false;
		} else if (nominal > (saldo + batasOverdraft)) {
			System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo + batasOverdraft));
			return false;
		} else {
			saldo-=nominal;
			
			String idTrx = "TRX_T_" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit Giro", nominal));
			
			System.out.println("Tarik tunai Giro Rp" + nominal + " berhasil. Sisa saldo Rp" + saldo);
			return true;
		}
	}
	
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
