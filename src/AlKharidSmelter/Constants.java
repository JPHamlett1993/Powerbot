package AlKharidSmelter;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Tile;

public class Constants {

    private static int[][] PATH = {
            {3269, 3168},
            {3275, 3169},
            {3276, 3174},
            {3280, 3181},
            {3275, 3185}
    };

    public static Tile[] TILE_PATH = Tile.fromArray(PATH);

    public static Tile BANK_TILE = new Tile(3269, 3168, 0);
    public static Tile FURNACE_TILE = new Tile(3275, 3185,0);

    public static int TIN_ID = 438;
    public static int COPPER_ID = 436;
    public static int IRON_ID = 440;
    public static int COAL_ID = 453;
    public static int SILVER_ID = 442;
    public static int GOLD_ID = 444;
    public static int MITHRIL_ID = 447;
    public static int ADAMANT_ID = 449;
    public static int RUNE_ID = 451;
    
    public static int BRONZE_BAR_ID = 2349;
    public static int IRON_BAR_ID = 2351;
    public static int SILVER_BAR_ID = 2355;
    public static int STEEL_BAR_ID = 2353;
    public static int GOLD_BAR_ID = 2357;
    public static int MITHRIL_BAR_ID = 2359;
    public static int ADAMANT_BAR_ID = 2361;
    public static int RUNE_BAR_ID = 2363;
    public static int FURNACE_ID = 24009;

    public static int BRONZE_COMPONENT_ID = 14;
    public static int IRON_COMPONENT_ID = 15;
    public static int SILVER_COMPONENT_ID = 17;
    public static int STEEL_COMPONENT_ID = 18;
    public static int GOLD_COMPONENT_ID = 18;
    public static int MITHRIL_COMPONENT_ID = 19;
    public static int ADAMANT_COMPONENT_ID = 20;
    public static int RUNE_COMPONENT_ID = 21;

    public static Ore[] BRONZE_ORES = {new Ore(COPPER_ID, 14), new Ore(TIN_ID, 14)};
    public static Ore[] IRON_ORES = {new Ore(IRON_ID, 28)};
    public static Ore[] SILVER_ORES = {new Ore(Constants.SILVER_ID, 28)};
    public static Ore[] STEEL_ORES = {new Ore(Constants.IRON_ID, 9), new Ore(Constants.COAL_ID, 18)};
    public static Ore[] GOLD_ORES = {new Ore(Constants.GOLD_ID, 28)};
    public static Ore[] MITHRIL_ORES = {new Ore(Constants.MITHRIL_ID, 4), new Ore(Constants.COAL_ID, 18)};
    public static Ore[] ADAMANT_ORES = {new Ore(Constants.ADAMANT_ID, 3), new Ore(Constants.COAL_ID, 18)};
    public static Ore[] RUNE_ORES = {new Ore(Constants.RUNE_ID, 2), new Ore(Constants.COAL_ID, 16)};


    public static boolean inBank(ClientContext ctx) {
        return ctx.movement.distance(BANK_TILE) < 5;
    }

    public static boolean inSmelter(ClientContext ctx) {
        return ctx.movement.distance(FURNACE_TILE) < 5;
    }

    public static boolean hasOres(ClientContext ctx) {
        Ore[] ores = new Ore[0];
        switch (JPSmelters.BAR_TO_SMELT) {
            case BRONZE: {
                ores = BRONZE_ORES;
                break;
            }
            case IRON: {
                ores = IRON_ORES;
                break;
            }
            case SILVER: {
                ores = SILVER_ORES;
                break;
            }
            case STEEL: {
                ores = STEEL_ORES;
                break;
            }
            case GOLD: {
                ores = GOLD_ORES;
                break;
            }
            case MITHRIL: {
                ores = MITHRIL_ORES;
                break;
            }
            case ADAMANT: {
                ores = ADAMANT_ORES;
                break;
            }
            case RUNE: {
                ores = RUNE_ORES;
                break;
            }
        }
        for (Ore ore: ores) {
            System.out.println(ctx.inventory.select().id(ore.getId()).count());
            if (ctx.inventory.select().id(ore.getId()).count() == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean invFullOfBars(ClientContext ctx) {
        int barId = 0;
        switch (JPSmelters.BAR_TO_SMELT) {
            case BRONZE: {
                barId = BRONZE_BAR_ID;
                break;
            }
            case IRON: {
                barId = IRON_BAR_ID;
                break;
            }
            case SILVER: {
                barId = SILVER_BAR_ID;
                break;
            }
            case STEEL: {
                barId = STEEL_BAR_ID;
                break;
            }
            case GOLD: {
                barId = GOLD_BAR_ID;
                break;
            }
            case MITHRIL: {
                barId = MITHRIL_BAR_ID;
                break;
            }
            case ADAMANT: {
                barId = ADAMANT_BAR_ID;
                break;
            }
            case RUNE: {
                barId = RUNE_BAR_ID;
                break;
            }
        }
        return ctx.inventory.select().id(barId).count() == ctx.inventory.select().count();
    }
}
