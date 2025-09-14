package io.github.leawind.resonator.neoforge;

import io.github.leawind.resonator.ResonatorMod;
import io.github.leawind.resonator.mixin.CameraInvoker;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent.ComputeCameraAngles;

public class ResonatorNeoForgeEvents {
  @SubscribeEvent(priority = EventPriority.LOW)
  public static void onComputeCameraAngles(ComputeCameraAngles event) {
    Minecraft mc = Minecraft.getInstance();
    Entity entity = mc.getCameraEntity();
    if (entity == null) {
      return;
    }
    var camera = event.getCamera();
    var eyePos = entity.position().toVector3f().add(0, entity.getEyeHeight(), 0);

    // Set roll with event
    {
      // This work fine
      event.setRoll(t(2, 3));
    }

    // set position with event.getCamera()
    {
      var beforePos = camera.getPosition();

      var offset = camera.getLeftVector().mul(t(3, 1));
      var pos = eyePos.add(offset);

      // The log looks fine, but it doesn't work in the game
      ((CameraInvoker) camera).invokeSetPosition(new Vec3(pos));

      ResonatorMod.LOGGER.info("Camera pos delta: {}", camera.getPosition().subtract(beforePos));
      // Log example:
      // Camera pos delta: (-0.5529568855677063, -1.6531877517700195, 0.31263917829077137)
    }
  }

  private static float t(double hz, double scale) {
    return (float) (Math.sin(System.currentTimeMillis() * 1e-3 * hz) * scale);
  }
}
