package strategy;

import bean.Card;
import bean.HandCard;

public class Rule {
	
	public static final int MAXIMUM_POINT = 21;
	private static final int ACE = 11;
	
	public enum ResultValue {
		_WIN("win"), LOSE("lose"), EVEN("even");
		
		private ResultValue(String value){}
		
	}
	public static int setPoint(Card card){
		if(card == null) return 0;
		if(card.getValue() >= 10) return 10;
		if(card.getValue() > 1) return card.getValue();
		return ACE;
	}
	
	public static boolean isBlackJack(HandCard cards){
		if(cards == null) return false;
		int point = 0;
		for (Card card : cards.handCards) {
			point += card.getPoint();
		}
		return cards.size() == 2 && point == MAXIMUM_POINT;
	}
	
	public static boolean isBurst(HandCard cards){
		return calPoints(cards) > MAXIMUM_POINT;
	}
	
	public static int calPoints(HandCard hands){
		if(hands == null) return 0;
		int point = 0, aceTimes = 0;
		for (Card card : hands.handCards) {
			int cardPoint = card.getPoint();
			if(cardPoint == Card.ACE_POINT) aceTimes++;
			point += cardPoint;
		}
		while(point > MAXIMUM_POINT && aceTimes > 0) {
			point -= 10;
			aceTimes--;
		}
		return point;
	}
	
}
