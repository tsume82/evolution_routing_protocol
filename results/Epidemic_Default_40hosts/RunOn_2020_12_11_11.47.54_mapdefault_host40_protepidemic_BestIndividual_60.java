package routing;
import core.Settings;
public class BestIndividual_60 extends ActiveRouter{ 
	public BestIndividual_60(Settings s) {
		super(s);
	}
	protected BestIndividual_60(BestIndividual_60 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!((!isTransferring() != (isTransferring() != canStartTransfer())) || (canStartTransfer() != isTransferring()))){exchangeDeliverableMessages();
this.tryAllMessagesToAllConnections();
if(isTransferring()){super.update();}
if((canStartTransfer() || canStartTransfer())){this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();}
if(canStartTransfer()){super.update();}
if(isTransferring()){this.tryAllMessagesToAllConnections();}}
if((((canStartTransfer() != isTransferring()) != (isTransferring() || isTransferring())) || ((canStartTransfer() != isTransferring()) || !canStartTransfer()))){if(((canStartTransfer() != isTransferring()) || (canStartTransfer() != isTransferring()))){if((isTransferring() != canStartTransfer())){if(isTransferring()){return;}
if((canStartTransfer() || canStartTransfer())){exchangeDeliverableMessages();
return;}
if(isTransferring()){return;}
if(isTransferring()){this.tryAllMessagesToAllConnections();}}}}	}


	@Override
	public BestIndividual_60 replicate() {
		return new BestIndividual_60(this);
	}
}