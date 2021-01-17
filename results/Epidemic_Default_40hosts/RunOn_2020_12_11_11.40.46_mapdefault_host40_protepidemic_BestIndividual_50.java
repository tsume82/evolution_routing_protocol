package routing;
import core.Settings;
public class BestIndividual_50 extends ActiveRouter{ 
	public BestIndividual_50(Settings s) {
		super(s);
	}
	protected BestIndividual_50(BestIndividual_50 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((!!(isTransferring() || canStartTransfer()) || !!canStartTransfer())){if(((canStartTransfer() != isTransferring()) || (isTransferring() || isTransferring()))){super.update();
super.update();
if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if((!canStartTransfer() || (canStartTransfer() != isTransferring()))){if((isTransferring() != canStartTransfer())){if(canStartTransfer()){exchangeDeliverableMessages();}}}}	}


	@Override
	public BestIndividual_50 replicate() {
		return new BestIndividual_50(this);
	}
}