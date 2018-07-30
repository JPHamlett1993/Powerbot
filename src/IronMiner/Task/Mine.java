package IronMiner.Task;


import IronMiner.Constants;
import IronMiner.MineIron;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Mine extends Task<ClientContext> {

    public Mine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() != 28 &&
                ctx.inventory.select().id(Constants.IRON_ORE).count() < 9 ? ctx.objects.select().id(Constants.IRON_ROCKS).nearest().poll().inViewport() : ctx.objects.select().id(Constants.COAL_ROCKS).nearest().poll().inViewport()
                && !ctx.players.local().inMotion() && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        MineIron.state = "Mine";
        GameObject rock = ctx.inventory.select().id(Constants.IRON_ORE).count() < 9 ? ctx.objects.select().id(Constants.IRON_ROCKS).nearest().poll() : ctx.objects.select().id(Constants.COAL_ROCKS).nearest().poll();
        rock.click();
        Condition.sleep(150);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.players.local().inMotion() && ctx.players.local().animation() == -1;
            }
        }, 750, 1000);

    }
}
