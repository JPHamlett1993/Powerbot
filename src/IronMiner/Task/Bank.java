package IronMiner.Task;

import IronMiner.Constants;
import IronMiner.MineIron;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class Bank extends Task<ClientContext> {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28 &&
                ctx.objects.select().id(Constants.BACK_CHEST).poll().inViewport()
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        MineIron.state = "Bank";
        while (!ctx.bank.opened()) {
            ctx.bank.open();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.bank.opened();
                }
            }, 500, 10);
        }
        while (ctx.inventory.select().count() == 28) {
            ctx.bank.depositAllExcept(11920);
            Condition.sleep(350);
        }
        while (ctx.bank.opened()) {
            ctx.bank.close();
            Condition.sleep(350);
        }

    }
}
