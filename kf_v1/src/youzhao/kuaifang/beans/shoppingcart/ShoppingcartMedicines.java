package youzhao.kuaifang.beans.shoppingcart;

import com.google.gson.Gson;

public class ShoppingcartMedicines {
	private Medicine[] medicines;
	
	public static ShoppingcartMedicines parseMedicine(String json){
		return new Gson().fromJson(json, ShoppingcartMedicines.class);
	}

	public Medicine[] getMedicines() {
		return medicines;
	}

	public void setMedicines(Medicine[] medicines) {
		this.medicines = medicines;
	}
	
}
