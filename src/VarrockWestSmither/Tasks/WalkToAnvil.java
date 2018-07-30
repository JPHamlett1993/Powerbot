package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.rt4.ClientContext;

public class WalkToAnvil extends Task<ClientContext> {


    public WalkToAnvil(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() &&
                ctx.inventory.select().id(Constants.getBronzeBarId()).count() != 0 &&
                ctx.movement.distance(Constants.getSmitherTile()) > 5;
    }

    @Override
    public void execute() {
        ctx.movement.newTilePath(Constants.getTilePath()).reverse().traverse();
    }

    @Override
    public String getName() {
        return "Walking to anvil";
    }
}
