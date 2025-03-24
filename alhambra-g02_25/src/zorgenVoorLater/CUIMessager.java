package zorgenVoorLater;

public class CUIMessager implements MessagerInterface {

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

}
