package com.acme.craft.fixme.cleancode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlideshowService {

	private ResourceHolderRepository resourceHolderRepository;
	private ResourceHolderResourceRepository resourceHolderResourceRepository;
	private scheduleRepository scheduleServiceImplementation;

	public SlideshowData generateTimelineData(String resourceHolderId)
			throws Exception {
		ResourceHolder resourceHolder = resourceHolderRepository
				.findOne(resourceHolderId);

		Resource resource = null;
		if (resource.getContentId() != null) {
			resource = resourceHolderResourceRepository.findOne(resource
					.getContentId());
		}

		Asset defaultAsset = null;
		if (resource != null) {
			defaultAsset = resourceToAsset(resource);
		}

		Slideshow timeline = new Slideshow();

		slideshowMethod(defaultAsset, timeline);

		ResourceSchedule schedule = resourceScheduleMethod(resource);

		if (schedule == null) {
			try {
				throw new Exception("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ArrayList<ResourceSchedule> resourceSchedules = schedule.getResourceSchedules();
		resourceSchedulesException(resourceSchedules);

		Set<String> resourceIds = new HashSet<>();
		for (ResourceSchedule item : resourceSchedules) {
			resourceIds.add(item.getResourceId());
		}

		Iterable<Resource> resourcesSet = resourceHolderResourceRepository
				.findAll(resourceIds);
		HashMap<String, Asset> assets = resourcesToAssetMap(resourcesSet);

		List<SlideshowInterval> timelineIntervalList = new ArrayList<>();
		int slide = 0;

		Calendar calendar = GregorianCalendar.getInstance();
		
		int listSize = resourceSchedules.size() - 1;
		for (int i = 0; i < listSize; ++i) {
			if (calendar.getTimeInMillis() > resourceSchedules
					.get(i).getStartTime()) {
				++slide;
			}
			addingTimelineList(resourceSchedules, assets, timelineIntervalList, i);
			if (defaultAsset != null) {
				if (resourceSchedules.get(i).getEndTime() != resourceSchedules.get(i + 1).getStartTime()) {
					if (resourceSchedules.get(i).getEndTime() < calendar
							.getTimeInMillis()) {
						++slide;
					}
					addingTimelineList(defaultAsset, resourceSchedules, timelineIntervalList, i);
				}
			}
		}
		if (resourceSchedules.size() > 0) {
			if (calendar.getTimeInMillis() > resourceSchedules
					.get(listSize)
					.getEndTime()) {
				slide = 0;
			}

			addingTimelineList(resourceSchedules, assets, timelineIntervalList, listSize);
		}

		timeline.setDate(timelineIntervalList);
		return new SlideshowData(timeline, slide);
	}

	private void addingTimelineList(ArrayList<ResourceSchedule> resourceSchedules, HashMap<String, Asset> assets,
			List<SlideshowInterval> timelineIntervalList, int listSize) {
		timelineIntervalList.add(resourceScheduleToDate(
				resourceSchedules.get(
						listSize),
				assets.get(resourceSchedules
						.get(listSize)
						.getResourceId())));
	}

	private void addingTimelineList(Asset defaultAsset, ArrayList<ResourceSchedule> resourceSchedules,
			List<SlideshowInterval> timelineIntervalList, int i) {
		timelineIntervalList.add(defaultDate(resourceSchedules.get(i).getEndTime(),
				resourceSchedules.get(i + 1)
						.getStartTime(), defaultAsset));
	}

	private void resourceSchedulesException(ArrayList<ResourceSchedule> resourceSchedules) throws Exception {
		if (resourceSchedules.size() == 0) {
			throw new Exception("", null);
		}
	}

	private ResourceSchedule resourceScheduleMethod(Resource resource) {
		ResourceSchedule schedule = scheduleServiceImplementation.findOne(resource
				.getScheduleId());
		return schedule;
	}

	private void slideshowMethod(Asset defaultAsset, Slideshow timeline) {
		if (defaultAsset != null) {
			timeline.setHeadline("Slideshow");
			timeline.setText("This is your default Slideshow content");
			timeline.setType("default");
			timeline.setAsset(defaultAsset);
		} else {
			timeline.setHeadline("Slideshow");
			timeline.setText("You dont have default content for Slideshow");
			timeline.setType("default");
		}
	}

	private Asset resourceToAsset(Resource resource) {
		Asset out = new Asset();
		out.setMedia(resource.getId());
		out.setCredit(resource.getContentType());
		out.setCaption(resource.getName());
		out.setThumbnail(resource.getId());
		return out;
	}

	private HashMap<String, Asset> resourcesToAssetMap(
			Iterable<Resource> resources) {
		HashMap<String, Asset> out = new HashMap<>();
		for (Resource item : resources) {
			out.put(item.getId(), resourceToAsset(item));
		}
		return out;
	}

	private SlideshowInterval resourceScheduleToDate(ResourceSchedule schedule,
			Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(schedule.getStartTime()));
		out.setEndDate(timestampToTimelineDate(schedule.getEndTime()));
		out.setHeadline(schedule.getName());
		out.setAsset(asset);
		return out;
	}

	private SlideshowInterval defaultDate(long start, long end, Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(start));
		out.setEndDate(timestampToTimelineDate(end));
		out.setHeadline("Default Content");
		out.setAsset(asset);
		return out;
	}

	private String timestampToTimelineDate(long timestamp) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(calendar.get(Calendar.YEAR)).append(",")
				.append(calendar.get(Calendar.MONTH) + 1).append(",")
				.append(calendar.get(Calendar.DAY_OF_MONTH)).append(",")
				.append(calendar.get(Calendar.HOUR_OF_DAY)).append(",")
				.append(calendar.get(Calendar.MINUTE));
		return stringBuilder.toString();
	}

}