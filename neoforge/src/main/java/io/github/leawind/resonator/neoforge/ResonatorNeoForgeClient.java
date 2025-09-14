package io.github.leawind.resonator.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@SuppressWarnings("unused")
@Mod(value = "resonator", dist = Dist.CLIENT)
public final class ResonatorNeoForgeClient {
  public ResonatorNeoForgeClient(IEventBus modBus, ModContainer container) {
    NeoForge.EVENT_BUS.register(ResonatorNeoForgeEvents.class);
  }
}
