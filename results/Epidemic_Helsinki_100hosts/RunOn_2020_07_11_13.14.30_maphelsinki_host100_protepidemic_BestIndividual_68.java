package routing;
import core.Settings;
public class BestIndividual_68 extends ActiveRouter{ 
	public BestIndividual_68(Settings s) {
		super(s);
	}
	protected BestIndividual_68(BestIndividual_68 r) {
		super(r);
	}
	@Override
	public void update() {
		super.update();
if((((((!canStartTransfer() != !isTransferring()) != isTransferring()) != !isTransferring()) != ((isTransferring() != canStartTransfer()) != !isTransferring())) || ((!canStartTransfer() || !canStartTransfer()) || ((isTransferring() || isTransferring()) != !isTransferring())))){if((((isTransferring() || canStartTransfer()) != (isTransferring() != canStartTransfer())) || ((canStartTransfer() || canStartTransfer()) || (isTransferring() || isTransferring())))){if(isTransferring()){this.tryAllMessagesToAllConnections();}
super.update();
exchangeDeliverableMessages();
if(isTransferring()){super.update();}}}	}


	@Override
	public BestIndividual_68 replicate() {
		return new BestIndividual_68(this);
	}
}