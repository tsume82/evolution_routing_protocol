package routing;
import core.Settings;
public class BestIndividual_89 extends ActiveRouter{ 
	public BestIndividual_89(Settings s) {
		super(s);
	}
	protected BestIndividual_89(BestIndividual_89 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((isTransferring() || !(canStartTransfer() || isTransferring()))){if((canStartTransfer() || (isTransferring() || isTransferring()))){if(!canStartTransfer()){this.tryAllMessagesToAllConnections();}}}
if((canStartTransfer() != isTransferring())){exchangeDeliverableMessages();
return;}
if(isTransferring()){this.tryAllMessagesToAllConnections();}
this.tryAllMessagesToAllConnections();
this.tryAllMessagesToAllConnections();	}


	@Override
	public BestIndividual_89 replicate() {
		return new BestIndividual_89(this);
	}
}