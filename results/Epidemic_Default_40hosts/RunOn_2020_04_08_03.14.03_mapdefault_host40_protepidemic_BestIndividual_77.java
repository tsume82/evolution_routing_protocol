package routing;
import core.Settings;
public class BestIndividual_77 extends ActiveRouter{ 
	public BestIndividual_77(Settings s) {
		super(s);
	}
	protected BestIndividual_77(BestIndividual_77 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((!!canStartTransfer() || (((isTransferring() || canStartTransfer()) != (!(isTransferring() || ((canStartTransfer() || canStartTransfer()) != (canStartTransfer() || canStartTransfer()))) != ((!isTransferring() != (((canStartTransfer() || canStartTransfer()) != !canStartTransfer()) != ((canStartTransfer() != isTransferring()) != (isTransferring() != (isTransferring() != canStartTransfer()))))) != (canStartTransfer() || canStartTransfer())))) || (canStartTransfer() || canStartTransfer())))){if(!isTransferring()){if(((canStartTransfer() || canStartTransfer()) != !canStartTransfer())){if(canStartTransfer()){exchangeDeliverableMessages();}
if(isTransferring()){this.tryAllMessagesToAllConnections();}}}}	}


	@Override
	public BestIndividual_77 replicate() {
		return new BestIndividual_77(this);
	}
}