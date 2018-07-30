package VarrockWestSmither.Tasks;

import VarrockWestSmither.Constants;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class OpenSmithingMenu extends Task<ClientContext> {

    public OpenSmithingMenu(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.movement.distance(Constants.getSmitherTile()) < 5
                && ctx.inventory.select().id(Constants.getBronzeBarId()).count() > 0;
    }

    @Override
    public void execute() {
        ctx.objects.select().id(Constants.getAnvilId()).nearest().poll().interact("Smith");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return Constants.isMenuOpen(ctx);
            }
        }, 10, 100);
    }

    @Override
    public String getName() {
        return "Opening Smithing Anvil";
    }
}
