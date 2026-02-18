package com.rane.emichipped;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChippedEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> outputs;
    private final EmiRecipeCategory category;
    
    /**
     * Constructor for Chipped recipes with multiple outputs
     */
    public ChippedEmiRecipe(Identifier id, EmiRecipeCategory category, EmiStack input, List<EmiStack> outputs) {
        this.id = id;
        this.category = category;
        this.input = List.of(input);  // EmiStack already implements EmiIngredient
        this.outputs = outputs;
    }
    
    @Override
    public EmiRecipeCategory getCategory() {
        return category;
    }
    
    @Override
    public @Nullable Identifier getId() {
        return id;
    }
    
    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }
    
    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }
    
    @Override
    public int getDisplayWidth() {
        // Width for input + arrow + scrollable outputs
        return 125;
    }
    
    @Override
    public int getDisplayHeight() {
        // Height to show scrollable outputs (3 rows)
        return 54;
    }
    
    @Override
    public void addWidgets(WidgetHolder widgets) {
        // Add input slot on the left
        widgets.addSlot(input.get(0), 0, 18);
        
        // Add arrow
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 26, 19);
        
        // Add output slots in a scrollable grid (3x3 visible, scrollable)
        // This will show the first 9 outputs, with ability to scroll for more
        int outputX = 58;
        int outputY = 0;
        int slotsPerRow = 3;
        int maxVisibleSlots = 9;
        
        for (int i = 0; i < Math.min(outputs.size(), maxVisibleSlots); i++) {
            int row = i / slotsPerRow;
            int col = i % slotsPerRow;
            int x = outputX + (col * 18);
            int y = outputY + (row * 18);
            
            widgets.addSlot(outputs.get(i), x, y).recipeContext(this);
        }
        
        // If there are more than 9 outputs, add a text indicator
        if (outputs.size() > maxVisibleSlots) {
            widgets.addText(
                net.minecraft.text.Text.literal("+" + (outputs.size() - maxVisibleSlots) + " more"),
                outputX,
                outputY + 54,
                0x808080,
                false
            );
        }
    }
    
    @Override
    public boolean supportsRecipeTree() {
        return true;
    }
}
