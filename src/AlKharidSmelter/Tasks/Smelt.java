package AlKharidSmelter.Tasks;

import AlKharidSmelter.Constants;
import AlKharidSmelter.JPSmelters;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Smelt extends Task<ClientContext> {

    public Smelt(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
         return ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion()
                && Constants.hasOres(ctx) &&
                Constants.inSmelter(ctx);
    }

    @Override
    public void execute() {
        GameObject furnace = ctx.objects.select().id(Constants.FURNACE_ID).nearest().poll();
        if (!furnace.inViewport()) {
            ctx.camera.turnTo(furnace.tile());
        }
        furnace.interact("Smelt");
        int componentId = 0;
        switch (JPSmelters.BAR_TO_SMELT) {
            case BRONZE: {
                componentId = Constants.BRONZE_COMPONENT_ID;
                break;
            }
            case IRON: {
                componentId = Constants.IRON_COMPONENT_ID;
                break;
            }
            case SILVER: {
                componentId = Constants.SILVER_COMPONENT_ID;
                break;
            }
            case STEEL: {
                componentId = Constants.STEEL_COMPONENT_ID;
                break;
            }
            case GOLD: {
                componentId = Constants.GOLD_COMPONENT_ID;
                break;
            }
            case MITHRIL: {
                componentId = Constants.MITHRIL_COMPONENT_ID;
                break;
            }
            case ADAMANT: {
                componentId = Constants.ADAMANT_COMPONENT_ID;
                break;
            }
            case RUNE: {
                componentId = Constants.RUNE_COMPONENT_ID;
                break;
            }
        }
        final int finalComponentId = componentId;
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {return ctx.widgets.widget(270).component(finalComponentId).valid();
            }
        }, 100, 30);
        ctx.widgets.widget(270).component(finalComponentId).interact("Smelt");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return Constants.invFullOfBars(ctx)
                        || ctx.widgets.widget(233).component(1).valid();
            }
        }, 100, 600);
    }

    @Override
    public String getName() {
        return "Smelting";
    }
}
