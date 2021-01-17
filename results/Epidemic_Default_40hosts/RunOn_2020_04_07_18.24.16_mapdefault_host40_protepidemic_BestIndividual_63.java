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
if((!!(canStartTransfer() != isTransferring()) || (((isTransferring() || canStartTransfer()) != (isTransferring() || isTransferring())) != ((isTransferring() != isTransferring()) != (canStartTransfer() || isTransferring()))))){if(((isTransferring() || (((canStartTransfer() != isTransferring()) != (canStartTransfer() || isTransferring())) != ((canStartTransfer() != isTransferring()) != (canStartTransfer() || canStartTransfer())))) != ((canStartTransfer() != canStartTransfer()) || (isTransferring() || canStartTransfer())))){if(isTransferring()){super.update();}
exchangeDeliverableMessages();
return;}
if((isTransferring() != isTransferring())){if(canStartTransfer()){exchangeDeliverableMessages();}}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(isTransferring()){this.tryAllMessagesToAllConnections();}}	}


	@Override
	public BestIndividual_63 replicate() {
		return new BestIndividual_63(this);
	}
}