package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task<ClientContext>  {

    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                ctx.movement.distance(Constants.getBankerTile()) > 5 &&
                ctx.inventory.select().id(Constants.getBronzeBarId()).count() == 0;
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.getTilePath()).traverse();
    }

    @Override
    public String getName() {
        return "Walking to bank";
    }
}
