package util;


public class CardFactory {

	private final static CardFactory cardFatory = new CardFactory();
	
	
	private CardFactory(){
	}
	
	public static CardFactory getInstance(){
		return cardFatory;
	}
	
}
