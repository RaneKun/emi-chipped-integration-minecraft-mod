package com.rane.emichipped;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.*;

public class EmiChippedPlugin implements EmiPlugin {
    
    // Recipe type IDs for all Chipped workbenches
    public static final Identifier BOTANIST_ID = new Identifier("chipped", "botanist_workbench");
    public static final Identifier GLASSBLOWER_ID = new Identifier("chipped", "glassblower");
    public static final Identifier CARPENTERS_ID = new Identifier("chipped", "carpenters_table");
    public static final Identifier LOOM_ID = new Identifier("chipped", "loom_table");
    public static final Identifier MASON_ID = new Identifier("chipped", "mason_table");
    public static final Identifier ALCHEMY_ID = new Identifier("chipped", "alchemy_bench");
    public static final Identifier MECHANIST_ID = new Identifier("chipped", "mechanist_workbench");
    
    // Create recipe categories for each workbench
    public static final EmiRecipeCategory BOTANIST_CATEGORY = 
        new EmiRecipeCategory(
            BOTANIST_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "botanist_workbench")))
        );
    
    public static final EmiRecipeCategory GLASSBLOWER_CATEGORY = 
        new EmiRecipeCategory(
            GLASSBLOWER_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "glassblower")))
        );
    
    public static final EmiRecipeCategory CARPENTERS_CATEGORY = 
        new EmiRecipeCategory(
            CARPENTERS_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "carpenters_table")))
        );
    
    public static final EmiRecipeCategory LOOM_CATEGORY = 
        new EmiRecipeCategory(
            LOOM_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "loom_table")))
        );
    
    public static final EmiRecipeCategory MASON_CATEGORY = 
        new EmiRecipeCategory(
            MASON_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "mason_table")))
        );
    
    public static final EmiRecipeCategory ALCHEMY_CATEGORY = 
        new EmiRecipeCategory(
            ALCHEMY_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "alchemy_bench")))
        );
    
    public static final EmiRecipeCategory MECHANIST_CATEGORY = 
        new EmiRecipeCategory(
            MECHANIST_ID,
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "tinkering_table")))
        );
    
    // Map workbench types to categories and their tag lists
    private static final Map<String, WorkbenchInfo> WORKBENCH_INFO = new HashMap<>();
    
    static class WorkbenchInfo {
        final EmiRecipeCategory category;
        final List<String> tagNames;
        
        WorkbenchInfo(EmiRecipeCategory category, List<String> tagNames) {
            this.category = category;
            this.tagNames = tagNames;
        }
    }
    
    static {
        // COMPLETE TAG LISTS - These match exactly what's in Chipped's recipe JSON files
        WORKBENCH_INFO.put("botanist_workbench", new WorkbenchInfo(
            BOTANIST_CATEGORY,
            Arrays.asList(
                "blue_ice", "dirt", "ice", "packed_ice", "snow_block", "clay", "mud", "packed_mud",
                "acacia_leaves", "birch_leaves", "dark_oak_leaves", "jungle_leaves", "mangrove_roots",
                "oak_leaves", "spruce_leaves", "ochre_froglight", "pearlescent_froglight", "verdant_froglight",
                "bone_block", "brown_mushroom_block", "red_mushroom_block", "brown_mushroom", "red_mushroom",
                "cobweb", "mushroom_stem", "gravel", "hay_block", "moss_block", "melon", "shroomlight",
                "sand", "soul_sand", "crimson_roots", "warped_roots", "dried_kelp_block", "lily_pad",
                "nether_sprouts", "nether_wart_block", "pumpkin", "carved_pumpkin", "jack_o_lantern",
                "vine", "warped_wart_block", "crimson_fungus", "warped_fungus"
            )
        ));
        
        WORKBENCH_INFO.put("glassblower", new WorkbenchInfo(
            GLASSBLOWER_CATEGORY,
            Arrays.asList(
                "glass", "glass_pane", "black_stained_glass", "black_stained_glass_pane",
                "blue_stained_glass", "blue_stained_glass_pane", "brown_stained_glass", "brown_stained_glass_pane",
                "cyan_stained_glass", "cyan_stained_glass_pane", "gray_stained_glass", "gray_stained_glass_pane",
                "green_stained_glass", "green_stained_glass_pane", "light_blue_stained_glass", "light_blue_stained_glass_pane",
                "light_gray_stained_glass", "light_gray_stained_glass_pane", "lime_stained_glass", "lime_stained_glass_pane",
                "magenta_stained_glass", "magenta_stained_glass_pane", "orange_stained_glass", "orange_stained_glass_pane",
                "pink_stained_glass", "pink_stained_glass_pane", "purple_stained_glass", "purple_stained_glass_pane",
                "red_stained_glass", "red_stained_glass_pane", "white_stained_glass", "white_stained_glass_pane",
                "yellow_stained_glass", "yellow_stained_glass_pane"
            )
        ));
        
        WORKBENCH_INFO.put("carpenters_table", new WorkbenchInfo(
            CARPENTERS_CATEGORY,
            Arrays.asList(
                "acacia_planks", "birch_planks", "dark_oak_planks", "jungle_planks", "mangrove_planks",
                "oak_planks", "spruce_planks", "crimson_planks", "warped_planks", "cherry_planks", "bamboo_planks",
                "acacia_log", "birch_log", "dark_oak_log", "jungle_log", "mangrove_log", "oak_log", "spruce_log",
                "crimson_stem", "warped_stem", "cherry_log", "barrel", "bookshelf", "ladder",
                "stripped_acacia_log", "stripped_birch_log", "stripped_dark_oak_log", "stripped_jungle_log",
                "stripped_mangrove_log", "stripped_oak_log", "stripped_spruce_log", "stripped_warped_stem",
                "stripped_crimson_stem", "stripped_cherry_log", "acacia_door", "birch_door", "dark_oak_door",
                "jungle_door", "mangrove_door", "oak_door", "spruce_door", "crimson_door", "warped_door",
                "cherry_door", "bamboo_door", "acacia_trapdoor", "birch_trapdoor", "dark_oak_trapdoor",
                "jungle_trapdoor", "mangrove_trapdoor", "oak_trapdoor", "spruce_trapdoor", "crimson_trapdoor",
                "warped_trapdoor", "cherry_trapdoor", "bamboo_trapdoor", "torch"
            )
        ));
        
        WORKBENCH_INFO.put("loom_table", new WorkbenchInfo(
            LOOM_CATEGORY,
            Arrays.asList(
                "black_wool", "blue_wool", "brown_wool", "cyan_wool", "gray_wool", "green_wool",
                "light_blue_wool", "light_gray_wool", "lime_wool", "magenta_wool", "orange_wool",
                "pink_wool", "purple_wool", "red_wool", "white_wool", "yellow_wool",
                "black_carpet", "blue_carpet", "brown_carpet", "cyan_carpet", "gray_carpet", "green_carpet",
                "light_blue_carpet", "light_gray_carpet", "lime_carpet", "magenta_carpet", "orange_carpet",
                "pink_carpet", "purple_carpet", "red_carpet", "white_carpet", "yellow_carpet"
            )
        ));
        
        WORKBENCH_INFO.put("mason_table", new WorkbenchInfo(
            MASON_CATEGORY,
            Arrays.asList(
                "ancient_debris", "andesite", "basalt", "blackstone", "calcite", "cobblestone",
                "dark_prismarine", "deepslate", "diorite", "dripstone_block", "end_stone", "gilded_blackstone",
                "granite", "magma_block", "mossy_cobblestone", "mossy_stone_bricks", "nether_bricks",
                "netherrack", "obsidian", "prismarine", "purpur_block", "quartz_block", "raw_copper_block",
                "raw_gold_block", "raw_iron_block", "red_nether_bricks", "red_sandstone", "sandstone",
                "stone", "smooth_stone", "tuff", "terracotta",
                "black_terracotta", "blue_terracotta", "brown_terracotta", "cyan_terracotta", "gray_terracotta",
                "green_terracotta", "light_blue_terracotta", "light_gray_terracotta", "lime_terracotta",
                "magenta_terracotta", "orange_terracotta", "pink_terracotta", "purple_terracotta",
                "red_terracotta", "white_terracotta", "yellow_terracotta",
                "black_glazed_terracotta", "blue_glazed_terracotta", "brown_glazed_terracotta",
                "cyan_glazed_terracotta", "gray_glazed_terracotta", "green_glazed_terracotta",
                "light_blue_glazed_terracotta", "light_gray_glazed_terracotta", "lime_glazed_terracotta",
                "magenta_glazed_terracotta", "orange_glazed_terracotta", "pink_glazed_terracotta",
                "purple_glazed_terracotta", "red_glazed_terracotta", "white_glazed_terracotta",
                "yellow_glazed_terracotta",
                "black_concrete", "blue_concrete", "brown_concrete", "cyan_concrete", "gray_concrete",
                "green_concrete", "light_blue_concrete", "light_gray_concrete", "lime_concrete",
                "magenta_concrete", "orange_concrete", "pink_concrete", "purple_concrete", "red_concrete",
                "white_concrete", "yellow_concrete",
                "bricks", "borderless_bricks", "mud_bricks", "pointed_dripstone"
            )
        ));
        
        WORKBENCH_INFO.put("alchemy_bench", new WorkbenchInfo(
            ALCHEMY_CATEGORY,
            Arrays.asList(
                "amethyst_block", "coal_block", "crying_obsidian", "lodestone", "lapis_block",
                "diamond_block", "emerald_block", "gold_block", "iron_block", "netherite_block",
                "sponge", "glowstone", "waxed_copper_block", "waxed_exposed_copper_block",
                "waxed_weathered_copper", "waxed_oxidized_copper"
            )
        ));
        
        WORKBENCH_INFO.put("tinkering_table", new WorkbenchInfo(
            MECHANIST_CATEGORY,
            Arrays.asList(
                "iron_bars", "sea_lantern", "redstone_lamp", "redstone_block", "lantern",
                "soul_lantern", "special_lantern", "special_soul_lantern", "redstone_torch"
            )
        ));
    }
    
    @Override
    public void register(EmiRegistry registry) {
        System.out.println("[EMI Chipped] Plugin starting...");
        
        // Register all categories
        registry.addCategory(BOTANIST_CATEGORY);
        registry.addCategory(GLASSBLOWER_CATEGORY);
        registry.addCategory(CARPENTERS_CATEGORY);
        registry.addCategory(LOOM_CATEGORY);
        registry.addCategory(MASON_CATEGORY);
        registry.addCategory(ALCHEMY_CATEGORY);
        registry.addCategory(MECHANIST_CATEGORY);
        System.out.println("[EMI Chipped] All categories registered");
        
        // Add workstations
        registry.addWorkstation(BOTANIST_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "botanist_workbench"))));
        registry.addWorkstation(GLASSBLOWER_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "glassblower"))));
        registry.addWorkstation(CARPENTERS_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "carpenters_table"))));
        registry.addWorkstation(LOOM_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "loom_table"))));
        registry.addWorkstation(MASON_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "mason_table"))));
        registry.addWorkstation(ALCHEMY_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "alchemy_bench"))));
        registry.addWorkstation(MECHANIST_CATEGORY, 
            EmiStack.of(Registries.ITEM.get(new Identifier("chipped", "tinkering_table"))));
        System.out.println("[EMI Chipped] All workstations registered");
        
        // Load Chipped recipes by reading tags from registry
        loadChippedRecipesFromTags(registry);
    }
    
    private void loadChippedRecipesFromTags(EmiRegistry registry) {
        int totalRecipes = 0;
        Map<String, Integer> recipeCountByType = new HashMap<>();
        
        System.out.println("[EMI Chipped] Loading recipes from tag registry...");
        
        for (Map.Entry<String, WorkbenchInfo> entry : WORKBENCH_INFO.entrySet()) {
            String workbenchType = entry.getKey();
            WorkbenchInfo info = entry.getValue();
            
            // Build a map of tag -> items for this workbench
            Map<String, List<Item>> tagToItems = new HashMap<>();
            
            for (String tagName : info.tagNames) {
                TagKey<Item> tag = TagKey.of(RegistryKeys.ITEM, new Identifier("chipped", tagName));
                List<Item> itemsInTag = new ArrayList<>();
                
                // Iterate through all registered items and check if they have this tag
                for (Item item : Registries.ITEM) {
                    if (item.getRegistryEntry().isIn(tag)) {
                        itemsInTag.add(item);
                    }
                }
                
                if (!itemsInTag.isEmpty()) {
                    tagToItems.put(tagName, itemsInTag);
                    System.out.println("[EMI Chipped] Tag '" + tagName + "' has " + itemsInTag.size() + " items");
                }
            }
            
            // Create specific recipes: for each item, create a recipe showing what it can transform into
            int workbenchRecipeCount = 0;
            
            for (Map.Entry<String, List<Item>> tagEntry : tagToItems.entrySet()) {
                String tagName = tagEntry.getKey();
                List<Item> itemsInTag = tagEntry.getValue();
                
                // For each specific item in this tag
                for (Item inputItem : itemsInTag) {
                    // Create output list (all items in tag EXCEPT this input item)
                    List<EmiStack> outputs = new ArrayList<>();
                    for (Item outputItem : itemsInTag) {
                        if (outputItem != inputItem) {
                            outputs.add(EmiStack.of(outputItem));
                        }
                    }
                    
                    // Only create recipe if there are outputs
                    if (!outputs.isEmpty()) {
                        String inputItemId = Registries.ITEM.getId(inputItem).toString();
                        Identifier recipeId = new Identifier("chipped", 
                            "emi/" + workbenchType + "/" + tagName + "/" + inputItemId.hashCode());
                        
                        registry.addRecipe(new ChippedEmiRecipe(
                            recipeId,
                            info.category,
                            EmiStack.of(inputItem),
                            outputs
                        ));
                        totalRecipes++;
                        workbenchRecipeCount++;
                    }
                }
            }
            
            recipeCountByType.put(workbenchType, workbenchRecipeCount);
            System.out.println("[EMI Chipped] " + workbenchType + " generated " + workbenchRecipeCount + " recipes");
        }
        
        System.out.println("[EMI Chipped] Added " + totalRecipes + " total recipes");
        recipeCountByType.forEach((type, count) -> 
            System.out.println("[EMI Chipped]   - " + type + ": " + count + " recipes"));
    }
}
