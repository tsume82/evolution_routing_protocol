package routing;
import core.Settings;
public class BestIndividual_69 extends ActiveRouter{ 
	public BestIndividual_69(Settings s) {
		super(s);
	}
	protected BestIndividual_69(BestIndividual_69 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(((!(((isTransferring() != canStartTransfer()) || !(isTransferring() || canStartTransfer())) || isTransferring()) != ((isTransferring() != canStartTransfer()) != (canStartTransfer() != isTransferring()))) || (((isTransferring() || ((!(isTransferring() || isTransferring()) != ((canStartTransfer() != canStartTransfer()) != (isTransferring() != isTransferring()))) || (!(isTransferring() != isTransferring()) || ((isTransferring() != isTransferring()) != !canStartTransfer())))) || (canStartTransfer() != isTransferring())) || !(isTransferring() || canStartTransfer())))){if((((canStartTransfer() != canStartTransfer()) || !isTransferring()) || ((canStartTransfer() != canStartTransfer()) != (canStartTransfer() != ((isTransferring() != canStartTransfer()) != (canStartTransfer() != isTransferring())))))){if((isTransferring() != canStartTransfer())){exchangeDeliverableMessages();
return;}
this.tryAllMessagesToAllConnections();
exchangeDeliverableMessages();
this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();}}	}


	@Override
	public BestIndividual_69 replicate() {
		return new BestIndividual_69(this);
	}
}