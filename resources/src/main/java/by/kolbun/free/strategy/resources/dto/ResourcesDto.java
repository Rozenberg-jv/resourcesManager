package by.kolbun.free.strategy.resources.dto;

import by.kolbun.free.strategy.resources.ResourceType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ResourcesDto {

	@Getter
	private final Map<ResourceType, Integer> resources;

	private ResourcesDto() {

		resources = new HashMap<>();
	}

	public static ResourcesIncomeDtoBuilder getBuilder() {

		return new ResourcesIncomeDtoBuilder();
	}

	private void setResourcesDelta(ResourceType resource, int delta) {

		resources.put(resource, delta);
	}

	public static class ResourcesIncomeDtoBuilder {

		ResourcesDto build = new ResourcesDto();

		public ResourcesIncomeDtoBuilder addResourcesDelta(ResourceType resource, int delta) {

			build.setResourcesDelta(resource, delta);
			return this;
		}

		public ResourcesDto build() {

			return build;
		}
	}

}
