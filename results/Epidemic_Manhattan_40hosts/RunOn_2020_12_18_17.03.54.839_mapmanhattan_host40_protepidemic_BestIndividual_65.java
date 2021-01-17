package routing;
import core.Settings;
public class BestIndividual_65 extends ActiveRouter{ 
	public BestIndividual_65(Settings s) {
		super(s);
	}
	protected BestIndividual_65(BestIndividual_65 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((canStartTransfer() || canStartTransfer()) != (isTransferring() != isTransferring())) || !!canStartTransfer()) || (!isTransferring() != ((isTransferring() || (canStartTransfer() != canStartTransfer())) || (isTransferring() != isTransferring()))))){if((((isTransferring() != canStartTransfer()) != (canStartTransfer() != isTransferring())) || !!canStartTransfer())){if((isTransferring() != canStartTransfer())){exchangeDeliverableMessages();
exchangeDeliverableMessages();}
super.update();
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}}}	}


	@Override
	public BestIndividual_65 replicate() {
		return new BestIndividual_65(this);
	}
}