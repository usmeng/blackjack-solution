package bean;



public class Player extends Person{

	private int originalChip;
	private boolean spilt;
	private HandCard splitHand;

	public Player(String name, int chip) {
		super(name, chip);
		originalChip = chip;
	}

	public boolean isSpilted() {
		return spilt;
	}
	
	public void setSplit(boolean split){
		this.spilt = split;
	}
	
	public void split() {
		this.spilt = true;
		HandCard hand = getHandCard();
		if(hand.size() == 2 && hand.get(0).getPoint() == (hand.get(1).getPoint())) {
			Card card = hand.remove(1);
			splitHand = new HandCard();
			splitHand.addCard(card);
			splitHand.setBetChip(hand.getBetChip());
		}
	}
	
	public HandCard getSplitHand(){
		return splitHand;
	}

	/*public int getBetChip() {
		return getHandCard().bet;
	}

	public int getSpiltBetChip(){
		return splitHand != null ? splitHand.bet : 0;
	}*/

	public void setBetChip(int betChip) {
		getHandCard().bet = betChip;
	}
	
	public void setSpiltBetChip(int betChip) {
		if(splitHand != null) splitHand.bet = betChip;
	}
	
	public int getTotalBetChip(){
		if(splitHand == null) return getHandCard().bet;
		return getHandCard().bet + splitHand.bet;
	}
	
	public int getOriginalChip(){
		return originalChip;
	}

}
