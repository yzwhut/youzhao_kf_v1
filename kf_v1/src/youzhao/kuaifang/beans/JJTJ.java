package youzhao.kuaifang.beans;

public class JJTJ {
	private String TL;
	private String PC;
	private MM[] MM;
	public String getTL() {
		return TL;
	}
	public void setTL(String tL) {
		TL = tL;
	}
	public String getPC() {
		return PC;
	}
	public void setPC(String pC) {
		PC = pC;
	}
	public MM[] getMM() {
		return MM;
	}
	public void setMM(MM[] mM) {
		MM = mM;
	}
	
	public class MM{
		private String PID;
		private String TL;
		private String PC;
		private String MS;
		private String MM;
		private String PR;
		public String getPID() {
			return PID;
		}
		public void setPID(String pID) {
			PID = pID;
		}
		public String getTL() {
			return TL;
		}
		public void setTL(String tL) {
			TL = tL;
		}
		public String getPC() {
			return PC;
		}
		public void setPC(String pC) {
			PC = pC;
		}
		public String getMS() {
			return MS;
		}
		public void setMS(String mS) {
			MS = mS;
		}
		public String getMM() {
			return MM;
		}
		public void setMM(String mM) {
			MM = mM;
		}
		public String getPR() {
			return PR;
		}
		public void setPR(String pR) {
			PR = pR;
		}
		
		
	}
}
