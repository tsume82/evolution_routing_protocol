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
if((!!canStartTransfer() || (((canStartTransfer() || isTransferring()) || (isTransferring() != canStartTransfer())) || !(canStartTransfer() != isTransferring())))){if((((isTransferring() != canStartTransfer()) != (isTransferring() != canStartTransfer())) != ((canStartTransfer() != canStartTransfer()) || !isTransferring()))){if(((canStartTransfer() || canStartTransfer()) || (isTransferring() != canStartTransfer()))){exchangeDeliverableMessages();
super.update();
if(isTransferring()){this.tryAllMessagesToAllConnections();
super.update();}}}}	}


	@Override
	public BestIndividual_58 replicate() {
		return new BestIndividual_58(this);
	}
}