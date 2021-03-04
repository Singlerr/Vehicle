package io.github.singlerr.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;

@Getter
@Setter
@Builder
public class ElementSet {
    private ArmorStand armorStand;
    private FallingBlock fallingBlock;
}
