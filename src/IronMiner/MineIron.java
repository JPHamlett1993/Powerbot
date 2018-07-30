package IronMiner;

import IronMiner.Task.*;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="Miner", description="cuts willow logs and drops them")
public class MineIron extends PollingScript<ClientContext>  implements PaintListener {

    private List<Task> taskList = new ArrayList<>();

    public static String state = "None";

    @Override
    public void start() {
        taskList.addAll(Arrays.asList(new Mine(ctx), new WalkToRocks(ctx), new WalkToBank(ctx), new Bank(ctx)));
    }

    @Override
    public void poll() {
        for (Task task: taskList) {
            if (task.activate()) {
                task.execute();
            }
        }
    }

    @Override
    public void repaint(Graphics graphics) {
        graphics.drawString(state, 100, 100);
    }
}