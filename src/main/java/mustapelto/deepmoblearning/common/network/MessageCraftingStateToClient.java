package mustapelto.deepmoblearning.common.network;

import io.netty.buffer.ByteBuf;
import mustapelto.deepmoblearning.common.tiles.CraftingState;
import mustapelto.deepmoblearning.common.tiles.TileEntityMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class MessageCraftingStateToClient implements IMessage {
    private BlockPos pos;
    private CraftingState craftingState;

    public MessageCraftingStateToClient() {
        craftingState = CraftingState.IDLE;
    }

    public MessageCraftingStateToClient(BlockPos pos, CraftingState craftingState) {
        this.pos = pos;
        this.craftingState = craftingState;
    }

    public MessageCraftingStateToClient(TileEntityMachine te) {
        this(te.getPos(), te.getCraftingState());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(craftingState.getIndex());
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        craftingState = CraftingState.byIndex(buf.readInt());
    }

    public static class Handler implements IMessageHandler<MessageCraftingStateToClient, IMessage> {
        @Override
        @Nullable
        public IMessage onMessage(MessageCraftingStateToClient message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                TileEntityMachine te = (TileEntityMachine) mc.world.getTileEntity(message.pos);
                if (te != null) {
                    te.setCraftingState(message.craftingState);
                }
            });
            return null;
        }
    }
}
