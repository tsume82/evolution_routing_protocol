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
if((((isTransferring() != (((isTransferring() || (isTransferring() != isTransferring())) || (isTransferring() || isTransferring())) || ((canStartTransfer() || canStartTransfer()) || (canStartTransfer() || canStartTransfer())))) != (((isTransferring() || isTransferring()) || (isTransferring() != isTransferring())) || ((canStartTransfer() || canStartTransfer()) != (isTransferring() != canStartTransfer())))) || ((canStartTransfer() != canStartTransfer()) != (isTransferring() != isTransferring())))){if(isTransferring()){exchangeDeliverableMessages();}
if(isTransferring()){this.tryAllMessagesToAllConnections();}
if((canStartTransfer() || canStartTransfer())){exchangeDeliverableMessages();
exchangeDeliverableMessages();}}
if((((isTransferring() != isTransferring()) || (canStartTransfer() != isTransferring())) || (canStartTransfer() || (canStartTransfer() != canStartTransfer())))){if(!canStartTransfer()){return;}
if((canStartTransfer() || canStartTransfer())){exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_63 replicate() {
		return new BestIndividual_63(this);
	}
}