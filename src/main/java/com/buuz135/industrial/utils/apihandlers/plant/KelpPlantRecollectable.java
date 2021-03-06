package com.buuz135.industrial.utils.apihandlers.plant;

import com.buuz135.industrial.api.plant.PlantRecollectable;
import com.buuz135.industrial.utils.BlockUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class KelpPlantRecollectable extends PlantRecollectable {

    public KelpPlantRecollectable() {
        super("kelp");
    }

    @Override
    public boolean canBeHarvested(World world, BlockPos pos, BlockState blockState) {
        return world.getBlockState(pos).getBlock().equals(Blocks.KELP_PLANT) && (world.getBlockState(pos.up()).getBlock().equals(Blocks.KELP) || world.getBlockState(pos.up()).getBlock().equals(Blocks.KELP_PLANT));
    }

    @Override
    public List<ItemStack> doHarvestOperation(World world, BlockPos pos, BlockState blockState) {
        while (world.getBlockState(pos.up()).getBlock().equals(Blocks.KELP) || world.getBlockState(pos.up()).getBlock().equals(Blocks.KELP_PLANT))
            pos = pos.up();
        NonNullList<ItemStack> stacks = NonNullList.create();
        stacks.addAll(BlockUtils.getBlockDrops(world, pos));
        world.setBlockState(pos, Blocks.WATER.getDefaultState());
        return stacks;
    }

    @Override
    public boolean shouldCheckNextPlant(World world, BlockPos pos, BlockState blockState) {
        return world.getBlockState(pos).getBlock().equals(Blocks.KELP);
    }
}
