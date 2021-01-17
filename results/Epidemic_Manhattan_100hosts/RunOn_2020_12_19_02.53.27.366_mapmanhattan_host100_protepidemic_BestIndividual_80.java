package routing;
import core.Settings;
public class BestIndividual_80 extends ActiveRouter{ 
	public BestIndividual_80(Settings s) {
		super(s);
	}
	protected BestIndividual_80(BestIndividual_80 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((isTransferring() || isTransferring()) != (isTransferring() != canStartTransfer())) || ((isTransferring() || canStartTransfer()) != (!(((isTransferring() != canStartTransfer()) != (isTransferring() || canStartTransfer())) != ((((isTransferring() || canStartTransfer()) || ((!canStartTransfer() || (isTransferring() || isTransferring())) || ((isTransferring() != isTransferring()) != (isTransferring() != canStartTransfer())))) || isTransferring()) || (!((isTransferring() != canStartTransfer()) || !canStartTransfer()) || isTransferring()))) || isTransferring())))){if((isTransferring() != canStartTransfer())){exchangeDeliverableMessages();
return;}
if(canStartTransfer()){this.tryAllMessagesToAllConnections();}
if(!((isTransferring() != canStartTransfer()) || isTransferring())){this.tryAllMessagesToAllConnections();}}
if(isTransferring()){this.tryAllMessagesToAllConnections();}
if((!canStartTransfer() || (isTransferring() || isTransferring()))){super.update();
this.tryAllMessagesToAllConnections();
if(isTransferring()){return;}}	}


	@Override
	public BestIndividual_80 replicate() {
		return new BestIndividual_80(this);
	}
}