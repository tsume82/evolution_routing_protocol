package routing;
import core.Settings;
public class BestIndividual_63 extends ActiveRouter{ 
	public BestIndividual_63(Settings s) {
		super(s);
	}
	protected BestIndividual_63(BestIndividual_63 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((!(!(canStartTransfer() != canStartTransfer()) != ((canStartTransfer() || canStartTransfer()) || (canStartTransfer() || (canStartTransfer() || canStartTransfer())))) || (!(canStartTransfer() != !((isTransferring() != isTransferring()) != isTransferring())) || !(isTransferring() != (canStartTransfer() || (isTransferring() || canStartTransfer())))))){if(!(isTransferring() != (canStartTransfer() || (canStartTransfer() || canStartTransfer())))){this.tryAllMessagesToAllConnections();}
if(!(canStartTransfer() != canStartTransfer())){if((isTransferring() || (canStartTransfer() || !(canStartTransfer() || isTransferring())))){exchangeDeliverableMessages();
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}}}}	}


	@Override
	public BestIndividual_63 replicate() {
		return new BestIndividual_63(this);
	}
}