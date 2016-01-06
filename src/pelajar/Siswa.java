package pelajar;

import java.util.*;
import iuran.*;

public abstract class Siswa{
	public Profil profil;
	
	public ArrayList<IuranOnce> iuran; //utk semua iuran yg sekali aja
	public ArrayList<IuranPeriodic> IPP[]; //perbulan
	public ArrayList<IuranPeriodic> IUS[]; //persemester (6 bln)
	public ArrayList<IuranPeriodic> OSIS[]; //perbulan
	public ArrayList<IuranPeriodic> IKK[];	//pertahun
	public ArrayList<IuranOnce> IKS[]; //dianggap ga periodic karna kadang2 aja
	public ArrayList<IuranOnce> DLL[]; //dianggap ga periodic karna kadang2 aja
	public ArrayList<IuranOnce> sumbangan[]; //dianggap ga periodic karna kadang2 aja
	
	public ArrayList<TransactionSummary> rangkumanTransaksi;
	
        public Siswa(){}
        
	public Siswa(Profil profil){
		this.profil = profil;
	}
}