package routing;
import core.Settings;
public class BestIndividual_75 extends ActiveRouter{ 
	public BestIndividual_75(Settings s) {
		super(s);
	}
	protected BestIndividual_75(BestIndividual_75 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(isTransferring()){this.tryAllMessagesToAllConnections();}
if((canStartTransfer() != isTransferring())){exchangeDeliverableMessages();
return;}
if((canStartTransfer() || isTransferring())){exchangeDeliverableMessages();}
if(isTransferring()){if((canStartTransfer() || canStartTransfer())){this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();}}
if(((canStartTransfer() || canStartTransfer()) || (canStartTransfer() || isTransferring()))){super.update();
exchangeDeliverableMessages();
if(isTransferring()){exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_75 replicate() {
		return new BestIndividual_75(this);
	}
}