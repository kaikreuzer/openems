package io.openems.edge.fenecon.pro.pvmeter;

import java.util.Arrays;
import java.util.stream.Stream;

import io.openems.edge.common.channel.AbstractReadChannel;
import io.openems.edge.common.channel.IntegerReadChannel;
import io.openems.edge.common.channel.StateCollectorChannel;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.meter.api.AsymmetricMeter;
import io.openems.edge.meter.api.SymmetricMeter;

public class Utils {
	public static Stream<? extends AbstractReadChannel<?>> initializeChannels(FeneconProPvMeter c) {
		return Stream.of( //
				Arrays.stream(OpenemsComponent.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case STATE:
						return new StateCollectorChannel(c, channelId);
					}
					return null;
				}), Arrays.stream(SymmetricMeter.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case ACTIVE_POWER:
					case REACTIVE_POWER:
					case MAX_ACTIVE_POWER:
					case ACTIVE_CONSUMPTION_ENERGY:
					case ACTIVE_PRODUCTION_ENERGY:
					case CURRENT:
					case FREQUENCY:
					case MIN_ACTIVE_POWER:
					case VOLTAGE:
						return new IntegerReadChannel(c, channelId);
					}
					return null;
				}), Arrays.stream(AsymmetricMeter.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case ACTIVE_POWER_L1:
					case ACTIVE_POWER_L2:
					case ACTIVE_POWER_L3:
					case CURRENT_L1:
					case CURRENT_L2:
					case CURRENT_L3:
					case REACTIVE_POWER_L1:
					case REACTIVE_POWER_L2:
					case REACTIVE_POWER_L3:
					case VOLTAGE_L1:
					case VOLTAGE_L2:
					case VOLTAGE_L3:
						return new IntegerReadChannel(c, channelId);
					}
					return null;
				}), Arrays.stream(FeneconProPvMeter.ChannelId.values()).map(channelId -> {
					switch (channelId) {
					case ACTIVE_ENERGY_L1:
					case ACTIVE_ENERGY_L2:
					case ACTIVE_ENERGY_L3:
						return new IntegerReadChannel(c, channelId);
					}
					return null;
				})).flatMap(channel -> channel);
	}
}
