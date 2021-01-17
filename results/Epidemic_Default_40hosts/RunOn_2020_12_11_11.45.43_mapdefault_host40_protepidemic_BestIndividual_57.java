package routing;
import core.Settings;
public class BestIndividual_57 extends ActiveRouter{ 
	public BestIndividual_57(Settings s) {
		super(s);
	}
	protected BestIndividual_57(BestIndividual_57 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!(((canStartTransfer() || canStartTransfer()) || (isTransferring() || isTransferring())) != (!canStartTransfer() != (canStartTransfer() || canStartTransfer())))){if((((canStartTransfer() || canStartTransfer()) || (isTransferring() || canStartTransfer())) || ((canStartTransfer() || canStartTransfer()) || (canStartTransfer() || isTransferring())))){if(((canStartTransfer() != canStartTransfer()) != isTransferring())){this.tryAllMessagesToAllConnections();
exchangeDeliverableMessages();}
if(canStartTransfer()){exchangeDeliverableMessages();}
exchangeDeliverableMessages();
super.update();
exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_57 replicate() {
		return new BestIndividual_57(this);
	}
}