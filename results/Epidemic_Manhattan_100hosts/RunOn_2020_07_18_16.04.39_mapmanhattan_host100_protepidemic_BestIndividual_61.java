package routing;
import core.Settings;
public class BestIndividual_61 extends ActiveRouter{ 
	public BestIndividual_61(Settings s) {
		super(s);
	}
	protected BestIndividual_61(BestIndividual_61 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((isTransferring() || canStartTransfer())){if((((isTransferring() != canStartTransfer()) || (canStartTransfer() || !isTransferring())) || ((canStartTransfer() || canStartTransfer()) || canStartTransfer()))){if(((isTransferring() || canStartTransfer()) || (canStartTransfer() || canStartTransfer()))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
exchangeDeliverableMessages();
if(isTransferring()){this.tryAllMessagesToAllConnections();}}}}	}


	@Override
	public BestIndividual_61 replicate() {
		return new BestIndividual_61(this);
	}
}