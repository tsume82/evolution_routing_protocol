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
if(((((isTransferring() != isTransferring()) || (isTransferring() != isTransferring())) || ((canStartTransfer() != canStartTransfer()) || isTransferring())) != ((canStartTransfer() || canStartTransfer()) || (isTransferring() || canStartTransfer())))){super.update();
super.update();
exchangeDeliverableMessages();
if(((isTransferring() || canStartTransfer()) || (isTransferring() || canStartTransfer()))){return;}
if(canStartTransfer()){return;}}
if((isTransferring() != isTransferring())){if(canStartTransfer()){return;}}
exchangeDeliverableMessages();
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
super.update();
this.tryAllMessagesToAllConnections();
if(((canStartTransfer() || canStartTransfer()) != (isTransferring() != isTransferring()))){if((canStartTransfer() || (canStartTransfer() != canStartTransfer()))){if(isTransferring()){return;}}}	}


	@Override
	public BestIndividual_69 replicate() {
		return new BestIndividual_69(this);
	}
}