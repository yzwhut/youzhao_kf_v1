package youzhao.kuaifang.beans.goodDetail;

import com.google.gson.Gson;

public class GoodDetail {
	private String ID;
	private String TL;
	private String ATY;
	private String PC1;
	private String PC2;
	private String PC3;
	private String PC4;
	private String PC5;
	private String PC6;
	private String SM;
	private String BC;
	private String UT;
	private String TY;
	private String MM_NA;
	private String MM_CF;
	private String MM_XZ;
	private String MM_ZZ;
	private String MM_MS;
	private String MM_YF;
	private String MM_BL;
	private String MM_JJ;
	private String MM_SX;
	private String MM_ZY;
	private String MM_CC;
	private String MM_BZ;
	private String MM_XQ;
	private String MM_AN;
	private String KFPRIC;
	private String PR;
	private String XSPR;
	private String MM_CY;
	private String URL;
	private String XGSP;
	private String SPXQTP;
	private String CFZT;
	private String QTY;
	private String TJBJ;
	private TM[] TM;
	private String CX;
	private String ST;
	private String XSMM;
	private String SC;
	private String FX;
	private String YPR;
	private String YID;
	private String YDTL;
	private String YDPC;
	private String CPSM;
	private String BNT1;
	private String BNT2;
	private String BNT3;
	
	public static GoodDetail parseGoodDetail(String json){
		return new Gson().fromJson(json, GoodDetail.class);
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTL() {
		return TL;
	}
	public void setTL(String tL) {
		TL = tL;
	}
	public String getATY() {
		return ATY;
	}
	public void setATY(String aTY) {
		ATY = aTY;
	}
	public String getPC1() {
		return PC1;
	}
	public void setPC1(String pC1) {
		PC1 = pC1;
	}
	public String getPC2() {
		return PC2;
	}
	public void setPC2(String pC2) {
		PC2 = pC2;
	}
	public String getPC3() {
		return PC3;
	}
	public void setPC3(String pC3) {
		PC3 = pC3;
	}
	public String getPC4() {
		return PC4;
	}
	public void setPC4(String pC4) {
		PC4 = pC4;
	}
	public String getPC5() {
		return PC5;
	}
	public void setPC5(String pC5) {
		PC5 = pC5;
	}
	public String getPC6() {
		return PC6;
	}
	public void setPC6(String pC6) {
		PC6 = pC6;
	}
	public String getSM() {
		return SM;
	}
	public void setSM(String sM) {
		SM = sM;
	}
	public String getBC() {
		return BC;
	}
	public void setBC(String bC) {
		BC = bC;
	}
	public String getUT() {
		return UT;
	}
	public void setUT(String uT) {
		UT = uT;
	}
	public String getTY() {
		return TY;
	}
	public void setTY(String tY) {
		TY = tY;
	}
	public String getMM_NA() {
		return MM_NA;
	}
	public void setMM_NA(String mM_NA) {
		MM_NA = mM_NA;
	}
	public String getMM_CF() {
		return MM_CF;
	}
	public void setMM_CF(String mM_CF) {
		MM_CF = mM_CF;
	}
	public String getMM_XZ() {
		return MM_XZ;
	}
	public void setMM_XZ(String mM_XZ) {
		MM_XZ = mM_XZ;
	}
	public String getMM_ZZ() {
		return MM_ZZ;
	}
	public void setMM_ZZ(String mM_ZZ) {
		MM_ZZ = mM_ZZ;
	}
	public String getMM_MS() {
		return MM_MS;
	}
	public void setMM_MS(String mM_MS) {
		MM_MS = mM_MS;
	}
	public String getMM_YF() {
		return MM_YF;
	}
	public void setMM_YF(String mM_YF) {
		MM_YF = mM_YF;
	}
	public String getMM_BL() {
		return MM_BL;
	}
	public void setMM_BL(String mM_BL) {
		MM_BL = mM_BL;
	}
	public String getMM_JJ() {
		return MM_JJ;
	}
	public void setMM_JJ(String mM_JJ) {
		MM_JJ = mM_JJ;
	}
	public String getMM_SX() {
		return MM_SX;
	}
	public void setMM_SX(String mM_SX) {
		MM_SX = mM_SX;
	}
	public String getMM_ZY() {
		return MM_ZY;
	}
	public void setMM_ZY(String mM_ZY) {
		MM_ZY = mM_ZY;
	}
	public String getMM_CC() {
		return MM_CC;
	}
	public void setMM_CC(String mM_CC) {
		MM_CC = mM_CC;
	}
	public String getMM_BZ() {
		return MM_BZ;
	}
	public void setMM_BZ(String mM_BZ) {
		MM_BZ = mM_BZ;
	}
	public String getMM_XQ() {
		return MM_XQ;
	}
	public void setMM_XQ(String mM_XQ) {
		MM_XQ = mM_XQ;
	}
	public String getMM_AN() {
		return MM_AN;
	}
	public void setMM_AN(String mM_AN) {
		MM_AN = mM_AN;
	}
	public String getKFPRIC() {
		return KFPRIC;
	}
	public void setKFPRIC(String kFPRIC) {
		KFPRIC = kFPRIC;
	}
	public String getPR() {
		return PR;
	}
	public void setPR(String pR) {
		PR = pR;
	}
	public String getXSPR() {
		return XSPR;
	}
	public void setXSPR(String xSPR) {
		XSPR = xSPR;
	}
	public String getMM_CY() {
		return MM_CY;
	}
	public void setMM_CY(String mM_CY) {
		MM_CY = mM_CY;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getXGSP() {
		return XGSP;
	}
	public void setXGSP(String xGSP) {
		XGSP = xGSP;
	}
	public String getSPXQTP() {
		return SPXQTP;
	}
	public void setSPXQTP(String sPXQTP) {
		SPXQTP = sPXQTP;
	}
	public String getCFZT() {
		return CFZT;
	}
	public void setCFZT(String cFZT) {
		CFZT = cFZT;
	}
	public String getQTY() {
		return QTY;
	}
	public void setQTY(String qTY) {
		QTY = qTY;
	}
	public String getTJBJ() {
		return TJBJ;
	}
	public void setTJBJ(String tJBJ) {
		TJBJ = tJBJ;
	}
	public TM[] getTM() {
		return TM;
	}
	public void setTM(TM[] tM) {
		TM = tM;
	}
	public String getCX() {
		return CX;
	}
	public void setCX(String cX) {
		CX = cX;
	}
	public String getST() {
		return ST;
	}
	public void setST(String sT) {
		ST = sT;
	}
	public String getXSMM() {
		return XSMM;
	}
	public void setXSMM(String xSMM) {
		XSMM = xSMM;
	}
	public String getSC() {
		return SC;
	}
	public void setSC(String sC) {
		SC = sC;
	}
	public String getFX() {
		return FX;
	}
	public void setFX(String fX) {
		FX = fX;
	}
	public String getYPR() {
		return YPR;
	}
	public void setYPR(String yPR) {
		YPR = yPR;
	}
	public String getYID() {
		return YID;
	}
	public void setYID(String yID) {
		YID = yID;
	}
	public String getYDTL() {
		return YDTL;
	}
	public void setYDTL(String yDTL) {
		YDTL = yDTL;
	}
	public String getYDPC() {
		return YDPC;
	}
	public void setYDPC(String yDPC) {
		YDPC = yDPC;
	}
	public String getCPSM() {
		return CPSM;
	}
	public void setCPSM(String cPSM) {
		CPSM = cPSM;
	}
	public String getBNT1() {
		return BNT1;
	}
	public void setBNT1(String bNT1) {
		BNT1 = bNT1;
	}
	public String getBNT2() {
		return BNT2;
	}
	public void setBNT2(String bNT2) {
		BNT2 = bNT2;
	}
	public String getBNT3() {
		return BNT3;
	}
	public void setBNT3(String bNT3) {
		BNT3 = bNT3;
	}

	
}
