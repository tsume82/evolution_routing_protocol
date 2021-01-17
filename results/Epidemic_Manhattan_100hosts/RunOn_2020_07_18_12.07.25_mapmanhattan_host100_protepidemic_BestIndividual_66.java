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
if(((isTransferring() || canStartTransfer()) != (canStartTransfer() != isTransferring()))){exchangeDeliverableMessages();
super.update();
this.tryAllMessagesToAllConnections();}
if(!(canStartTransfer() != isTransferring())){this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();
exchangeDeliverableMessages();
super.update();}
if((isTransferring() || canStartTransfer())){super.update();
exchangeDeliverableMessages();}	}


	@Override
	public BestIndividual_66 replicate() {
		return new BestIndividual_66(this);
	}
}