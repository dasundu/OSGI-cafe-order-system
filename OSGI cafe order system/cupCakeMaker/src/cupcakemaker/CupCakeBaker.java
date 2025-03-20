package cupcakemaker;

import com.cafe.api.CupCakeService;

public class CupCakeBaker implements CupCakeService {

	@Override
	public void makeCupCake(String customerName, String flavor) {
		 System.out.println(" CUPCAKE BAKER = Made " + flavor + " cupcake for " + customerName);

	}

}
