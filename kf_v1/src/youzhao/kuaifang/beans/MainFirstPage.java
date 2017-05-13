package youzhao.kuaifang.beans;

import com.google.gson.Gson;

public class MainFirstPage {
	private String GX;
	private String CS;
	private KF KF;
	private SY SY;
	private WZ WZ;
	private String MU;
	private SYSJ[] SYSJ;
	
	public static MainFirstPage parseMainFirstPage(String json){
		return new Gson().fromJson(json, MainFirstPage.class);	
	}
	
	public String getGX() {
		return GX;
	}
	public void setGX(String gX) {
		GX = gX;
	}
	public String getCS() {
		return CS;
	}
	public void setCS(String cS) {
		CS = cS;
	}
	public KF getKF() {
		return KF;
	}
	public void setKF(KF kF) {
		KF = kF;
	}
	public SY getSY() {
		return SY;
	}
	public void setSY(SY sY) {
		SY = sY;
	}
	public WZ getWZ() {
		return WZ;
	}
	public void setWZ(WZ wZ) {
		WZ = wZ;
	}
	public String getMU() {
		return MU;
	}
	public void setMU(String mU) {
		MU = mU;
	}
	public SYSJ[] getSYSJ() {
		return SYSJ;
	}
	public void setSYSJ(SYSJ[] sYSJ) {
		SYSJ = sYSJ;
	}
}
