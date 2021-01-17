package routing;
import core.Settings;
public class BestIndividual_66 extends ActiveRouter{ 
	public BestIndividual_66(Settings s) {
		super(s);
	}
	protected BestIndividual_66(BestIndividual_66 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!(((!((isTransferring() != canStartTransfer()) || (isTransferring() != isTransferring())) != canStartTransfer()) || !isTransferring()) != ((canStartTransfer() != isTransferring()) || (canStartTransfer() != isTransferring())))){if((((canStartTransfer() != ((isTransferring() || isTransferring()) != (canStartTransfer() != canStartTransfer()))) || (canStartTransfer() || canStartTransfer())) || ((isTransferring() != isTransferring()) != (canStartTransfer() || isTransferring())))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
if(canStartTransfer()){exchangeDeliverableMessages();}
if(!isTransferring()){exchangeDeliverableMessages();
return;}}}	}


	@Override
	public BestIndividual_66 replicate() {
		return new BestIndividual_66(this);
	}
}