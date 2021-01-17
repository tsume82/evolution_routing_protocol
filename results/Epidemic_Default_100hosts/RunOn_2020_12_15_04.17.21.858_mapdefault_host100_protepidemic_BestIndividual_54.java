package routing;
import core.Settings;
public class BestIndividual_54 extends ActiveRouter{ 
	public BestIndividual_54(Settings s) {
		super(s);
	}
	protected BestIndividual_54(BestIndividual_54 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if(!!((isTransferring() || isTransferring()) || (canStartTransfer() != isTransferring()))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
if(isTransferring()){this.tryAllMessagesToAllConnections();}
if((canStartTransfer() != isTransferring())){exchangeDeliverableMessages();
exchangeDeliverableMessages();}
if(((canStartTransfer() || isTransferring()) || isTransferring())){exchangeDeliverableMessages();}}	}


	@Override
	public BestIndividual_54 replicate() {
		return new BestIndividual_54(this);
	}
}