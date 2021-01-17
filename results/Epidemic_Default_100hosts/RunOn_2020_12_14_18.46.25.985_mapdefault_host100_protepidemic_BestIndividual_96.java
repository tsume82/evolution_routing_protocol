package routing;
import core.Settings;
public class BestIndividual_96 extends ActiveRouter{ 
	public BestIndividual_96(Settings s) {
		super(s);
	}
	protected BestIndividual_96(BestIndividual_96 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(canStartTransfer()){if((((isTransferring() != canStartTransfer()) != (canStartTransfer() != isTransferring())) != (!canStartTransfer() != (canStartTransfer() || isTransferring())))){super.update();
super.update();
super.update();
exchangeDeliverableMessages();
if(((canStartTransfer() || isTransferring()) != (isTransferring() != (canStartTransfer() || canStartTransfer())))){this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();}}}	}


	@Override
	public BestIndividual_96 replicate() {
		return new BestIndividual_96(this);
	}
}