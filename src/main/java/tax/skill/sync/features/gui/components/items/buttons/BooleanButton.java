package tax.skill.sync.features.gui.components.items.buttons;

import tax.skill.sync.Sync;
import tax.skill.sync.features.gui.SyncGui;
import tax.skill.sync.features.modules.client.ClickGui;
import tax.skill.sync.features.setting.Setting;
import tax.skill.sync.util.RenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

import java.awt.*;

public class BooleanButton
        extends Button {
    private final Setting setting;
    int color = new Color(ClickGui.getInstance().red.getValue(),ClickGui.getInstance().blue.getValue(),ClickGui.getInstance().green.getValue()).getRGB();

    public BooleanButton(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(this.x, this.y, this.x + (float) this.width + 7.4f, this.y + (float) this.height - 0.5f, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Sync.colorManager.getColorWithAlpha(Sync.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : Sync.colorManager.getColorWithAlpha(Sync.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        Sync.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 1.7f - (float) SyncGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }

    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
        this.setting.setValue((Boolean) this.setting.getValue() == false);
    }

    @Override
    public boolean getState() {
        return (Boolean) this.setting.getValue();
    }
}