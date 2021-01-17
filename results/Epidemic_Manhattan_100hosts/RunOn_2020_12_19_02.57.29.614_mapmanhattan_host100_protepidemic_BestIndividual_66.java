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
if(((canStartTransfer() || isTransferring()) || canStartTransfer())){super.update();}
if(isTransferring()){if(isTransferring()){this.tryAllMessagesToAllConnections();}
if(((canStartTransfer() || isTransferring()) != !isTransferring())){if(isTransferring()){super.update();}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}}
if(((canStartTransfer() || (isTransferring() != isTransferring())) || !canStartTransfer())){super.update();}
if(isTransferring()){if(isTransferring()){this.tryAllMessagesToAllConnections();}
if(((canStartTransfer() || isTransferring()) != !isTransferring())){if(isTransferring()){super.update();}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}}
super.update();
this.tryAllMessagesToAllConnections();}
this.tryAllMessagesToAllConnections();}
if(((!isTransferring() || (canStartTransfer() || isTransferring())) || ((canStartTransfer() || canStartTransfer()) != (isTransferring() || canStartTransfer())))){if(canStartTransfer()){exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_66 replicate() {
		return new BestIndividual_66(this);
	}
}