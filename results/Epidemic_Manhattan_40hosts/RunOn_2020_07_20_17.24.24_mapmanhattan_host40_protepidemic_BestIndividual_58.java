package routing;
import core.Settings;
public class BestIndividual_58 extends ActiveRouter{ 
	public BestIndividual_58(Settings s) {
		super(s);
	}
	protected BestIndividual_58(BestIndividual_58 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((((canStartTransfer() || isTransferring()) || (canStartTransfer() != canStartTransfer())) || ((isTransferring() != canStartTransfer()) || isTransferring())) || (((isTransferring() || isTransferring()) != (isTransferring() || isTransferring())) || !(canStartTransfer() || isTransferring())))){if((isTransferring() || isTransferring())){this.tryAllMessagesToAllConnections();}
if((canStartTransfer() || (isTransferring() || isTransferring()))){super.update();
exchangeDeliverableMessages();}
if(((canStartTransfer() || canStartTransfer()) != !canStartTransfer())){if((isTransferring() != canStartTransfer())){this.tryAllMessagesToAllConnections();}}}	}


	@Override
	public BestIndividual_58 replicate() {
		return new BestIndividual_58(this);
	}
}