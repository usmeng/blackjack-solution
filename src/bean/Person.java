package bean;

import strategy.hit.HitStrategy;

public class Person {

	private int id;
	private String name;
//	private List<Card> hand = new ArrayList<Card>();
	private HandCard handCard = new HandCard();
	private HitStrategy strategy;
	private int currentChip;

	public Person(String name) {
		this.name = name + "\t";
	}

	public Person(String name, int totalChip) {
		this.name = name + "\t";
		this.currentChip = totalChip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setHand(List<Card> hand) {
//		this.hand = hand;
//	}
//
//	public List<Card> getHand() {
//		return hand;
//	}

	public void addCard(Card card) {
		if (card != null) {
			handCard.addCard(card);
		}
	}

	public HitStrategy getHitStrategy() {
		return strategy;
	}

	public void setStrategy(HitStrategy strategy) {
		this.strategy = strategy;
	}

	public int getCurrentChip() {
		return currentChip;
	}

	public void setTotalChip(int totalChip) {
		this.currentChip = totalChip;
	}

	public void updateTotalChip(int chip) {
		currentChip += chip;
	}

	public HandCard getHandCard() {
		return handCard;
	}

	public void setHandCard(HandCard handCard) {
		this.handCard = handCard;
	}

}
